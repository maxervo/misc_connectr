package com.company.product;
import com.company.behavior.Behavior;
import com.company.behavior.HumanStrategy;
import com.company.behavior.IAMonkeyStrategy;
import com.company.ui.CLI;
import com.company.ui.GUI;
import com.company.ui.UI;

import java.util.*;

public class Game{
    private static final String RUNNING_STATE = "running";      //keeping game loop flow for GUI
    private static final String PAUSE_STATE = "pause";
    public static final String RESUME_CMD = "resumer";
    public static final String QUIT_CMD = "sortir";         //specified in scope statement

    private Grid grid;
    private Menu menu;
    private UI ui;

    private List<Player> playerPool;    //circular list to hold player pool
    private ListIterator<Player> playerPoolIterator;
    private List<Integer> score;

    private int maxNumRounds;
    private int numPlayers;
    private int numRound;
    private String state;

    public Game() {
        //Parameters
        this.maxNumRounds = 3;  //CLI terminal query possible to adapt number of rounds/players, scope statement definition needed
        this.numPlayers = 2;

        //Init components
        this.grid = new Grid(9,8);  //CLI terminal query possible to adapt grid dimensions, scope statement definition needed

        //Prepare token set for number of player generalized
        Token.defineTokenSet(numPlayers);

        //Prompt user
        this.menu = new Menu(numPlayers);

        //Create players
        this.menu.getValidatedInputs();
        spawnPlayers(this.menu);

        //Misc
        zeroScore();
        this.numRound = 1;
        this.state = RUNNING_STATE;

        //Init UI
        this.ui = new CLI(grid, score);             //CLI terminal query possible, scope statement definition needed to choose between UIs
        //this.ui = new GUI(grid, score);
        this.ui.statusGamesPlayers(this.playerPool);
    }

    public boolean manager() {  //return value for continuing/ending game

        //Overall end
        if(this.numRound > this.maxNumRounds) {
            this.ui.statusEnd(score);
            return false;   //end game
        }

        //Current player turn
        Player player = electNextPlayer();

        //Playing
        try {
            return play(player);    //keep playing, or quits based on decision
        } catch(OutOfGridException e) {
            this.playerPoolIterator.previous();  //loop with same player
        } catch(ColumnFullException e) {
            this.playerPoolIterator.previous();  //loop with same player
        } catch(VictoryException e) {
            this.score.set(player.getId()-1, this.score.get(player.getId()-1)+1); //gain points
            this.state = PAUSE_STATE;
            this.ui.statusVictory(player.getId(), this.score);
        } catch(DrawException e) {
            this.state = PAUSE_STATE;
            this.ui.statusDraw(this.score);
        }

        return true;   //keep playing
    }

    private void zeroScore() {
        this.score = new ArrayList<Integer>();

        for (int i = 0; i < this.playerPool.size(); i++) {
            this.score.add(0);
        }
    }

    private boolean play(Player player) throws OutOfGridException, ColumnFullException, VictoryException, DrawException {
        String decision = UI.NO_DECISION_YET;
        if (this.state.equals(this.RUNNING_STATE)) {
            decision = player.behavior.decide(this.ui);
        }

        //Not decided
        if(this.state.equals(this.RUNNING_STATE) && decision.equals(UI.NO_DECISION_YET)) {
            this.playerPoolIterator.previous();  //loop with same player
        }

        //Resume
        else if(this.state.equals(this.PAUSE_STATE)) {  //&& decision.equals(this.RESUME_CMD) : scope statement does not specifies if new round started automatically
            resume();   //prepare next round
            this.ui.statusNewRound(score);
            this.ui.displayGrid(this.grid);
        }

        //Quit
        else if(decision.equals(this.QUIT_CMD)) {
            this.ui.statusEnd(score);
            return false; //quit game
        }

        //Token
        else if(this.state.equals(this.RUNNING_STATE)) {
            try {
                int positionDecided = Integer.parseInt(decision)-1; //adapt to zero based arrays
                this.grid.addToken(player.getToken(), positionDecided);
                this.ui.displayGrid(this.grid);     //Graphics refresh when grid change: keeping the game loop flow and limiting the slow reload of JFrame
                this.ui.statusPlayerTurn(player.getName(), player.getId(), positionDecided,  score);
            }
            catch (NumberFormatException e) {
                this.playerPoolIterator.previous();  //loop with same player
                this.ui.statusFormatInput(player.getId(), score);
            }

            //Verifying
            if ( this.grid.isFinished(player.getToken()) ) {
                throw new VictoryException();
            }
        }

        //Request correct input
        else {
            this.ui.statusFormatInput(player.getId(), score);
        }

        return true;
    }

    private Player electNextPlayer() {

        //Circular turn based for switching between multiple players
        if (this.playerPoolIterator.hasNext()) {
            return this.playerPoolIterator.next();
        }
        else {
            this.playerPoolIterator = this.playerPool.listIterator();
            return playerPoolIterator.next();
        }
    }

    private void spawnPlayers(Menu menu) {
        Behavior playerBehaviorDefined;
        String[] playerBehavior = menu.getPlayerBehavior();
        String[] playerName = menu.getPlayerName();

        //Player pool list holding all players (view it as circular)
        this.playerPool = new LinkedList<Player>();

        for(int i=0; i < menu.getNumPlayers(); i++) {
            //Getting behaviors
            if(playerBehavior[i].equals("human")) {
                playerBehaviorDefined = new HumanStrategy();
            }
            else {
                playerBehaviorDefined = new IAMonkeyStrategy(this.grid);    //default behavior otherwise
            }

            this.playerPool.add( new Player(i+1, playerName[i], new Token(Token.getTokenValueFromSet(i)), playerBehaviorDefined));
        }
        this.playerPoolIterator = this.playerPool.listIterator();

    }

    private void resume() {
        this.grid.reset();

        //reset player cursor to next one, circular
        this.playerPoolIterator = this.playerPool.listIterator();   //reset iterator
        for(int i = 0; i < this.numRound; i++) {    //multiple players loop
            electNextPlayer();
        }
        this.numRound++;

        this.state = RUNNING_STATE; //resume the game
    }

    public void setMaxNumRounds(int maxNumRounds) {
        this.maxNumRounds = maxNumRounds;
    }
}
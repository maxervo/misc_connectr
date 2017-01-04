package com.company.product;
import com.company.behavior.HumanStrategy;
import com.company.ui.CLI;
import com.company.ui.UI;

import java.util.*;

/**
 * Created by rstoke on 12/7/16.
 */

public class Game{
    private static final String RUNNING_STATE = "running";
    private static final String PAUSE_STATE = "pause";
    public static final String RESUME_CMD = "resume";
    public static final String QUIT_CMD = "quit";

    private Grid grid;
    private Menu menu;
    private UI ui;

    private List<Player> playerPool;    //circular list to hold player pool
    private ListIterator<Player> playerPoolIterator;
    private List<Integer> score;

    private int maxNumRounds = 3;   //TODO do an interface to set this? how will it integrate with CLI terminal with rules of teacher?
    private int numRound;
    private String state;

    public Game() {

        //Init components
        //this.grid = new Grid(); // TODO choice
        this.grid = new Grid(9,8);

        this.menu = new Menu();

        //Create players
        this.menu.choosePlayerNames();
        spawnPlayers(this.menu);

        //Misc
        zeroScore();
        this.numRound = 1;
        this.state = RUNNING_STATE;

        //Init UI
        this.ui = new CLI(grid, score);     //TODO how does teacher want to switch GUI/CLI in UI terminal?
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
            this.ui.statusOutOfGrid(this.score);
        } catch(ColumnFullException e) {
            this.playerPoolIterator.previous();  //loop with same player
        } catch(VictoryException e) {
            int playerIndex = player.getId()-1;
            this.score.set(playerIndex, this.score.get(playerIndex)+1); //gain points
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
        String decision = player.behavior.decide(this.ui);

        //Not decided
        if(decision.equals(UI.NO_DECISION_YET)) {
            this.playerPoolIterator.previous();  //loop with same player
        }

        //Resume
        else if(this.state.equals(this.PAUSE_STATE) && decision.equals(this.RESUME_CMD)) {
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
                this.grid.addToken(player.getToken(), Integer.parseInt(decision));
                this.ui.displayGrid(this.grid);     //Graphics when change, due to slow reload of JFrame
                this.ui.statusPlayerTurn(player.getName(), player.getId(), Integer.parseInt(decision),  score);
            }
            catch (NumberFormatException e) {
                this.playerPoolIterator.previous();  //loop with same player
                this.ui.statusFormatInput(player.getId(), score);
            }

            //TODO tmp
            if ( !this.grid.isNotFinished(player.getToken()) ) { //TODO : instead of this whole new verification, better to verify at each step -> quicker, then raise exception
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
        this.playerPool = new LinkedList<Player>();
        this.playerPool.add( new Player(1, menu.getPlayer1(), new Token('x'), new HumanStrategy()) );
        this.playerPool.add( new Player(2, menu.getPlayer2(), new Token('o'), new HumanStrategy()) );
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

        this.state = RUNNING_STATE; //resume
    }
}
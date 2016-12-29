package com.company.product;
import com.company.behavior.HumanStrategy;

import java.util.*;

//TODO do java 8 change compiler

/**
 * Created by rstoke on 12/7/16.
 */

public class Game{
    private Scanner sc = new Scanner(System.in);

    private Grid grid;
    private Menu menu;

    private List<Player> playerPool;    //circular list to hold player pool
    private ListIterator<Player> playerPoolIterator;
    private List<Integer> score;

    private int numRound = 1;

    // Test block
    // to use different behavior during the game
    //private int testNbTurn = 0;

    public Game() {

        //Init components
        this.grid = new Grid();
        this.menu = new Menu();

        //Create players
        this.menu.choosePlayerNames();  //TODO think about method chaining, advantages and drawbacks
        spawnPlayers(this.menu);

        //Zero score
        zeroScore();
    }

    public boolean manager() {  //return value for continuing/ending game

        //Graphics
        display();

        //Current player turn
        Player player = electPlayer();
        System.out.println("Turn: " + player.getName());

        //Playing
        try {
            play(player);
        } catch(ExceptionOutOfGrid e) {
            playerPoolIterator.previous();  //loop with same player
            return true;
        } catch(VictoryException e) {
            int playerIndex = player.getId()-1;

            System.out.println("Player" + player.getId() + " " + player.getName() + " won the game!");
            this.score.set(playerIndex, this.score.get(playerIndex)+1);   //
            return resume();
        } catch(DrawException e) {
            System.out.println("It is a draw");
            return resume();
        }

        return true;   //keep playing while no VictoryException

        //Test block
        /*
        testNbTurn++;
        if(testNbTurn == 3){
            playerPoolIterator.previous().setBehavior(new IAMonkeyStrategy());
        }*/


    }

    private void zeroScore() {
        this.score = new ArrayList<Integer>();

        for (int i = 0; i < this.playerPool.size(); i++) {
            this.score.add(0);
        }
    }

    private void display() {
        String scoreLine = "Score: ";

        //Display grid
        this.grid.display();

        //Display score
        for (int playerScore : this.score) {
            scoreLine +=  playerScore + "/" ;
        }
        System.out.println(scoreLine);
    }

    private void play(Player player) throws ExceptionOutOfGrid, VictoryException, DrawException {
        this.grid.addToken(player.getToken(), player.behavior.decide());

        if ( !this.grid.isNotFinished(player.getToken()) ) { //TODO : instead of this whole new verification, better to verify at each step -> quicker, then raise exception
            throw new VictoryException();
        }
    }

    private Player electPlayer() {

        //Circular turn based for switching players
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
        this.playerPool.add( new Player(1, menu.getPlayer1()[1], new Token('x'), menu.getPlayer1()[0]) );
        this.playerPool.add( new Player(2, menu.getPlayer2()[1], new Token('o'), menu.getPlayer2()[0]) );
        this.playerPoolIterator = this.playerPool.listIterator();
    }

    private boolean resume() {
        String answer = "";

        System.out.println("Do you want to play again? (y/n)");
        do {
            answer = sc.nextLine();
        } while(!validateResumeAnswer(answer));

        if(answer.equals("y")) {
            //reset grid
            this.grid.reset();

            //reset player cursor to next one, circular
            this.playerPoolIterator = this.playerPool.listIterator();
            for(int i = 0; i < this.numRound; i++) {
                this.playerPoolIterator.next();
            }
            this.numRound++;

            return true;
        }

        return false;
    }

    /* Utilities */
    private boolean validateResumeAnswer(String answer) {
        //Rules
        String regex = "^[yn]$";

        //Process
        if(!answer.matches(regex)) {
            System.err.println("Answer y or n");
        }

        //Synthesis
        return (answer.matches(regex));
    }
}
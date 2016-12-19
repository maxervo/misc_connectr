package com.company.product;
import com.company.behavior.HumanStrategy;
import com.company.behavior.IAMonkeyStrategy;
import com.company.behavior.IARandom;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by rstoke on 12/7/16.
 */

public class Game{
    private Scanner sc = new Scanner(System.in);
    private Grid grid;
    private List<Player> playerPool;
    private ListIterator<Player> playerPoolIterator;

    private Player player;

    // Test to use different behavior during the game
    private int nbTurn = 0;

    public Game() {
        this.grid = new Grid();
        this.playerPool = new LinkedList<Player>();

        //TODO load parameters
        this.playerPool.add( new Player(1, "loic", new Token('x'), new HumanStrategy()) );
        this.playerPool.add( new Player(2, "eric", new Token('o'), new HumanStrategy()) );
        this.playerPoolIterator = this.playerPool.listIterator();   //TODO change place
    }

    public void displayGrid() {
        for(int i = 0; i < grid.getGrid().length; i++){
            for(int j = 0; j < grid.getGrid()[i].length; j++){
                if ( (j + 1) == grid.getGrid()[i].length){
                    System.out.println(grid.getGrid()[i][j]);
                }
                else{
                    System.out.print(grid.getGrid()[i][j] +"  ");
                }
            }
        }
    }

    public boolean play() {

        //Player player; // current player put in game attribute. Maybe there is a better design
        if (player == null){ // for the first round
            player =  playerPoolIterator.next();
        }

        System.out.println("turn " + player.getName());
        try {
            grid.addToken(player.getToken(), player.behavior.decide());
        } catch (ExceptionOutOfGrid e) {
            return true; // loop with same player
        }

        // Assignment after the catch to conserve the current player if a exception is thrown by this current player.
        if (playerPoolIterator.hasNext()) {
            System.out.println("hello bitch");
            player =  playerPoolIterator.next();    // DONE verifiy if cast design cool
        }
        else {
            playerPoolIterator = this.playerPool.listIterator();
            player =  playerPoolIterator.next();    // DONE verifiy if cast design cool, TODO do java 8 change compiler
        }

        displayGrid();

        // test behavior
        nbTurn++;
        if(nbTurn == 3){
            playerPoolIterator.previous().setBehavior(new IARandom());
        }
        return grid.isNotFinished(player.getToken());
    }
}
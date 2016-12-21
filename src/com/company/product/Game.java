package com.company.product;
import com.company.behavior.HumanStrategy;
import com.company.behavior.IAMonkeyStrategy;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

//TODO do java 8 change compiler

/**
 * Created by rstoke on 12/7/16.
 */

public class Game{
    private Scanner sc = new Scanner(System.in);
    private Grid grid;
    private List<Player> playerPool;
    private ListIterator<Player> playerPoolIterator;

    // Test block
    // to use different behavior during the game
    private int testNbTurn = 0;

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

        Player player;

        if (playerPoolIterator.hasNext()) {
            player =  playerPoolIterator.next();
        }
        else {
            playerPoolIterator = this.playerPool.listIterator();
            player =  playerPoolIterator.next();
        }

        System.out.println("turn " + player.getName());
        try {
            grid.addToken(player.getToken(), player.behavior.decide());
        } catch (ExceptionOutOfGrid e) {
            playerPoolIterator.previous();
            return true; // loop with same player
        }

        displayGrid();

        //Test block
        /*
        testNbTurn++;
        if(testNbTurn == 3){
            playerPoolIterator.previous().setBehavior(new IAMonkeyStrategy());
        }*/

        return grid.isNotFinished(player.getToken());
    }
}
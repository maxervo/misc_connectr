package com.company.product;
import java.util.Scanner;

import com.company.behavior.HumanStrategy;

/**
 * Created by rstoke on 12/7/16.
 */

public class Game{
    Scanner sc = new Scanner(System.in);
    Grid grid = new Grid();
    Token token = new Token('x');

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

    // test pas d'arg normalement
    public boolean round(Token[] token) {
        Player player = new Player(1, "hello", token[0], new HumanStrategy());
        System.out.println("test " + player.getBehavior().decide());

        // test ici il y aura deux joueur et non 2 token
        int pos[] = new int[2];
        System.out.println("Joueur 1");
        grid.addToken(token[0], this.sc.nextInt() - 1);
        displayGrid();
        if(!grid.isNotFinished(token[0])){
            return false;
        }

        System.out.println("Joueur 2");
        grid.addToken(token[1], this.sc.nextInt() - 1);
        displayGrid();
        return grid.isNotFinished(token[1]);

    }

}
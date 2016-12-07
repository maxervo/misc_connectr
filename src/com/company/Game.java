package com.company;
import java.util.Scanner;

/**
 * Created by rstoke on 12/7/16.
 */

public class Game{
    Scanner sc = new Scanner(System.in);
    Grid grid = new Grid();
    // Ajout futur de player


    public void displayGrid(){
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
    public boolean round(Token[] token){
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
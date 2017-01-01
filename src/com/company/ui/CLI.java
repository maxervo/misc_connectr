package com.company.ui;

import com.company.product.Grid;
import com.company.product.Main;

import java.util.List;
import java.util.Scanner;

/**
 * Created by rstoke on 12/30/16.
 */
public class CLI extends UI {
    private Scanner sc = new Scanner(System.in);

    public CLI(Grid grid, List<Integer> score) {
        displayStatus("Welcome to " + Main.PROGRAM_NAME + " (CLI)", score);
        displayGrid(grid);
    }

    @Override
    public void displayGrid(Grid grid) {
        char[][] dataGrid = grid.getGrid();

        for(int i = 0; i < dataGrid.length; i++){
            for(int j = 0; j < dataGrid[i].length; j++){
                if ( (j + 1) == dataGrid[i].length){
                    System.out.println(dataGrid[i][j]);
                }
                else{
                    System.out.print(dataGrid[i][j] +"  ");
                }
            }
        }
    }

    @Override
    protected void displayStatus(String statusMsg, List<Integer> score) {
        //Score
        String scoreLine = "Score: ";
        for (int playerScore : score) {
            scoreLine +=  playerScore + "/" ;
        }
        System.out.println(scoreLine);

        //Msg
        System.out.println(statusMsg);
    }

    @Override
    public String requestDecision() {
        return this.sc.nextLine();
    }

}
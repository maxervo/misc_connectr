package com.company.ui;

import com.company.product.Grid;
import com.company.product.Main;

import java.util.List;
import java.util.Scanner;

public class CLI extends UI {
    private Scanner sc = new Scanner(System.in);

    public CLI(Grid grid, List<Integer> score) {
        displayStatus("Welcome to " + Main.PROGRAM_NAME + " (CLI)", score);
        displayGrid(grid);
    }

    @Override
    public void displayGrid(Grid grid) {
        char[][] dataGrid = grid.getGrid();

        //Assign numbers to columns
        for(int k = 1; k <= grid.getWidth(); k++){
            if (k<10) {
                System.out.print(Integer.toString(k) + "  ");
            }
            else{
                System.out.print(Integer.toString(k) + " ");
            }
        }
        System.out.println();

        //Building grid
        for(int i = 0; i < grid.getHeight(); i++){
            for(int j = 0; j < grid.getWidth(); j++){
                if ( (j + 1) == grid.getWidth()){
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

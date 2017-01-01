package com.company.ui;

import com.company.product.Game;
import com.company.product.Grid;

import java.util.List;

/**
 * Created by rstoke on 12/30/16.
 */
public abstract class UI {      //No need of strategy pattern because no need for dynamic swapping UIs
    public final static String NO_DECISION_YET = "";
    public final static String MSG_ALTERNATIVE = "(" + Game.RESUME_CMD + "/" + Game.QUIT_CMD + ")";
    public final static String MSG_OUT_OF_GRID = "Token out of grid. Please position the token inside.";
    public final static String MSG_VICTORY = "Congratulations you won! Do you want to continue? " + MSG_ALTERNATIVE;
    public final static String MSG_DRAW = "It was a draw! Do you want to continue? " + MSG_ALTERNATIVE;
    public final static String MSG_NEW_GAME = "A new game has started!";

    public abstract void displayGrid(Grid grid);
    protected abstract void displayStatus(String statusMsg, List<Integer> score);

    //input
    public abstract String requestDecision();

    //output
    public void statusOutOfGrid(List<Integer> score) {
        displayStatus(UI.MSG_OUT_OF_GRID, score);
    }

    public void statusVictory(List<Integer> score) {
        displayStatus(UI.MSG_VICTORY, score);
    }

    public void statusDraw(List<Integer> score) {
        displayStatus(UI.MSG_DRAW, score);
    }

    public void statusNewGame(List<Integer> score) {
        displayStatus(UI.MSG_NEW_GAME, score);
    }

    public void statusPlayerTurn(String playerName, List<Integer> score) {
        displayStatus(playerName + "'s turn ended...", score);
    }

    public void statusEnd(List<Integer> score) {
        if(score.get(0) > score.get(1)) {
            displayStatus("THE END : Player1 has won and Player2 has lost.", score);
        }
        else {
            displayStatus("THE END : Player2 has won and Player1 has lost.", score);
        }
    }

    public void statusFormatInput(List<Integer> score) {
        displayStatus("Non valid input in this current game state. Please enter a correct position, or a correct command.", score);
    }
}

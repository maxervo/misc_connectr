package com.company.ui;

import com.company.product.Game;
import com.company.product.Grid;
import com.company.product.History;

import java.util.List;

public abstract class UI {      //No need: strategy pattern because no need for dynamic swapping UIs

    //Hardcoded UI Major Messages
    public final static String NO_DECISION_YET = "";
    public final static String MSG_ALTERNATIVE = "(" + Game.RESUME_CMD + "/" + Game.QUIT_CMD + ")";
    public final static String MSG_VICTORY = "Congratulations you won! Do you want to continue? " + MSG_ALTERNATIVE;
    public final static String MSG_DRAW = "It was a draw! Do you want to continue? " + MSG_ALTERNATIVE;
    public final static String MSG_NEW_ROUND = "A new round has started!";


    protected History history = new History();

    public abstract void displayGrid(Grid grid);

    protected abstract void displayStatus(String statusMsg, List<Integer> score);

    //input
    public abstract String requestDecision();

    //output
    public void statusVictory(int id, List<Integer> score) {
        displayStatus(UI.MSG_VICTORY, score);
        this.history.save("Joueur " + id + " gagne");

        String scoreLine = "";
        boolean flag = false;
        for (int playerScore : score) {
            if (!flag) {
                scoreLine += playerScore;
                flag = true;
            } else {
                scoreLine += " - " + playerScore;
            }
        }
        this.history.save("Score " + scoreLine);
    }

    public void statusDraw(List<Integer> score) {
        displayStatus(UI.MSG_DRAW, score);
        this.history.save(History.MSG_DRAW);
    }

    public void statusNewRound(List<Integer> score) {
        displayStatus(UI.MSG_NEW_ROUND, score);
        this.history.save(History.MSG_NEW_ROUND);
    }

    public void statusPlayerTurn(String playerName, int id, int decide, List<Integer> score) {
        displayStatus(playerName + " a fini son tour", score);
        this.history.save("Joueur " + id + " joue " + (decide + 1));
    }

    public void statusEnd(List<Integer> score) {
        if (score.get(0) > score.get(1)) {
            displayStatus("FIN : Joueur 1 a gagné et joueur 2 a perdu", score);
        }
        else if (score.get(0) < score.get(1)){
            displayStatus("FIN : Joueur 2 a gagné et joueur 1 a perdu", score);
        }
        else {
            displayStatus("FIN : Egalité.", score);
        }
        this.history.save(this.history.MSG_END_GAME);
        this.history.close();
    }

    public void statusFormatInput(int id, List<Integer> score) {
        //displayStatus("Non valid input in this current game state. Please enter a correct position, or a correct command.", score);
        displayStatus("Erreur saisie Joueur " + id, score);

    }

    public void statusGamesPlayers(String[] playerName, String[] playerBehavior) {
        for (int i = 0; i < playerName.length; i++) {
            this.history.save("Joueur " + (i + 1) + " est " + playerBehavior[i] + " " + playerName[i]);
        }

    }
}
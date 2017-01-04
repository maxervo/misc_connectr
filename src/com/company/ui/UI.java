package com.company.ui;

import com.company.product.Game;
import com.company.product.Grid;
import com.company.product.Player;
import com.company.product.History;

import java.util.List;

public abstract class UI {      //No need: strategy pattern because no need for dynamic swapping UIs

    //Hardcoded UI Major Messages
    public final static String NO_DECISION_YET = "";
    public final static String MSG_ALTERNATIVE = "(" + Game.RESUME_CMD + "/" + Game.QUIT_CMD + ")";
    public final static String MSG_OUT_OF_GRID = "Token out of grid. Please position the token inside.";
    public final static String MSG_VICTORY = "Congratulations you won! Do you want to continue? " + MSG_ALTERNATIVE;
    public final static String MSG_DRAW = "It was a draw! Do you want to continue? " + MSG_ALTERNATIVE;
    public final static String MSG_NEW_ROUND = "A new round has started!";


    protected History history = new History();

    public abstract void displayGrid(Grid grid);

    // TODO remove duplication code in display CLI and GUI
    // TODO not abstract maybe? and use super.displayStatus() -> It's ok because CLI/GUI different entities and their code are in fact independent (score were written the same way because quick++ but normally they should have different representations depending on CLI or GUI) so OK, but yeah good remark Walt
    protected abstract void displayStatus(String statusMsg, List<Integer> score);

    //input
    public abstract String requestDecision();

    //output
    public void statusOutOfGrid(List<Integer> score) {
        displayStatus(UI.MSG_OUT_OF_GRID, score);
    }

    public void statusVictory(int id, List<Integer> score) {
        displayStatus(UI.MSG_VICTORY, score);
        this.history.save("Joueur 1 " + id + " gagne");

        // TODO save the score without code duplication
        // TODO not beautiful but score is asked to be written like that
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
        this.history.save(scoreLine);
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
        displayStatus(playerName + "'s turn ended...", score);
        this.history.save("Joueur " + id + " joue " + (decide + 1));
    }

    public void statusEnd(List<Integer> score) {
        if (score.get(0) > score.get(1)) {
            displayStatus("THE END : Player1 has won and Player2 has lost.", score);
        }
        else if (score.get(0) < score.get(1)){
            displayStatus("THE END : Player2 has won and Player1 has lost.", score);
        }
        else {
            displayStatus("THE END : it's a draw.", score);
        }
        this.history.save(this.history.MSG_END_GAME);
        this.history.close();
    }

    public void statusFormatInput(int id, List<Integer> score) {
        //displayStatus("Non valid input in this current game state. Please enter a correct position, or a correct command.", score);
        displayStatus("Erreur saisie Joueur " + id, score);

    }

    public void statusGamesPlayers(List<Player> playerPool) {   //TODO write also the identity (human, ia:Monkey...)
        for (int i = 0; i < playerPool.size(); i++) {
            this.history.save("Joueur " + (i + 1) + " est " + playerPool.get(i).getName());
        }

    }
}
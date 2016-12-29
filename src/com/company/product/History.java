package com.company.product;

import java.io.*;
import java.util.*;
/**
 * Created by loic on 29/12/16.
 */
public class History  {


    private File file;
    private FileWriter writer;

    public History() {
        try {
            file = new File("log.txt");
            // creates the file
            file.createNewFile();
            // creates a FileWriter Object
            writer = new FileWriter(file, false); //overwrite
        } catch (IOException e){
            System.err.println("error write");
        }
    }


    public void setPlayers(List<Player> playerPool) {
        try {
            for(int i = 0; i< playerPool.size(); i++) {
                writer.write("Joueur " + (i+1) + " est " + playerPool.get(i).getName() + "\n");
                writer.flush(); // important
            }
        } catch (IOException e){
            System.err.println("error write");
        }
    }
    public void setRound() {
        try{
            writer.write("Manche commence\n");
            writer.flush(); // important
        } catch (IOException e){
            System.err.println("error write");
        }
    }

    public void setPlay(Player player, int decide) {
        try{
            writer.write("Joueur " + player.getId() + " joue " + (decide +1) + "\n");
            writer.flush(); // important
        } catch (IOException e){
            System.err.println("error write");
        }
    }

    public void setResultRound(Player player, boolean win) {
        try{
            if (win) {
                writer.write("Joueur " + player.getId() + " gagne \n");
                writer.flush(); // important
            }
            else {
                writer.write("Egalité \n");
                writer.flush(); // important
            }
        } catch (IOException e){
            System.err.println("error write");
        }
    }

    public void setScore(List<Integer> score){
        try{
            String scoreLine = "";
            boolean flag = false;
            //Display score
            for (int playerScore : score) {
                if (!flag) {
                    scoreLine += playerScore;
                    flag = true;
                } else {
                    scoreLine += " - " + playerScore;
                }
            }
            writer.write("Score " + scoreLine + "\n");
            writer.flush(); // important
        } catch (IOException e){
            System.err.println("error write");
        }
    }
    public void setEndGame(){
        try{
            writer.write("Partie finie \n");
            writer.flush(); // important
            writer.close();
        } catch (IOException e){
            System.err.println("error write");
        }
    }

    public static void setEndGameStatic(){
        try{
            File file;
            FileWriter writer;
            file = new File("log.txt");
            // creates the file
            file.createNewFile();
            // creates a FileWriter Object
            writer = new FileWriter(file, true); // append true

            writer.write("Partie finie \n");
            writer.flush(); // important
            writer.close();
        } catch (IOException e){
            System.err.println("error write");
        }
    }
}

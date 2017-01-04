package com.company.product;

import java.util.Scanner;

public class Menu {
    public static final int NUM_ARG = 2;  //when defining players: "ia:monkey Vador"
    private Scanner sc = new Scanner(System.in);

    private int numPlayers;
    private String[] playerInput;
    private String[] playerName;
    private String[] playerBehavior;

    public Menu(int numPlayers) {
        this.numPlayers = numPlayers;    //CLI terminal query possible, respecting scope statement interface
        this.playerInput = new String[NUM_ARG];
        this.playerName = new String[numPlayers];
        this.playerBehavior = new String[numPlayers];
    }

    public void getValidatedInputs() {

        for(int i=0; i<this.numPlayers; i++) {
            System.out.println("Joueur " + (i+1) + "?");

            //Player i input
            promptUser(i);
        }
    }

    private void promptUser(int i) {

        do {
            this.playerInput = sc.nextLine().split("\\s+");

            //format
            if(playerInput.length == 1) {
                System.err.println("Define your behavior as follows: human <name> or ia:monkey <name>.");
                continue;
            }

            //defining player
            this.playerBehavior[i] = this.playerInput[0];
            this.playerName[i] = this.playerInput[1];

        } while(playerInput.length == 1 || !(validatePlayerName(this.playerName[i]) && validatePlayerBehavior(this.playerBehavior[i])));
    }

    /* Utilities */
    private boolean validatePlayerName(String name) {
        //Rules
        String regex = "^[a-zA-Z ]+$";   //only letters or space
        int maxLength = 20;

        //Process                               //possible to use a standard library validator
        if(!name.matches(regex)) {
            System.err.println("Only letters or space allowed, please try again.");
        }
        if(name.length() > maxLength) {
            System.err.println("Short and concise names are awesome. Find one under 20 characters!");
        }

        //Synthesis
        return (name.matches(regex)) && (name.length() < maxLength);
    }

    private boolean validatePlayerBehavior(String behavior) {
        if(!(behavior.equals("human") || behavior.equals("ia:monkey"))) {
            System.err.println("Define your behavior as follows: human <name> or ia:monkey <name>.");
        }

        return (behavior.equals("human") || behavior.equals("ia:monkey"));
    }

    /* Getters */

    public int getNumPlayers() {
        return numPlayers;
    }

    public String[] getPlayerName() {
        return playerName;
    }

    public String[] getPlayerBehavior() {
        return playerBehavior;
    }
}

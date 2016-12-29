package com.company.product;

import java.util.Scanner;

/**
 *
 */
public class Menu {
    private Scanner sc = new Scanner(System.in);

    private String[] player1;
    private String[] player2;

    public Menu() {
        System.out.println("Welcome to " + Main.PROGRAM_NAME);
        this.player1 = new String[2];
        this.player2 = new String[2];
    }

    public void choosePlayerNames() {     //TODO Rules enforced
        //Player1 input
        System.out.println("Joueur 1?");
        do {
            this.player1 = sc.nextLine().split("\\s+");
        } while(!(validatePlayerIdentity(player1[0]) && validatePlayerName(player1[1])));

        //Player2 input
        System.out.println("Joueur 2?");
        do {
            this.player2 = sc.nextLine().split("\\s+");
        } while(!(validatePlayerIdentity(player2[0]) && validatePlayerName(player2[1])));
    }

    /* Utilities */
    private boolean validatePlayerName(String name) {   //TODO maybe find design pattern, factory to design model unique place..etc
        //Rules
        String regex = "^[a-zA-Z ]+$";   //only letters or space
        int maxLength = 20;

        //Process                               //TODO find one day a validator standard library
        if(!name.matches(regex)) {
            System.err.println("Only letters or space allowed, please try again.");
        }
        if(name.length() > maxLength) {
            System.err.println("Short and concise names are awesome. Find one under 20 characters!");
        }

        //Synthesis
        return (name.matches(regex)) && (name.length() < maxLength);
    }

    private boolean validatePlayerIdentity(String identity) {

        if(!(identity.equals("human") || identity.equals("ia:monkey"))) {
            System.err.println("Define your identity as follows: human <name> or ia:monkey <name>.");
        }

        return (identity.equals("human") || identity.equals("ia:monkey"));
    }
    /* Getters */
    public String[] getPlayer1() { return player1; }

    public String[] getPlayer2() {
        return player2;
    }
}

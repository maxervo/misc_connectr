package com.company.product;

import java.util.Scanner;

/**
 *
 */
public class Menu {
    private Scanner sc = new Scanner(System.in);

    private String player1;
    private String player2;

    public Menu() {
        System.out.println("Welcome to " + Main.PROGRAM_NAME);
        this.player1 = "";
        this.player2 = "";
    }

    public void choosePlayerNames() {     //TODO Rules enforced
        //Player1 input
        System.out.println("Joueur 1?");
        do {
            this.player1 = sc.nextLine();
        } while(!validatePlayerName(player1));

        //Player2 input
        System.out.println("Joueur 2?");
        do {
            this.player2 = sc.nextLine();
        } while(!validatePlayerName(player2));
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

    /* Getters */
    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}

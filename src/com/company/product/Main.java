package com.company.product;

public class Main{
  public static final String PROGRAM_NAME = "Connectr";

  public static void main(String[] args){
    Game game = new Game();

    //Game Loop
    while (game.manager());

    System.out.println("Game ended");
  }
}

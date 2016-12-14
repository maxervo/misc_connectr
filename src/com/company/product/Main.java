package com.company.product;

public class Main{
  public static void main(String[] args){
    Game game = new Game();
    game.displayGrid(); //beginning empty grid

    while (game.play());

    System.out.println("Game ended");
  }
}

package com.company.product;

public class Main{
  public static void main(String[] args){
    Game game = new Game();
    game.displayGrid();
    // test ici il y aura deux joueur et non 2 token
    Token token[] = new Token[2];
    token[0] = new Token('x');
    token[1] = new Token('o');

    while (game.round(token));

    System.out.println("Game ended");
  }
}

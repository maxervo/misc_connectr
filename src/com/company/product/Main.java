package com.company.product;

public class Main{
  public static final String PROGRAM_NAME = "Connectr";
  public static final int REFRESH_RATE = 60;  //in Hz
  public static final int REFRESH_SPEED = 1000/REFRESH_RATE;  //in ms

  public static void main(String[] args){
    Game game = new Game();
    int timer;
    int executionSpeed;

    //Game Loop - with Refresh Rate
    while (game.manager()) {
      timer = (int) System.currentTimeMillis();
      executionSpeed = (int) System.currentTimeMillis() - timer;

      if (executionSpeed < REFRESH_SPEED) {
        try {
          Thread.sleep(REFRESH_SPEED - executionSpeed);   //maintaining refresh rate
        } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
    }

    System.out.println("Game ended");
  }
}

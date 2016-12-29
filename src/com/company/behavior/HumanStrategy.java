package com.company.behavior;
import java.util.Scanner;

/**
 * Created by rstoke on 12/7/16.
 */
public class HumanStrategy implements Behavior {
    private Scanner sc = new Scanner(System.in);

    public HumanStrategy() {

    }

    public int decide() {

        if(this.sc.hasNextInt()) {
            return this.sc.nextInt()-1;
            //return  Integer.parseInt(this.sc.match().group(1)) - 1;
        }
        else if ( this.sc.hasNextLine()) {
            String next = this.sc.nextLine(); // very important to save in a buffer
            if (next.equals("sortir")) {
                System.out.println("Game ended by the player");
                System.exit(1); // TODO bad
            } else {
                System.err.println("Error input " + next + ". Please enter a correct one");
            }
        }
        return -2;
    }

}

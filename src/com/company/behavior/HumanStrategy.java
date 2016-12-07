package com.company.behavior;
import java.util.Scanner;

/**
 * Created by rstoke on 12/7/16.
 */
class HumanStrategy implements behavior {
    private Scanner sc = new Scanner(System.in);

    public int decide() {
        return this.sc.nextInt()-1;
    }

}

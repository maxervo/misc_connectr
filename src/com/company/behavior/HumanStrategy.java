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
        return this.sc.nextInt()-1;
    }

}

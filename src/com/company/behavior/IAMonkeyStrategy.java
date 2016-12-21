package com.company.behavior;

/**
 * Created by rstoke on 12/7/16.
 */
public class IAMonkeyStrategy implements Behavior {

    public IAMonkeyStrategy() {

    }

    @Override
    public int decide() {
        return ((int) (Math.random() * (7 - 1 + 1)) + 1);
    }

}

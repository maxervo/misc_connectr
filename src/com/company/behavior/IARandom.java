package com.company.behavior;

/**
 * Created by loic on 19/12/16.
 */
public class IARandom implements Behavior{

    public IARandom() {

    }

    @Override
    public int decide() {
        return ((int) (Math.random() * (7 - 1 + 1)) + 1);
    }
}

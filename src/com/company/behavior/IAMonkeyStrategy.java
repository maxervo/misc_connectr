package com.company.behavior;

import com.company.product.Grid;
import com.company.ui.UI;

public class IAMonkeyStrategy implements Behavior {

    private int systemWidth;

    public IAMonkeyStrategy(Grid grid) {   //IAs require system specifications
        systemWidth = grid.getWidth();
    }

    @Override
    public String decide(UI ui) {
        int lowerBound = 1;    //included
        int higherBound = this.systemWidth+1;   //excluded

        return Double.toString( Math.random() * (higherBound-lowerBound) + lowerBound); //random choice
        //return (Double.toString(Math.random() * (7 - 1 + 1)) + 1);      //TODO adapt with width of grid, ok done, test it if ok?
    }

}

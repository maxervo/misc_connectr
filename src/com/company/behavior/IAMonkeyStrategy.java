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

        return Integer.toString( (int) (Math.random() * (higherBound-lowerBound) + lowerBound) ); //random choice
    }

}

package com.company.behavior;

import com.company.ui.UI;

/**
 * Created by rstoke on 12/7/16.
 */
public class IAMonkeyStrategy implements Behavior {

    public IAMonkeyStrategy() {

    }

    @Override
    public String decide(UI ui) {
        return (Double.toString(Math.random() * (7 - 1 + 1)) + 1);
    }

}

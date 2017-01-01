package com.company.behavior;

import com.company.ui.UI;

/**
 * Created by rstoke on 12/7/16.
 */
public class HumanStrategy implements Behavior {


    public HumanStrategy() {

    }

    @Override
    public String decide(UI ui) {
        return ui.requestDecision();
    }

}

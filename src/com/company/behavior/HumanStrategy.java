package com.company.behavior;

import com.company.ui.UI;

public class HumanStrategy implements Behavior {

    @Override
    public String decide(UI ui) {
        return ui.requestDecision();
    }

}

package com.company.behavior;

import com.company.ui.UI;

/**
 * Created by rstoke on 12/7/16.
 */
public interface Behavior {     //Strategy Pattern for changing AIs on the fly ("hot-swapping" style)
    String decide(UI ui);
}



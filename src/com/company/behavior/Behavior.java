package com.company.behavior;

import com.company.ui.UI;

//Strategy Pattern for defining a catalogue of AIs and being able to change AIs on the fly ("hot-swapping" style)
public interface Behavior {
    String decide(UI ui);
}



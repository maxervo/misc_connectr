package com.company.product;

import com.company.behavior.Behavior;
import com.company.behavior.IAMonkeyStrategy;

public class Player {
    private int id;
    private String name;
    private Token token;
    public Behavior behavior;   //strategy design pattern, public for syntactic sugar access : object.behavior.method

    public Player(int id, String name, Token token, Behavior behavior) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.behavior = behavior;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

}



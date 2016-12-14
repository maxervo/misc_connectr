package com.company.product;

import com.company.behavior.Behavior;
import com.company.behavior.IAMonkeyStrategy;

/**
 * Created by rstoke on 12/7/16.
 */
public class Player {
    private int id;
    private String name;
    private Token token;
    private Behavior behavior;

    public Player() {
        this.id = 0;
        this.name = "";
        this.token = new Token();
        this.behavior = new IAMonkeyStrategy();
    }

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

    public Behavior getBehavior() {
        return behavior;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}



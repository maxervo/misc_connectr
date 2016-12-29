package com.company.product;

import com.company.behavior.*;

/**
 * Created by rstoke on 12/7/16.
 */
public class Player {
    private int id;
    private String name;
    private Token token;
    public Behavior behavior;   //public for syntactic sugar object.behavior.method
                                //TODO change it in private. The control is now done by a behavior string

    public Player() {
        this.id = 0;
        this.name = "";
        this.token = new Token();
        this.behavior = new HumanStrategy();
    }

    public Player(int id, String name, Token token, String behavior) {
        this.id = id;
        this.name = name;
        this.token = token;
        setBehavior(behavior);
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

    public void setBehavior(String behavior) {
        if(behavior.equals("human")) {
            this.behavior = new HumanStrategy();
        }
        else if (behavior.equals("ia:monkey")) {
            this.behavior = new IAMonkeyStrategy();
        }
    }
}



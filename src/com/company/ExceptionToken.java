package com.company;

/**
 * Created by rstoke on 12/7/16.
 */

class ExceptionToken extends Exception{

    public ExceptionToken(){

        System.out.println("You try to use a bad token. Please use \"x\" or \"o\" ");

    }

}
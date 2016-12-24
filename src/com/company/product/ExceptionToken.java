package com.company.product;

/**
 * Created by rstoke on 12/7/16.
 */

//TODO change to Exception keyword as suffix, convention
//TODO this is validating a user input, so do not use exceptions
class ExceptionToken extends Exception{

    public ExceptionToken(){

        System.out.println("You try to use a bad token. Please use \"x\" or \"o\" ");

    }

}
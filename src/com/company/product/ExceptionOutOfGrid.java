package com.company.product;

/**
 * Created by rstoke on 12/14/16.
 */
class ExceptionOutOfGrid extends Exception{

    public ExceptionOutOfGrid(){
        System.out.println("Out of grid. Please set your token between 1 and 7");
    }

}

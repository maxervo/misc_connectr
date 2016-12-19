package com.company.product;

/**
 * Created by rstoke on 12/14/16.
 */
class ExceptionOutOfGrid extends Exception {

    public ExceptionOutOfGrid() {
        System.out.println("Out of grid. Please set your token between 1 and 7");
    }

    public ExceptionOutOfGrid(int col) {
        this(); // call the basic constructor
        System.out.println("and not in col " + (col + 1));

    }
}
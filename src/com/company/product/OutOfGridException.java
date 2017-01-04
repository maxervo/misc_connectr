package com.company.product;

/**
 * Created by rstoke on 12/14/16.
 */

//TODO change to Exception keyword as suffix, convention
//OK considered as exceptional event, checked exception to handle
class OutOfGridException extends Exception {

    public OutOfGridException(int col) {
        System.out.println("Erreur colonne non valide " + (col + 1));
    }

}
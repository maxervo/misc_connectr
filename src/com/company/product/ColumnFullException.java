package com.company.product;

/**
 * Created by loic on 04/01/17.
 */
class ColumnFullException extends Exception{

    public ColumnFullException(int col) {
        System.out.println("Erreur colonne pleine " + (col + 1));
    }
}

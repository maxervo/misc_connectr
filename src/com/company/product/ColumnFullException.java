package com.company.product;

class ColumnFullException extends Exception{

    public ColumnFullException(int col) {
        System.out.println("Erreur colonne pleine " + (col + 1));
    }
}

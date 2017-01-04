package com.company.product;

class OutOfGridException extends Exception {

    public OutOfGridException(int col) {
        System.out.println("Erreur colonne non valide " + (col + 1));
    }

}
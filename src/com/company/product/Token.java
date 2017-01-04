package com.company.product;

public class Token {
    private static char[] tokenSet;

    char token;

    public Token(){ setToken(Character.MIN_VALUE); }

    public Token(char token){
        setToken(token);
    }

    private void setToken(char token) {     //used internally only
        if (token == 'x' || token == 'o' || token == Character.MIN_VALUE){  //empty character
            this.token = token;
        }
    }

    public char getToken(){
        return this.token;
    }

    public static void defineTokenSet(int numPlayers) {
        Token.tokenSet = new char[numPlayers];

        //Case 2 because of scope statement
        if(numPlayers == 2) {
            Token.tokenSet[0] = 'x';
            Token.tokenSet[1] = 'o';
        }
        //Generalisation : Building symbols
        else {
            for(int i=0; i < numPlayers; i++) {
                Token.tokenSet[i] = (char) i;  //symbols integers for generalization, but bijection possible so all symbols possible here
            }
        }
    }

    public static char getTokenValueFromSet(int i) {
        if (i >= tokenSet.length) {
            throw new IllegalArgumentException();
        }

        return Token.tokenSet[i];
    }
}

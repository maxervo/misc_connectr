package com.company.product;

/**
 * Created by rstoke on 12/7/16.
 */
public class Token {
    char token;

    public static boolean isTokenValue(char val) {
        return (val == 'x')||(val == 'o');
    }

    public Token(){ setToken(Character.MIN_VALUE); }

    public Token(char token){
        setToken(token);
    }

    public char getToken(){
        return this.token;
    }

    private void setToken(char token) {     //used internally only
        if (token == 'x' || token == 'o' || token == Character.MIN_VALUE){  //empty character
            this.token = token;
        }
        /*else{     //TODO whassup
            throw new ExceptionToken();
        }*/
    }
}

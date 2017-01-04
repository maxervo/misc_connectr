package com.company.product;

/**
 * Created by rstoke on 12/7/16.
 */

public class Grid {

    private char grid[][];
    private int height = 5; //TODO no more magic numbers in the code, flexible
    private int width = 5;

    public Grid() {
        this.grid = new char[this.height][this.width];
        reset();
    }

    // private method
    public void reset() {
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                //if (i==0){
                  //  this.grid[i][j] = (char)(j + '0');
                //}else{
                    this.grid[i][j] = '.';
                //}
            }
        }
    }

    private boolean isInGrid(int i, int j){// the first line cannot contain a token
        if ( i<this.height && j<this.width && i>=0 && j>=0){
            return true;
        }
        return false;
    }
    private boolean isSameToken(int i, int j, Token token){
        if (isInGrid(i,j)){
            return (token.getToken() == this.grid[i][j]);
        }
        return false;
    }

    private boolean upperLineFull(){
        int  occu = 0;
        for (int j = 0; j < this.width; j++) {
            if (grid[0][j] != '.'){
                occu++;
            }
        }

        if (occu == this.width) {
            return true;
        }
        return false;
    }


    // public method
    public char[][] getGrid(){
        return this.grid;
    }

    public void addToken(Token token, int col) throws OutOfGridException, ColumnFullException, DrawException {
        //boolean upperLineFull = false;  //TODO for draw
        //Arrays.stream(this.grid[0]).forEach(x -> x = Token.isTokenValue(x)? 1:0).sum() == this.width;

        if(col <=0 || col>=this.width){
            throw new OutOfGridException(col);
        }
        else if (upperLineFull()) {
            throw new DrawException();
        }
        else{
            for(int i = (this.height - 1); i >= 0; i--){
                if (this.grid[i][col] == '.'){
                    this.grid[i][col] = token.getToken();
                    break;
                }
                else{
                    if (i == 0){ // if there is no more space for a new token
                        throw new ColumnFullException(col);
                    }
                }
            }
        }
    }

    //TODO maybe think better algorithm, instead of verifying whole, do it at end of every turn and raise exception,
    //maybe verify at the end of "add token" because victory linked to end of action of a player
    public boolean isNotFinished(Token token){
        int flagVictory[] = new int[8];

        for (int i =1; i< this.height; i++){
            for(int j = 0; j < this.width; j++){
                if (this.grid[i][j] != '.'){ // we check the grid just for a good token
                    //Arrays.fill(flagVictory, null);
                    flagVictory = new int[flagVictory.length];

                    for (int k = 0; k <4; k++){ // we check the four neighboors of grid[i][j]
                        // diagonal
                        // bottom right
                        if (isSameToken(i + k, j + k, token)){
                            flagVictory[0]++;
                        }
                        // bottom left
                        if (isSameToken(i + k, j - k, token)){
                            flagVictory[1]++;
                        }
                        // top right
                        if (isSameToken(i - k, j + k, token)){
                            flagVictory[2]++;
                        }
                        // top left
                        if (isSameToken(i - k, j - k, token)){
                            flagVictory[3]++;
                        }

                        // vertical
                        if (isSameToken(i + k, j, token)){
                            flagVictory[4]++;
                        }
                        if (isSameToken(i - k, j, token)){
                            flagVictory[5]++;
                        }
                        // horizontal
                        if (isSameToken(i, j + k, token)){
                            flagVictory[6]++;
                        }
                        if (isSameToken(i, j - k, token)){
                            flagVictory[7]++;
                        }
                    }
                    // check if somebody won
                    for (int l=0; l < flagVictory.length; l++){
                        if (flagVictory[l] == 4){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
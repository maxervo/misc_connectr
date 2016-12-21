package com.company.product;

/**
 * Created by rstoke on 12/7/16.
 */

public class Grid {

    private char grid[][] = new char[7][7];

    public Grid(){
        initGrid();
    }

    // private method
    private void initGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if (i==0){
                    this.grid[i][j] = (char)(j + 1 + '0');
                }else{
                    this.grid[i][j] = '.';
                }
            }
        }
    }

    private boolean isInGrid(int i, int j){// the first lign cannot contain a token
        if (i<(grid.length) && j<grid[0].length && i>0 && j>-1){
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

    // public method
    public char[][] getGrid(){
        return this.grid;
    }

    public void addToken(Token token, int col) throws ExceptionOutOfGrid {
        if(col <0 || col >6){
            throw new ExceptionOutOfGrid();
        }
        else{
            for(int i = (grid.length - 1); i >= 0; i--){
                if (grid[i][col] == '.'){
                    grid[i][col] = token.getToken();
                    break;
                }
                else{
                    if (i == 0){ // if there is no more space for a new token
                        throw new ExceptionOutOfGrid(col);
                    }
                }
            }
        }
    }


    //TODO maybe think better algorithm, instead of verifying whole at end of every turn,
    //maybe verify at the end of "add token" because victory linked to end of action of a player
    public boolean isNotFinished(Token token){
        int flagVictory[] = new int[8];

        for (int i =1; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if (grid[i][j] != '.'){ // we check the grid just for a good token
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

}
package com.company.product;

public class Grid {

    private char grid[][];
    private int height;
    private int width;

    /* TODO can be removed then
    public Grid() {
        this.width = 7;
        this.height = 6;
        this.grid = new char[this.height][this.width];
        reset();
    }*/

    public Grid(int height, int width){
        setGridResolution(height, width);
        this.grid = new char[this.height][this.width];
        reset();
    }

    //public method accessed by resume() when clearing the grid
    public void reset() {
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                this.grid[i][j] = '.';
            }
        }
    }

    private void setGridResolution(int height, int width){
        if(((height * width) % 2 == 0) && ((height * width) >= 8) && width >=4){
            this.height = height;
            this.width = width;
        }
        else{
            this.width = 7;
            this.height = 6;
            System.err.println("Erreur de taille de grille. Vous continuez avec une grille traditionelle");
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

    private boolean upperLineFull() {
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

        if(col <0 || col>=this.width){
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

    public boolean isFinished(Token token){
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
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
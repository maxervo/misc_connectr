public class Token {
    char token;

    public Token(char token){
        setToken(token);
    }

    public char getToken(){
        return this.token;
    }

    public void setToken(char token){
        if (token == 'x' || token == 'o'){
            this.token = token;
        }
        /*else{
            throw new ExceptionToken();
        }*/
    }
}

package main.Exceptions;

/**
 * Exception for incorrectly entered arguments
 */

public class WrongArgumentsException extends Exception{

    public WrongArgumentsException(){
    }

    public WrongArgumentsException(String message){
        super (message);
    }
    @Override
    public String toString (){
        return "Wrong arguments";
    }
}

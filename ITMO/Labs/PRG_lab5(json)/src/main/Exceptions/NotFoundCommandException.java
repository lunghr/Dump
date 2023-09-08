package main.Exceptions;

/**
 * Exception throws when user input command which doesn't exist
 */
public class NotFoundCommandException extends RuntimeException {

    public NotFoundCommandException(){}
    @Override
    public String toString (){
        return "The command doesn't exist";
    }

}

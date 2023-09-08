package main.Exceptions;

/**
 * Exception throws when element with the specified id doesn't exist
 */
public class NotFoundIdException extends Exception{
    public NotFoundIdException(){};

    @Override
    public String toString(){
        return "Element with this id was not found";
    }
}

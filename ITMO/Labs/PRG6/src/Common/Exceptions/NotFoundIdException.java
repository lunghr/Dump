package Common.Exceptions;

public class NotFoundIdException extends Exception {
    public NotFoundIdException(){};

    @Override
    public String toString(){
        return "Element with this id was not found";
    }
}

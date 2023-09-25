package com.lunghr.lab6.common.exceptions;

public class NotFoundIdException extends Exception {
    public NotFoundIdException(){};

    @Override
    public String toString(){
        return "Element with this id was not found";
    }
}

package com.lunghr.lab6.common.exceptions;

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

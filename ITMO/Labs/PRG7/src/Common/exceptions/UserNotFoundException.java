package Common.exceptions;

import Server.managers.DBManagers.UsersManager;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){}

    @Override
    public String toString(){
        return "User with this name does not exist";
    }
}

package Common.utils;

import Common.models.SpaceMarine;

import java.io.Serializable;

public class Message implements Serializable {
    private String command;
    private String[] args;
    private SpaceMarine spaceMarine;
    private int user_id;
    public Message(){
        this.spaceMarine = null;
        this.args = null;
        this.user_id = 0;
    }

    public String getCommand(){
        return command;
    }
    public SpaceMarine getSpaceMarine(){
        return spaceMarine;
    }
    public int getUser_id(){return user_id;}

    public void setSpaceMarine(SpaceMarine spaceMarine){
        this.spaceMarine = spaceMarine;
    }
    public void setCommand(String command){
        this.command = command;
    }
    public void setUser_id(int user_id){this.user_id = user_id;}

}

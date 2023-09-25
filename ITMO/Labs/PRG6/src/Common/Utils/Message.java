package Common.Utils;

import Common.Models.SpaceMarine;

import java.io.Serializable;

public class Message implements Serializable {
    private String command;
    private String[] args;
    private SpaceMarine spaceMarine;
    public Message(){
        this.spaceMarine = null;
        this.args = null;
    }

    public String getCommand(){
        return command;
    }
    public SpaceMarine getSpaceMarine(){
        return spaceMarine;
    }
    public String[] getArgs(){return args;}

    public void setSpaceMarine(SpaceMarine spaceMarine){
        this.spaceMarine = spaceMarine;
    }
    public void setCommand(String command){
        this.command = command;
    }
    public void setArgs(String[] strings){this.args = strings;}
}

package main.Commands;

import main.Console.Console;
import main.Models.SpaceMarine;

/**
 * Interface for commands that require a new element to be entered
 */
public abstract class CompoundCommand  extends Command{
    private  String name;
    private String description;
    protected CompoundCommand(String name, String description, Console console){
        super(name, description, console);
    }
    public abstract void setSpaceMarine(SpaceMarine spaceMarine);

}

package main.Commands;

import main.Console.Console;

/**
 * Interface for simple commands without entering a new item.
 */
public abstract class Command {
    private  String name;
    private String description;
    protected Console console;


    protected Command(String name, String description, Console console){
        this.name = name;
        this.description = description;
        this.console = console;
    }

    public String getName(){
        return name;
    }

    /**
     * Abstract method for command execution
     * @param strings
     */
    public abstract void execute(String[] strings);

    public String toString(){
        return name+": "+description;
    }
}

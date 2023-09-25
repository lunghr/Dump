package com.lunghr.lab6.common.commands;

import com.lunghr.lab6.common.consoles.Console;

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

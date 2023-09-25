package com.lunghr.lab6.common.commands;

import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.models.SpaceMarine;

public abstract class CompoundCommand extends Command{
    private  String name;
    private String description;
    protected CompoundCommand(String name, String description, Console console){
        super(name, description, console);
    }
    public abstract void setSpaceMarine(SpaceMarine spaceMarine);

}


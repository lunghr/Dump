package com.lunghr.lab6.common.commands;

import com.lunghr.lab6.common.consoles.Console;

public abstract class ServerCommand extends Command{
    private  String name;
    private String description;

    protected ServerCommand(String name, String description, Console console) {
        super(name, description, console);
    }

    public abstract void execute(String[] strings);
}

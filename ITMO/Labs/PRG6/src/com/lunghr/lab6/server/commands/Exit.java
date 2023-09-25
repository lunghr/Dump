package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.consoles.Console;

public class Exit extends Command {

    public Exit (Console console){
        super ("exit", "end the program", console);
    }

    @Override
    public void execute(String[] strings) {
        System.out.println("Client was disconnected");

    }
}

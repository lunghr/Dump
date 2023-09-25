package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.WrongArgumentsException;
import com.lunghr.lab6.server.managers.CollectionManager;

public class Show extends Command {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager, Console console) {
        super("show", "collection items output", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try {
            if (strings.length != 0) {
                throw new WrongArgumentsException();
            }
            collectionManager.printCollection();
        } catch (WrongArgumentsException e) {
            console.writeStr(e.toString() + ": show command doesn't need additional arguments");
        }
    }
}


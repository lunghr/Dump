package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.WrongArgumentsException;
import com.lunghr.lab6.server.managers.CollectionManager;

public class MinByCoordinates extends Command {
    private CollectionManager collectionManager;


    public MinByCoordinates(CollectionManager collectionManager, Console console) {
        super("min_by_coordinates", "finding the element with the smallest value of the coordinates field", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try {
            if (strings.length != 0) {
                throw new WrongArgumentsException();
            }
            collectionManager.findMinElementByCoordinates();
        } catch (WrongArgumentsException e) {
            console.writeStr(e.toString() + ": min_by_coordinates command doesn't need additional arguments");
        }
    }
}

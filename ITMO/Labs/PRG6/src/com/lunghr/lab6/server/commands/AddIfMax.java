package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.CompoundCommand;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.WrongArgumentsException;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.server.managers.CollectionManager;

public class AddIfMax extends CompoundCommand {
    private CollectionManager collectionManager;
    private SpaceMarine spaceMarine;

    public AddIfMax (CollectionManager collectionManager, Console console){
        super ("add_if_max", "adds a new item to the collection if its health field is greater than the maximum " +
                "health of the collection", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void execute(String[] strings) {
        if (strings.length != 0) {
            try {
                throw new WrongArgumentsException();
            } catch (WrongArgumentsException e) {
                console.writeStr(e.toString());
            }
        }
        collectionManager.addNewElementIfMax(spaceMarine);
    }
}

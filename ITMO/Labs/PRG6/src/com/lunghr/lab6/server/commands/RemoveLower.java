package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.CompoundCommand;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.WrongArgumentsException;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.server.managers.CollectionManager;

public class RemoveLower extends CompoundCommand {
    private CollectionManager collectionManager;
    private SpaceMarine spaceMarine;

    public RemoveLower(CollectionManager collectionManager, Console console) {
        super("remove_lower", "remove items from the collection with a lower health value than the specified one", console);
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
        collectionManager.removeLowerHealth(spaceMarine);
    }
}

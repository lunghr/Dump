package com.lunghr.lab6.server.commands;


import com.lunghr.lab6.common.commands.CompoundCommand;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.server.managers.CollectionManager;


public class Update extends CompoundCommand {
    private SpaceMarine spaceMarine;
    private CollectionManager collectionManager;


    public Update(CollectionManager collectionManager, Console console) {
        super("update", "updates a collection item with the given id", console);
        this.collectionManager = collectionManager;

    }

    @Override
    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void execute(String[] strings) {
        int id = Integer.parseInt(strings[0]);
        collectionManager.updateCollectionElement(id,spaceMarine);
    }
}

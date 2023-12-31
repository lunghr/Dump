package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.CompoundCommand;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.server.managers.CollectionManager;

public class Add extends CompoundCommand {
    private CollectionManager collectionManager;
    private SpaceMarine spaceMarine;
    // update on description
    public Add (Console console, CollectionManager collectionManager){
        super ("add","add an element to the collection (u can also interrupt input manual by \"end\" command)", console );
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void execute(String[] strings) {
        collectionManager.addNewElement(spaceMarine);
        console.writeStr("The new element was successfully added to the collection");
    }
}

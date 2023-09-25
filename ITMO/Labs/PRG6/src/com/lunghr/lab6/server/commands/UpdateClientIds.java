package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.server.managers.CollectionManager;

public class UpdateClientIds extends Command {
    private CollectionManager collectionManager;

    public UpdateClientIds(Console console, CollectionManager collectionManager){
        super ("updateClientIds", "update ids set on the com.lunghr.lab6.client module", console);
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] strings) {
        console.writeStr(collectionManager.getIds());
    }
}

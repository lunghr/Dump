package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.WrongArgumentsException;
import com.lunghr.lab6.server.managers.CollectionManager;

public class Clear extends Command {
    private CollectionManager collectionManager;

    public Clear (CollectionManager collectionManager, Console console){
        super ("clear", "collection cleanup", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!=0){
                throw new WrongArgumentsException();
            }
            collectionManager.cleanCollection();
            console.writeStr("Collection was cleared");
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": clear command doesn't need additional arguments");
        }
    }
}

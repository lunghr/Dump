package com.lunghr.lab6.server.commands;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.WrongArgumentsException;
import com.lunghr.lab6.server.managers.CollectionManager;

public class History extends Command {
    private CollectionManager collectionManager;

    public History(CollectionManager collectionManager, Console console) {
        super("history", "print the last 11 commands", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!= 0){
                throw new WrongArgumentsException();
            }
            collectionManager.getHistory();
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": history command doesn't need additional arguments");
        }
    }
}

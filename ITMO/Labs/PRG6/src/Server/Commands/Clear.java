package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Server.Managers.CollectionManager;

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

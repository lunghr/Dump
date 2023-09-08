package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;

/**
 * Command for collection clearing: remove all elements from set
 */
public class Clear extends Command{
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
            console.writeStr("Collection is cleared");
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": clear command doesn't need additional arguments");
        }
    }
}

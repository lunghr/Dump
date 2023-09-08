package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;

/**
 * Command for information about the collection
 */
public class Info extends Command{

    private CollectionManager collectionManager;

    public Info (CollectionManager collectionManager, Console console){
        super ("info", "collection information output ", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!= 0){
                throw new WrongArgumentsException();
            }
            collectionManager.getInfo();
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": info command doesn't need additional arguments");
        }
    }
}

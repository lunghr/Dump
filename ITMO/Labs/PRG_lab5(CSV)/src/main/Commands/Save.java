package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Managers.FileManager;

/**
 * Command to save collection in the file
 */

public class Save extends Command{
    private String fileName;
    private CollectionManager collectionManager;



    public Save (CollectionManager collectionManager, Console console, String fileName){
        super("save", "saves the collection to a file", console);
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }


    public void execute (String[] strings) {
        try {
            //save command doesn't need additional arguments for execution
            if (strings.length != 0) {
                throw new WrongArgumentsException();
            }
            FileManager.saveCollection(collectionManager.getCollection(), fileName);
        } catch (WrongArgumentsException e) {
            console.writeStr(e.toString() + ": save command doesn't need additional arguments");
        }
    }
}

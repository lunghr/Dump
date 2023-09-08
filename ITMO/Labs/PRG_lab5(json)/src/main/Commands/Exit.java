package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;

/**
 * Command to exit the program
 */
public class Exit extends Command{

    private CollectionManager collectionManager;

    public Exit (CollectionManager collectionManager, Console console){
        super ("exit", "end the program", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if(strings.length !=0){
                throw new WrongArgumentsException();
            }
            System.exit(0);
        } catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": exit command doesn't need additional arguments");
        }
    }
}

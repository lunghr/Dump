package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Managers.ConsoleManager;

/**
 * Command to get a sorted list of the elements health value (from max to min)
 */
public class PrintFieldDescendingHealth extends Command{
    private CollectionManager collectionManager;
    public PrintFieldDescendingHealth(CollectionManager collectionManager, Console console){
        super ("print_field_descending_health", "print the values of all health fields in descending order", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!= 0){
                throw new WrongArgumentsException();
            }
            collectionManager.printSortedHealthFields();
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": print_field_descending_health command doesn't need additional arguments");
        }

    }
}

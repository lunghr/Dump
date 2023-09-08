package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;

/**
 * Command to output the collection item with the minimum coordinates
 */
public class MinByCoordinates extends Command {
    private CollectionManager collectionManager;


    public MinByCoordinates(CollectionManager collectionManager, Console console) {
        super("min_by_coordinates", "finding the element with the smallest value of the coordinates field", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try {
            if (strings.length != 0) {
                throw new WrongArgumentsException();
            }
            collectionManager.findMinElementByCoordinates();
        } catch (WrongArgumentsException e) {
            console.writeStr(e.toString() + ": min_by_coordinates command doesn't need additional arguments");
        }
    }
}


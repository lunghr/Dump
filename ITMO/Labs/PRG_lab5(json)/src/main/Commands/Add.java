package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Models.SpaceMarine;

/**
 * Command to add a new input element to the collection
 */
public class Add extends CompoundCommand{
    private CollectionManager collectionManager;
    private SpaceMarine spaceMarine;
    // update on description
    public Add (Console console, CollectionManager collectionManager){
        super ("add","add an element to the collection (u can also interrupt input manual by \"end\" command)", console );
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void execute(String[] strings) {
        collectionManager.addNewElement(spaceMarine);
        console.writeStr("The new element was successfully added to the collection");
    }
}

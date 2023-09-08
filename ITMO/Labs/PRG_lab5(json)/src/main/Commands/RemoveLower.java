package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Models.SpaceMarine;

/**
 * Command to remove elements from the collection with a lower health value than the specified one
 */
public class RemoveLower extends CompoundCommand {
    private CollectionManager collectionManager;
    private SpaceMarine spaceMarine;

    public RemoveLower(CollectionManager collectionManager, Console console) {
        super("remove_lower", "remove items from the collection with a lower health value than the specified one", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void execute(String[] strings) {
        if (strings.length != 0) {
            try {
                throw new WrongArgumentsException();
            } catch (WrongArgumentsException e) {
                console.writeStr(e.toString());
            }
        }
        collectionManager.removeLowerHealth(spaceMarine);
    }
}


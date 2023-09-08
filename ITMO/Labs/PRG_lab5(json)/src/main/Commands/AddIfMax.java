package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Models.SpaceMarine;

/**
 * Command to add a new item to the collection if the health value of the new item is greater than the maximum health value of the collection item
 */
public class AddIfMax extends CompoundCommand{
    private CollectionManager collectionManager;
    private SpaceMarine spaceMarine;

    public AddIfMax (CollectionManager collectionManager, Console console){
        super ("add_if_max", "adds a new item to the collection if its health field is greater than the maximum " +
                "health of the collection", console);
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
        collectionManager.addNewElementIfMax(spaceMarine);
    }
}

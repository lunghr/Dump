package Server.Commands;

import Common.Commands.CompoundCommand;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Common.Models.SpaceMarine;
import Server.Managers.CollectionManager;

public class AddIfMax extends CompoundCommand {
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

package Server.Commands;

import Common.Commands.CompoundCommand;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Common.Models.SpaceMarine;
import Server.Managers.CollectionManager;

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

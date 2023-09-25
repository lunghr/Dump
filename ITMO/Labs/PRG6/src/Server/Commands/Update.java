package Server.Commands;


import Common.Commands.CompoundCommand;
import Common.Consoles.Console;
import Common.Models.SpaceMarine;
import Server.Managers.CollectionManager;


public class Update extends CompoundCommand {
    private SpaceMarine spaceMarine;
    private CollectionManager collectionManager;


    public Update(CollectionManager collectionManager, Console console) {
        super("update", "updates a collection item with the given id", console);
        this.collectionManager = collectionManager;

    }

    @Override
    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    @Override
    public void execute(String[] strings) {
        int id = Integer.parseInt(strings[0]);
        collectionManager.updateCollectionElement(id,spaceMarine);
    }
}

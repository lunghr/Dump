package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Server.Managers.CollectionManager;

public class Show extends Command {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager, Console console) {
        super("show", "collection items output", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try {
            if (strings.length != 0) {
                throw new WrongArgumentsException();
            }
            collectionManager.printCollection();
        } catch (WrongArgumentsException e) {
            console.writeStr(e.toString() + ": show command doesn't need additional arguments");
        }
    }
}


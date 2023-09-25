package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Consoles.ServerConsole;
import Server.Managers.CollectionManager;
import Server.Managers.ServerManager;

public class UpdateClientIds extends Command {
    private CollectionManager collectionManager;

    public UpdateClientIds(Console console, CollectionManager collectionManager){
        super ("updateClientIds", "update ids set on the client module", console);
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String[] strings) {
        console.writeStr(collectionManager.getIds());
    }
}

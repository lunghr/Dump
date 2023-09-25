package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Server.Managers.CollectionManager;

public class History extends Command {
    private CollectionManager collectionManager;

    public History(CollectionManager collectionManager, Console console) {
        super("history", "print the last 11 commands", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!= 0){
                throw new WrongArgumentsException();
            }
            collectionManager.getHistory();
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": history command doesn't need additional arguments");
        }
    }
}

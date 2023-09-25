package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Server.Managers.CollectionManager;



public class Info extends Command {
    private CollectionManager collectionManager;

    public Info (CollectionManager collectionManager, Console console){
        super ("info", "collection information output ", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!= 0){
                throw new WrongArgumentsException();
            }
            collectionManager.getInfo();
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": info command doesn't need additional arguments");
        }
    }
}

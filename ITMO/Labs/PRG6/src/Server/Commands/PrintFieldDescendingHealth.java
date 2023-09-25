package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;
import Server.Managers.CollectionManager;

public class PrintFieldDescendingHealth extends Command {
    private  CollectionManager collectionManager;
    public PrintFieldDescendingHealth(CollectionManager collectionManager, Console console){
        super ("print_field_descending_health", "print the values of all health fields in descending order", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length!= 0){
                throw new WrongArgumentsException();
            }
            collectionManager.printSortedHealthFields();
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": print_field_descending_health command doesn't need additional arguments");
        }

    }
}

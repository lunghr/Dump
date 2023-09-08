package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;

/**
 * Command to display a list of available commands and their description
 */
public class Help extends Command{
    private Command[] commands;

    public Help (Command[] commands, Console console){
        super("help", "list of available commands and their descriptions ", console);
        this.commands = commands;
    }

    @Override
    public void execute(String[] strings) {
        try{
            if (strings.length != 0){
                throw new WrongArgumentsException();
            }for (Command command : commands){
                console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                console.writeStr(command.toString());
            }
        }catch (WrongArgumentsException e){
            console.writeStr(e.toString()+": help command doesn't need additional arguments");
        }
    }
}

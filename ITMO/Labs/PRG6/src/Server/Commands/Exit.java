package Server.Commands;

import Common.Commands.Command;
import Common.Consoles.Console;
import Common.Exceptions.WrongArgumentsException;

public class Exit extends Command {

    public Exit (Console console){
        super ("exit", "end the program", console);
    }

    @Override
    public void execute(String[] strings) {
        System.out.println("Client was disconnected");

    }
}

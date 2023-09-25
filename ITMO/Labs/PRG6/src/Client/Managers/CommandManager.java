package Client.Managers;

import Client.Commands.ExecuteScript;
import Common.Commands.Command;

import java.util.LinkedHashMap;

public class CommandManager {
    // не доделано
    private LinkedHashMap<String, Command> listOfCommands = new LinkedHashMap<>();
    public CommandManager(){
        listOfCommands.put("execute_script", new ExecuteScript());

    }
}

package Client.Commands;

import Common.Commands.Command;
import Common.Commands.ServerCommand;
//тоже не доделано 
public class ExecuteScript extends ServerCommand {
    public ExecuteScript(){
        super("execute_script", "", console);
    }
}

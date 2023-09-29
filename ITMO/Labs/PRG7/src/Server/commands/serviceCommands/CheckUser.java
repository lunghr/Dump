package Server.commands.serviceCommands;

import Common.commands.Command;
import Common.consoles.Console;
import Server.managers.DBManagers.UsersManager;
import Server.managers.collectionManagers.CommandsManager;

import java.util.concurrent.ExecutorService;

import static Server.managers.collectionManagers.CommandsManager.threadPool;

public class CheckUser extends Command {
    private Console console;
    private UsersManager usersManager;
    private CommandsManager commandsManager;

    public CheckUser(Console console, UsersManager usersManager, CommandsManager commandsManager){
        super("check_user", "username checker", console);
        this.usersManager = usersManager;
        this.commandsManager = commandsManager;
    }

    @Override
    public void execute(String[] strings) {
        usersManager.isUserExist(strings[0]);
    }
}

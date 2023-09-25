package com.lunghr.lab6.server.managers;

import com.lunghr.lab6.common.commands.Command;
import com.lunghr.lab6.common.commands.CompoundCommand;
import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.NotFoundCommandException;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.common.utils.Message;
import com.lunghr.lab6.server.commands.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.lunghr.lab6.common.models.SpaceMarine.ids;
import static java.util.Collections.max;

public class CommandsManager {
    private Console console;
    private CollectionManager collectionManager;
    private LinkedHashMap<String, Command> listOfCommands = new LinkedHashMap<>();
    public static List<String> history = new ArrayList<>();

    public CommandsManager(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;

        Command[] commandsList = {new Show(collectionManager, console), new Clear(collectionManager, console),
                new Info(collectionManager, console), new History(collectionManager, console),
                new MinByCoordinates(collectionManager, console),
                new PrintFieldDescendingHealth(collectionManager, console),
                new RemoveAllByMeleeWeapon(collectionManager, console),new RemoveById(console, collectionManager),
                new Add(console, collectionManager), new AddIfMax(collectionManager, console),
                new Update(collectionManager,console), new UpdateClientIds(console, collectionManager),
                new RemoveLower(collectionManager, console), new Exit(console)};
        listOfCommands.put("help", new Help(commandsList, console));
        for (Command command : commandsList) {
            listOfCommands.put(command.getName(), command);
        }
    }

    /**
     * A method for tracking the last 11 commands executed
     * @param command
     */
    public void controlHistoryList(String command){
        String[] tmp = command.split("\\s+");
        command = tmp[0];
        if (history.size() == 11) {
            history.remove(0);
        }history.add(command);
    }

    /**
     * Method for invoking and executing commands
     * @param message
     */

    public void execute(Message message) {
        String[] tmp = (message.getCommand()).split("\\s+");
        String tmpCommand = tmp[0];
        System.out.println(tmpCommand);
        String[] arguments = new String[tmp.length - 1];
        for (int i = 1; i < tmp.length; i++) {
            arguments[i - 1] = tmp[i];
        }
        SpaceMarine spaceMarine = message.getSpaceMarine();
        try{
            if (!listOfCommands.containsKey(tmpCommand)) {
                throw new NotFoundCommandException();
            }
            Command command = listOfCommands.get(tmpCommand);

            if (command instanceof Update) {
                if (spaceMarine == null) return;
                Integer id = spaceMarine.getId();
                System.out.println(String.valueOf(id));
                arguments = new String[1];
                arguments[0] = String.valueOf(id);

                ((CompoundCommand) command).setSpaceMarine(spaceMarine);
            }
            if (command instanceof CompoundCommand){
                if (spaceMarine == null)  return;
                if (ids.contains(spaceMarine.getId())){
                    spaceMarine.setId(max(ids)+1);
                }
                ((CompoundCommand) command).setSpaceMarine(spaceMarine);
            }

            command.execute(arguments);
            controlHistoryList(tmpCommand);
        } catch (NotFoundCommandException e){
            console.writeStr(e.toString());
        }
    }

}

package main.Managers;

import main.Commands.*;
import main.Console.Console;
import main.Exceptions.*;
import main.Models.SpaceMarine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static main.Commands.ExecuteScript.cycleCounter;
import static main.Commands.ExecuteScript.maxCycleCount;
import static main.Models.SpaceMarine.ids;

/**
 * A class for storing command class objects and their execution
 */
public class CommandsManager {
    private Console console;
    private CollectionManager collectionManager;
    private InputDataManager inputDataManager;
    private LinkedHashMap<String, Command> listOfCommands = new LinkedHashMap<>();
    private String fileName;
    public static List<String> history = new ArrayList<>();

    public CommandsManager(InputDataManager inputDataManager) {
        this.inputDataManager = inputDataManager;
        this.console = inputDataManager.getConsole();
        this.collectionManager = inputDataManager.getCollectionManager();
        this.fileName = inputDataManager.getFileName();
        Command[] commandsList = {new Save(collectionManager, console, fileName), new Update(collectionManager, console),
                new Show(collectionManager,console), new Clear(collectionManager, console),
                new RemoveById(console, collectionManager), new Exit(collectionManager, console),
                new RemoveAllByMeleeWeapon(collectionManager, console), new History(collectionManager, console),
                new MinByCoordinates(collectionManager, console),
                new PrintFieldDescendingHealth(collectionManager, console),
                new Add(console, collectionManager), new AddIfMax(collectionManager, console),
                new RemoveLower(collectionManager, console), new Info(collectionManager, console),
                new ExecuteScript(collectionManager, console)
        };
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
     * @param consoleCommand
     */

    public void execute(String consoleCommand) {
        String[] tmp = consoleCommand.split("\\s+");
        consoleCommand = tmp[0];
        String[] arguments = new String[tmp.length - 1];
        for (int i = 1; i < tmp.length; i++) {
            arguments[i - 1] = tmp[i];
        }
        try{
            if (!listOfCommands.containsKey(consoleCommand)) {
              throw new NotFoundCommandException();
            }
            Command command = listOfCommands.get(consoleCommand);
            if (command instanceof Add) {
                if (arguments.length != 0) {
                    console.writeStr("At this stage add command doesn't need additional arguments");
                    return;
                }
            }
            if (command instanceof Update) {
                if (arguments.length != 1) {
                    console.writeStr("Update command requires an id (one int number)");
                    return;
                }
                if (!isInteger(arguments[0])){
                    console.writeStr("The argument must be an integer");
                    return;
                }
                if (!ids.contains(Integer.parseInt(arguments[0]))){
                    console.writeStr("There is no element with this id in the collection");
                    return;
                }
            }
            if (command instanceof AddIfMax){
                if (arguments.length != 0) {
                    console.writeStr("At this stage add_if_max command doesn't need additional arguments");
                    return;
                }
            }
            if (command instanceof RemoveLower){
                if (arguments.length != 0) {
                    console.writeStr("At this remove_lower command doesn't need additional arguments");
                    return;
                }
            }
            if (command instanceof CompoundCommand){
                SpaceMarine spaceMarine = inputDataManager.getSpaceMarine();
                if (spaceMarine == null)  return;
                ((CompoundCommand) command).setSpaceMarine(spaceMarine);
            }

            command.execute(arguments);
        } catch (NotFoundCommandException e){
            console.writeStr(e.toString());
        }catch (StopInputException | EndInputException e) {
            return;
        }
    }
    private boolean isInteger(String string){
        Integer tmp;
        try {
            tmp = Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }return true;
    }
}
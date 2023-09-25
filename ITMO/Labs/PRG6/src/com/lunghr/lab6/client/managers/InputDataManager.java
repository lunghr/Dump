package com.lunghr.lab6.client.managers;

import com.lunghr.lab6.common.consoles.Console;
import com.lunghr.lab6.common.exceptions.EndInputException;
import com.lunghr.lab6.common.exceptions.NotFoundCommandException;
import com.lunghr.lab6.common.exceptions.StopInputException;
import com.lunghr.lab6.common.managers.SpaceMarineManager;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.common.utils.Message;

import java.util.*;

import static com.lunghr.lab6.client.managers.ClientManager.clientIds;
import static com.lunghr.lab6.client.managers.TypeCheckingManager.isInteger;
import static java.util.Collections.max;

public class InputDataManager {
    private Console console;
    private String endInput = "end";
    private SpaceMarineManager spaceMarineManager;
    private List<String> listOfAvailableCommands = new ArrayList<String>();
    private List<String> listOfCompoundCommands = new ArrayList<String>();



    public InputDataManager(ClientManager clientManager) {
        spaceMarineManager = new SpaceMarineManager(clientManager.getConsole());
        this.console = clientManager.getConsole();
        listOfAvailableCommands.add("show");
        listOfAvailableCommands.add("clear");
        listOfAvailableCommands.add("info");
        listOfAvailableCommands.add("history");
        listOfAvailableCommands.add("help");
        listOfAvailableCommands.add("min_by_coordinates");
        listOfAvailableCommands.add("print_field_descending_health");
        listOfAvailableCommands.add("remove_all_by_melee_weapon");
        listOfAvailableCommands.add("remove_by_id");
        listOfAvailableCommands.add("add");
        listOfAvailableCommands.add("add_if_max");
        listOfAvailableCommands.add("update");
        listOfAvailableCommands.add("updateClientIds");
        listOfAvailableCommands.add("remove_lower");
        listOfAvailableCommands.add("exit");
        listOfAvailableCommands.add("execute_script");


        listOfCompoundCommands.add("add");
        listOfCompoundCommands.add("add_if_max");
        listOfCompoundCommands.add("update");
        listOfCompoundCommands.add("remove_lower");

    }

    public Console getConsole() {
        return console;
    }

    public boolean check(String command) {
        String[] tmp = command.split("\\s+");
        command = tmp[0];
        String[] arguments = new String[tmp.length - 1];
        for (int i = 1; i < tmp.length; i++) {
            arguments[i - 1] = tmp[i];
        }
        try {
            if (!listOfAvailableCommands.contains(command)) {
                throw new NotFoundCommandException();
            }
            if(command.equals("add")){
                if (arguments.length != 0) {
                    console.writeStr("At this stage add command doesn't need additional arguments");
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return false;
                }
            }
            if(command.equals("exit")) {
                if (arguments.length != 0) {
                    console.writeStr("Exit command doesn't need additional arguments");
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return false;
                }
            }
            if (command.equals("add_if_max")){
                if (arguments.length != 0) {
                    console.writeStr("At this stage add_if_max command doesn't need additional arguments");
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return false;
                }
            }
            if (command.equals("update")) {
                if (arguments.length != 1) {
                    console.writeStr("Update command requires an id (one int number)");
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return false;
                }
                if (!isInteger(arguments[0])){
                    console.writeStr("The argument must be an integer");
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return false;
                }
                if (!clientIds.contains(Integer.parseInt(arguments[0]))){
                    console.writeStr("There is no element with this id in the collection");
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    return false;
                }
            }
            if (command.equals("remove_lower")){
                if (arguments.length != 0) {
                    console.writeStr("At this remove_lower command doesn't need additional arguments");
                    return false;
                }
            }
            return true;
        } catch (NotFoundCommandException e) {
            console.writeStr(e.toString());
        }
        return false;
    }


    public Message execute(String command) {
        Message message = new Message();
        String[] tmp = command.split("\\s+");
        command = tmp[0];
        String[] arguments = new String[tmp.length - 1];
        for (int i = 1; i < tmp.length; i++) {
            arguments[i - 1] = tmp[i];
        }
        try {
            if (listOfCompoundCommands.contains(command)){
                SpaceMarine spaceMarine = spaceMarineManager.getSpaceMarine();
                if(spaceMarine == null){
                    message.setSpaceMarine(null);
                }
                else if(command.equals("update")){
                    spaceMarine.setId(Integer.valueOf(arguments[0]));
                }
                message.setSpaceMarine(spaceMarine);
            }
            message.setCommand(command);
            return  message;
        } catch (StopInputException | EndInputException e) {
            return null;
        }
    }
}

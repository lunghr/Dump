package Client.Managers;

import Common.Consoles.Console;
import Common.Utils.Message;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientManager {
    private Client client;
    private Console console;
    private ReceiveManager receiveM;
    private SendManager sendM;
    private int port;
    private InetAddress address;


    public static Set<Integer> clientIds = new TreeSet<>();

    public ClientManager(Client client, Console console, int port, InetAddress address){
        this.client = client;
        this.console = console;
        this.port = port;
        this.address = address;


    }

    public Console getConsole(){
        return console;
    }

    public void run() throws SocketException {

        client.start();
        receiveM = new ReceiveManager(client.getSocket());
        sendM = new SendManager(client.getSocket(), address, port, console);
        InputDataManager inputDataManager = new InputDataManager(this);

        while (console.isNextStr()){
            console.writeStr("Enter a command (use help for a list of available commands): ");
            try{

                String command = console.getNextStr();
                console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                /*if (isExecuteScript(command)){
                    String[] commands  = command.split("\s");
                    Message scriptMessage = new Message();
                    scriptMessage.setCommand(commands[0]);
                    String[] arguments = new String[commands.length - 1];
                    for (int i = 1; i < commands.length; i++) {
                        arguments[i - 1] = commands[i];
                    }
                    scriptMessage.setArgs(arguments);
                    sendM.sendMessage(scriptMessage);
                    console.writeStr(receiveM.getMessage());

                }*/

                if (isUpdate(command)){
                    Message updMessage = inputDataManager.execute("updateClientIds");
                    sendM.sendMessage(updMessage);
                    String [] ids = receiveM.getMessage().split("\s");
                    for (String id : ids){
                        clientIds.add(Integer.parseInt(id));
                    }
                }

               /* if(inputDataManager.check(command)){
                    Message message = inputDataManager.execute(command);
                    sendM.sendMessage(message);
                    if (isExit(command)){
                        client.stop();
                        System.exit(0);
                    }
                    console.writeStr(receiveM.getMessage());
                    console.writeStr("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }*/
            } catch (IOException e) {
                console.writeStr("bebe");
            }

        }
    }

    public boolean  isUpdate(String str){
        Matcher matcher = Pattern.compile("^update").matcher(str);
        String x = "";
        while (matcher.find()) {
            x = String.valueOf(matcher.group());
        }
        if (x.equals("update")) {
            return true;
        }
        return false;
    }
    public boolean  isExit(String str){
        Matcher matcher = Pattern.compile("^exit").matcher(str);
        String x = "";
        while (matcher.find()) {
            x = String.valueOf(matcher.group());
        }
        if (x.equals("exit")) {
            return true;
        }
        return false;
    }
    public boolean  isExecuteScript(String str){
        Matcher matcher = Pattern.compile("^execute_script").matcher(str);
        String x = "";
        while (matcher.find()) {
            x = String.valueOf(matcher.group());
        }
        if (x.equals("execute_script")) {
            return true;
        }
        return false;
    }



    public Client getClient(){
        return client;
    }


}

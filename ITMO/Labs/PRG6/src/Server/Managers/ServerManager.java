package Server.Managers;

import Common.Consoles.Console;
import Common.Consoles.ServerConsole;
import Common.Exceptions.NotFoundCommandException;
import Common.Managers.FileManager;
import Common.Models.SpaceMarine;
import Common.Utils.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Scanner;

public class ServerManager {
    private Server server;
    private static CollectionManager collectionManager;
    private static ServerConsole console;
    private ReceiveManager receiveM;
    private SendManager sendM;
    private Scanner serverCommandsInput = new Scanner(System.in);
    private boolean workingState = true;

    //literally server manager
    public ServerManager(Server server, CollectionManager collectionManager) {
        this.server = server;
        this.collectionManager = collectionManager;
        this.console = collectionManager.getConsole();
    }

    //main Server executor
    public void run() {
        try {
            //open channel, create buffer, bind InetSocketAddress
            server.start();
            System.out.println("server has been started");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            CommandsManager commandsManager = new CommandsManager(collectionManager, console);
            receiveM = new ReceiveManager(server.getDatagramChannel());
            sendM = new SendManager(server.getDatagramChannel());
            while (workingState){
                Message message= receiveM.getMessage();
                commandsManager.execute(message);
                sendM.setSocketAddress(receiveM.getSocketAddress());
                String result = console.getText();
                if (!message.getCommand().equals("exit")) {
                    sendM.sendMessage(result);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Console getConsole(){
        return console;
    }

    public static CollectionManager getCollectionManager(){
        return collectionManager;
    }




}

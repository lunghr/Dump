package com.lunghr.lab6.server;

import com.lunghr.lab6.common.consoles.ServerConsole;
import com.lunghr.lab6.common.managers.FileManager;
import com.lunghr.lab6.common.models.SpaceMarine;
import com.lunghr.lab6.server.managers.CollectionManager;
import com.lunghr.lab6.server.managers.ServerManager;

import java.net.UnknownHostException;
import java.util.HashSet;

public class ServerMain {
    public static void main(String[] args) {

//        String fileName = System.getenv("fileName");
//        if (fileName == null) {
//            System.out.println("The collection file was not found");
//            System.exit(0);
//        }

        String fileName = "meow.json";
        HashSet<SpaceMarine> collection = FileManager.readCollection(fileName);
        ServerConsole serverConsole = new ServerConsole();
        CollectionManager collectionManager = new CollectionManager(collection, serverConsole);

        int port = 6789;
        com.lunghr.lab6.server.managers.Server server = new com.lunghr.lab6.server.managers.Server(port);

        ServerManager serverM = new ServerManager(server, collectionManager);
        serverM.run();
    }
}

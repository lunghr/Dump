package Server;

import Common.Consoles.ServerConsole;
import Common.Managers.FileManager;
import Common.Models.SpaceMarine;
import Server.Managers.CollectionManager;
import Server.Managers.Server;
import Server.Managers.ServerManager;

import java.net.UnknownHostException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        /*String fileName = System.getenv("fileName");
        if (fileName == null) {
            System.out.println("The collection file was not found");
            System.exit(0);
        }*/
        String fileName = "meow.json";
        HashSet<SpaceMarine> collection = FileManager.readCollection(fileName);
        ServerConsole serverConsole = new ServerConsole();
        CollectionManager collectionManager = new CollectionManager(collection, serverConsole);

        int port = 6789;
        Server server = new Server(port);

        ServerManager serverM = new ServerManager(server, collectionManager);
        serverM.run();




    }
}

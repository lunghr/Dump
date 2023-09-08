package main;


import main.Managers.CollectionManager;
import main.Managers.ConsoleManager;
import main.Managers.InputDataManager;
import main.Managers.FileManager;
import main.Models.SpaceMarine;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*
        String fileName = System.getenv("fileName");
        if (fileName == null) {
            System.out.println("The collection file was not found");
            System.exit(0);
        }
        */
        String fileName = "meow.json";

        HashSet<SpaceMarine> collection = FileManager.readCollection(fileName);

        ConsoleManager consoleManager = new ConsoleManager();
        CollectionManager collectionManager = new CollectionManager(collection);


        InputDataManager inputDataManager = new InputDataManager(consoleManager, collectionManager, fileName);
        inputDataManager.start();


    }

}


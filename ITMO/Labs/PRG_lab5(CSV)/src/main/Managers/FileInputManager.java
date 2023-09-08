package main.Managers;

import main.Console.Console;

/**
 * A class for executing commands from the script
 */
public class FileInputManager implements Console {
    private String fileName;
    private String [] commands;
    private int comCounter = 0;
    public FileInputManager(String fileName){
        this.fileName = fileName;
        commands =FileManager.readTextFromFile(fileName);
    }
    @Override
    public boolean isNextStr(){
        return commands.length > comCounter;
    }
    @Override
    public String getNextStr() {
        return commands[comCounter++].strip();
    }
    @Override
    public void writeStr(String text) {
        System.out.println(text);

    }


}

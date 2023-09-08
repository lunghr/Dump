package main.Managers;
import main.Console.Console;


import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * A class for controlling input from the console
 */
public class ConsoleManager implements Console {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean isNextStr() {
        return true;
    }

    @Override
    public String getNextStr() {
        try {
            return scanner.nextLine().strip();
        } catch (NoSuchElementException e){
            System.out.println("You interrupted the input stream");
            System.exit(0);
        } return null;
    }


    @Override
    public void writeStr(String text) {
        System.out.println(text);

    }

}

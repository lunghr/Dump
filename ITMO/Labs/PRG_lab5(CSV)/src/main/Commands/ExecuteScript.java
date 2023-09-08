package main.Commands;

import main.Console.Console;
import main.Exceptions.StopInputException;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Managers.FileInputManager;
import main.Managers.InputDataManager;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Command for script execution (from the file)
 */
public class ExecuteScript extends Command {
    public static Integer cycleCounter = 1, maxCycleCount = 4;
    private CollectionManager collectionManager;

    public ExecuteScript(CollectionManager collectionManager, Console console) {
        super("execute_script", "execute commands from a file", console);
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute(String[] strings) {
        try {
            if (strings.length == 0) {
                throw new WrongArgumentsException();
            }
            String fileName = strings[0];
            if (!Files.exists(Path.of(fileName))) {
                console.writeStr("File with this name doesn't exist");
                return;

            }
            if (cycleCounter >= maxCycleCount) {
                console.writeStr("Invalid argument: this command will be interrupted");
                throw new StopInputException();

            }
            cycleCounter += 1;
            Console fileInputConsole = new FileInputManager(fileName);
            InputDataManager fileInputDataManager = new InputDataManager(fileInputConsole, collectionManager, fileName);
            fileInputDataManager.start();
            cycleCounter -= 1;

        } catch (WrongArgumentsException e) {
            console.writeStr(e.toString() + ": execute_script command need additional argument - file name");
        } catch (StopInputException e) {
            return;
        }
    }
}

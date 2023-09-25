package Common.Commands;

import Common.Consoles.Console;

public abstract class ServerCommand extends Command{
    private  String name;
    private String description;

    protected ServerCommand(String name, String description, Console console) {
        super(name, description, console);
    }

    public abstract void execute(String[] strings);
}

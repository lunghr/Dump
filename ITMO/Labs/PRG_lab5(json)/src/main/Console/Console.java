package main.Console;

/**
 * Consoles interface
 */
public interface Console {

    public boolean isNextStr(); //possibility of getting text line checking
    public String getNextStr(); //next line getter
    public void writeStr (String text); //line writing

}

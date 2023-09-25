package Common.Utils;

public class StringParser {
    public StringParser() {
    }

    public String[] parse(String string) {
        String[] tmp = string.split("\\s+");
        String[] arguments = new String[tmp.length];
        for (int i = 1; i < tmp.length; i++) {
            arguments[i - 1] = tmp[i];
        }
        return arguments;
    }
}

package com.lunghr.lab6.server.managers.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelsMatcher {
    private String text;
    public ModelsMatcher(){};
    public List<String> matchModels(String  text){
        List<String> tmp = new LinkedList<>();
        Matcher matcher = Pattern.compile("\\{\\s*\\\"cre.+?}}\\s").matcher(text);
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            tmp.add(text.substring(start,end));
        }
        return tmp;

    }
}

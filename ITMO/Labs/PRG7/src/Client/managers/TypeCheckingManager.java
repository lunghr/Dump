package Client.managers;

import Common.models.MeleeWeapon;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

public class TypeCheckingManager {
    public TypeCheckingManager(){};

    public static boolean isMeleeWeapon (String string){
        MeleeWeapon tmp;
        try{
            tmp = Enum.valueOf(MeleeWeapon.class, string);
        }catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String string){
        Integer tmp;
        try {
            tmp = Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }return true;
    }

    public static boolean isDouble(String string){
        double tmp;
        try {
            if (string.contains(String.valueOf(","))){
                string = string.replace(",", ".");
            }
            tmp = Double.parseDouble(string);
        }catch (NumberFormatException e){
            return false;
        }return true;
    }

    public static boolean isLong(String string){
        long tmp;
        try {
            tmp = Long.parseLong(string);
        }catch (NumberFormatException e){
            return false;
        }return true;
    }

    public static boolean isFloat(String string){
        float tmp;
        try {
            if (string.contains(String.valueOf(","))){
                string = string.replace(",", ".");
            }
            tmp = Float.parseFloat(string);
        }catch (NumberFormatException e){
            return false;
        }return true;
    }

    public static boolean isBoolean(String string){
        if (string.equals("true") || string.equals("false")){
            return true;
        }return false;
    }

    public static boolean isZoneDateTime (String string){
        ZonedDateTime tmp;
        try{
            tmp = ZonedDateTime.parse(string);
        }catch (DateTimeParseException e){
            return false;
        }return true;
    }
}

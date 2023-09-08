package main.Managers;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import main.Exceptions.WrongArgumentsException;
import main.Models.Chapter;
import main.Models.Coordinates;
import main.Models.Json.ModelsMatcher;
import main.Models.Json.ZoneDateTimeAdapter;
import main.Models.MeleeWeapon;
import main.Models.SpaceMarine;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.*;

import static main.Models.Chapter.matchText;
import static main.Models.Coordinates.matchX;
import static main.Models.Coordinates.matchY;
import static main.Models.SpaceMarine.ids;

/**
 * Manager for downloading and recording collection
 */

public class FileManager {

    /**
     * Recording collection in file
     *
     * @param collection
     * @param fileName
     */
    static Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZoneDateTimeAdapter() ).setPrettyPrinting().create();
    static ModelsMatcher matcher = new ModelsMatcher();
    public static void saveCollection(Collection<SpaceMarine> collection, String fileName) {
        Path fileCheck = Paths.get(fileName);
        if (!Files.isWritable(fileCheck)){
            System.out.println("You don't have access rights to this file");
            return;
        }
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            for (SpaceMarine spaceMarine : collection){
                writer.write(gson.toJson(spaceMarine));
            }

            System.out.println("Collection was loaded into the file");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Reading collection from file
     *
     * @param fileName
     * @return Collection from file or, if file is empty, empty HashSet
     */
    public static HashSet<SpaceMarine> readCollection(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            Path fileCheck = Paths.get(fileName);
           if (!Files.isReadable(fileCheck)){
               System.out.println("You don't have access rights to this file, so an empty collection will be created");
               return new HashSet<>();
           }else {
               try (Scanner scanner = new Scanner(new FileReader(fileName))) {
                   HashSet<SpaceMarine> collection = new HashSet<>();
                   SpaceMarine spaceMarine = new SpaceMarine();
                   String tmp = new String();
                   while (scanner.hasNext()){
                       tmp += scanner.nextLine().toString();
                   }
                   tmp+= "{";
                   tmp = tmp.replace("}}{", "}} {");
                   List<String> list = matcher.matchModels(tmp);
                   for (String string : list){
                       System.out.println(string);
                   }
                   for (String json : list){
                       collection.add(gson.fromJson(json, SpaceMarine.class));
                       ids.add(gson.fromJson(json, SpaceMarine.class).getId());
                   }
                   return collection;

               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }
           }
        } else {
            return new HashSet<>();
        }
    }


    /**
     * Reading text(instructions)  from file
     *
     *
     * @param fileName
     * @return Return list of strings with \n split from text file (class for text parser for script execution)
     */
    public static String[] readTextFromFile(String fileName) {
        String[] empty = new String[0];
        Path fieCheck = Paths.get(fileName);
        if (!Files.isReadable(fieCheck)){
            System.out.println("You don't have access rights to this file");
            return empty;
        }
        if (fileName != null && !fileName.isEmpty()) {
            try (Scanner scanner = new Scanner(new FileReader(fileName))) {
                ArrayList<String> tmp = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String text = scanner.nextLine().toString();
                    tmp.add(text);

                }
                String[] commands = new String[tmp.size()];
                for (int i = 0; i < tmp.size(); i++) {
                    commands[i] = tmp.get(i);
                }
                return commands;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return empty;
        }
    }

}




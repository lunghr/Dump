package main.Managers;



import com.opencsv.bean.ColumnPositionMappingStrategy;
import main.Exceptions.WrongArgumentsException;
import main.Models.Chapter;
import main.Models.Coordinates;
import main.Models.MeleeWeapon;
import main.Models.SpaceMarine;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


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
    public static void saveCollection(Collection<SpaceMarine> collection, String fileName) {
        Path fileCheck = Paths.get(fileName);
        if (!Files.isWritable(fileCheck)){
            System.out.println("You don't have access rights to this file");
            return;
        }
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (SpaceMarine spaceMarine : collection){
                printer.printRecord(spaceMarine.getId(),
                        spaceMarine.getName(),
                        spaceMarine.getCreationDate(),
                        spaceMarine.getCoordinates(),
                        spaceMarine.getHealth(),
                        spaceMarine.getHeartCount(),
                        spaceMarine.isLoyal(),
                        spaceMarine.getMeleeWeapon(),
                        spaceMarine.getChapter());
            }


            System.out.println("Collection was loaded into the file");
            printer.close();
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
                   while (scanner.hasNextLine()) {
                       String[] values = scanner.nextLine().split(",");
                       spaceMarine = fromCSVtoSpaceMarineParse(values);
                       if (spaceMarine != null){
                           collection.add(spaceMarine);
                       }

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
     * Data type parser from String[] to SpaceMarine's fields
     * needed data types
     *
     * @param values
     * @return spaceMarine obj
     */
    public static SpaceMarine fromCSVtoSpaceMarineParse(String[] values) {
        SpaceMarine spaceMarine = new SpaceMarine();
        try {
            if (!(TypeCheckingManager.isInteger(values[0]))) {
                throw new WrongArgumentsException();
            }
            if (ids.contains(Integer.parseInt(values[0]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setId(Integer.parseInt(values[0]));
            if (values[1].isEmpty() || (values[1] == null)) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setName(values[1]);
            if (!(TypeCheckingManager.isZoneDateTime(values[2]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setCreationDate(ZonedDateTime.parse(values[2]));
            if (!(TypeCheckingManager.isLong(matchX(values[3])) && TypeCheckingManager.isDouble(matchY(values[4])))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setCoordinates(new Coordinates(Long.parseLong(matchX(values[3])), Double.parseDouble(matchY(values[4]))));
            if (!(TypeCheckingManager.isFloat(values[5]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setHealth(Float.parseFloat(values[5]));
            if (!(TypeCheckingManager.isLong(values[6]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setHeartCount(Long.parseLong(values[6]));
            if (!(TypeCheckingManager.isLong(values[6]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setHeartCount(Long.parseLong(values[6]));
            if (!(TypeCheckingManager.isBoolean(values[7]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setLoyal(Boolean.parseBoolean(values[7]));
            if (!(TypeCheckingManager.isMeleeWeapon(values[8]))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setMeleeWeapon(Enum.valueOf(MeleeWeapon.class, values[8]));
            if (!(!values[9].isEmpty() && !(values[9] == null) && !values[10].isEmpty() && !(values[10] == null))) {
                throw new WrongArgumentsException();
            }
            spaceMarine.setChapter(new Chapter(matchText(values[9]), matchText(values[10])));

            return spaceMarine;

        } catch (WrongArgumentsException e) {
            return null;
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




package main.Managers;

import main.Exceptions.NotFoundIdException;
import main.Models.MeleeWeapon;
import main.Models.SpaceMarine;
import main.Console.Console;

import java.time.LocalDateTime;
import java.util.*;

import static main.Managers.CommandsManager.history;
import static main.Models.SpaceMarine.ids;

/**
 * Class for working with a collection (physical list)
 */


public class CollectionManager {
    private Console console = new ConsoleManager();
    private LocalDateTime creationDate;
    private HashSet<SpaceMarine> fileCollection;

    public CollectionManager() {

        fileCollection = new HashSet<>();
        creationDate = LocalDateTime.now();
    }

    public CollectionManager(HashSet<SpaceMarine> collection) {

        this();
        setCollection(collection);

    }

    /**
     * Clears the collection and list of occupied id
     */
    public void cleanCollection() {
        fileCollection.clear();
        ids.clear();
    }

    /**
     * Collection setter
     * @param spaceMarines
     */
    public HashSet<SpaceMarine> setCollection(HashSet<SpaceMarine> spaceMarines) {
        for (SpaceMarine spaceMarine : spaceMarines) {
            fileCollection.add(spaceMarine);
        }
        return fileCollection;
    }

    /**
     * Updating an element with the specified id
     * @param id
     * @param spaceMarine
     */
    public void updateCollectionElement(int id, SpaceMarine spaceMarine) {
        Iterator<SpaceMarine> iterator = fileCollection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine sm = iterator.next();
            if (sm.getId() == id) {
                sm.updateSpaceMarine(spaceMarine);
                console.writeStr(spaceMarine.getName() + " with id = " + id + " has been updated");
            }
        }
    }



    /**
     * By id element getter
     * @param id
     */
    public SpaceMarine getSpaceMarineFromCollection(int id) {
        Optional<SpaceMarine> sm = fileCollection.stream()
                .filter(spaceMarine -> spaceMarine.getId() == id)
                .findAny();
        return sm.get();
    }

    /**
     * Collection getter
     */
    public HashSet<SpaceMarine> getCollection() {
        return fileCollection;
    }

    /**
     * Collection elements printer
     */
    public void printCollection() {
        if (!(fileCollection.size() > 0)) {
            console.writeStr("Collection is empty");
        }
        for (SpaceMarine spaceMarine : fileCollection) {
            console.writeStr(spaceMarine.toString());
        }
    }

    /**
     * Remove element by specified id
     * @param id
     */
    public void removeElementById(int id) {
        try {
            if (!ids.contains(id)) {
                throw new NotFoundIdException();
            }
            SpaceMarine spaceMarine = getSpaceMarineFromCollection(id);
            ids.remove(id);
            fileCollection.remove(spaceMarine);
            console.writeStr("Collection element with id=" + id + " is successfully removed");
        } catch (NotFoundIdException e) {
            console.writeStr(e.toString());
        }
    }

    /**
     * Add new element to the collection
     * @param spaceMarine
     */
    public void addNewElement(SpaceMarine spaceMarine) {
        fileCollection.add(spaceMarine);
    }

    /**
     * Remove collection elements with specified meleeWeapon fields
     * @param meleeWeapon
     */
    public void removeSameMeleeweaponElements(MeleeWeapon meleeWeapon) {
        Iterator<SpaceMarine> iterator = fileCollection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getMeleeWeapon() == meleeWeapon) {
                iterator.remove();
            }
        }

    }

    /**
     * List of last eleven commands getter
     */
    public void getHistory() {
        List<String> tmp = history;
        if (tmp.size() == 0) {
            console.writeStr("History is empty");
        } else {
            for (String command : tmp) {
                console.writeStr(command);
            }
        }
    }

    /**
     * Find and print collection element with minimum coordinates value
     */
    public void findMinElementByCoordinates() {
        SpaceMarine minCoordinatesSpaceMarine = Collections.min(fileCollection, new SpaceMarine.SpaceMarineCoordinatesComparator());
        console.writeStr(minCoordinatesSpaceMarine.toString());
    }

    /**
     * Sort and print the lis of collection elements health values
     */
    public void printSortedHealthFields() {
        ArrayList<SpaceMarine> tmp = new ArrayList<>(fileCollection);
        Collections.sort(tmp, new SpaceMarine.SpaceMarineHealthComparator());
        for (int i = tmp.size() - 1; i > -1; i--) {
            console.writeStr(String.valueOf(tmp.get(i).getHealth()));
        }
    }

    /**
     * Add new element to the collection if new element health value is more than max one in the collection
     * @param spaceMarine
     */

    public void addNewElementIfMax(SpaceMarine spaceMarine) {
        if (spaceMarine.getHealth() > Collections.max(fileCollection, new SpaceMarine.SpaceMarineHealthComparator()).getHealth()) {
            fileCollection.add(spaceMarine);
            console.writeStr("The new element was successfully added to the collection");
        } else {
            console.writeStr("Oops, the health field of the new element is less than the maximum one in the collection");
        }
    }

    /**
     * Deletes collection elements with health values less than the entered item
     * @param spaceMarine
     */
    public void removeLowerHealth(SpaceMarine spaceMarine) {
        int counter = 0;
        Iterator<SpaceMarine> iterator = fileCollection.iterator();
        while (iterator.hasNext()) {
            SpaceMarine sm = iterator.next();
            if (spaceMarine.getHealth() > sm.getHealth()) {
                iterator.remove();
                counter += 1;
            }
        }
        console.writeStr("Items were successfully deleted: " + counter);
    }

    /**
     * Displaying information about the collection
     */
    public void getInfo (){
        console.writeStr("Collection type: "+fileCollection.getClass().getName());
        console.writeStr("Creation date: "+ creationDate);
        console.writeStr("Collection size: "+ fileCollection.size());
    }
}

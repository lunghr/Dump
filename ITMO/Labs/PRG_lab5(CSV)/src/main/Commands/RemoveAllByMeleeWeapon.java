package main.Commands;

import main.Console.Console;
import main.Exceptions.WrongArgumentsException;
import main.Managers.CollectionManager;
import main.Models.MeleeWeapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command to delete all elements of the collection whose meleeWeapon field value is equivalent to the entered
 */
public class RemoveAllByMeleeWeapon extends Command{
    private CollectionManager collectionManager;

    public RemoveAllByMeleeWeapon(CollectionManager collectionManager, Console console){
        super ("remove_all_by_melee_weapon",
                "delete all items with the MeleeWeapon field equivalent to the specified " + "\n"+"\n"+
                "List of available Melee Weapon types:"+"\n"+"1 - CHAIN_SWORD,"+ "\n" +"2 - POWER_SWORD," +"\n"+
                        "3 - LIGHTING_CLAW,"+"\n"+"4 - POWER_BLADE", console);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] strings) {
        Integer tmp;
        MeleeWeapon x;
        List<String> strMeleeWeapon = new ArrayList<>(Arrays.asList("CHAIN_SWORD", "POWER_SWORD", "LIGHTING_CLAW", "POWER_BLADE"));
        try{
            if (strings.length != 1) {
                throw new WrongArgumentsException();
            }
            if (isInteger(strings[0])) {
                tmp = Integer.parseInt(strings[0]);
                if (!(tmp >= 1 && tmp <= 4)) {
                    console.writeStr("Index out of range: enter a number from 1 to 4");
                    return;
                } else {
                    x = Enum.valueOf(MeleeWeapon.class, strMeleeWeapon.get(tmp - 1));
                    collectionManager.removeSameMeleeweaponElements(Enum.valueOf(MeleeWeapon.class,
                            strMeleeWeapon.get(tmp-1)));
                }
            }
            if (!isMeleeWeapon(strings[0])){
                console.writeStr("This kind of Melee Weapon doesn't exist");
                return;
            }
            collectionManager.removeSameMeleeweaponElements(Enum.valueOf(MeleeWeapon.class, strings[0]));
            console.writeStr("Collection element with MeleeWeapon="+strings[0]+" is successfully removed");

        } catch (WrongArgumentsException e){
                console.writeStr(e.toString() +": remove_all_by_melee_weapon command requires only one Melee Weapon argument");
            }

    }
    private boolean isMeleeWeapon (String string){
        MeleeWeapon tmp;
        try{
            tmp = Enum.valueOf(MeleeWeapon.class, string);
        }catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean isInteger(String string){
        Integer tmp;
        try {
            tmp = Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }return true;
    }


}

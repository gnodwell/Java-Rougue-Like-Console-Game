package rogue;

import java.awt.Point;

public class Potion extends Magic implements Edible, Tossable {

    private Boolean canWear = false;
    private Boolean canThrow = true;
    private Boolean canEat = true;


    /**
     * constructor.
     * @param id id of the item
     * @param name name of the item
     * @param type type of the item
     * @param xyLocation point of the item
     * @param itemDescription description of the item
     */
    public Potion(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }

    /**
     * default constructor.
     */
    public Potion() {
    }


    /**
     * @return what happens when a potion that can be consumed is used.
     */
    @Override
    public String eat() {
        return "Sometyhing";
    }

    /**
     * @return what happens when a potion that is throw is used.
     */
    @Override
    public String toss() {
        return "Something";
    }

    /**
     * @return returns wether it can wear an item.
     */
    public Boolean getCanWear() {
        // return canWear;
        return false;
    }

    /**
     * @return wether an item can be thrown.
     */
    public Boolean getCanThrow() {
        // return canThrow;
        return true;
    }

    /**
     * @return wether an item can be ate.
     */
    public Boolean getCanEat() {
        // return canEat;
        return true;
    }




}

package rogue;

import java.awt.Point;

public class Ring extends Magic implements Wearable {

    private Boolean canWear = true;
    private Boolean canEat = false;
    private Boolean canThrow = false;

    /**
     * constructor.
     * @param id id of the item
     * @param name of the item
     * @param type of the item
     * @param xyLocation of the item
     * @param itemDescription of the item
     */
    public Ring(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }

    /**
     * default constructor.
     */
    public Ring() {
    }


    @Override
    public Boolean getCanWear() {
        // return canWear;
        return true;
    }

    @Override
    public Boolean getCanThrow() {
        // return canThrow;
        return false;
    }

    @Override
    public Boolean getCanEat() {
        // return canEat;
        return false;
    }

    /**
     * @return what happens when a ring that is wearable is used.
     */
    @Override
    public String wear() {
        return "something";
    }
}

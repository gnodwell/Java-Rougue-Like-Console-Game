package rogue;

import java.awt.Point;

public class Clothing extends Item implements Wearable {

    private Boolean canWear = true;
    private Boolean canThrow = false;
    private Boolean canEat = false;


    /**
     * constructor.
     * @param id id of the item
     * @param name name of the item
     * @param type type of the item
     * @param xyLocation Point of the item
     * @param itemDescription description of the item
     */
    public Clothing(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }

    /**
     * default constructor.
     */
    public Clothing() {

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
     * Returns the output if you put on a wearable item.
     * @return what happens when an items that is wearable is used
     */
    @Override
    public String wear() {
        return "Something";
    }
}

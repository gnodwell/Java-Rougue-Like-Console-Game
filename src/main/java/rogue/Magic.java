package rogue;

import java.awt.Point;

public class Magic extends Item {

    private boolean canWear = false;
    private boolean canThrow = false;
    private boolean canEat = false;



    /**
     * constructor.
     * @param id id of the item
     * @param name name of the item
     * @param type type of the item
     * @param xyLocation point of the item
     * @param itemDescription description
     */
    public Magic(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }

    /**
     * constructor.
     */
    public Magic() {
    }


    @Override
    public Boolean getCanWear() {
        // return canWear;
        return false;
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

}

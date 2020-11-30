package rogue;

import java.awt.Point;

public class Food extends Item implements Edible {

    private Boolean canWear = false;
    private Boolean canThrow = false;
    private Boolean canEat = true;



    /**
     * constructor.
     * @param id id of the item
     * @param name name of the item
     * @param type type of the item
     * @param xyLocation point of the item
     * @param itemDescription description of the item
     */
    public Food(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }

    /**
     * default constructor.
     */
    public Food() {
    }


    @Override
    public Boolean getCanWear() {
        return canWear;
    }

    @Override
    public Boolean getCanThrow() {
        return canThrow;
    }

    @Override
    public Boolean getCanEat() {
        return canEat;
    }



    /**
     * return for items that are edible and are used.
     * @return what happens when an edible item is consumed
     */
    public String eat() {
        return "something";
    }

}

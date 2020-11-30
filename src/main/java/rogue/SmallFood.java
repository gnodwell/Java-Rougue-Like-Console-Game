package rogue;

import java.awt.Point;

public class SmallFood extends Food implements Tossable {

    private Boolean canWear = false;
    private Boolean canThrow = true;
    private Boolean canEat = false;



    /**
     * constructor.
     * @param id id of the item
     * @param name name of the item
     * @param type type of the item
     * @param xyLocation point of the item
     * @param itemDescription decription of the item
     */
    public SmallFood(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }

    /**
     * default constructor.
     */
    public SmallFood() {
    }


    @Override
    public Boolean getCanWear() {
        // return canWear;
        return false;
    }

    @Override
    public Boolean getCanThrow() {
        // return canThrow;
        return true;
    }

    @Override
    public Boolean getCanEat() {
        // return canEat;
        return false;
    }



    /**
     * @return what happens when an food item that can be thrown is used.
     */
    @Override
    public String toss() {
        return "something";
    }
}

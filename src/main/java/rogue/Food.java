package rogue;

public class Food extends Item implements Edible {

    /**
     * return for items that are edible and are used.
     * @return what happens when an edible item is consumed
     */
    public String eat() {
        return "something";
    }

}

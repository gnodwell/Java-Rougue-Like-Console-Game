package rogue;

import java.util.HashMap;

import java.io.Serializable;

public class Inventory implements Serializable {

    private HashMap<Integer, Item> inventory;


    /**
     * Constructor.
     */
    public Inventory() {
        inventory = new HashMap<Integer, Item>();
    }

    /**
     * used to put an item is the inventory.
     * @param item (Item) Item object to be put into the hash map
     */
    public void addItem(Item item) {
        inventory.put(inventory.size(), item);
    }

    /**
     * used to retrieve a certain item from inventory.
     * @param idx (Integer) index of the item to be retrieve
     * @return inventory.get(idx) (Item) returns the item requested
     */
    public Item getItem(Integer idx) {
        return inventory.get(idx);
    }


    /**
     * used to get the entire inventory.
     * @return the entire HashMap
     */
    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }


    /**
     *
     * @return the size of the hashmap.
     */
    public int getSize() {
        return inventory.size();
    }

}

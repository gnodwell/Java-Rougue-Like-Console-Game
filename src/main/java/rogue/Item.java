package rogue;
import java.awt.Point;

/**
 * A basic Item class; basic functionality for both consumables and equipment
 */
public class Item  {

    private int itemId;
    private String itemName;
    private String itemType;
    private Point itemLoc;
    private Room currentRoom;
    private Character chr;
    private String itemDesc;


    //Constructors
    public Item() {
        
    }

    public Item(int id, String name, String type, Point xyLocation) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);

    }
    
    // Getters and setters


    public int getId() {
        return itemId;
       
    }


    public void setId(int id) {
        itemId = id;
    }


    public String getName() {

        return itemName;
    }


    public void setName(String name) {
        itemName = name;
    }


    public String getType() {
        return itemType;

    }


    public void setType(String type) {
        itemType = type;
    }
    

    public Character getDisplayCharacter() {
        return chr;
        
    }


    public void setDisplayCharacter(Character newDisplayCharacter) {
        chr = newDisplayCharacter;
    }


    public String getDescription() {
        return itemDesc;
     
    }


    public void setDescription(String newDescription) {
     itemDesc = newDescription;
    }


    public Point getXyLocation() {

        return itemLoc;
     
    }

    
    public void setXyLocation(Point newXyLocation) {
        itemLoc = newXyLocation;
    }


    public Room getCurrentRoom() {
        return currentRoom;
        
    }


    public void setCurrentRoom(Room newCurrentRoom) {
        currentRoom = newCurrentRoom;
    }
}

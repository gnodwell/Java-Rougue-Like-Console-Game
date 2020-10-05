package rogue;
import java.awt.Point;

/**
 * A basic Item class; basic functionality for both consumables and equipment
 */
public class Item  {

    int itemId;
    String itemName;
    String itemType;
    Point itemLoc;
    Room currentRoom;


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
        return null;
        
    }


    public void setDisplayCharacter(Character newDisplayCharacter) {
        
    }


    public String getDescription() {
        return null;
     
    }


    public void setDescription(String newDescription) {
     
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

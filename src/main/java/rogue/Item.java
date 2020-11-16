package rogue;
import java.awt.Point;

/**
A basic Item class; basic functionality for both consumables and equipment.
*/
public class Item  {

    private int itemId;
    private String itemName;
    private String itemType;
    private Point itemLoc;
    private Room currentRoom;
    private Character chr;
    private String itemDesc;
    private String description;


/**
* Constructors.
*/
    public Item() {
    }


/**
setters.
@param id  (int) id value of the item to be added
@param name  (String)  name of the item to be added
@param type   (String)  type of item (i.e consumable, equipment)
@param xyLocation   (Point)   x,y coordinates of the item in the room
@param itemDescription (String) description of the item
*/
    public Item(int id, String name, String type, Point xyLocation, String itemDescription) {
        setId(id);
        setName(name);
        setType(type);
        setXyLocation(xyLocation);
        setDescription(itemDescription);
    }



/**
returns the id of the requested item.
@return itemId  (int) id of item
*/
    public int getId() {
        return itemId;
    }


/**
sets itemId to given id.
@param id   (int) values of the item to be added
*/
    public void setId(int id) {
        itemId = id;
    }


/**
returns name of the item.
@return itemName  (String) name of the item
*/
    public String getName() {
        return itemName;
    }


/**
sets name.
@param name  (String) name of the item
*/
    public void setName(String name) {
        itemName = name;
    }


/**
returns the type of the item.
@return itemType (String)  gives the type of item
*/
    public String getType() {
        return itemType;

    }


/**
sets the type of the item.
@param type   (String) contains the type of the item
*/
    public void setType(String type) {
        itemType = type;
    }


/**
returns object of type Character.
@return chr   (Character) holds the class of type Character
*/
    public Character getDisplayCharacter() {
        return chr;
    }



/**
sets the display character.
@param newDisplayCharacter   (Character)  used to set the new display character
*/
    public void setDisplayCharacter(Character newDisplayCharacter) {
        chr = newDisplayCharacter;
    }


/**
retunrs item discription.
@return itemDesc ()
*/
    public String getDescription() {
        return itemDesc;
    }


/**
sets the item description.
@param newDescription   (String)  contains the description of the item
*/
    public void setDescription(String newDescription) {
     itemDesc = newDescription;
    }

/**
returns the coordinate of the item.
@return itemLoc  (Point) returns the point of the item
*/
    public Point getXyLocation() {
        return itemLoc;
    }

/**
sets the item location.
@param newXyLocation
*/
    public void setXyLocation(Point newXyLocation) {
        itemLoc = newXyLocation;
    }

/**
returns the current room that you are in.
@return currentRoom  (Room)  returns the room that the program is currently in
*/
    public Room getCurrentRoom() {
        return currentRoom;
    }


/**
sets the current room.
@param newCurrentRoom (Room)  contains the new room
 */
    public void setCurrentRoom(Room newCurrentRoom) {
        currentRoom = newCurrentRoom;
    }
}

package rogue;
// import org.json.simple.JSONObject;

// import java.lang.reflect.Array;
import java.util.ArrayList;
// import java.util.Map;
import java.awt.Point;
import java.util.HashMap;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 *
 *
 */
public class Room  {

    private int width;
    private int height;
    private int id;
    private ArrayList<Item> items;
    private Player currentPlayer;
    // private HashMap<String, Integer> doors;
    private ArrayList<Door> doors;
    private HashMap<String, String> symbols;
    private Boolean start;


/**
default constructor.
 */
    public Room() {
        items = new ArrayList<Item>();
        // doors = new HashMap<String, Integer>();
        doors = new ArrayList<Door>();
    }


/**
returns the width of the room.
@return width (int) returns interger value of the rooms width
 */
    public int getWidth() {
        return width;
    }

    public ArrayList<Door> getDoorList() {
        return doors;
    }
/**
 * sets the symbols to be used to build the room.
 * @param newSymbols (HashMap<String, String>) contains the key of the symbol and character used to reprsent symbol
 */
    public void  setSymbols(HashMap<String, String> newSymbols) {
        symbols = newSymbols;
    }


/**
retuns the symbols Hashmap.
@return symbols (HashMap<String, String>) contains the key of the symbol and character used to reprsent symbol
 */
    public HashMap<String, String> getSymbols() {
        return symbols;
    }


/**
sets width of the room.
@param newWidth (int) width of new room
 */
    public void setWidth(int newWidth) {
        width = newWidth;
    }


/**
returns height of the room.
@return height (int) returns the height of the room
 */
    public int getHeight() {
        return height;
    }


/**
sets the height of the new room.
@param newHeight (int) contains the new height of the room
 */
    public void setHeight(int newHeight) {
        height = newHeight;
    }


/**
gets id of the room
@return id (int) id of the room
 */
    public int getId() {
        return id;
    }


/**
sets the id of the room
@param newId (int) room id
 */
    public void setId(int newId) {
        id = newId;
    }


/**
returns an array list containing all the items in a room's informations.
@return items (ArrayList<Item>) contains information on the items correlationg to the room
 */
    public ArrayList<Item> getRoomItems() {
        return items;
    }


/**
sets the items information inside the room.
@param newRoomItems (ArrayList<Item>) contains information on the items
 */
    public void setRoomItems(ArrayList<Item> newRoomItems) {
        items = newRoomItems;
    }


/**
gets the players information correlating to the room.
@return currentPlayer (Player) returns players information
 */
    public Player getPlayer() {
        return currentPlayer;

    }


/**
sets the players information.
@param newPlayer (Player) contains the players information
 */
    public void setPlayer(Player newPlayer) {
        currentPlayer = newPlayer;
    }


/**
gets the doors location.
 * @param direction (String) contains which wall the door is on
 * @return doors.get(direction.toUpperCase()) (int) returns location on the wall
 */
    public int getDoor(String direction) {
        // return doors.get(direction.toUpperCase()); //change this, no longer hash map
        for (Door door: doors) {
            if (door.getDirection().equals(direction)) {
                return door.getLocation();
            }
        }
        
        return -1; 
    }

/*
direction is one of NSEW.
location is a number between 0 and the length of the wall
*/


public void setDoorConnection( String direction, Integer doorConnection) {
    for (Door d: doors) {
        if (d.getDirection().equals(direction)) {
            d.setConnectedTo(doorConnection);
        }
    }
}


/**
sets the doors information.
@param direction (String) contains which wall the door is on
@param location (int) contains where the door is
 */
    public void setDoor(String direction, int location) {
        // doors.put(direction.toUpperCase(), location);
        Door door = new Door(direction, location);
        doors.add(door);
    }

/**
checks if the player is located inside the room.
@return true (boolean) returns if player is inside a room or false (boolean) returns if player is outside a room
 */
    public boolean isPlayerInRoom() {
        Point playerLoc = currentPlayer.getXyLocation();

        int xLoc = (int) playerLoc.getX();
        int yLoc = (int) playerLoc.getY();

        return ((xLoc < width + 1) && (yLoc < height + 1));

        // if ((xLoc < width + 1) && (yLoc < height + 1)) {
        //     return true;
        // } else {
        //     return false;
        // }
    }


/**
sets the starting position of the player.
 * @param playerStart (Boolean) contains wether the player has started or not
 */
    public void setStart(Boolean playerStart) {
        start = playerStart;
    }


    private Boolean containsDoorKey(String dir) {
        for (Door d: doors) {
            if (d.getDirection().equals(dir)) {
                return true;
            }
        }

        return false;
    }



    public Room getOtherRoom(Door d) {
        
        return null;
    }


/**
Produces a string that can be printed to produce an ascii rendering of the room and all of its contents.
@return (String) String representation of how the room looks
*/
    public String displayRoom() {

        int xVal;
        int yVal;
        String roomDescription = "<---- [ROOM " + id + "] ---->\n";
        int flag = 0;

        if (start) {
            roomDescription = roomDescription.concat("-Starting Room \n");
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i == 1) && (j == 1)) {
                    roomDescription = roomDescription.concat((symbols.get("PLAYER")));
                } else if (i == 0) {
                    if (containsDoorKey("N")) {
                        if (j == getDoor("N")) {
                            roomDescription = roomDescription.concat(symbols.get("DOOR"));
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                        }
                    } else {
                        roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                    }
                } else if (i == height - 1) {
                    if (containsDoorKey("S")) {
                        if (j == getDoor("S")) {
                            roomDescription = roomDescription.concat(symbols.get("DOOR"));
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                        }
                    } else {
                        roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                    }
                } else {
                    if (j == 0) {
                        if (containsDoorKey("E")) {
                            if (i == getDoor("E")) {
                                roomDescription = roomDescription.concat(symbols.get("DOOR"));
                            } else {
                                roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                            }
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                        }
                    } else if (j == width - 1) {
                        if (containsDoorKey("W")) {
                            if (i == getDoor("W")) {
                                roomDescription = roomDescription.concat(symbols.get("DOOR"));
                            } else {
                                roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                            }
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                        }
                    } else {
                        if (items.isEmpty()) {
                            for (Item item : items) {
                                Point point;
                                point = item.getXyLocation();
                                if ((j == (int) point.getX()) && (i == (int) point.getY()) && (flag == 0)) {
                                    roomDescription = roomDescription.concat(symbols.get("ITEM"));
                                    flag = 1;
                                }
                            }
                            if (flag == 0) {
                                roomDescription = roomDescription.concat(symbols.get("FLOOR"));
                            }
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("FLOOR"));
                        }
                        flag = 0;
                    }
                }
            }
            roomDescription = roomDescription.concat("\n");
        }

        return roomDescription;

    }




    public void addItem(Item toAdd) throws ImpossiblePositionException, NoSuchItemException   {

        Point xyLoc = toAdd.getXyLocation();
        int xLoc = (int)xyLoc.getX();
        int yLoc = (int)xyLoc.getY();
        int catcher = 0;

        
        if (xLoc == 0 || xLoc == this.getWidth() || yLoc == 0 || yLoc == this.getHeight()) {
            throw new ImpossiblePositionException();
        }
        
        for (Item it: items) {
            if (xyLoc.equals(it.getXyLocation())) {
                throw new ImpossiblePositionException();
            }
            if (it.getId() == toAdd.getId()) {
                catcher = 1;
            }
            if (catcher != 1) {
                throw new NoSuchItemException("Item does not exist");
            }
        }

        // if (xyLoc.equals(currentPlayer.getXyLocation())) {
        //     System.out.println("hmmmmm");
        //     throw new ImpossiblePositionException();
        // }


        items.add(toAdd);
    }


}

package rogue;
// import org.json.simple.JSONObject;

// import java.lang.reflect.Array;
import java.util.ArrayList;
// import java.util.Map;
import java.awt.Point;
import java.util.HashMap;

import java.io.Serializable;


/**
 * doors out, etc.
 * A room within the dungeon - contains monsters, treasure,
 *
 *
 */
public class Room implements Serializable {

    private int width;
    private int height;
    private int id;
    private ArrayList<Item> items;
    private Player currentPlayer;
    private HashMap<String, Door> doorsHash;
    // private ArrayList<Door> doors;
    private HashMap<String, String> symbols;
    private Boolean start;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int ELEVEN = 11;
    private static final int TWELVE = 12;
    private static final int THIRTEEN = 13;
    private static final int FOURTEEN = 14;
    private static final int FIFTEEN = 15;


/**
default constructor.
 */
    public Room() {
        items = new ArrayList<Item>();
        doorsHash = new HashMap<String, Door>();
        // doors = new ArrayList<Door>();
        currentPlayer = null;
    }

/**
gets the starting room.
@return start (Boolean) returns true if the room is the starting room and false if it is not
 */
    public Boolean getStart() {
        return start;
    }


/**
gets the door on a certain wall.
@param direction (String) what wall you are looking for the door on
@return d (Door) returns the door object on that wall if found, else returns null
 */
    public Door getDoorObject(String direction) {

        // for (Door d: doors) {
        //     if (d.getDirection().equals(direction)) {
        //         return d;
        //     }
        // }
        if (doorsHash.containsKey(direction)) {
            return doorsHash.get(direction);
        }

        return null;
    }


/**
gets the list of doors.
@return doors (Door) returns a list of door objects */
// public ArrayList<Door> getDoorList() {
//     return doors;
// }
    public HashMap<String, Door> getDoorHash() {
        return doorsHash;
    }




    /**
    gets the doors location.
    * @param direction (String) contains which wall the door is on
    * @return doors.get(direction.toUpperCase()) (int) returns location on the wall
    */
    public int getDoor(String direction) {
        // return doors.get(direction.toUpperCase()); //change this, no longer hash map

        // for (Door door: doors) {
        //     if (door.getDirection().equals(direction)) {
        //         return door.getLocation();
        //     }
        // }

        if (doorsHash.containsKey(direction)) {
            Door d = doorsHash.get(direction);
            return d.getLocation();
        }

        return -1;
    }

    /**
     * verifies the doors in the room.
     * @return true if it is verified
     * @throws NotEnoughDoorsException
     */
    public boolean verifyRoom() throws NotEnoughDoorsException {
        if (!doorsHash.isEmpty()) {
            return true;
        } else {
            // return false;
            throw new NotEnoughDoorsException();
        }
    }


    /**
    sets the door connections.
    @param direction (String) which direction the door is on
    @param doorConnection (Integer) id of the room the door is connection to */
    public void setDoorConnection(String direction, Integer doorConnection) {
        Door d = doorsHash.get(direction);
        d.setConnectedTo(doorConnection);
    }


    /**
    sets the doors information.
    @param direction (String) contains which wall the door is on
    @param location (int) contains where the door is
    */
    public void setDoor(String direction, int location) {
        // doors.put(direction.toUpperCase(), );
        Door door = new Door(direction, location);
        // doors.add(door);
        doorsHash.put(direction.toUpperCase(), door);

    }


    private Boolean containsDoorKey(String dir) {
        // for (Door d: doors) {
        //     if (d.getDirection().equals(dir)) {
        //         return true;
        //     }
        // }
        // return false;

        return (doorsHash.containsKey(dir));
    }



/**
returns the width of the room.
@return width (int) returns interger value of the rooms width
 */
    public int getWidth() {
        return width;
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
gets id of the room.
@return id (int) id of the room
 */
    public int getId() {
        return id;
    }


/**
sets the id of the room.
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
     * remove an item from the room.
     * @param itemTBR the item to be remove
     */
    public void removeItem(Item itemTBR) {
        items.remove(itemTBR);
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



/*
direction is one of NSEW.
location is a number between 0 and the length of the wall
*/


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


/**
gets the other room that the door is connected to.
@param d (door) what door you are looking for the connection
@return d.getOtherRoom(this) (Door) returns the linked room to the one you are currently in */
    public Room getOtherRoom(Door d) {

        return d.getOtherRoom(this);
    }


/**
Produces a string that can be printed to produce an ascii rendering of the room and all of its contents.
@return (String) String representation of how the room looks
*/
    public String displayRoom() {


        String roomDescription = "<---- [ROOM " + id + "] ---->\n";
        int flag = 0;

        int pX = 1;
        int pY = 1;
        if (currentPlayer != null) {
            Point p = currentPlayer.getXyLocation();
            //p.setLocation(4, 4);
            pX = (int) p.getX();
            pY = (int) p.getY();

        }

        if (start) {
            roomDescription = roomDescription.concat("-Starting Room \n");
        } else {
            roomDescription = roomDescription.concat("               \n");
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i == pY) && (j == pX)) {
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
                        if (containsDoorKey("W")) {
                            if (i == getDoor("W")) {
                                roomDescription = roomDescription.concat(symbols.get("DOOR"));
                            } else {
                                roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                            }
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                        }
                    } else if (j == width - 1) {
                        if (containsDoorKey("E")) {
                            if (i == getDoor("E")) {
                                roomDescription = roomDescription.concat(symbols.get("DOOR"));
                            } else {
                                roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                            }
                        } else {
                            roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                        }
                    } else {
                        if (!items.isEmpty()) {
                            for (Item item : items) {
                                Point point;
                                point = item.getXyLocation();
                                if ((j == (int) point.getX()) && (i == (int) point.getY()) && (flag == 0)) {
                                    String itemName = item.getType().toUpperCase();
                                    roomDescription = roomDescription.concat(symbols.get(itemName));
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



/**
adds item that has been parsed to an array list of items.
@param toAdd (Item) contains the items to be added that have been parsed form RougueParser
@throws ImpossiblePositionException thrown when the position is impossible for the player to be in
@throws NoSuchItemException thrown when trying to add an item that does not exist
 */
    public void addItem(Item toAdd) throws ImpossiblePositionException, NoSuchItemException {

        Point xyLoc = toAdd.getXyLocation();
        int xLoc = (int) xyLoc.getX();
        int yLoc = (int) xyLoc.getY();
        // int catcher = 0;


        if (xLoc == 0 || xLoc == this.getWidth() - 1 || yLoc == 0 || yLoc == this.getHeight() - 1) {
            throw new ImpossiblePositionException();
        }

        if (currentPlayer != null) { /* Change */
            if (currentPlayer.getXyLocation().equals(xyLoc)) {
                throw new ImpossiblePositionException();
            }
        }

        int[] idList = {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
            NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN};
        int match = 0;
        for (int i: idList) {
            if (i == toAdd.getId()) {
                match = 1;
            }
        }
        if (match == 0) {
            throw new NoSuchItemException();
        }


        for (Item it: items) {
            if (xyLoc.equals(it.getXyLocation())) {
                throw new ImpossiblePositionException();
            }
        }



        items.add(toAdd);
    }


}

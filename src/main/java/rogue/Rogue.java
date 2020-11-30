package rogue;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
// import java.util.concurrent.ConcurrentNavigableMap;

// import javax.print.attribute.standard.PrinterInfo;

import java.util.HashMap;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;

import java.awt.Point;
import java.io.Serializable;
// import java.nio.charset.MalformedInputException;

// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;

/**
 * creates the game.
 */
public class Rogue implements Serializable {
    public static final char UP = 'h';
    public static final char DOWN = 'l';
    public static final char LEFT = 'j';
    public static final char RIGHT = 'k';
    public static final char EAT = 'e';
    public static final char WEAR = 'w';
    public static final char TOSS = 't';
    private String nextDisplay = "-----\n|.@..|\n|....|\n-----";
    private RogueParser parser;
    private Inventory playerInv;
    private Player player;
    private ArrayList<Room> myRooms;
    private ArrayList<Item> myItems;
    // private ArrayList<Door> doorsInTheRoom;
    private HashMap<String, Door> doorsInTheRoom;
    private HashMap<String, String> symbolsMap;
    private Room currentRoom; //current room that is being displayed.


/**
 * constructor.
 * @param theDungeonInfo (RogueParser) gets info to be used
 */
    public Rogue(RogueParser theDungeonInfo) {
        parser = theDungeonInfo;
        playerInv = new Inventory();
        myRooms = new ArrayList<Room>();
        myItems = new ArrayList<Item>();
        symbolsMap = new HashMap<String, String>();
        setAllSymbols();
        Map roomInfo = parser.nextRoom();
        while (roomInfo != null) {
            addRoom(roomInfo);
            roomInfo = parser.nextRoom();
        }
        Map itemInfo = parser.nextItem();
        while (itemInfo != null) {
            addItem(itemInfo);
            itemInfo = parser.nextItem();
        }
        linkRooms();
        setStartingPosition();
    }


    /**
     * default constructor.
     */
    public Rogue() {
        playerInv = new Inventory();
        myRooms = new ArrayList<Room>();
        myItems = new ArrayList<Item>();
        symbolsMap = new HashMap<String, String>();
    }


    /**
     * sets the rooms.
     * @param newRooms
     */
    public void setRooms(ArrayList<Room> newRooms) {
        myRooms = newRooms;
    }

    /**
     * gets the players inventory.
     * @return playerInv.getInventory() this is the players inventory
     */
    public HashMap<Integer, Item> getInventory() {
        return playerInv.getInventory();
    }


    /**
     * sets the starting room.
     */
    private void setStartingPosition() {
        for (Room r: myRooms) {
            if (r.getStart()) {
                currentRoom = r;
            }
        }
    }


    /**
     * sets the starting display.
     */
    private void setStartingDisplay() {
        for (Room r: myRooms) {
            if (r.getStart()) {
                nextDisplay = r.displayRoom();
            }
        }
    }


/**
 * sets the player.
 * @param thePlayer (Player) contains information on the player
 */
    public void setPlayer(Player thePlayer) {
        player = thePlayer;
        currentRoom.setPlayer(player);
        setStartingDisplay(); //fsrp
    }



/**
creates a map that contains all symolbols used to print the displays.
 */
    public void setAllSymbols() {
        symbolsMap.put("NS_WALL", parser.getSymbol("NS_WALL").toString());
        symbolsMap.put("EW_WALL", parser.getSymbol("EW_WALL").toString());
        symbolsMap.put("DOOR", parser.getSymbol("DOOR").toString());
        symbolsMap.put("PLAYER", parser.getSymbol("PLAYER").toString());
        symbolsMap.put("GOLD", parser.getSymbol("GOLD").toString());
        symbolsMap.put("POTION", parser.getSymbol("POTION").toString());
        symbolsMap.put("SCROLL", parser.getSymbol("SCROLL").toString());
        symbolsMap.put("CLOTHING", parser.getSymbol("CLOTHING").toString());
        symbolsMap.put("FOOD", parser.getSymbol("FOOD").toString());
        symbolsMap.put("RING", parser.getSymbol("RING").toString());
        symbolsMap.put("SMALLFOOD", parser.getSymbol("SMALLFOOD").toString());
        symbolsMap.put("PASSAGE", parser.getSymbol("PASSAGE").toString());
        symbolsMap.put("FLOOR", parser.getSymbol("FLOOR").toString());
    }

/**
 * gets players.
 * @return returns player
 */
    public Player getPlayer() {
        return player;
    }


/**
 * gets an arraylist of the rooms that contains all information inside the rooms.
 * @return null
 */
    public ArrayList<Room> getRooms() {
        return myRooms;

    }


/**
 * gets information on the items in the rooms.
 * @return returns the array list of the items
 */
    public ArrayList<Item> getItems() {
        return myItems;
    }


    /**
     *
     * @param item item to be thorwn back in.
     * @return item
     */
    public Item thorwBackIn(Item item) {
        return item;
    }



/**
 * displays all the rooms in the dungeon.
 * @return null
 */
    public String displayAll() {
        String fullDesc = "";
        for (Room r: myRooms) {
            fullDesc = fullDesc.concat(r.displayRoom());
            fullDesc = fullDesc.concat("\n\n");
        }
        return fullDesc;
    }



/**
 * makes the player move through the dungeon.
 * @param input (char) which direction to move the player
 * @return string
 * @throws InvalidMoveException used if an invalid move is made
 */
    public String makeMove(char input) throws InvalidMoveException {
        Point newLocation = new Point();
        Point currentLocation = player.getXyLocation();
        int pX = (int) currentLocation.getX();
        int pY = (int) currentLocation.getY();
        player.setCurrentRoom(currentRoom);
        switch (input) {
            case LEFT:
                goLeft(pX, pY, newLocation);
            break;
            case UP:
                goUp(pX, pY, newLocation);
            break;
            case DOWN:
                goDown(pX, pY, newLocation);
            break;
            case RIGHT:
                goRight(pX, pY, newLocation);
            break;
                default:
            break;
        }
        nextDisplay = currentRoom.displayRoom();
        return "That's a lovely move: " +  Character.toString(input);
    }

    /**
     * makes a move left.
     * @param pX players x position
     * @param pY playes y position
     * @param newLocation players newLocation after move
     * @throws InvalidMoveExceptio
     */
    public void goLeft(int pX, int pY, Point newLocation) throws InvalidMoveException {
        int flag0 = 0;
        if (pX == 1) {
            int doorLoc = currentRoom.getDoor("W");
            if (doorLoc == pY) {
                goThroughDoorLeft(pX, pY, newLocation);
                flag0 = 1;
            } else {
                flag0 = 1;
                throw new InvalidMoveException("You've hit a wall !");
            }
        }
        if (flag0 != 1) {
            newLocation.setLocation(pX - 1, pY);
            player.setXyLocation(newLocation);
            checkItemPickUp();
        }
    }

    /**
     * makes a move right.
     * @param pX players x position
     * @param pY playes y position
     * @param newLocation players newLocation after move
     * @throws InvalidMoveExceptio
     */
    public void goRight(int pX, int pY, Point newLocation) throws InvalidMoveException {
        int flag3 = 0;
        if (pX == currentRoom.getWidth() - 2) {
            int doorLoc = currentRoom.getDoor("E");
            if (doorLoc == pY) {
                goThroughDoorRight(pX, pY, newLocation);
                flag3 = 1;
            } else {
                flag3 = 1;
                throw new InvalidMoveException("You've hit a wall !");
            }
        }
        if (flag3 != 1) {
            newLocation.setLocation(pX + 1, pY);
            player.setXyLocation(newLocation);
            checkItemPickUp();
        }
    }

    /**
     * makes a move up.
     * @param pX players x position
     * @param pY playes y position
     * @param newLocation players newLocation after move
     * @throws InvalidMoveExceptio
     */
    public void goUp(int pX, int pY, Point newLocation) throws InvalidMoveException {
        int flag1 = 0;
        if (pY == 1) {
            int doorLoc = currentRoom.getDoor("N");
            if (doorLoc == pX) {
                goThroughDoorUp(pX, pY, newLocation);
                flag1 = 1;
            } else {
                flag1 = 1;
                throw new InvalidMoveException("You've hit a wall !");
            }
        }
        if (flag1 != 1) {
            newLocation.setLocation(pX, pY - 1);
            player.setXyLocation(newLocation);
            checkItemPickUp();
        }
    }

    /**
     * makes a move down.
     * @param pX players x position
     * @param pY playes y position
     * @param newLocation players newLocation after move
     * @throws InvalidMoveExceptio
     */
    public void goDown(int pX, int pY, Point newLocation) throws InvalidMoveException {
        int flag2 = 0;
        if (pY == currentRoom.getHeight() - 2) {
            int doorLoc = currentRoom.getDoor("S");
            if (doorLoc == pX) {
                goThroughDoorDown(pX, pY, newLocation);
                flag2 = 1;
            } else {
                flag2 = 1;
                throw new InvalidMoveException("You've hit a wall !");
            }
        }
        if (flag2 != 1) {
            newLocation.setLocation(pX, pY + 1);
            player.setXyLocation(newLocation);
            checkItemPickUp();
        }
    }

    /**
     * goes through a door on the west wall.
     * @param pX players x positiion
     * @param pY players y position
     * @param newLocation new point after move
     */
    public void goThroughDoorLeft(int pX, int pY, Point newLocation) {
        Door d = currentRoom.getDoorObject("W");
        Room otherRoom = d.getOtherRoom(currentRoom);
        player.setCurrentRoom(otherRoom);
        pX = otherRoom.getWidth() - 2;
        pY = otherRoom.getDoor("E");
        newLocation.setLocation(pX, pY);
        player.setXyLocation(newLocation);
        currentRoom = otherRoom;
        currentRoom.setPlayer(player);
    }

    /**
     * goes through a door on the north wall.
     * @param pX players x positiion
     * @param pY players y position
     * @param newLocation new point after move
     */
    public void goThroughDoorUp(int pX, int pY, Point newLocation) {
        Door d = currentRoom.getDoorObject("N");
        Room otherRoom = d.getOtherRoom(currentRoom);
        player.setCurrentRoom(otherRoom);
        pX = otherRoom.getDoor("S");
        pY = otherRoom.getHeight() - 2;
        newLocation.setLocation(pX, pY);
        player.setXyLocation(newLocation);
        currentRoom = otherRoom;
        currentRoom.setPlayer(player);
    }

    /**
     * goes through a door on the South wall.
     * @param pX players x positiion
     * @param pY players y position
     * @param newLocation new point after move
     */
    public void goThroughDoorDown(int pX, int pY, Point newLocation) {
        Door d = currentRoom.getDoorObject("S");
        Room otherRoom = d.getOtherRoom(currentRoom);
        player.setCurrentRoom(otherRoom);
        pX = otherRoom.getDoor("N");
        pY = 1;
        newLocation.setLocation(pX, pY);
        player.setXyLocation(newLocation);
        currentRoom = otherRoom;
        currentRoom.setPlayer(player);
    }

    /**
     * goes through a door on the East wall.
     * @param pX players x positiion
     * @param pY players y position
     * @param newLocation new point after move
     */
    public void goThroughDoorRight(int pX, int pY, Point newLocation) {
        Door d = currentRoom.getDoorObject("E");
        Room otherRoom = d.getOtherRoom(currentRoom);
        player.setCurrentRoom(otherRoom);
        pX = 1;
        pY = otherRoom.getDoor("W");
        newLocation.setLocation(pX, pY);
        player.setXyLocation(newLocation);
        currentRoom = otherRoom;
        currentRoom.setPlayer(player);
    }

    /**
     * picks up an item of the ground.
     */
    public void checkItemPickUp() {
        for (Item i: myItems) {
            if (i.getCurrentRoom().equals(player.getCurrentRoom())) {
                if (i.getXyLocation().equals(player.getXyLocation())) {
                    playerInv.addItem(i);
                    System.out.println("picked up"
                    + playerInv.getItem(playerInv.getInventory().size() - 1).getName());
                }
            }
        }
        myItems.remove(playerInv.getItem(playerInv.getSize() - 1));
        currentRoom.removeItem(playerInv.getItem(playerInv.getSize() - 1));
    }


/**
gets new postiong after a move has been made.
@param currentPos (Point) holds the current position
@param input (char) holds the move to be made
@return newPos (Point) returns new postion */
    public Point getNewPos(Point currentPos, char input) {
        Point newPos = new Point();
        int x = (int) currentPos.getX();
        int y = (int) currentPos.getY();
        if (input == UP) {
            newPos.setLocation(x, y - 1);
        }
        if (input == DOWN) {
            newPos.setLocation(x, y +  1);
        }
        if (input == RIGHT) {
            newPos.setLocation(x + 1, y);
        }
        if (input == LEFT) {
            newPos.setLocation(x - 1, y);
        }
        return newPos;
    }


    private void linkRooms() {
        for (Room r: myRooms) {
            doorsInTheRoom = r.getDoorHash();
            for (Door d: doorsInTheRoom.values()) {
                int connectionLoc = d.getConnectedTo();
                for (Room r2: myRooms) {
                    if (r2.getId() == connectionLoc) {
                        d.connectRoom(r);
                        d.connectRoom(r2);
                    }
                }
            }
        }
    }

/**
 * gets next room to display.
 * @return nextDisplay (String) next room to be displayed
 */
    public String getNextDisplay() {
        return nextDisplay;
    }


/**
 * adds room.
 * @param toAdd (Map<String , String>) information on the next room stored in a map
 */
    public void addRoom(Map<String, String> toAdd) {
        Room newRoom = new Room();
        setConstants(toAdd, newRoom);
        setDoorConnections(toAdd, newRoom);
        try {
            myRooms.add(newRoom);
        } catch (NullPointerException e) {
            System.out.print("Came accross a null pointer when tring to add room");
        }
    }

    /**
     * sets the constants for the rooms.
     * @param toAdd this is the map from the parser
     * @param newRoom this is the new room being created
     */
    public void setConstants(Map<String, String> toAdd, Room newRoom) {
        newRoom.setWidth(Integer.parseInt(toAdd.get("width")));
        newRoom.setHeight(Integer.parseInt(toAdd.get("height")));
        newRoom.setId(Integer.parseInt(toAdd.get("id")));
        newRoom.setStart(Boolean.parseBoolean(toAdd.get("start")));
        newRoom.setSymbols(symbolsMap);
        newRoom.setPlayer(player);
    }

    /**
     * sets the door connections.
     * @param toAdd this is the map from the parser
     * @param newRoom this is the new room being created
     */
    public void setDoorConnections(Map<String, String> toAdd, Room newRoom) {
        if (!toAdd.get("E").equals("-1")) {
            newRoom.setDoor("E", Integer.parseInt(toAdd.get("E")));
            newRoom.setDoorConnection("E", Integer.parseInt(toAdd.get("E_con_room")));
        }
        if (!toAdd.get("N").equals("-1")) {
            newRoom.setDoor("N", Integer.parseInt(toAdd.get("N")));
            newRoom.setDoorConnection("N", Integer.parseInt(toAdd.get("N_con_room")));
        }
        if (!toAdd.get("S").equals("-1")) {
            newRoom.setDoor("S", Integer.parseInt(toAdd.get("S")));
            newRoom.setDoorConnection("S", Integer.parseInt(toAdd.get("S_con_room")));
        }
        if (!toAdd.get("W").equals("-1")) {
            newRoom.setDoor("W", Integer.parseInt(toAdd.get("W")));
            newRoom.setDoorConnection("W", Integer.parseInt(toAdd.get("W_con_room")));
        }
    }



/**
 * adds item to room.
 * @param toAdd (Map<String, String>) information on the items to be added
 */
    public void addItem(Map<String, String> toAdd) {
        int itemId = Integer.parseInt(toAdd.get("id"));
        String name = toAdd.get("name");
        String type = toAdd.get("type");          //info
        String des = toAdd.get("description");
        int catcher = 1;
        Random rand = new Random();
        if (toAdd.containsKey("x")) {
            Point p = new Point();
            p.setLocation(Integer.parseInt(toAdd.get("x")), Integer.parseInt(toAdd.get("y")));
            Item newItem = makeItem(itemId, name, type, p, des);
            int roomId = Integer.parseInt(toAdd.get("room"));
            setRoomItems(rand, newItem, roomId, catcher);
        }
    }


    /**
     * adds items to rooms.
     * @param rand random number generator
     * @param newItem item being added to the room
     * @param roomId current room
     * @param catcher used for logic
     */
    public void setRoomItems(Random rand, Item newItem, int roomId, int catcher) {
        for (Room r: myRooms) {
            if (r.getId() == roomId) {
                newItem.setCurrentRoom(r);
                while (catcher == 1) {
                    try {
                        r.addItem(newItem);
                        catcher = 0;
                    } catch (ImpossiblePositionException e) {
                        catcher = 1;
                        newItem.setXyLocation(new Point(rand.nextInt(r.getWidth()), rand.nextInt(r.getHeight())));
                    } catch (NoSuchItemException e) {
                        System.out.println("Invalid Item");
                    }
                }
            }
        }
        myItems.add(newItem);
    }


    /**
     * Creates an item after parsing.
     * @param id id of the item
     * @param name name of the item
     * @param type type of the item
     * @param p point of the item
     * @param des description of the item
     * @return item to be added to the list
     */
    public Item makeItem(Integer id, String name, String type, Point p, String des) {
        if (type.equals("Scroll")) {
            return (new Magic(id, name, type, p, des));
        } else if (type.equals("Clothing")) {
            return (new Clothing(id, name, type, p, des));
        } else if (type.equals("Ring")) {
            return (new Ring(id, name, type, p, des));
        } else if (type.equals("Food")) {
            return (new Food(id, name, type, p, des));
        } else if (type.equals("SmallFood")) {
            return (new SmallFood(id, name, type, p, des));
        } else if (type.equals("Potion")) {
            return (new Potion(id, name, type, p, des));
        } else {
            return null;
        }
    }
}

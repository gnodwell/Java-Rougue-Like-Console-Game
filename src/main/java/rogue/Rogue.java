package rogue;

import java.util.ArrayList;
import java.util.Map;
// import java.util.concurrent.ConcurrentNavigableMap;

// import javax.print.attribute.standard.PrinterInfo;

import java.util.HashMap;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;

import java.awt.Point;
// import java.nio.charset.MalformedInputException;

// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;

/**
 * creates the game.
 */
public class Rogue {
    public static final char UP = 'h';
    public static final char DOWN = 'l';
    public static final char LEFT = 'j';
    public static final char RIGHT = 'k';
    private String nextDisplay = "-----\n|.@..|\n|....|\n-----";
    private RogueParser parser;
    private Player player;
    private ArrayList<Room> myRooms;
    private ArrayList<Item> myItems;
    private ArrayList<Door> doorsInTheRoom;
    private HashMap<String, String> symbolsMap;
    private Room currentRoom; //current room that is being displayed.

/**
 * constructor.
 * @param theDungeonInfo (RogueParser) gets info to be used
 */
    public Rogue(RogueParser theDungeonInfo) {

        parser = theDungeonInfo;
        // player = new Player();
        myRooms = new ArrayList<Room>();
        myItems = new ArrayList<Item>();
        symbolsMap = new HashMap<String, String>();
        // setAllSymbols(parser);
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
        System.out.println("\n\n\n\n\n\n\n ..... \n\n\n");
        setDefaultPlayer();
        // setStartingDisplay();


    }

    /**
     * Defualt constructor
     */
    public Rogue() {
        myRooms = new ArrayList<Room>();
        myItems = new ArrayList<Item>();
        symbolsMap = new HashMap<String, String>();
        setDefaultPlayer();
        setAllSymbols();
        // linkRooms();
        // setStartingPosition();
    }


    private void setStartingPosition() {
        for (Room r: myRooms) {
            if (r.getStart()) {
                currentRoom = r;
                // currentRoom.setPlayer(player);
            }
        }
    }



    private void setStartingDisplay() {
        for (Room r: myRooms) {
            if (r.getStart()) {
                nextDisplay = r.displayRoom();
                //player.setCurrentRoom(r);
            }
        }
    }



    private void setDefaultPlayer() {
        player = new Player();
        currentRoom.setPlayer(player);
        player.setXyLocation(new Point(1,1));
        player.setName("Bob");
        player.setCurrentRoom(currentRoom);
        currentRoom.setPlayer(player);

    }



/**
 * sets the player.
 * @param thePlayer (Player) contains information on the player
 */
    public void setPlayer(Player thePlayer) {
        // player = new Player (thePlayer);
        player = thePlayer;
        currentRoom.setPlayer(player);
        player.setCurrentRoom((currentRoom));
        // player.setXyLocation(1,1);
        setStartingDisplay(); //fsrp
    }



    // public void setAllSymbols(RogueParser parser) {
/**
creates a map that contains all symolbols used to print the displays.
 */
    public void setAllSymbols() {

        symbolsMap.put("NS_WALL", parser.getSymbol("NS_WALL"));//.toString());
        symbolsMap.put("EW_WALL", parser.getSymbol("EW_WALL"));//.toString());
        symbolsMap.put("DOOR", parser.getSymbol("DOOR"));//.toString());
        symbolsMap.put("PLAYER", parser.getSymbol("PLAYER"));//.toString());
        symbolsMap.put("ITEM", parser.getSymbol("ITEM"));//.toString());
        symbolsMap.put("PASSAGE", parser.getSymbol("PASSAGE"));//.toString());
        symbolsMap.put("FLOOR", parser.getSymbol("FLOOR"));//.toString());
    }

/**
 * gets players.
 * @return returns player
 */
    public Player getPlayer() {
        return player;
    }


/**
 * sets the symbols to be used.
 * @param filename (String) filename of the JSON file containing required information
 */
    public void setSymbols(String filename) {

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
 * creates the rooms.
 * @param filename (String) file containing all information to be used
 */
    public void createRooms(String filename) {
        // int height;
        // int width;
        // int pos;

        // for (Room r: myRooms) {
        //     height = r.getHeight();
        //     width =  r.getWidth();
        //     char[][] roomDes = new char[width][height];
        //     if (r.containsDoorKey("N")) {
        //         pos = r.getDoor("N");
        //         roomDes[pos][0] = r.getSymbols("Door");
        //     } else if (r.containsDoorKey("E")) {
        //         pos = r.getDoor("E");
        //         roomDes[width][pos] = r.getSymbols("Door");
        //     } else if if (r.containsDoorKey("S")) {
        //         pos = r.getDoor("S");
        //         roomDes[height][pos] = r.getSymbols("Door");
        //     } else if (r.containsDoorKey("W")) {
        //         pos = r.getDoor("W");
        //         roomDes[0][pos] = r.getSymbols("Door");
        //     }


        // }



    }





/**
 * displays all the rooms in the dungeon.
 * @return null
 */
    public String displayAll() {
        //creates a string that displays all the rooms in the dungeon
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
        /* this method assesses a move to ensure it is valid.
        If the move is valid, then the display resulting from the move
        is calculated and set as the 'nextDisplay' (probably a private member variable)
        If the move is not valid, an InvalidMoveException is thrown
        and the nextDisplay is unchanged
        */

        Point newLocation = new Point();
        Point currentLocation = player.getXyLocation();

        int pX = (int) currentLocation.getX();
        int pY = (int) currentLocation.getY();


        player.setCurrentRoom(currentRoom);
        // currentRoom.setPlayer(player);


        switch (input) {
            case LEFT:
            int flag0 = 0;
            if (pX == 1) {
                int doorLoc = currentRoom.getDoor("W");
                if (doorLoc == pY) {
                    //handle door case
                    Door d = currentRoom.getDoorObject("W");
                    Room otherRoom = d.getOtherRoom(currentRoom);
                    player.setCurrentRoom(otherRoom);
                    pX = 1;
                    pY = 1;
                    newLocation.setLocation(pX, pY);
                    player.setXyLocation(newLocation);
                    // otherRoom.setPlayer(player);
                    currentRoom = otherRoom;
                    currentRoom.setPlayer(player);
                    flag0 = 1;

                } else {
                    flag0 = 1;
                    throw new InvalidMoveException("You've hit a wall !");
                }
            }

            if (flag0 != 1) {
                newLocation.setLocation(pX - 1, pY);
                player.setXyLocation(newLocation);
            }

            break;

            case UP:
                int flag1 = 0;
                if (pY == 1) {
                    int doorLoc = currentRoom.getDoor("N");
                    if (doorLoc == pX) {
                        Door d = currentRoom.getDoorObject("N");
                        Room otherRoom = d.getOtherRoom(currentRoom);
                        player.setCurrentRoom(otherRoom);
                        pX = 1;
                        pY = 1;
                        newLocation.setLocation(pX, pY);
                        player.setXyLocation(newLocation);
                        // otherRoom.setPlayer(player);
                        currentRoom = otherRoom;
                        currentRoom.setPlayer(player);
                        flag1 = 1;

                    } else {
                        flag1 = 1;
                        throw new InvalidMoveException("You've hit a wall !");
                    }
                }
                if (flag1 != 1) {
                    newLocation.setLocation(pX, pY - 1);
                    player.setXyLocation(newLocation);
                }


            break;

            case DOWN:
                int flag2 = 0;
                if (pY == currentRoom.getHeight() - 2) {
                    int doorLoc = currentRoom.getDoor("S");
                    if (doorLoc == pX) {
                        Door d = currentRoom.getDoorObject("S");
                        Room otherRoom = d.getOtherRoom(currentRoom);
                        player.setCurrentRoom(otherRoom);
                        pX = 1;
                        pY = 1;
                        newLocation.setLocation(pX, pY);
                        player.setXyLocation(newLocation);
                        // otherRoom.setPlayer(player);
                        currentRoom = otherRoom;
                        currentRoom.setPlayer(player);
                        flag2 = 1;

                    } else {
                        flag2 = 1;
                        throw new InvalidMoveException("You've hit a wall !");
                    }
                }
                if (flag2 != 1) {
                    newLocation.setLocation(pX, pY + 1);
                    player.setXyLocation(newLocation);
                }

            break;

            case RIGHT:
                //move down
                int flag3 = 0;
                if (pX == currentRoom.getWidth() - 2) {
                    //if there is no door
                    int doorLoc = currentRoom.getDoor("E");
                    if (doorLoc == pY) {
                        //handle door case
                        Door d = currentRoom.getDoorObject("E");
                        Room otherRoom = d.getOtherRoom(currentRoom);
                        player.setCurrentRoom(otherRoom);
                        pX = 1;
                        pY = 1;
                        newLocation.setLocation(pX, pY);
                        player.setXyLocation(newLocation);
                        // otherRoom.setPlayer(player);
                        currentRoom = otherRoom;
                        currentRoom.setPlayer(player);
                        flag3 = 1;

                    } else {
                        flag3 = 1;
                        throw new InvalidMoveException("You've hit a wall !");
                    }
                }
                if (flag3 != 1) {
                    newLocation.setLocation(pX + 1, pY);
                    player.setXyLocation(newLocation);
                }

            break;

            default:
            //other input
        }
        nextDisplay = currentRoom.displayRoom();
        // System.out.println(player.getXyLocation());

        // CUR_POS = (4,6)
        // input = "KKKJKKKLU"
        // NEW_POS = calulate_loc(input)
        // check if its valid
        // find if NEW_POS is a door:
            // newRoom = givenDoor.getOtherRoom(cur_room)
            // newDisplay = newRoom.getDisplayRoom


        /* validate the move
        if the move goes to a given door then get the other room in that door
        using the getOtherRoom method and set the display to the display of that room.
        */



        return "That's a lovely move: " +  Character.toString(input);

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
        // ArrayList<Door> doorsInTheRoom;
        for (Room r: myRooms) {
            doorsInTheRoom = r.getDoorList();
            for (Door d: doorsInTheRoom) {
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

        /* allocate memory for a room object.
           look up the attributes of the room in the map
           set the fielddsd for the room object you just created using the values you looked up

           int theWidth = somehwoconvertoint(toAdd.get("width"));
           theRoom.setWidth(theWidth)
           do this for every attribut

           add the room object to the list of dungeon rooms
            myRooms.add(theRoom);

            */

        Room newRoom = new Room();
        newRoom.setWidth(Integer.parseInt(toAdd.get("width")));
        newRoom.setHeight(Integer.parseInt(toAdd.get("height")));
        newRoom.setId(Integer.parseInt(toAdd.get("id")));
        newRoom.setStart(Boolean.parseBoolean(toAdd.get("start")));
        newRoom.setSymbols(symbolsMap);
        newRoom.setPlayer(player);



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


        try {
            myRooms.add(newRoom);
        } catch (NullPointerException e) {
            System.out.print("Came accross a null pointer when tring to add room");
        }

    }


/*
id
name           ---info
type
description

room
x      ---- loot
y

*/

/**
 * adds item to room.
 * @param toAdd (Map<String, String>) information on the items to be added
 */
    public void addItem(Map<String, String> toAdd) {
        /*
        allocate memory for the item object
        look up the attributes of the item in the map
        set the fields in the object you just created using those values
        add the item object to the list of items in the dungeon
        add the item to the room it is currently located in
        */

        int itemId = Integer.parseInt(toAdd.get("id"));
        String name = toAdd.get("name");
        String type = toAdd.get("type");          //info
        String des = toAdd.get("description");

        if (toAdd.containsKey("x")) {
            Point p = new Point();
            p.setLocation(Integer.parseInt(toAdd.get("x")), Integer.parseInt(toAdd.get("y")));
            Item newItem = new Item(itemId, name, type, p, des);     //loot

            int roomId = Integer.parseInt(toAdd.get("room"));

            for (Room r: myRooms) {
                if (r.getId() == roomId) {
                    newItem.setCurrentRoom(r);
                    try {
                        r.addItem(newItem);
                    } catch (ImpossiblePositionException e) {
                        e.printStackTrace();
                    } catch (NoSuchItemException e) {
                        System.out.println("Invalid Item");
                    }

                }
            }

            myItems.add(newItem);

        }


    }

}

package rogue;

import java.util.ArrayList;
import java.util.Map;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;

import java.awt.Point;

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


/**
 * constructor.
 * @param theDungeonInfo (RogueParser) gets info to be used
 */
    public Rogue(RogueParser theDungeonInfo) {

        parser = theDungeonInfo;
        player = new Player();
        myRooms = new ArrayList<Room>();
        myItems = new ArrayList<Item>();

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

    }



/**
 * sets the player.
 * @param thePlayer (Player) contains information on the player
 */
    public void setPlayer(Player thePlayer) {
            // thePlayer.setPosition(3,4);
            player = thePlayer;

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
        return null;

    }


/**
 * gets information on the items in the rooms.
 * @return returns the array list of the items
 */
    public ArrayList<Item> getItems() {
        return null;

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
        for (Room r: myRooms ) {
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

        ArrayList<Door> doors;
        Point curPos = player.getXyLocation();
        Point newPos = getNewPos(curPos, input);
        int xVal = (int) newPos.getX();
        int yVal = (int) newPos.getY();

        for (Room r: myRooms) { 
            if (xVal == 0 || xVal == r.getWidth() || yVal == 0 || yVal == r.getHeight()) {
                if ((r.getDoor("N") == xVal) && yVal == 0) {
                    Room otherRoom = null ;
                    for (Door d: doorsInTheRoom) {
                        if (d.getDirection() == "N") {
                            otherRoom = d.getOtherRoom(r);
                        }
                    }
                    nextDisplay = otherRoom.displayRoom();

                    player.setCurrentRoom(otherRoom);
                    newPos.setLocation(1, 1);
                    player.setXyLocation(newPos);
                
                } else if ((r.getDoor("E") == yVal) && xVal == r.getWidth()) {
                        Room otherRoom = null ;
                        for (Door d: doorsInTheRoom) {
                            if (d.getDirection() == "N") {
                                otherRoom = d.getOtherRoom(r);
                            }
                        }
                        nextDisplay = otherRoom.displayRoom();
                    // Room newRoom = r.getOtherRoom(r);
                    // newRoom.displayRoom();
                    player.setCurrentRoom(r);
                    newPos.setLocation(1, 1);
                    player.setXyLocation(newPos);
                } else if ((r.getDoor("S") == xVal) && yVal == r.getHeight()) {
                        Room otherRoom = null ;
                        for (Door d: doorsInTheRoom) {
                            if (d.getDirection() == "N") {
                                otherRoom = d.getOtherRoom(r);
                            }
                        }
                        nextDisplay = otherRoom.displayRoom();
                    // Room newRoom = r.getOtherRoom(r);
                    // newRoom.displayRoom();
                    player.setCurrentRoom(r);
                    newPos.setLocation(1, 1);
                    player.setXyLocation(newPos);
                }
                else if ((r.getDoor("W") == yVal) && xVal == 0) {
                        Room otherRoom = null ;
                        for (Door d: doorsInTheRoom) {
                            if (d.getDirection() == "N") {
                                otherRoom = d.getOtherRoom(r);
                            }
                        }
                        nextDisplay = otherRoom.displayRoom();
                    // newRoom = r.getOtherRoom(r);
                    // newRoom.displayRoom();
                    player.setCurrentRoom(r);
                    newPos.setLocation(1, 1);
                    player.setXyLocation(newPos);
                }
                else {
                    throw new InvalidMoveException("You hit a wall");
                }
            }
            for (Item i: myItems) {
                if (newPos.equals(i.getXyLocation())) {
                    throw new InvalidMoveException("there is an item in the way");
                }
                else {
                    //call next display
                    player.setXyLocation(newPos);
                }
            }
        }





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


    public Point getNewPos(Point currentPos, char input) {
        Point newPos = new Point();
        int x = (int) currentPos.getX();
        int y = (int) currentPos.getY();

        if (input == UP) {
            newPos.setLocation(x, y - 1);
        } else if (input == DOWN) {
            newPos.setLocation(x, y +  1);
        } if (input == RIGHT) {
            newPos.setLocation(x + 1, y);
        } if (input == LEFT) {
            newPos.setLocation(x - 1, y);
        }

        return newPos;
    }


    private void linkRooms() {
        // ArrayList<Door> doorsInTheRoom;
        for (Room r: myRooms) {
            doorsInTheRoom = r.getDoorList();
            for (Door d: doorsInTheRoom) {
                int connection_loc = d.getConnectedTo();
                for (Room r2: myRooms) {
                    if (r2.getId() == connection_loc) {
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



        if (!toAdd.get("E").equals("-1")) {
            newRoom.setDoor("E", Integer.parseInt(toAdd.get("E")));
            newRoom.setDoorConnection("E" ,Integer.parseInt(toAdd.get("E_con_room")));
        }
        if (!toAdd.get("N").equals("-1")) {
            newRoom.setDoor("N", Integer.parseInt(toAdd.get("N")));
        }
        if (!toAdd.get("S").equals("-1")) {
            newRoom.setDoor("S", Integer.parseInt(toAdd.get("S")));
        }
        if (!toAdd.get("W").equals("-1")) {
            newRoom.setDoor("W", Integer.parseInt(toAdd.get("W")));
        }

        // if (newRoom) {
        //     myRooms.add(newRoom);   
        System.out.println(newRoom.getWidth()); 
        try {
            myRooms.add(newRoom);
            System.out.println("added new room");
        } catch (NullPointerException e) {
            System.out.println("somethings fucked");
        }

    }


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
        System.out.println("test item to be added -- " + toAdd);
        int itemId = Integer.parseInt(toAdd.get("id"));
        String name = toAdd.get("name");
        String type = toAdd.get("type");
        // int roomId = Integer.parseInt(toAdd.get("wall_pos"));
        // int roomId = Integer.parseInt(toAdd.get("room"));
        String roomId = toAdd.get("room");
        String des = toAdd.get("description");
        // String xVal = toAdd.get("x");
        // String yVal = toAdd.get("y");
        System.out.println("testing x -- " + toAdd.get("x"));
        Point xyLoc = new Point();
        if (toAdd.get("x") != null) {
            try {
                xyLoc.setLocation(Integer.parseInt(toAdd.get("x")), Integer.parseInt(toAdd.get("y")));
            } catch (NullPointerException e) {
                System.out.print("coordinates are fucked");
            }
        }
        // try {
        //     xyLoc.setLocation(Integer.parseInt(toAdd.get("x")), Integer.parseInt(toAdd.get("y")));
        // } catch (NullPointerException e) {
        //     System.out.print("coordinates are fucked");
        // }

        Item newItem = new Item(itemId, name, type, xyLoc, des);
        Point zeroPoint = new Point();
        zeroPoint.setLocation(0,0);
        if (!xyLoc.equals(zeroPoint)) {
            try {
                System.out.println("xy loc of item added is -- " + xyLoc );
                myItems.add(newItem);
                System.out.println("added new item");
                System.out.println(toAdd);
            } catch (NullPointerException e) {
                System.out.println("adding a new item is fucked");
            } 
        } else {
            System.out.println("did not add item");
        }
        // try {
        //     myItems.add(newItem);
        //     System.out.println("added new item");
        //     System.out.println(toAdd);
        // } catch (NullPointerException e) {
        //     System.out.println("adding a new item is fucked");
        // }

        for (Room room: myRooms) {
            System.out.println("test getId -- " + room.getId());
            System.out.println("Test roomId -- " + roomId);
        if (roomId != null) {
            if (room.getId() == Integer.parseInt(roomId)) {
                try {
                    room.addItem(newItem);
                } catch (ImpossiblePositionException e) {
                    System.out.println("something is fucked");
                    continue;
                } catch (NoSuchItemException e) {
                    System.out.println("your fucked");
                    continue;
                }
            }
        }
        }

    }

}

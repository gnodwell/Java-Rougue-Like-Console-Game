package rogue;


import java.util.ArrayList;


public class Door {

    private ArrayList<Room> rooms;
    private String dir;
    private int loc;
    private int connectedTo;


/**
construction.
@param direction (String) what side of the room the door is located
@param location (int) where the door is located on the wall
 */
    public Door(String direction, int location) {
        rooms = new ArrayList<Room>();
        dir = direction;
        loc = location;

    }

/**
sets what room the door is connected to.
@param con2 (int) id of the room that is connected to the door
 */
    public void setConnectedTo(int con2) {
        connectedTo = con2;
    }


/**
gets the connected room.
@return connectedTo (int) the id of the connected room
 */
    public int getConnectedTo() {
        return connectedTo;
    }


/**
gets the direction of where the door is on the wall.
@return dir (String) what wall the door is located on
 */
    public String getDirection() {
        return dir;
    }

/**
returns the location on the wall where the door is located.
@return loc (int) the wall position where the door is located
 */
    public int getLocation() {
        return loc;
    }


/**
connects two rooms together.
@param r (Room) connected room
 */
    public void connectRoom(Room r) {
        rooms.add(r);
    }


/**
retuns the list of room.
@return rooms (ArrayList<Room>) returns the array list of rooms
 */
    public ArrayList<Room> getConnectedRooms() {
        return rooms;
    }


/**
gets the connected room to the on the player is crrently in.
@param currentRoom (Room) where the player is currently in
@return rooms.get() (Room) depending on what room the player is in this will return the other room
 */
    public Room getOtherRoom(Room currentRoom) {
        if (rooms.get(0) != currentRoom) {
            return rooms.get(0);
        } else {
            return rooms.get(1);
        }
    }

}

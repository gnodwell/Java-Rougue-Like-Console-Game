package rogue;


import java.util.ArrayList;


public class Door {

    private ArrayList<Room> rooms;
    private String dir;
    private int loc;
    private int connectedTo;


    public Door(String direction, int location) {
        rooms = new ArrayList<Room>();
        dir = direction;
        loc = location;
        
    }


    public void setConnectedTo(int con2) {
        connectedTo = con2;
    }


    public int getConnectedTo() {
        return connectedTo;
    }


    public String getDirection() {
        return dir;
    }


    public int getLocation() {
        return loc;
    }



    public void connectRoom(Room r) {
        rooms.add(r);

    }


    public ArrayList<Room> getConnectedRooms() {
        return rooms;
    }


    public Room getOtherRoom(Room currentRoom) {
        if (rooms.get(0) != currentRoom) {
            return rooms.get(0);
        } else {
            return rooms.get(1);
        }
    }






}
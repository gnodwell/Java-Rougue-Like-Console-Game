package rogue;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Point;
import java.util.HashMap;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 *
 *
 */
public class Room  {

    int width;
    int height;
    int id;
    ArrayList<Item> items;
    Player currentPlayer;
    HashMap<String, Integer> doors;
    HashMap<String, String> symbols;
    Boolean start;




  

    // Default constructor
 public Room() {

    items = new ArrayList<Item>();
    doors = new HashMap<String, Integer>();



 }




   // Required getter and setters below

 
 public int getWidth() {

return width;
 }


public void  setSymbols (HashMap<String, String> newSymbols) {
     symbols = newSymbols;

}

public HashMap<String, String> getSymbols () {
     return symbols;
}







 public void setWidth(int newWidth) {
     width = newWidth;

 }

 
 public int getHeight() {

return height;
 }


 public void setHeight(int newHeight) {
     height = newHeight;

 }

 public int getId() {

    return id;

 }


 public void setId(int newId) {
    id = newId;
 }


 public ArrayList<Item> getRoomItems() {
    return items;

 }


 public void setRoomItems(ArrayList<Item> newRoomItems) {

    items = newRoomItems;



 }


 public Player getPlayer() {
    return currentPlayer;

 }


 public void setPlayer(Player newPlayer) {
    currentPlayer = newPlayer;

 }


 


 public int getDoor(String direction){

    return doors.get(direction.toUpperCase());

 }

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/

public void setDoor(String direction, int location){

    doors.put(direction.toUpperCase(), location);

}


public boolean isPlayerInRoom() {
    Point playerLoc = currentPlayer.getXyLocation();

    int xLoc = (int)playerLoc.getX();
    int yLoc = (int)playerLoc.getY();

    if ((xLoc < width+1) && (yLoc < height+1)) {
        return true;
    }
    else {
        return false;
    }

}

public void setStart(Boolean playerStart) {
    start = playerStart;
}




   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */
   public String displayRoom() {

       int xVal;
       int yVal;
       String roomDescription = "<---- [ROOM " + id + "] ---->\n";
       int flag = 0;

       if (start == true) {
           roomDescription = roomDescription.concat("-Starting Room \n");
       }

       for (int i = 0; i < height+2; i++ ) {
           for (int j = 0; j < width+2; j++) {
               if ((i == 1) && (j == 1)) {
                   roomDescription = roomDescription.concat((symbols.get("PLAYER")));
               }




               else if (i == 0) {
                   if (doors.containsKey("N") == true) {
                       if (j == doors.get("N")) {
                           roomDescription = roomDescription.concat(symbols.get("DOOR"));
                       }
                       else {
                           roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                       }
                   }
                   else {
                       roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                   }
               }
               else if (i == height + 1){
                   if (doors.containsKey("S") == true) {
                       if (j == doors.get("S")) {
                           roomDescription = roomDescription.concat(symbols.get("DOOR"));
                       }
                       else {
                           roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                       }
                   }
                   else{
                       roomDescription = roomDescription.concat(symbols.get("NS_WALL"));
                   }
               }
               else {
                   if (j == 0){
                       if (doors.containsKey("E") == true) {
                           if (i == doors.get("E")) {
                               roomDescription = roomDescription.concat(symbols.get("DOOR"));
                           }
                           else {
                               roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                           }
                       }
                       else {
                           roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                       }
                   }
                   else if (j == width+1) {
                       if (doors.containsKey("W") == true) {
                           if (i == doors.get("W")) {
                               roomDescription = roomDescription.concat(symbols.get("DOOR"));
                           }
                           else {
                               roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                           }
                       }
                       else {
                           roomDescription = roomDescription.concat(symbols.get("EW_WALL"));
                       }
                   }
                   else {
                       if (items.isEmpty() == false) {
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
           roomDescription =    roomDescription.concat("\n");
       }


       return roomDescription;
     
   }
}
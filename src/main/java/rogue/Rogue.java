package rogue;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Point;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Rogue{

    ArrayList<Item> allItems = new ArrayList<Item>();
    ArrayList<Room> rooms = new ArrayList<Room>();
    Player currentPlayer = new Player();
    HashMap<String, String> symbols = new HashMap<String, String>();




    public void setPlayer(Player thePlayer){
        currentPlayer = thePlayer;
    }


    public void setSymbols(String filename) {





        JSONParser parser = new JSONParser();
        JSONObject symbolJSON = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(filename));
            symbolJSON = (JSONObject) obj;


        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object obj : (JSONArray) symbolJSON.get("symbols")) {

            JSONObject symbolObj = (JSONObject) obj;
            String symbolName = symbolObj.get("name").toString();
            String symbolSymbol = symbolObj.get("symbol").toString();
            symbols.put(symbolName, symbolSymbol);






        }



    }

    public ArrayList<Room> getRooms(){
        return rooms;

    }

    public ArrayList<Item> getItems(){
        return allItems;

    }
    public Player getPlayer(){
        return currentPlayer;

    }

    public void createRooms(String filename){

        JSONParser parser = new JSONParser();
        JSONObject roomJSON = new JSONObject();
        try {
            Object obj = parser.parse(new FileReader(filename));
            roomJSON = (JSONObject) obj;

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object object : (JSONArray) roomJSON.get("room") ) {
            Room newRoom = new Room();
            ArrayList<Item> roomItems = new ArrayList<Item>();
            JSONObject roomObject = (JSONObject)object;

            newRoom.setSymbols(symbols);
            newRoom.setWidth(Integer.parseInt(roomObject.get("width").toString()));
            newRoom.setHeight(Integer.parseInt(roomObject.get("height").toString()));
            newRoom.setId(Integer.parseInt(roomObject.get("id").toString()));
            newRoom.setStart(Boolean.parseBoolean(roomObject.get("start").toString()));



            for (Object doorObject : (JSONArray) roomObject.get("doors")) {
                JSONObject doorJSON = (JSONObject)doorObject;

                int doorId = Integer.parseInt(doorJSON.get("id").toString());
                String doorDir = doorJSON.get("dir").toString();
                newRoom.setDoor(doorDir, doorId);

            }



            for (Object itemObject : (JSONArray) roomObject.get("loot")) {
                JSONObject itemJSON = (JSONObject)itemObject;
                int itemId = Integer.parseInt(itemJSON.get("id").toString());
                Point location = new Point(Integer.parseInt(itemJSON.get("x").toString()), Integer.parseInt(itemJSON.get("y").toString()));
                ArrayList<String> itemInfo = getInfo(roomJSON, itemId);
                Item newItem = new Item(itemId, itemInfo.get(0), itemInfo.get(1), location);

                roomItems.add(newItem);
                allItems.add(newItem);



            }








            newRoom.setRoomItems(roomItems);
            rooms.add(newRoom);
            newRoom.displayRoom();


        }


    }
    public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        String fullDesc = "";
        for (Room room: rooms) {
            fullDesc = fullDesc.concat(room.displayRoom());
            fullDesc = fullDesc.concat("\n\n");
        }

        return fullDesc;
    }

    private ArrayList getInfo(JSONObject roomJSON, int id) {
        ArrayList<String> info = new ArrayList<>();

        for (Object object : (JSONArray) roomJSON.get("items")) {
            JSONObject itemJSON = (JSONObject)object;
            if (Integer.parseInt(itemJSON.get("id").toString()) == id) {
                info.add(0, itemJSON.get("name").toString());
                info.add(1, itemJSON.get("type").toString());
            }
        }

    return info;
    }




}

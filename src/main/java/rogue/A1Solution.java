package rogue;

import java.util.Scanner;
import java.util.ArrayList;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Point;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class A1Solution{




    public static void main(String[] args) { 
        // Hardcoded configuration file location/name



        String configurationFileLocation = "fileLocations.json";  //please don't change this for this version of the assignment
        
 // reading the input file locations using the configuration file
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        String roomFileName = "randomStr";
        String symbolsFileName = "randomStr2";

        try {

            Object obj = parser.parse(new FileReader(configurationFileLocation));
            JSONObject configurationJSON = (JSONObject) obj;

            roomFileName = (String)configurationJSON.get("Rooms");
            symbolsFileName = (String)configurationJSON.get("Symbols");

            // Extract the Rooms value from the file to get the file location for rooms

            // Extract the Symbols value from the file to get the file location for symbols-map

            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Player player = new Player();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Shall I pick a character's race, role, gender and alignment for you? [ynq]");
        player.setPlayerAutoAssign(myObj.nextLine());
        System.out.println("Choosing Character's Role\t\t\t\t\tPick a role for your character");
        System.out.println("                         \t\t\t\t\ta - an Archeologist");
        System.out.println("                         \t\t\t\t\tb - a Barbarian");
        System.out.println("                         \t\t\t\t\tc - a Caveman/Cavewoman");
        System.out.println("                         \t\t\t\t\th - Healer");
        System.out.println("                         \t\t\t\t\tk - Knight");
        System.out.println("                         \t\t\t\t\tm - Monk");
        System.out.println("                         \t\t\t\t\tp - Priest/Priestess");
        System.out.println("                         \t\t\t\t\tr - a Rogue");
        System.out.println("                         \t\t\t\t\tR - a Ranger");
        System.out.println("                         \t\t\t\t\ts - a Samurai");
        System.out.println("                         \t\t\t\t\tt - a Tourist");
        System.out.println("                         \t\t\t\t\tv - a Valkyrie");
        System.out.println("                         \t\t\t\t\tw - a Wizard");
        System.out.println("                         \t\t\t\t\t* - Random");
        System.out.println("                         \t\t\t\t\tq - Quit");
        System.out.println("                         \t\t\t\t\t(end");
        player.setPlayerRole(myObj.nextLine());
        System.out.println("Choosing Gender          \t\t\t\t\tPick the gender of your human " + player.getPlayerRole());
        System.out.println("");
        System.out.println("                         \t\t\t\t\tm - Male");
        System.out.println("                         \t\t\t\t\tf - Female");
        System.out.println("                         \t\t\t\t\t* - Random");
        System.out.println("                         \t\t\t\t\tq - Quit");
        System.out.println("                         \t\t\t\t\t(end)");
        player.setPlayerGender(myObj.nextLine());











// instantiate a new Rogue object and call methods to do the required things
//        Rogue rogue = new Rogue();
//        rogue.createRooms(roomFileName);


        System.out.println("We have liftoff!");
        Rogue rogue = new Rogue();
        rogue.setSymbols(symbolsFileName);
        rogue.createRooms(roomFileName);
        System.out.println(rogue.displayAll());

    }


}
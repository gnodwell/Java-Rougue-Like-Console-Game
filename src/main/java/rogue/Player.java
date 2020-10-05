package rogue;
import java.util.ArrayList;
import java.awt.Point;
/**
 * The player character
 */
public class Player {
    String playerAutoAssign;
    String playerName;
    String playerRole;
    String playerGender;



    // Default constructor
    public Player() {

    }


    public Player(String name) {

    }


    public String getName() {

        return playerName;
    }


    public void setName(String newName) {
        playerName = newName;

    }

    public Point getXyLocation() {
        return null;

    }


    public void setXyLocation(Point newXyLocation) {

    }


    public Room getCurrentRoom() {
        return null;

    }


    public void setCurrentRoom(Room newRoom) {

    }

    public void setPlayerAutoAssign(String autoAssign){
        playerAutoAssign = autoAssign;
    }

    public String getPlayerAutoAssign() {
        return playerAutoAssign;
    }

    public void setPlayerRole(String role){
        playerRole = role;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerGender(String gender){
        playerGender = gender;
    }

    public String getPlayerGender() {
        return playerGender;
    }
}

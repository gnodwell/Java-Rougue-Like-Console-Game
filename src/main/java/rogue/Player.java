package rogue;
// import java.util.ArrayList;
import java.awt.Point;
/**
 * The player character.
 */
public class Player {
    private String playerAutoAssign;
    private String playerName;
    private String playerRole;
    private String playerGender;
    private Point xyLocation;
    private Room curRoom;


/**
default constructor.
*/
    public Player() {
        xyLocation = new Point();
        xyLocation.setLocation(1, 1);
    }

/**
constructor.
@param name  (String) contains the players name
 */
    public Player(String name) {
        xyLocation = new Point();
        xyLocation.setLocation(2, 2);
        this.playerName = name;
    }


/**
returns the players name.
 * @return playerName (String)  return players name
 */
    public String getName() {
        return playerName;
    }


/**
sets the player name .
@param newName  (String) contains the playes name
 */
    public void setName(String newName) {
        playerName = newName;
    }


/**
gets the coorinate of the player.
@return null
*/
    public Point getXyLocation() {
        return xyLocation;
    }


/**
sets the player location.
@param newXyLocation (Point) contains the coordinate value of the player
 */
    public void setXyLocation(Point newXyLocation) {
        xyLocation = newXyLocation;
    }


/**
gets the current room the player is in.
@return null
 */
    public Room getCurrentRoom() {
        return curRoom;
    }


/**
sets the current room the player is in.
@param newRoom (Room) contains the room
 */
    public void setCurrentRoom(Room newRoom) {
        curRoom = newRoom;
    }


/**
auto assigns the players characters archetype.
@param autoAssign  (String)  constant auto assignment of the archetype
 */
    public void setPlayerAutoAssign(String autoAssign) {
        playerAutoAssign = autoAssign;
    }


/**
retuns the auto assigned archetype.
@return playerAutoAssign (String) holds the archetype
 */
    public String getPlayerAutoAssign() {
        return playerAutoAssign;
    }


/**
sets player role.
@param role (String) contains chosen role
 */
    public void setPlayerRole(String role) {
        playerRole = role;
    }


/**
gets the players role to return.
@return playerRole (String) contains the chosen players role and returns it
 */
    public String getPlayerRole() {
        return playerRole;
    }


/**
sets the players gender.
@param gender (String) contains the players chosen gender
 */
    public void setPlayerGender(String gender) {
        playerGender = gender;
    }


/**
returns the players chosen gender.
@return playerGender (String) contains the chosen gender and returns it
 */
    public String getPlayerGender() {
        return playerGender;
    }
}

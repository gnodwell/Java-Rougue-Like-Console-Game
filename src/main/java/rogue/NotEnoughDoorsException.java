package rogue;


public class NotEnoughDoorsException extends Exception {
/**
used if there are a total of 0 doors inside a room.
 */
    public NotEnoughDoorsException() {
        super("Not enough doors in the room");
    }
}


package rogue;

public class NotEnoughDoorsException extends Exception {
    public NotEnoughDoorsException() {
        super("Item is not in the Room boundaries");
    }
}


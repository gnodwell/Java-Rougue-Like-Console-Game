package rogue;


public class ImpossiblePositionException extends Exception {
/**
this is throw when you are tring to access a location that is not inside the boundaries of the room.
 */
    public ImpossiblePositionException() {
        super("Item is not in the Room boundaries");
    }
}








// package rogue;







// /**
//  * exception to determine wehter a valid movement was made.
//  */
// public class ImpossiblePositionException extends Exception {


// /**
//  *idk what this does yet .
//  */
//     public ImpossiblePositionException() {
//         super();
//     }


// /**
//  * idk what this does yet.
//  * @param message (String) idk what this is yet
//  */
//     public ImpossiblePositionException(String message) {
//         super(message);
//     }

// }

package rogue;

public class SmallFood extends Food implements Tossable {

    /**
     * @return what happens when an food item that can be thrown is used.
     */
    @Override
    public String toss() {
        return "something";
    }
}

package rogue;

public class Potion extends Magic implements Edible, Tossable {

    /**
     * @return what happens when a potion that can be consumed is used.
     */
    @Override
    public String eat() {
        return "Sometyhing";
    }

    /**
     * @return what happens when a potion that is throw is used.
     */
    @Override
    public String toss() {
        return "Something";
    }
}

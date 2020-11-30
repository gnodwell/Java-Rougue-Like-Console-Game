package rogue;

public class Ring extends Magic implements Wearable {

    /**
     * @return what happens when a ring that is wearable is used.
     */
    @Override
    public String wear() {
        return "something";
    }
}

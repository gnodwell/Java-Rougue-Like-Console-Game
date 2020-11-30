package rogue;

public class Clothing extends Item implements Wearable {
    /**
     * Returns the output if you put on a wearable item.
     * @return what happens when an items that is wearable is used
     */
    @Override
    public String wear() {
        return "Something";
    }
}

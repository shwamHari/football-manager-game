package Items;
import main.Athlete;
import main.GameManager;

/**
 * 
 * Represents an abstract class for Item objects.
 *
 * This abstract class defines common properties and methods that items in the game will have.
 * Subclasses will implement specific behavior for returning information, using the item,
 * retrieving the cost, and getting the name.
 * 
 * @author Shyam Hari, Benjamin Joli
 */
public abstract class Item {

    /**
     * Returns information about the item.
     *
     * @return Information about the item.
     */
    public abstract String returnInformation();

    /**
     * Uses the item in the game.
     *
     * @param manager The game manager.
     * @param player  The athlete who uses the item.
     */
    public abstract void useItem(GameManager manager, Athlete player);

    /**
     * Retrieves the cost of the item.
     *
     * @return The cost of the item.
     */
    public abstract int getCost();

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public abstract String getName();

    /**
     * Returns a string representation of the item.
     *
     * @return A string representation of the item.
     */
    @Override
    public abstract String toString();
}

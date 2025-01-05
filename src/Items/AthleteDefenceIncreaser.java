package Items;
import main.Athlete;
import main.GameManager;


/**
 * 
 * Represents an item that increases the defense of an athlete.
 *
 * This class extends the abstract class Item and implements specific behavior for
 * returning information, using the item, retrieving the cost, getting the name, and
 * providing a string representation for the Athlete Defence Increaser item.
 * 
 * @author Shyam Hari
 * 
 */
public class AthleteDefenceIncreaser extends Item {

    /**
     * Returns the description of the Athlete Defence Increaser item.
     *
     * @return The description of the Athlete Defence Increaser item.
     */
    @Override
    public String returnInformation() {
        return "Increases the defense power (DEF) of the selected player in substitutes by 5. Note: The increase will not exceed the maximum value of 99.";
    }

    /**
     * Uses the Athlete Defence Increaser by increasing the given athlete's defense by 5.
     *
     * @param manager The game manager.
     * @param player  The athlete to apply the defense increase.
     */
    @Override
    public void useItem(GameManager manager, Athlete player) {
        manager.increaseAthletesDefence(player);
    }

    /**
     * Retrieves the cost of the Athlete Defence Increaser item, which is $100.
     *
     * @return The cost of the Athlete Defence Increaser item.
     */
    @Override
    public int getCost() {
        return 100;
    }

    /**
     * Returns a string representation of the Athlete Defence Increaser item for display purposes.
     *
     * @return A string representation of the Athlete Defence Increaser item.
     */
    @Override
    public String toString() {
        return "$100: Athlete Defence Increaser";
    }

    /**
     * Retrieves the name of the Athlete Defence Increaser item.
     *
     * @return The name of the Athlete Defence Increaser item.
     */
    @Override
    public String getName() {
        return "Athlete Defence Increaser";
    }
}

package Items;
import main.Athlete;
import main.GameManager;

/**
 * 
 * 
 * Represents an item that restores the stamina of an athlete.
 *
 * This class extends the abstract class Item and implements specific behavior for
 * returning information, using the item, retrieving the cost, getting the name, and
 * providing a string representation for the Athlete Stamina Restorer item.
 * 
 * @author Shyam Hari
 */
public class AthleteStaminaRestorer extends Item {

    /**
     * Returns the description of the Athlete Stamina Restorer item.
     *
     * @return The description of the Athlete Stamina Restorer item.
     */
    @Override
    public String returnInformation() {
        return "Restores the stamina of the selected player in substitutes to full (100).";
    }

    /**
     * Uses the Athlete Stamina Restorer item by restoring the given athlete's stamina.
     *
     * @param manager The game manager.
     * @param player  The athlete to restore stamina.
     */
    @Override
    public void useItem(GameManager manager, Athlete player) {
        manager.restoreAthletesStamina(player);
    }

    /**
     * Retrieves the cost of the Athlete Stamina Restorer item, which is $50.
     *
     * @return The cost of the Athlete Stamina Restorer item.
     */
    @Override
    public int getCost() {
        return 50;
    }

    /**
     * Returns a string representation of the Athlete Stamina Restorer item for display purposes.
     *
     * @return A string representation of the Athlete Stamina Restorer item.
     */
    @Override
    public String toString() {
        return "$50 Athlete Stamina Restorer";
    }

    /**
     * Retrieves the name of the Athlete Stamina Restorer item.
     *
     * @return The name of the Athlete Stamina Restorer item.
     */
    @Override
    public String getName() {
        return "Athlete Stamina Restorer";
    }
}

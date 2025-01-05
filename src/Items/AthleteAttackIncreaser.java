package Items;
import main.Athlete;
import main.GameManager;

/**
 * 
 * 
 * Represents an item that increases the attack of an athlete.
 *
 * This class extends the abstract class Item and implements specific behavior for
 * returning information, using the item, retrieving the cost, getting the name, and
 * providing a string representation for the Athlete Attack Increaser item.
 * 
 * @author Shyam Hari
 */
public class AthleteAttackIncreaser extends Item {
	
	/**
	 * Returns the description of the Athlete Attack Increaser item
	 */
	@Override
	public String returnInformation() {
		String information = "Increases the attacking power(ATT) of selected player in substitutes by 5. Note: The increase will not exceed the maximum value of 99.";
		return information;
	}
	
	/**
	 * Uses the Athlete Attack Increaser by increasing the given athletes attack by 5
	 */
	@Override
	public void useItem(GameManager manager, Athlete player) {
		manager.increaseAthletesAttack(player);
	}

	/**
	 * Returns the cost of the Athlete Attack Increaser which is $100
	 */
	@Override
	public int getCost() {
		return 100;
	}


	/**
	 * Overrides the to string method for the Athlete Attack Increaser so it is correctly represented in J Lists
	 */
	@Override
	public String toString() {
		return "$100: Athlete Attack Increaser";
	}
	
	/**
	 * Returns Athlete Attack Increaser which is the name of this item
	 */
	@Override
	public String getName() {
		return "Athlete Attack Increaser";
	}
	
	
}
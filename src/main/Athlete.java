package main;

/**
 * 
* represents an Athlete in a sports game.
* 
* @author Benjamin Joli, Shyam Hari
* 
*/
public class Athlete {
	private String name;
	private int stamina;
	private int offenceStat;
	private int defenceStat;
	private int cost;
	
	/**
	 * Creates an Athlete with a name, offenceStat, defenceStat and Cost
	 * 
	 * @param name 			The name of an Athlete
	 * @param offenceStat	The offensive stat of an Athlete
	 * @param defenceStat	The defensive stat of an Athlete
	 * @param cost			The cost of an Athlete
	 */
	public Athlete(String name, int offenceStat, int defenceStat, int cost) {
		this.name = name;
		this.stamina = 100;
		this.offenceStat = offenceStat;
		this.defenceStat = defenceStat;
		this.cost = cost;
	}
	/** 
	 * 
	 *
	 * @return the name of the Athlete
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 *
	 * @return the current stamina stat of an Athlete
	 */

	public int getStamina() {
		return stamina;
	}
	/**
	 * 
	 * @return the current attack stat on an Athlete
	 */
	public int getAttack() {
		return offenceStat * stamina / 100;
	}
	/**
	 *
	 * @return the current defence stat of an Athlete
	 */

	public int getDefence() {
		return defenceStat * stamina / 100;
	}
	/**
	 * 
	 * 
	 * @return the cost of an Athlete
	 */

	public int getCost() {
		return cost;
	}
	/**
	 * Increase the offenceStat of an Athlete by 5 points
	 * The offenceStat has a maximum of 99 and cannot be exceeded
	 * 
	 */
	public void increaseAttack() {
		offenceStat += 5;
		if (offenceStat > 99) {
			offenceStat = 99;
		}
	}
	/**
	 * Increase the defenceStat of an Athlete by 5 points
	 * The defenceStat cannot exceed 99
	 * 
	 */

	public void increaseDefence() {
		defenceStat += 5;
		if (defenceStat > 99) {
			defenceStat = 99;
		}
	}
	/**
	 * Decrease the stamina of an Athlete by 5 points
	 * Stamina has a minimum of 0 and cannot be decreased lower than 0 otherwise the athlete is considered injured.
	 */
	public void decreaseStamina() {
		stamina -= 5;
		if (stamina < 0){
			stamina = 0;
		}
	}

	/**
	 * Restore an Athletes Stamina to 100
	 */

	public void restoreStamina() {
		stamina = 100;
	}
	/**
	 * Returns a String format of the Athlete that is easier for a user to read
	 */
	public String toString() {
		return name + ": $" + cost + ": ATT: " + offenceStat + ": DEF: " + defenceStat + ": STA: " + stamina;
	}
	/**
	 * Creates a nickname for an Athlete
	 * 
	 * @param newName The new nickname for the athlete
	 * 
	 */
	public void setName(String newName) {
		name = newName;
	}

}
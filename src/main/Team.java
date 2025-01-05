package main;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * Represents a Team in the football manager sports game.
 * 
 * @author Shyam Hari, Benjamin Joli
 * 
 */
public class Team {

	private String name;
	private ArrayList<Athlete> playingAttackers;
	private ArrayList<Athlete> playingDefenders;
	private ArrayList<Athlete> substitutePlayers;
	private ArrayList<Athlete> allPlayers;

	/**
	 * Creates a Team with the specified name.
	 *
	 * @param teamName The name of the team.
	 */
	public Team(String teamName) {
		name = teamName;
		playingAttackers = new ArrayList<Athlete>();
		playingDefenders = new ArrayList<Athlete>();
		substitutePlayers = new ArrayList<Athlete>();
		allPlayers = new ArrayList<Athlete>();
	}

	/**
	 * Returns the name of the Team.
	 *
	 * @return The name of the Team.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a selected Athlete to the substitutePlayer ArrayList of a Team.
	 *
	 * @param player The Athlete to be added.
	 */
	public void addSubstitute(Athlete player) {
		substitutePlayers.add(player);
		if (!allPlayers.contains(player)) {
			allPlayers.add(player);
		}
	}

	/**
	 * Adds a selected Athlete to the playingAttackers ArrayList of a Team.
	 *
	 * @param player The Athlete to be added.
	 */
	public void addAttacker(Athlete player) {
		playingAttackers.add(player);
		if (!allPlayers.contains(player)) {
			allPlayers.add(player);
		}
	}

	/**
	 * Adds a selected Athlete to the playingDefenders ArrayList of a Team.
	 *
	 * @param player The Athlete to be added.
	 */
	public void addDefender(Athlete player) {
		playingDefenders.add(player);
		if (!allPlayers.contains(player)) {
			allPlayers.add(player);
		}
	}

	/**
	 * Removes a selected Athlete from the playingAttackers ArrayList of the team.
	 *
	 * @param index The index of the Athlete to be removed.
	 */
	public void removeAttacker(int index) {
		playingAttackers.remove(index);
	}

	/**
	 * Removes a selected Athlete from the playingDefenders ArrayList of the team.
	 *
	 * @param index The index of the Athlete to be removed.
	 */
	public void removeDefender(int index) {
		playingDefenders.remove(index);
	}

	/**
	 * Removes a selected Athlete from the substitutePlayers ArrayList of the team.
	 *
	 * @param index The index of the Athlete to be removed.
	 */
	public void removeSubstitute(int index) {
		substitutePlayers.remove(index);
	}

	/**
	 * Takes an Athlete from the substitutePlayers ArrayList and moves the selected
	 * Athlete into the playingDefenders ArrayList or the playingAttackers ArrayList.
	 *
	 * @param player The Athlete to be substituted.
	 * @param frame  The JFrame used for displaying the dialog.
	 */
	public void substitutePlayer(Athlete player, JFrame frame) {
		String[] options = new String[] { "Attacker", "Defender" };
		String message = "Would you like to substitute " + player.getName() + " as an Attacker or Defender?";
		int selection = JOptionPane.showOptionDialog(frame, message, "Attacker or Defender?", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, null);
		if (selection == 0) {
			if (playingAttackers.size() < 3) {
				this.addAttacker(player);
			}
		}
	}

	/**
	 * Returns the Athletes inside the playingAttackers ArrayList.
	 *
	 * @return The playingAttackers ArrayList.
	 */
	public ArrayList<Athlete> getAttackers() {
		return playingAttackers;
	}

	/**
	 * Returns the Athletes inside the playingDefenders ArrayList.
	 *
	 * @return The playingDefenders ArrayList.
	 */
	public ArrayList<Athlete> getDefenders() {
		return playingDefenders;
	}

	/**
	 * Returns the Athletes inside the substitutePlayers ArrayList.
	 *
	 * @return The substitutePlayers ArrayList.
	 */
	public ArrayList<Athlete> getSubstitutes() {
		return substitutePlayers;
	}

	/**
	 * Returns the Athletes inside the allPlayers ArrayList.
	 *
	 * @return The allPlayers ArrayList.
	 */
	public ArrayList<Athlete> getWholeTeam() {
		return allPlayers;
	}

	/**
	 * Decreases the stamina of each Athlete in the playingAttackers and
	 * playingDefenders ArrayLists by 5.
	 */
	public void decreaseTeamStamina() {
		for (Athlete player : playingAttackers) {
			player.decreaseStamina();
		}
		for (Athlete player : playingDefenders) {
			player.decreaseStamina();
		}
	}

	/**
	 * Restores the stamina of every Athlete in the allPlayers ArrayList back to 100.
	 */
	public void restoreTeamStamina() {
		for (Athlete player : getWholeTeam()) {
			player.restoreStamina();
		}
	}

	/**
	 * Returns a String representation of the Team's name.
	 *
	 * @return The name of the Team.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Restores the stamina of a selected Athlete back to 100.
	 *
	 * @param player The Athlete whose stamina is to be restored.
	 */
	public void restoreAthletesStamina(Athlete player) {
		int index = substitutePlayers.indexOf(player);
		Athlete athlete = substitutePlayers.get(index);
		athlete.restoreStamina();
		substitutePlayers.set(index, athlete);

		int index2 = allPlayers.indexOf(player);
		allPlayers.set(index2, athlete);
	}

	/**
	 * Increases the attack of a selected Athlete by 5 points.
	 *
	 * @param player The Athlete whose attack is to be increased.
	 */
	public void increaseAthletesAttack(Athlete player) {
		int index = substitutePlayers.indexOf(player);
		Athlete athlete = substitutePlayers.get(index);
		athlete.increaseAttack();
		substitutePlayers.set(index, athlete);

		int index2 = allPlayers.indexOf(player);
		allPlayers.set(index2, athlete);
	}

	/**
	 * Increases the defence of a selected Athlete by 5 points.
	 *
	 * @param player The Athlete whose defence is to be increased.
	 */
	public void increaseAthletesDefence(Athlete player) {
		int index = substitutePlayers.indexOf(player);
		Athlete athlete = substitutePlayers.get(index);
		athlete.increaseDefence();
		substitutePlayers.set(index, athlete);

		int index2 = allPlayers.indexOf(player);
		allPlayers.set(index2, athlete);
	}

	/**
	 * Sets a selected Athlete's name in the team to a custom nickname chosen by the user.
	 *
	 * @param player The Athlete whose name is to be set.
	 * @param name   The custom nickname chosen by the user.
	 */
	public void nicknameAthlete(Athlete player, String name) {
		int index = substitutePlayers.indexOf(player);
		Athlete athlete = substitutePlayers.get(index);
		athlete.setName(name);
		substitutePlayers.set(index, athlete);
	}
}

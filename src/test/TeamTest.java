package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Athlete;
import main.Team;

class TeamTest {

	private Athlete testAthleteOne;
	private Athlete testAthleteTwo;
	private Athlete testAthleteThree;
	private Team team;
	@BeforeEach
	public void init() {
		testAthleteOne = new Athlete("rick", 22, 33, 142);
		testAthleteTwo = new Athlete("James", 99, 99, 200);
		testAthleteThree = new Athlete("Fred", 32, 22, 85);
		team = new Team("testTeam");
	}

	@Test
	public void teamTests() {
		
		// ensures Team name is correct
		
		assertEquals("testTeam", team.getName());
		
		// ensures athletes can be added to selected positions in team 
		team.addSubstitute(testAthleteOne);
		team.addAttacker(testAthleteTwo);
		team.addDefender(testAthleteThree);
		
		assertTrue(team.getSubstitutes().contains(testAthleteOne));
		assertTrue(team.getAttackers().contains(testAthleteTwo));
		assertTrue(team.getDefenders().contains(testAthleteThree));
		
		// ensures athletes can be removed from selected positions in team
		
		team.removeAttacker(0);
		team.removeDefender(0);
		team.removeSubstitute(0);
		
		assertFalse(team.getSubstitutes().contains(testAthleteOne));
		assertFalse(team.getAttackers().contains(testAthleteTwo));
		assertFalse(team.getSubstitutes().contains(testAthleteThree));
		
		// ensures athletes by are a part of the allPlayers ArrayList regardless of
		//their position
		
		assertTrue(team.getWholeTeam().contains(testAthleteOne));
		assertTrue(team.getWholeTeam().contains(testAthleteTwo));
	
		
		// ensures Decrease Stamina and RestoreTeam Stamina is working as Intended
		
		team.addAttacker(testAthleteOne);
		team.addDefender(testAthleteTwo);
		
		team.decreaseTeamStamina();
		
		assertEquals(95, testAthleteOne.getStamina());
		assertEquals(95, testAthleteTwo.getStamina());
		
		team.restoreTeamStamina();
		
		assertEquals(100, testAthleteOne.getStamina());
		assertEquals(100, testAthleteTwo.getStamina());
		
		
		// ensures Selected Athletes in team can have their stats modified
		
		team.addSubstitute(testAthleteThree);
		team.addSubstitute(testAthleteOne);
		team.addSubstitute(testAthleteTwo);
		
		testAthleteThree.decreaseStamina();
		assertEquals(95, testAthleteThree.getStamina());
		
		team.restoreAthletesStamina(testAthleteThree);
		team.increaseAthletesAttack(testAthleteThree);
		team.increaseAthletesDefence(testAthleteThree);
		
		assertEquals(37, testAthleteThree.getAttack());
		assertEquals(27, testAthleteThree.getDefence());
		assertEquals(100, testAthleteThree.getStamina());
		
		// ensures Selected Athletes can receive nicknames chosen by user
		
		team.nicknameAthlete(testAthleteOne, "speedy");
		
		assertEquals("speedy", testAthleteOne.getName());
		
		
	}

}

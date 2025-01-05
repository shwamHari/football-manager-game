package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Athlete;
import org.junit.jupiter.api.BeforeEach;

class AthleteTest {

	private Athlete testAthleteOne;
	private Athlete testAthleteTwo;
	
	@BeforeEach
	public void init() {
		testAthleteOne = new Athlete ("rick", 22, 33, 142);
		testAthleteTwo = new Athlete("James", 99, 99, 200);
	}

	@Test
	public void testGetters() {
		// ensures all variables for athlete are being defined as intended
		String athleteName = testAthleteOne.getName();
		int athleteCost = testAthleteOne.getCost();
		int athleteStamina = testAthleteOne.getStamina();
		int athleteDefence = testAthleteOne.getDefence();
		int athleteAttack = testAthleteOne.getAttack();
		
		assertEquals("rick", athleteName);
		assertEquals(142, athleteCost);
		assertEquals(100, athleteStamina);
		assertEquals(33, athleteDefence);
		assertEquals(22, athleteAttack);
	}
	
	@Test
	public void testSetters() {
		// ensures all setters increases are working as intended
		
		testAthleteOne.increaseAttack();
		testAthleteOne.increaseDefence();
		
		assertEquals(27, testAthleteOne.getAttack());
		assertEquals(38, testAthleteOne.getDefence());
		
		// ensures that stamina cannot exceed 100
		
		assertEquals(100, testAthleteOne.getStamina());
		
		testAthleteOne.decreaseStamina();
		
		assertEquals(95, testAthleteOne.getStamina());
		
		testAthleteOne.restoreStamina();
		
		assertEquals(100, testAthleteOne.getStamina());
		
		testAthleteTwo.increaseAttack();
		testAthleteTwo.increaseDefence();
		
		// ensures that Attack and Defence cannot exceed 99
		assertEquals(99, testAthleteTwo.getAttack());
		assertEquals(99, testAthleteTwo.getDefence());
		
		// ensures Stamina cannot decrease below 0
		for (int i = 0; i < 22; i++) {
			testAthleteOne.decreaseStamina();
		}
		
		assertEquals(0, testAthleteOne.getStamina());
		
		// ensures Athletes can receive nicknames
		
		testAthleteOne.setName("Speedy");
		
		assertEquals("Speedy", testAthleteOne.getName());
		
		//ensures attack and defence is returning correct value after
		// decreasing stamina
		testAthleteOne.restoreStamina();
		
		assertEquals(27, testAthleteOne.getAttack());
		assertEquals(38, testAthleteOne.getDefence());
		
		testAthleteOne.decreaseStamina();
		testAthleteOne.decreaseStamina();
		
		assertEquals(24, testAthleteOne.getAttack());
		assertEquals(34, testAthleteOne.getDefence());
	}
	
	@Test
	public void testToString() {
		// ensures ToString method is working as intended
		String toString = testAthleteTwo.toString();
		
		assertEquals("James: $200: ATT: 99: DEF: 99: STA: 100", toString);
		
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Athlete;
import main.GameManager;

class GameManagerTest {

	private GameManager manager;
	private ArrayList<Athlete> AllMarketPlayers;
	private Athlete testAthleteOne;
	private Athlete testAthleteTwo;
	private Athlete testAthleteThree;
	private Athlete testAthleteFour;
	
	
	@BeforeEach
	public void setUp() {
		manager = new GameManager();
		testAthleteOne = new Athlete("John", 80, 70, 500);
		testAthleteTwo = new Athlete("Jame", 50, 40, 100);
		testAthleteThree = new Athlete("Randy", 34, 65, 400);
		testAthleteFour = new Athlete("Liam", 30, 50, 320);
		manager.createPlayersTeam("PlayerTestTeam");
		
	}
	
	

	@Test
	public void TestManager() {
		// Ensure that Gamemanager is initialising correctly
		assertEquals(1200, manager.getPlayersFunds());
		assertEquals(null, manager.getDifficulty());
		assertEquals(0, manager.getGameLength());
		assertEquals(0, manager.getPlayersPoints());
		manager.setName("PlayerTestTeam");
		assertEquals("PlayerTestTeam", manager.getTeamName());
		
		// Ensure that GameManager is updating its values correctly
		manager.setPlayersFunds(1000);
		manager.setGameLength(5);
		manager.setDifficulty("Hard");
		manager.setCurrentWeek(1);
		manager.increaseWeek();
		manager.increasePlayersPoints(1);
		manager.getPlayersPoints();
		
		assertEquals(1, manager.getPlayersPoints());
		assertEquals(1, manager.getPlayersPoints());
		assertEquals(1000, manager.getPlayersFunds());
		assertEquals(5, manager.getGameLength());
		assertEquals(2, manager.getCurrentWeek());
		assertEquals("Hard", manager.getDifficulty());
		
		manager.deductPlayerFunds(500);
		
		assertEquals(500, manager.getPlayersFunds());
		
		manager.increasePlayerFunds(50);
		
		assertEquals(550, manager.getPlayersFunds());
		
		// Ensure players are being added to Team through the GameManager
		
		manager.addToAttackers(testAthleteOne);
		manager.addToDefenders(testAthleteTwo);
		manager.addToSubstitutes(testAthleteTwo);

		manager.removeAttacker(0);
		manager.removeDefender(0);
		manager.removeSubstitute(0);
		
	
	}

}

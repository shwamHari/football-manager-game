package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Items.AthleteAttackIncreaser;
import Items.AthleteDefenceIncreaser;
import Items.AthleteStaminaRestorer;
import Items.Item;

import static org.junit.jupiter.api.Assertions.*;



import main.Athlete;
import main.GameManager;

class ItemsTest {

	private GameManager manager;
    private Athlete testAthleteOne;
    private Athlete testAthleteTwo;
    private Item testAttackItem;
    private Item testDefenceItem;
    private Item testStaminaItem;
    
    
    @BeforeEach
    public void setUp() {
    	// Initialise setup required for Items 
        manager = new GameManager();
        testAthleteOne = new Athlete("John", 80, 70, 500);
        manager.createPlayersTeam("testTeam");
        manager.addToSubstitutes(testAthleteOne);
        manager.addToSubstitutes(testAthleteTwo);
        
        // Ensure Items can be created 
        
        testAttackItem = new AthleteAttackIncreaser();
        testDefenceItem = new AthleteDefenceIncreaser();
        testStaminaItem = new AthleteStaminaRestorer();
    }

    @Test
    public void testReturnInformation() {
    	// Ensure Items are being created as intended and returning expected information
    	
        String expectedAttackInformation = "Increases the attacking power(ATT) of selected player in substitutes by 5. Note: The increase will not exceed the maximum value of 99.";
        assertEquals(expectedAttackInformation, testAttackItem.returnInformation());
        
        String expectedDefenceInformation = "Increases the defense power (DEF) of the selected player in substitutes by 5. Note: The increase will not exceed the maximum value of 99.";
        assertEquals(expectedDefenceInformation, testDefenceItem.returnInformation());
        
        String expectedStaminaInformation = "Restores the stamina of the selected player in substitutes to full (100).";
        assertEquals(expectedStaminaInformation, testStaminaItem.returnInformation());
        
        
        // Ensure cost is being called with the correct amount 
        
        assertEquals(100, testAttackItem.getCost());
        assertEquals(100, testDefenceItem.getCost());
        assertEquals(50, testStaminaItem.getCost());
        
        
        // Ensure toString is displaying correct correct price of Item and correct information
        
        assertEquals("$" + testAttackItem.getCost() + ": Athlete Attack Increaser", testAttackItem.toString() );
        assertEquals("$" + testDefenceItem.getCost() + ": Athlete Defence Increaser", testDefenceItem.toString() );
        assertEquals("$" + testStaminaItem.getCost() + " Athlete Stamina Restorer", testStaminaItem.toString() );

    }
    
    @Test
    public void testUseItem() {
    	// Ensures Athlete stats are being called correctly before testing Items on Athlete
    	assertEquals(80, testAthleteOne.getAttack());
    	assertEquals(70, testAthleteOne.getDefence());
    	assertEquals(100, testAthleteOne.getStamina());
    	
    	// Ensure Items can be called and used on desired Athlete
    	
    	testAttackItem.useItem(manager, testAthleteOne);
    	testDefenceItem.useItem(manager, testAthleteOne);
    	
    	//Ensure Athletes stats have been increased after using Item
    	
    	assertEquals(85, testAthleteOne.getAttack());
    	assertEquals(75, testAthleteOne.getDefence());
    	
    	testAthleteOne.decreaseStamina();
    	
    	assertEquals(95, testAthleteOne.getStamina());
    	
    	testStaminaItem.useItem(manager, testAthleteOne);
    	
    	assertEquals(100, testAthleteOne.getStamina());
    	
    	//Ensure Athlete stats cannot surpass 100 points when given Items that would increase stats past 100
    	
		for (int i = 0; i < 50; i++) {
			testAttackItem.useItem(manager, testAthleteOne);
			testDefenceItem.useItem(manager, testAthleteOne);
			testStaminaItem.useItem(manager, testAthleteOne);
		}
		
		assertEquals(100, testAthleteOne.getStamina());
		assertEquals(99, testAthleteOne.getDefence());
		assertEquals(99, testAthleteOne.getAttack());
		
    }

}

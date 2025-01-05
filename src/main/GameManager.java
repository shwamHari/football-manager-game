package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Items.AthleteAttackIncreaser;
import Items.AthleteDefenceIncreaser;
import Items.AthleteStaminaRestorer;
import Items.Item;
import screens.ClubMenu;
import screens.DraftTeamScreen;
import screens.GameFinishedScreen;
import screens.MainMenu;
import screens.MarketMenu;
import screens.MatchMenu;
import screens.SetupScreen;
import screens.StadiumMenu;


/**
 *
 * The GameManager class manages the game and its components.
 * 
 * @author Shyam Hari, Benjamin Joli
 * 
 */
public class GameManager {
	/**
	 * amount of money the user has at any given time
	 */
    private int playersFunds;
    /**
     * amount of money the user earns from matches throughout the game
     */
    private int moneyEarnt;
    
    /**
     * The user's chosen team name
     */
    private String teamname;

    /**
     * The user's team
     */
    private Team playersTeam;
    
    /**
     * The options the user will have to choose from for their next opponent.
     */
    
    private ArrayList<Team> nextOpponentOptions;
    /**
     * The next opponent the user's team will vs
     */
    private Team nextComputerTeam;

    /**
     * The selected difficulty by the user
     */
    private String difficulty;
    
    /*
     * The selected game length(number of weeks) by the user
     */
    private int gameLength;
    
    /**
     * The current week in the game
     */
    private int currentWeek;
    
    /**
     * The athlete options the user will be able to draft from
     */
    private ArrayList<Athlete> draftPlayers;
    
    /**
     * The athletes currently on the market
     */
    private ArrayList<Athlete> currentMarketPlayers;
    
    /**
     * The pool of potential athletes that could go on the market
     */
    private ArrayList<Athlete> allMarketPlayers;
    
    /**
     * Items currently on the market
     */
    private ArrayList<Item> currentMarketItems;
    
    /**
     * The pool of potential items that could go on the market
     */
    private ArrayList<Item> allMarketItems;
    
    /**
     * The items the player currently has
     */
    private ArrayList<Item> playersItems;
    
    /**
     * The game seed chosen by the user if they choose one. This will determine outcomes in the game. 
     */
    private Random gameRandom;
    
    /**
     * The pool of all athletes generated in the game to be split into 10 teams of 6, draftPlayers, allMarketPlayers and 
     */
    private ArrayList<Athlete> allGamePlayers;
    
    /**
     * The pool of teams in the game. There are 10 teams which will have random players from allGamePlayers each time. 
     */
    private ArrayList<Team> teamsPool;
    
    /**
     * A list of all the outcomes of matched throughout the game, to be shown at the end of the game.
     */
    private ArrayList<String> seasonOutcomes;
    
    /**
     * Number of points the player has earned throughout the game form matches.
     */
    private int playersPoints;

    
    /**
     * Creates the GameManager to be used to run the game
     */
    public GameManager() {
        playersFunds = 1200;
        teamname = null;
        difficulty = null;
        gameLength = 0;
        playersPoints = 0;
        gameRandom = new Random();
        
        playersItems = new ArrayList<Item>();
        allGamePlayers = new ArrayList<Athlete>();
        seasonOutcomes = new ArrayList<String>();
        
        generateGame();

    }

    // Launch Methods
    
    /**
     * Launches the setup screen
     */
    public void launchSetupScreen() {
        SetupScreen setupWindow = new SetupScreen(this);
    }
    
    
    /**
     * Launches the draft team screen
     */
    public void launchDraftTeamScreen() {
        DraftTeamScreen draftTeamWindow = new DraftTeamScreen(this);
    }

    /**
     * Launches the main menu
     */
    public void launchMainMenu() {
        MainMenu mainWindow = new MainMenu(this);
    }

    /**
     * Launches the club menu
     */
    public void launchClubMenu() {
        ClubMenu clubMenuWindow = new ClubMenu(this);
    }

    /**
     * Launches the market menu
     */
    public void launchMarketMenu() {
        MarketMenu marketMenu = new MarketMenu(this);
    }
    
    /**
     * Launches the stadium menu
     */
    public void launchStadiumMenu() {
        StadiumMenu stadiumMenu = new StadiumMenu(this);
    }

    /**
     * Launches the match menu
     */
    public void launchMatchMenu() {
        MatchMenu matchWindow = new MatchMenu(this);
    }
    
    /**
     * Launches the end of game screen
     */
    public void launchFinishedGameScreen() {
    	GameFinishedScreen gameFinishedWindow = new GameFinishedScreen(this);
    }

    // Close Methods
    
    /**
     * Closes the setup screen at the start of the game and opens the draft team screen for the user to draft their team
     * @param setupWindow The window to be closed
     */
    public void closeSetupScreen(SetupScreen setupWindow) {
        setupWindow.closeWindow();
		launchDraftTeamScreen();

    }
    
    /**
     * Closes the draft team screen and opens the club menu for the player to assign their drafted players to positions
     * @param draftTeamWindow The window to be closed
     */
    public void closeDraftTeamScreen(DraftTeamScreen draftTeamWindow) {
        draftTeamWindow.closeWindow();
        launchClubMenu();
    }
    
    /**
     * Closes the club menu screen and launches the main menu
     * @param clubMenuWindow The window to be closed
     */
    public void closeClubMenuScreen(ClubMenu clubMenuWindow) {
    	clubMenuWindow.closeWindow();
        MainMenu mainWindow = new MainMenu(this);
    }
    
    /**
     * Closes the match menu screen and launches the main menu
     * @param matchWindow The window to be closed
     */
    public void closeMatchMenu(MatchMenu matchWindow) {
        matchWindow.closeWindow();
        MainMenu mainWindow = new MainMenu(this);
    }
    
    /**
     * Closes the stadium menu screen and launches the main menu
     * @param stadiumWindow The window to be closed
     */
    public void closeStadiumMenu(StadiumMenu stadiumWindow) {
        stadiumWindow.closeWindow();
        MainMenu mainWindow = new MainMenu(this);
    }
    
    /**
     * Closes the market menu screen and launches the main menu
     * @param marketWindow The window to be closed
     */
    public void closeMarketMenu(MarketMenu marketWindow) {
    	marketWindow.closeWindow();
        MainMenu mainWindow = new MainMenu(this);
    }
    
    /**
     * Closes the main menu screen and launches the club menu
     * @param mainWindow The window to be closed
     */
    public void mainMenuToClubMenu(MainMenu mainWindow) {
    	mainWindow.closeWindow();
    	launchClubMenu();
    }
    
    /**
     * Closes the main menu screen and launches the main menu
     * @param mainWindow The window to be closed
     */
    public void mainMenuToMarketMenu(MainMenu mainWindow) {
    	mainWindow.closeWindow();
    	launchMarketMenu();
    }
    
    /**
     * Closes the main menu and launches the match menu
     * @param mainWindow The window to be closed
     */
    public void mainMenuToMatchMenu(MainMenu mainWindow) {
    	mainWindow.closeWindow();
    	launchMatchMenu();
    }
    
    /**
     * Closes the main menu and launches the stadium menu
     * @param mainWindow The window to be closed
     */
    public void mainMenuToStadiumMenu(MainMenu mainWindow) {
    	mainWindow.closeWindow();
    	launchStadiumMenu();
    }
    
    /**
     * Closes the main menu and launches the end of game screen
     * @param mainWindow The window to be closed
     */
    public void mainMenuToFinishedGame(MainMenu mainWindow) {
    	mainWindow.closeWindow();
    	launchFinishedGameScreen();
    }
    
    /**
     * Closes the finished game screen and ends the game
     * @param gameFinishedWindow The final window to be closed
     */
    public void closeGameFinishedScreen(GameFinishedScreen gameFinishedWindow) {
    	gameFinishedWindow.closeWindow();
    }

    // Getters
    /**
     * Retrieves and returns the items in the player's inventory
     * @return The ArrayList of items in the player's inventory
     */
    public ArrayList<Item> getplayersItems(){
    	return playersItems;
    }
    
    /**
     * Retrieves and returns the amount of money the player has
     * @return The players funds
     */
    public int getPlayersFunds() {
        return playersFunds;
    }
    
    /**
     * Retrieves and returns the player's Team object
     * @return The player's team
     */
    public Team getPlayersTeam() {
        return playersTeam;
    }
    
    /**
     * Retrieves and returns points the player has earnt thus far through matches
     * @return Points player has earnt
     */
    public int getPlayersPoints() {
    	return playersPoints;
    }
    
    /**
     * Retrieves and returns the money the player has earnt through matches
     * @return The amount of money the user has earned from matches
     */
    public int getMoneyEarnt() {
    	return moneyEarnt;
    }
    
    /**
     * Retrieves and returns the next opponent the player has selected and confirmed
     * @return The next team the player will go up against
     */
    public Team getNextOpponent() {
        return nextComputerTeam;
    }
    
    /**
     * Retrieves and returns the next opponent options from which the player will select their next opponent in the stadium menu
     * @return The next opponent options for the user to choose from
     */
    public ArrayList<Team> getNextOpponentOptions() {
        return nextOpponentOptions;
    }
    
    /**
     * Retrieves and returns the players team's name
     * @return The players team's name
     */
    public String getTeamName() {
        return teamname;
    }

    /**
     * Retrieves and returns the difficulty the player chose
     * @return The game difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Retrieves and returns the game length the user chose(number of weeks)
     * @return The length of the game(number of weeks)
     */
    public int getGameLength() {
        return gameLength;
    }
    
    /**
     * Retrieves and returns the list of match outcomes from the matches the player has played
     * @return The user's match outcomes
     */
    public ArrayList<String> getSeasonOutcomes() {
    	return seasonOutcomes;
    }
    
    /**
     * Retrieves and returns the current week in the game
     * @return The current week in the game
     */
    public int getCurrentWeek() {
        return currentWeek;
    }
    
    /**
     * Retrieves and returns the athletes that the athlete will draft their team from
     * @return The athletes to be drafted in the draft team screen
     */
    public ArrayList<Athlete> getPlayersToDraft() {
        return draftPlayers;
    }
    
    /**
     * Retrieves and returns the Random object created at the start of the game using either 1 as the seed, or the seed the user chose
     * 
     * @return A default random object or a Random object using the selected seed by the user, if they chose
     * a seed in the setup screen
     */
    public Random getRandom() {
    	return gameRandom;
    }

    // Setters
    
    /**
     * Creates a Random object to be used during the game to determine outcomes, using the seed the user has chosen.
     * @param seed The seed the user has chosen in the setup screen
     */
    public void setGameSeed(int seed) {
    	gameRandom = new Random(seed);
    }
    
    /**
     * Sets the player's funds they will receive at the start of the game.
     * @param funds The amount of funds the player will receive
     */
    public void setPlayersFunds(int funds) {
        playersFunds = funds;
    }
    
    /**
     * Creates the players team in the setup screen using the name the player has chosen
     * @param teamName The chosen team name by the user
     */
    public void createPlayersTeam(String teamName) {
        playersTeam = new Team(teamName);
    }

    /**
     * Sets the next opponent that the user will play against
     * @param nextOpponent The opponent the user will play against
     */
    public void setNextOpponent(Team nextOpponent) {
        nextComputerTeam = nextOpponent;
    }

    /**
     * Sets the user's team name
     * @param name The name the user has input in the setup screen
     */
    public void setName(String name) {
        teamname = name;
    }
    
    /**
     * Set's the difficulty the user has chosen in the setup screen
     * @param currentDifficulty the difficulty select(Normal or Hard)
     */
    public void setDifficulty(String currentDifficulty) {
        difficulty = currentDifficulty;
    }
    
    /**
     * Sets the game length(number of weeks) the user has chosen in the setup screen
     * @param newgameLength The number of weeks chosen
     */
    public void setGameLength(int newgameLength) {
        this.gameLength = newgameLength;
    }
    
    /**
     * Sets the current week of the game
     * @param currentWeek the current week of the game
     */
    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    // Other Methods
    /**
     * deducts players funds by an amount when they purchase players or items
     * @param amount The amount that the player's funds will be deducted
     */
    public void deductPlayerFunds(int amount) {
        playersFunds -= amount;
    }
    
    /**
     * Increases the player's funds when they sell players/items or after a game
     * @param amount The amount that the player's funds will increase by
     */
    public void increasePlayerFunds(int amount) {
        playersFunds += amount;
    }
    
    /**
     * Increases the money earnt total that the user has earnt throughout the game from matches
     * @param amount The amount of money earnt in the match
     */
    public void increaseMoneyEarnt(int amount) {
    	moneyEarnt += amount;
    }
    
    /**
     * Adds an athlete to the player's substitutes
     * @param player The athlete to be added to the player's substitutes
     */
    public void addToSubstitutes(Athlete player) {
        playersTeam.addSubstitute(player);
    }
    
    /**
     * Adds an athlete to the player's attackers
     * @param player The athlete to be addded to the player's attackers
     */
    public void addToAttackers(Athlete player) {
    	playersTeam.addAttacker(player);
    }
    
    /**
     * Add an athlete to the player's defenders
     * @param player The athlete to be added to the player's defenders
     */
    public void addToDefenders(Athlete player) {
    	playersTeam.addDefender(player);
    }
    
    /**
     * Removes an attacker from the player's attackers
     * @param index The index of the attacker to be removed
     */
    public void removeAttacker(int index) {
    	playersTeam.removeAttacker(index);
    }
    
    /**
     * Removes a defender from the player's defenders
     * @param index The index of the defender to be removed
     */
    public void removeDefender(int index) {
    	playersTeam.removeDefender(index);
    }
    
    /**
     * Removes a substitute from the player's substitutes
     * @param index The index of the athlete to be removed
     */
    public void removeSubstitute(int index) {
    	playersTeam.removeSubstitute(index);
    }
    
    /**
     * Adds an athlete to the pool of market athletes when the player sells an athlete
     * @param player The athlete that will be added to the pool of market athletes
     */
    public void addToMarket(Athlete player) {
    	allMarketPlayers.add(player);
    }
    
    /**
     * Removes an item from the player's inventory when they use the item
     * @param index The index of the item to be removed
     */
    public void removeItem(int index) {
    	playersItems.remove(index);
    }
    
    /**
     * Restore an athlete's stamina in the player's team
     * @param player Athlete whose stamina is to be restore
     */
    public void restoreAthletesStamina(Athlete player) {
    	playersTeam.restoreAthletesStamina(player);
    }
    
    /**
     * Increases the attack power of an athlete in the player's team
     * @param player Athlete whose attack is to be increased
     */
    public void increaseAthletesAttack(Athlete player) {
    	playersTeam.increaseAthletesAttack(player);
    }
    
    /**
     * Increases the defence power of an athlete in the player's team
     * @param player Athlete whose defence is to be increased
     */
    public void increaseAthletesDefence(Athlete player) {
    	playersTeam.increaseAthletesDefence(player);
    }
    
    /**
     * Set's a nickname for an athlete is the player's team
     * @param player Athlete whose name is to be updated
     * @param name The new nickname for the athlete
     */
    public void setAthleteName(Athlete player, String name) {
    	playersTeam.nicknameAthlete(player, name);
    	
    }
    
    /**
     * If a player buys an athlete from the market and wishes to 'substitute in now' then this will be called and take the
     * user to the club menu so that they can substitute in the purchased athlete
     * @param marketWindow The window to be closed
     */
    public void substituteNow(MarketMenu marketWindow) {
    	marketWindow.closeWindow();
        ClubMenu clubMenuWindow = new ClubMenu(this);
    }
    
    /**
     * Removes an athlete from the market if it has been purchased by the user
     * @param index The index of the purchased athlete
     */
    public void removePlayerFromMarket(int index) {
    	currentMarketPlayers.remove(index);
    	allMarketPlayers.remove(index);
    }
    
    /**
     * Removes an item from the market if it has been purchased by the user
     * @param index The index of the item purchased
     */
    public void removeItemFromMarket(int index) {
    	currentMarketItems.remove(index);
    	allMarketItems.remove(index);
    }
    
    /**
     * Retrieves and returns the athletes currently available on the market
     * @return The athletes currently available to be purchased on the market
     */
    public ArrayList<Athlete> getPlayersAvailable() {
    	return currentMarketPlayers;
    }
    
    /**
     * Retrieves and returns the items currently available on the market
     * @return The items available to be purchased on the market
     */
    public ArrayList<Item> getItemsAvailable() {
    	return currentMarketItems;
    	
    }
    
    /**
     * Adds an item to the players item inventory
     * @param newItem The new item to be added to the player's inventory
     */
    public void addToPlayersItems(Item newItem) {
    	playersItems.add(newItem);
    }
    
    /**
     * Decreases the players team's stamina after a match is player
     */
    public void decreasePlayersTeamStamina() {
    	playersTeam.decreaseTeamStamina();
    }
    
    /**
     * Adds the outcome of a match to a list that stores all the match outcomes. This will be displayed at the end of the game
     * @param outcome The outcome of a match
     */
    public void addOutcome(String outcome) {
    	seasonOutcomes.add(outcome);
    }
    
    /**
     * Increases the current week after a bye is taken or after a match is played
     */
    public void increaseWeek() {
    	currentWeek++;
    }
    
    /**
     * Increases the player's points after a game if they earnt any points
     * @param amount The number of points the player earned
     */
    public void increasePlayersPoints(int amount) {
    	playersPoints += amount;
    }
    
    /**
     * Retrieves and returns a new athlete to be added to the players team as a result of a random event
     * @return The new athlete that is added to the player's team
     */
    public Athlete newPlayer() {
    	int newIndex = allMarketPlayers.size() - 1;
    	Athlete player = allMarketPlayers.get(newIndex);
    	addToSubstitutes(player);
    	allMarketPlayers.remove(newIndex);
    	return player;
    }
    
    /**
     * This is called when a bye is taken by the user. It will update the market, update the opponent choices,
     * and give the user an attack increaser item and a defence increaser item for them to use on their athletes.
     * It will also restore the stamina of all the athletes on the user's team.
     */
    public void takeBye() {
    	// update opponents
    	shuffleNextOpponents();
    	
    	// update market
    	Collections.shuffle(allMarketItems);
    	Collections.shuffle(allMarketPlayers);
    	
    	ArrayList<Item> newMarketItems = new ArrayList<Item>();
    	ArrayList<Athlete> newMarketPlayers = new ArrayList<Athlete>();
    	for (int i = 0; i < 5; i++) {
    		newMarketItems.add(allMarketItems.get(i));
    		newMarketPlayers.add(allMarketPlayers.get(i));
    	}
    	currentMarketPlayers = newMarketPlayers;
    	currentMarketItems = newMarketItems;
    	
    	// give player an attack increaser and a defence increaser to train athletes
    	addToPlayersItems(new AthleteAttackIncreaser());
    	addToPlayersItems(new AthleteDefenceIncreaser());
    	
    	playersTeam.restoreTeamStamina();
    	
    	increaseWeek();
    	
    	setNextOpponent(null);
    }
    
    /**
     * Shuffles the next opponent options for the user to choose from.
     */
    public void shuffleNextOpponents() {
    	Collections.shuffle(teamsPool);
    	for (int i = 0; i < 3; i++) {
    		nextOpponentOptions.set(i, teamsPool.get(i));
    	}
    }
    
    
    /**
     * Generates 100 athlete objects that will be used in the game. It adds them to the allGamePlayers ArrayList
     * athlete prices were determined in the range of (attackingStat + defenceStat) plus or minus 30
     */
    public void generatePlayers() {
    	allGamePlayers.add(new Athlete("Ace", 81, 68, 141));
    	allGamePlayers.add(new Athlete("Bolt", 66, 92, 147));
    	allGamePlayers.add(new Athlete("Cruz", 87, 54, 138));
    	allGamePlayers.add(new Athlete("Dash", 73, 80, 133));
    	allGamePlayers.add(new Athlete("Echo", 58, 79, 113));
    	allGamePlayers.add(new Athlete("Finn", 92, 64, 161));
    	allGamePlayers.add(new Athlete("Gabe", 77, 94, 166));
    	allGamePlayers.add(new Athlete("Hugo", 64, 80, 128));
    	allGamePlayers.add(new Athlete("Ivy", 90, 84, 183));
    	allGamePlayers.add(new Athlete("Jax", 93, 87, 198));
    	allGamePlayers.add(new Athlete("Kai", 85, 92, 181));
    	allGamePlayers.add(new Athlete("Lyla", 75, 71, 142));
    	allGamePlayers.add(new Athlete("Max", 89, 55, 133));
    	allGamePlayers.add(new Athlete("Nina", 84, 88, 183));
    	allGamePlayers.add(new Athlete("Orion", 51, 87, 144));
    	allGamePlayers.add(new Athlete("Piper", 92, 91, 211));
    	allGamePlayers.add(new Athlete("Quinn", 82, 57, 132));
    	allGamePlayers.add(new Athlete("Ryder", 68, 72, 126));
    	allGamePlayers.add(new Athlete("Sky", 93, 86, 197));
    	allGamePlayers.add(new Athlete("Tess", 84, 64, 146));
    	allGamePlayers.add(new Athlete("Vince", 79, 93, 178));
    	allGamePlayers.add(new Athlete("Zara", 61, 59, 105));
    	allGamePlayers.add(new Athlete("Axel", 89, 73, 181));
    	allGamePlayers.add(new Athlete("Belle", 52, 83, 131));
    	allGamePlayers.add(new Athlete("Cody", 83, 74, 171));
    	allGamePlayers.add(new Athlete("Dylan", 63, 76, 129));
    	allGamePlayers.add(new Athlete("Eva", 93, 84, 200));
    	allGamePlayers.add(new Athlete("Felix", 74, 84, 162));
    	allGamePlayers.add(new Athlete("Gemma", 88, 85, 198));
    	allGamePlayers.add(new Athlete("Hank", 56, 72, 119));
    	allGamePlayers.add(new Athlete("Iris", 84, 82, 181));
    	allGamePlayers.add(new Athlete("Jude", 82, 79, 179));
    	allGamePlayers.add(new Athlete("Kira", 70, 58, 120));
    	allGamePlayers.add(new Athlete("Liam", 89, 86, 202));
    	allGamePlayers.add(new Athlete("Mia", 77, 83, 150));
    	allGamePlayers.add(new Athlete("Noah", 86, 92, 200));
    	allGamePlayers.add(new Athlete("Olivia", 63, 68, 131));
    	allGamePlayers.add(new Athlete("Pax", 93, 92, 212));
    	allGamePlayers.add(new Athlete("Quincy", 79, 57, 132));
    	allGamePlayers.add(new Athlete("Riley", 57, 70, 116));
    	allGamePlayers.add(new Athlete("Sam", 92, 85, 199));
    	allGamePlayers.add(new Athlete("Tara", 81, 71, 152));
    	allGamePlayers.add(new Athlete("Vera", 66, 60, 110));
    	allGamePlayers.add(new Athlete("Zane", 88, 74, 190));
    	allGamePlayers.add(new Athlete("Ash", 85, 83, 191));
    	allGamePlayers.add(new Athlete("Blake", 69, 86, 144));
    	allGamePlayers.add(new Athlete("Clara", 78, 57, 139));
    	allGamePlayers.add(new Athlete("Dylan", 92, 88, 212));
    	allGamePlayers.add(new Athlete("Ella", 75, 84, 152));
    	allGamePlayers.add(new Athlete("Finn", 91, 70, 192));
    	allGamePlayers.add(new Athlete("Grace", 80, 73, 153));
    	allGamePlayers.add(new Athlete("Henry", 63, 63, 129));
    	allGamePlayers.add(new Athlete("Isabella", 94, 90, 216));
    	allGamePlayers.add(new Athlete("Jack", 77, 80, 152));
    	allGamePlayers.add(new Athlete("Knox", 86, 92, 202));
    	allGamePlayers.add(new Athlete("Lily", 68, 71, 139));
    	allGamePlayers.add(new Athlete("Mason", 88, 85, 201));
    	allGamePlayers.add(new Athlete("Nora", 76, 83, 151));
    	allGamePlayers.add(new Athlete("Oliver", 93, 74, 200));
    	allGamePlayers.add(new Athlete("Paige", 82, 79, 177));
    	allGamePlayers.add(new Athlete("Quentin", 61, 70, 115));
    	allGamePlayers.add(new Athlete("Ruby", 90, 91, 204));
    	allGamePlayers.add(new Athlete("Seth", 81, 57, 125));
    	allGamePlayers.add(new Athlete("Tessa", 95, 83, 213));
    	allGamePlayers.add(new Athlete("Uma", 75, 89, 179));
    	allGamePlayers.add(new Athlete("Violet", 88, 82, 194));
    	allGamePlayers.add(new Athlete("Wyatt", 71, 68, 139));
    	allGamePlayers.add(new Athlete("Xander", 82, 75, 162));
    	allGamePlayers.add(new Athlete("Yara", 56, 81, 130));
    	allGamePlayers.add(new Athlete("Zack", 90, 90, 207));
    	allGamePlayers.add(new Athlete("Neo", 88, 82, 189));
    	allGamePlayers.add(new Athlete("Zara", 76, 75, 151));
    	allGamePlayers.add(new Athlete("Kai", 82, 88, 187));
    	allGamePlayers.add(new Athlete("Luna", 65, 78, 137));
    	allGamePlayers.add(new Athlete("Ryder", 89, 86, 201));
    	allGamePlayers.add(new Athlete("Zoe", 74, 81, 155));
    	allGamePlayers.add(new Athlete("Milo", 90, 92, 209));
    	allGamePlayers.add(new Athlete("Stella", 67, 70, 137));
    	allGamePlayers.add(new Athlete("Atlas", 87, 84, 189));
    	allGamePlayers.add(new Athlete("Nova", 76, 76, 152));
    	allGamePlayers.add(new Athlete("Felix", 93, 90, 213));
    	allGamePlayers.add(new Athlete("Isla", 71, 74, 141));
    	allGamePlayers.add(new Athlete("Jasper", 86, 83, 190));
    	allGamePlayers.add(new Athlete("Poppy", 69, 73, 141));
    	allGamePlayers.add(new Athlete("Axel", 91, 88, 205));
    	allGamePlayers.add(new Athlete("Zara", 76, 75, 151));
    	allGamePlayers.add(new Athlete("Knox", 82, 87, 187));
    	allGamePlayers.add(new Athlete("Luna", 64, 77, 135));
    	allGamePlayers.add(new Athlete("Ryker", 89, 85, 200));
    	allGamePlayers.add(new Athlete("Zoe", 74, 80, 154));
    	allGamePlayers.add(new Athlete("Milo", 90, 91, 208));
    	allGamePlayers.add(new Athlete("Stella", 67, 69, 136));
    	allGamePlayers.add(new Athlete("June", 88, 83, 190));
    	allGamePlayers.add(new Athlete("Nova", 76, 75, 151));
    	allGamePlayers.add(new Athlete("Felix", 93, 89, 212));
    	allGamePlayers.add(new Athlete("Isla", 70, 73, 140));
    	allGamePlayers.add(new Athlete("Jasper", 86, 82, 188));
    	allGamePlayers.add(new Athlete("Poppy", 69, 72, 140));
    	allGamePlayers.add(new Athlete("Ace", 92, 87, 204));
    	allGamePlayers.add(new Athlete("Ivy", 75, 78, 151));
    	allGamePlayers.add(new Athlete("Leo", 90, 91, 208));
    }
    
    /**
     * Create the 10 teams in the 'League' that the user could go up against and adds them to the 'teamsPool'
     * The first 60 of the generated players will then be randomly assigned to these 10 teams(6 players in a team).
     *  The next 15 players will be added to the 'athletes to be drafted' list from which the athlete will draft their
     *  team at the beginning of the game. Finally, the remaining 25 players will be the pool of all market players and 
     *  5 of these will be added to the current market from which the athlete could purchase these 5.
     *  This function will also set the first set of next opponent options in the game.
     */
    public void assignPlayers() {
    	teamsPool = new ArrayList<Team>();
    	
    	teamsPool.add(new Team("Blazers"));
    	teamsPool.add(new Team("Strikers"));
        teamsPool.add(new Team("Phoenixes"));
        teamsPool.add(new Team("Maverick"));
        teamsPool.add(new Team("Inferno"));
        teamsPool.add(new Team("Fury"));
        teamsPool.add(new Team("Gladiators"));
        teamsPool.add(new Team("Zenith"));
        teamsPool.add(new Team("Rampage"));
        teamsPool.add(new Team("Nova"));
        
        Collections.shuffle(teamsPool);
        
        nextOpponentOptions = new ArrayList<Team>();
        
        for (int i = 0; i < 3; i++) {
        	nextOpponentOptions.add(teamsPool.get(i));
        }
        
        // assign players to teams
        Collections.shuffle(allGamePlayers);

        for (int i = 0; i < 10; i++) {
            Team team = teamsPool.get(i);
            for (int j = 0; j < 3; j++) {
                Athlete attacker = allGamePlayers.get(i * 6 + j);
                Athlete defender = allGamePlayers.get(i * 6 + j + 3);
                team.addAttacker(attacker);
                team.addDefender(defender);
            }
            teamsPool.set(i, team);
        }
        
        
        // assign players to be drafted
        draftPlayers = new ArrayList<Athlete>();
        
        for (int i = 60; i < 75; i++) {
        	draftPlayers.add(allGamePlayers.get(i));
        }
        
        // remaining players go on the market
        allMarketPlayers = new ArrayList<Athlete>();
        
        for (int i = 75; i < 100; i++) {
        	allMarketPlayers.add(allGamePlayers.get(i));
        }
        
        currentMarketPlayers = new ArrayList<Athlete>();
        
        for (int i = 0; i < 5; i++) {
        	currentMarketPlayers.add(allMarketPlayers.get(i));
        }
    }
    
    /**
     * Generates 30 items(10 of each type) which could be used in the game. These items are shuffled and 5 will be 
     * put on the current market to be potentially purchased by the user
     */
    public void generateItems() {
    	// creates the items in the game and puts 5 on the market
    	allMarketItems = new ArrayList<Item>();
    	
    	for (int i = 0 ; i < 10; i++) {
    		allMarketItems.add(new AthleteStaminaRestorer());
    		allMarketItems.add(new AthleteAttackIncreaser());
    		allMarketItems.add(new AthleteDefenceIncreaser());
    	}
    	
    	Collections.shuffle(allMarketItems);
    	
    	currentMarketItems = new ArrayList<Item>();

    	for (int i = 0; i < 5; i++) {
        	currentMarketItems.add(allMarketItems.get(i));
    	}
    }
    
    
    /**
     * Calls the functions associated with generating the game.
     */
    public void generateGame() {
    	generatePlayers();
    	assignPlayers();
    	generateItems();
    }

    /**
     * This main function will be called when the app is run and will create the game.
     * @param args The arguments to run the game.
     */
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        manager.launchSetupScreen();
    }
}

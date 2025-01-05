package screens;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;

import main.Athlete;
import main.GameManager;

/**
 * 
 * The MainMenu class represents the main menu screen of a game. It allows the player to navigate to different menus
 * such as the club menu, stadium menu, market menu, and match menu. It also displays various information about the game
 * and provides options to take a bye or view the game rules.
 * 
 * @author Shyam Hari, Benjamin Joli
 * 
 */
public class MainMenu {

	private JFrame mainMenuFrame;
	private GameManager manager;
	
	
	/**
	 * Constructs a MainMenu object with the specified GameManager.
	 * If the current week is greater than the game length, it launches the finished game screen;
	 * otherwise, it initializes and displays the main menu.
	 *
	 * @param gameManager the GameManager object for the game
	 */
	public MainMenu(GameManager gameManager) {
		manager = gameManager;
		if (manager.getCurrentWeek() > manager.getGameLength()) {
			manager.launchFinishedGameScreen();
		}
		else {
			initialize();
			mainMenuFrame.setVisible(true);
		}
	}
	
	
	/**
	 * Closes the main menu window.
	 */
	public void closeWindow() {
		mainMenuFrame.dispose();
	}
	
	/**
	 * Navigates to the club menu.
	 */
	public void toClubMenu() {
		manager.mainMenuToClubMenu(this);
	}
	
	/**
	 * Navigates to the stadium menu.
	 */
	public void toStadiumMenu() {
		manager.mainMenuToStadiumMenu(this);
	}
	
	/**
	 * Navigates to the market menu.
	 */
	public void toMarketMenu() {
		manager.mainMenuToMarketMenu(this);
	}
	
	/**
	 * Navigates to the match menu.
	 */
	public void toMatchMenu() {
		manager.mainMenuToMatchMenu(this);
	}
	
	/**
	 * Navigates to the finished game screen.
	 */
	public void toFinishedGameScreen() {
		manager.mainMenuToFinishedGame(this);
	}


	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. Allows the user to navigate to different windows
	 */
	private void initialize() {
		mainMenuFrame = new JFrame();
		mainMenuFrame.setBounds(100, 100, 650, 450);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton MarketButton = new JButton("Visit Market");
		MarketButton.setBounds(76, 319, 89, 37);
		MarketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toMarketMenu();
			}
		});
		mainMenuFrame.getContentPane().setLayout(null);
		mainMenuFrame.getContentPane().add(MarketButton);
		
		JButton PlayGameButtom = new JButton("Play Game");
		PlayGameButtom.setBounds(379, 319, 89, 37);
		PlayGameButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean injured = false;
				
				if (manager.getPlayersTeam().getAttackers().size() == 3 && manager.getPlayersTeam().getDefenders().size() == 3) {
					for (int i = 0; i < 3; i++) {
						if (manager.getPlayersTeam().getAttackers().get(i).getStamina() < 0 || manager.getPlayersTeam().getDefenders().get(i).getStamina() < 0) {
							injured = true;
						}
					}
				}
				
				if (injured == true || manager.getNextOpponent() == null || manager.getPlayersTeam().getAttackers().size() < 3 || manager.getPlayersTeam().getDefenders().size() < 3) {
					if (injured == true) {
						String message = "1 or more of your team is injured. You cannot use injured players";
						JOptionPane.showMessageDialog(mainMenuFrame, message, "Player/s injured", JOptionPane.ERROR_MESSAGE);
					}
					if (manager.getNextOpponent() == null) {
						JOptionPane.showMessageDialog(mainMenuFrame, "You must select an opponent in the stadium menu.", "No Team Selected", JOptionPane.ERROR_MESSAGE);
					}
					if (manager.getPlayersTeam().getAttackers().size() < 3 || manager.getPlayersTeam().getDefenders().size() < 3) {
						String message = "Your team is incomplete. You must allocate 3 attackers and 3 defenders";
						JOptionPane.showMessageDialog(mainMenuFrame, message, "Incomplete Team", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					toMatchMenu();
				}
			}
		});
		mainMenuFrame.getContentPane().add(PlayGameButtom);
		
		JButton ClubMenuButton = new JButton("Visit Club");
		ClubMenuButton.setBounds(177, 319, 89, 37);
		ClubMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toClubMenu();
			}
		});
		mainMenuFrame.getContentPane().add(ClubMenuButton);
		
		JLabel DifficultyLabel = new JLabel("Difficulty: " + manager.getDifficulty());
		DifficultyLabel.setBounds(10, 111, 634, 27);
		DifficultyLabel.setHorizontalAlignment(0);
		mainMenuFrame.getContentPane().add(DifficultyLabel);
		
		JLabel TeamNameLabel = new JLabel("Team: " + manager.getTeamName());
		TeamNameLabel.setBounds(10, 71, 634, 27);
		TeamNameLabel.setHorizontalAlignment(0);
		mainMenuFrame.getContentPane().add(TeamNameLabel);
		
		JLabel FundsLabel = new JLabel("Funds: $" + manager.getPlayersFunds());
		FundsLabel.setBounds(10, 152, 634, 27);
		FundsLabel.setHorizontalAlignment(0);
		mainMenuFrame.getContentPane().add(FundsLabel);
		
		JLabel currentWeekLabel = new JLabel("Current Week: " + manager.getCurrentWeek());
		currentWeekLabel.setBounds(201, 232, 128, 37);
		mainMenuFrame.getContentPane().add(currentWeekLabel);
		
		JLabel WeeksLeftLabel = new JLabel("Weeks Left: " + (manager.getGameLength() - manager.getCurrentWeek()));
		WeeksLeftLabel.setBounds(357, 236, 132, 27);
		mainMenuFrame.getContentPane().add(WeeksLeftLabel);
		
		JButton TakeByeButton = new JButton("Take Bye");
		TakeByeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure you want to take a bye this week?" + "\n" + 
			"- Your opponent choices in the stadium will be updated and items and players on the market will also be updated" + "\n"
			+ "- You will receive an Athlete Attack Increaser item and an Athlete Defence Increaser item to train players of your choice in the club menu." + "\n"
			+ "- The stamina of all players in your team will be restored";
				int answer = JOptionPane.showConfirmDialog(mainMenuFrame, message, "Confirm bye", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION) {
					manager.takeBye();
					currentWeekLabel.setText("Current Week: " + manager.getCurrentWeek());
					WeeksLeftLabel.setText("Weeks Left: " + (manager.getGameLength() - manager.getCurrentWeek()));
					if (manager.getCurrentWeek() > manager.getGameLength()) {
						toFinishedGameScreen();
					}
					
				}
				
				Random rng = manager.getRandom();
				int num = rng.nextInt(25);
				int playerIndex = rng.nextInt(3);

				if (num == 1) {
					if (!(manager.getPlayersTeam().getAttackers().size() > playerIndex)) {
						String information = manager.getPlayersTeam().getAttackers().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						manager.removeAttacker(playerIndex);
					}
					else if (!(manager.getPlayersTeam().getDefenders().size() > playerIndex)) {
						String information = manager.getPlayersTeam().getDefenders().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						
						manager.removeDefender(playerIndex);
					}
					else if (!(manager.getPlayersTeam().getSubstitutes().size() > playerIndex)) {
						String information = manager.getPlayersTeam().getSubstitutes().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						
						manager.removeDefender(playerIndex);
					}
					
				}
				else if (num == 2) {
					if (!(manager.getPlayersTeam().getDefenders().size() > playerIndex)) {
						String information = manager.getPlayersTeam().getDefenders().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						
						manager.removeDefender(playerIndex);
					}
					else if (!(manager.getPlayersTeam().getAttackers().size() > playerIndex)) {
						String information = manager.getPlayersTeam().getAttackers().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						manager.removeAttacker(playerIndex);
					}
					else if (!(manager.getPlayersTeam().getSubstitutes().size() > playerIndex)) {
						String information = manager.getPlayersTeam().getSubstitutes().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						
						manager.removeDefender(playerIndex);
					}
					
				}
				else if (num == 3) {
					if (!(manager.getPlayersTeam().getAttackers().size() > playerIndex)) {
						Athlete player = manager.getPlayersTeam().getAttackers().get(playerIndex);
						String information = player.getName() + " has been training very hard and their attack power(ATT) has increased by 5";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Attack increase", JOptionPane.INFORMATION_MESSAGE);
						player.increaseAttack();
					}
					
					else if (!(manager.getPlayersTeam().getDefenders().size() > playerIndex)) {
						Athlete player = manager.getPlayersTeam().getDefenders().get(playerIndex);
						String information = player.getName() + " has been training very hard and their defence power(DEF) has increased by 5";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Defence increase", JOptionPane.INFORMATION_MESSAGE);
						player.increaseDefence();
					}					
				}
				
				else if (num == 4) {
					if (!(manager.getPlayersTeam().getDefenders().size() > playerIndex)) {
						Athlete player = manager.getPlayersTeam().getDefenders().get(playerIndex);
						String information = player.getName() + " has been training very hard and their defence power(DEF) has increased by 5";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Defence increase", JOptionPane.INFORMATION_MESSAGE);
						player.increaseDefence();
					}		
					else if (!(manager.getPlayersTeam().getAttackers().size() > playerIndex)) {
						Athlete player = manager.getPlayersTeam().getAttackers().get(playerIndex);
						String information = player.getName() + " has been training very hard and their attack power(ATT) has increased by 5";
						JOptionPane.showMessageDialog(mainMenuFrame, information, "Attack increase", JOptionPane.INFORMATION_MESSAGE);
						player.increaseAttack();
					}
				}
				else if (num == 5) {
					Athlete player = manager.newPlayer();
					String information = player.getName() + " has joined your club! They are now in your substitutes.";
					JOptionPane.showMessageDialog(mainMenuFrame, information, "New Athlete", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		TakeByeButton.setBounds(480, 319, 89, 37);
		mainMenuFrame.getContentPane().add(TakeByeButton);
		
		JButton stadiumMenuButton = new JButton("Stadium");
		stadiumMenuButton.setBounds(278, 319, 89, 37);
		stadiumMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toStadiumMenu();
			}
		});
		mainMenuFrame.getContentPane().add(stadiumMenuButton);
		
		JLabel mainMenuLabel = new JLabel("Main Menu");
		mainMenuLabel.setBounds(0, 6, 650, 35);
		mainMenuLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		mainMenuLabel.setHorizontalAlignment(0);
		mainMenuFrame.getContentPane().add(mainMenuLabel);
		
		JLabel pointsLabel = new JLabel("Points: " + manager.getPlayersPoints());
		pointsLabel.setBounds(10, 193, 634, 27);
		pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuFrame.getContentPane().add(pointsLabel);
		
		JButton viewRulesButton = new JButton("View Rules");
		viewRulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rules = "- Points are awarded in matches for each successful encounter ie. for every successful attack and defend." + "\n"
								+ "- If you win the match, you will receive a bonus point for each goal your team scored." + "\n" 
						+ "- You will receive $20 after each game and $5 for every point earnt in that game." + "\n"
								+ "- Your athletes that play in the game will lose 5 stamina after each game." + "\n"
						+ "- Stamina will affect how well the player performs. " + "\n"
								+ "   Eg. If their ATT is 80 and their stamina is 75, in game, their ATT will only be 60(75% of 80)." + "\n"
						+ "If an athlete's stamina goes down to 0, they will be considered injured and unable to play in the next game."
								+ "- You will have to select an opponent from the stadium menu in order to play each match." + "\n"
						+ "- Random events can occur throughout the game which may benefit or disadvantage you." + "\n"
								+ "- To view what will happen when taking a bye, click the take bye button." + "\n" 
						+ "- Good luck and we hope you earn as many points as possible!" + "\n"
								+ "- If you do not have enough athletes and do not have enough money to buy any, you will have to take a bye";
				JOptionPane.showMessageDialog(mainMenuFrame, rules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		viewRulesButton.setBounds(10, 6, 89, 37);
		mainMenuFrame.getContentPane().add(viewRulesButton);
	}

}

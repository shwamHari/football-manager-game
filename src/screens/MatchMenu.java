package screens;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import main.Athlete;
import main.GameManager;
import main.Team;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * 
 * The MatchMenu class represents a GUI for the match menu in the game.
 * It allows players to view matchups of the teams, reveal the details of each outcome, and complete the match.
 * 
 * @author Shyam Hari
 * 
 */
public class MatchMenu {
	
	private int playerTeamScore;
	private int computerTeamScore;
	private ArrayList<String> outcomes;
	private ArrayList<String> matchUps;
	private String matchOutcome;
	private GameManager manager;
	private int pointsEarned;

	private JFrame matchMenuFrame;
	

	/**
	 * Create a MatchMenu object with the specified game manager.
	 *
	 * @param incomingManager The GameManager instance associated with the game.
	 */
	public MatchMenu(GameManager incomingManager) {
		
		
		manager = incomingManager;
		matchUps = new ArrayList<String>();
		outcomes = new ArrayList<String>(); 
			
		
		Team playerTeam = manager.getPlayersTeam();
		Team computerTeam = manager.getNextOpponent();
		
		
		// create duplicate arrays to determine random outcomes
		ArrayList<Athlete> playerAttackers = new ArrayList<Athlete>();
		playerAttackers.addAll(playerTeam.getAttackers());
		ArrayList<Athlete> computerAttackers = new ArrayList<Athlete>();
		computerAttackers.addAll(computerTeam.getAttackers());
		ArrayList<Athlete> playerDefenders = new ArrayList<Athlete>();
		playerDefenders.addAll(playerTeam.getDefenders());
		ArrayList<Athlete> computerDefenders = new ArrayList<Athlete>();
		computerDefenders.addAll(computerTeam.getDefenders());
		
		
		playerTeamScore = 0;
		computerTeamScore = 0;
		pointsEarned = 0;
		
		// Determine outcomes from players attacking
		for (int i = 3 ; i > 0 ; i--) {
			Random rng = manager.getRandom();
			int nextPlayerIndex = rng.nextInt(i);
			int nextComputerIndex = rng.nextInt(i);
			
			Athlete playersPlayer = playerAttackers.get(nextPlayerIndex);
			Athlete computersPlayer = computerDefenders.get(nextComputerIndex);
			
			String matchUp = playersPlayer.getName() + " to attack against " + computersPlayer.getName();
			matchUps.add(matchUp);
			
			if (playersPlayer.getAttack() > computersPlayer.getDefence()) {
				String outcome = playersPlayer.getName() + "(ATT: " +  + playersPlayer.getAttack() + ") scored against " 
			+ computersPlayer.getName() + "(DEF: " + computersPlayer.getDefence() +  "): " + playerTeam.getName() + " +1";				
				outcomes.add(outcome);
				playerTeamScore++;
				pointsEarned++;
			}
			else {
				String outcome = playersPlayer.getName() + "(ATT: " +  + playersPlayer.getAttack() + ") failed to score against " 
			+ computersPlayer.getName() + "(DEF: " + computersPlayer.getDefence() +  "): " + computerTeam.getName() + " +1";
				outcomes.add(outcome);
				computerTeamScore++;
			}
			playerAttackers.remove(nextPlayerIndex);
			computerDefenders.remove(nextComputerIndex);
		}
		
		// Determine outcomes for players defending
		for (int i = 3 ; i > 0 ; i--) {
			Random rng = manager.getRandom();
			int nextPlayerIndex = rng.nextInt(i);
			
			int nextComputerIndex = rng.nextInt(i);
			
			
			Athlete playersPlayer = playerDefenders.get(nextPlayerIndex);
			Athlete computersPlayer = computerAttackers.get(nextComputerIndex);
			
			String matchUp = playersPlayer.getName() + " to defened against " + computersPlayer.getName();
			matchUps.add(matchUp);
			if (computersPlayer.getAttack() > playersPlayer.getDefence()) {
				String outcome = playersPlayer.getName() + "(DEF: " + playersPlayer.getDefence() + ") failed to defend " 
			+ computersPlayer.getName() + "(ATT: " + computersPlayer.getAttack() +  "): " + computerTeam.getName() + " +1";
				outcomes.add(outcome);
				computerTeamScore++;
			}
			else {
				String outcome = playersPlayer.getName() + "(DEF: " + playersPlayer.getDefence() + ") successfully defended " 
			+ computersPlayer.getName() + "(ATT: " + computersPlayer.getAttack() +  "): " + playerTeam.getName() + " +1";
				outcomes.add(outcome);
				playerTeamScore++;
				pointsEarned++;
			}
			playerDefenders.remove(nextPlayerIndex);
			computerAttackers.remove(nextComputerIndex);
		}
			
		
		// shuffle the outcomes. Shuffle the matchups and outcomes in the same way
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ints.add(0);
		ints.add(1);
		ints.add(2);
		ints.add(3);
		ints.add(4);
		ints.add(5);

		Collections.shuffle(ints);
		
		ArrayList<String> finalOutcomes = new ArrayList<String>();
		ArrayList<String> finalMatchups = new ArrayList<String>();
		
		for (int num : ints) {
			finalOutcomes.add(outcomes.get(num));
			finalMatchups.add(matchUps.get(num));
		}
		
		outcomes = finalOutcomes;
		matchUps = finalMatchups;
		
		// determine match outcome
		if (playerTeamScore > computerTeamScore) {
			//bonus points if the player wins
			pointsEarned += playerTeamScore;
			matchOutcome = playerTeam.getName() + " won against " + computerTeam.getName() + ": " + playerTeamScore + "-" + computerTeamScore
					+ "\n" + "Points earned: " + pointsEarned;
		}
		else if (computerTeamScore > playerTeamScore) {
			matchOutcome = playerTeam.getName() + " lost against " + computerTeam.getName() + ": " + playerTeamScore + "-" + computerTeamScore
					+ "\n" + "Points earned: " + pointsEarned;
		}
		else {
			matchOutcome = playerTeam.getName() + " drew with " + computerTeam.getName() + ": " + playerTeamScore + "-" + computerTeamScore
					+ "\n" + "Points earned: " + pointsEarned;
		}
		
		// finish the match and aftermath
		manager.decreasePlayersTeamStamina();

		manager.addOutcome(matchOutcome);
		manager.increaseWeek();
		manager.increasePlayersPoints(pointsEarned);
		manager.shuffleNextOpponents();
		manager.increaseMoneyEarnt(20 + pointsEarned*5);
		manager.increasePlayerFunds(20 + pointsEarned*5);
		
		initialize();
		matchMenuFrame.setVisible(true);
				
	}

	
	/**
	 * Closes the window
	 */
	public void closeWindow() {
		matchMenuFrame.dispose();
	}
	
	 /**
	  * calls the game manager function to close this window and open the main menu after a match
	  */
	public void finishedWindow() {	
		manager.closeMatchMenu(this);
	}
	
	
	
	/**
	 * Initialize the contents of the frame. Assigns the determined outcomes and allows for them to be made
	 * visible by the user
	 */
	private void initialize() {
		matchMenuFrame = new JFrame();
		matchMenuFrame.setBounds(100, 100, 650, 450);
		matchMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		matchMenuFrame.getContentPane().setLayout(null);
		
		JLabel matchupTitle = new JLabel(manager.getPlayersTeam().getName() + " vs " + manager.getNextOpponent().getName());
		matchupTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		matchupTitle.setHorizontalAlignment(JLabel.CENTER);
		matchupTitle.setBounds(6, 6, 638, 30);
		matchMenuFrame.getContentPane().add(matchupTitle);
		
		JLabel outcome1 = new JLabel(matchUps.get(0));
		outcome1.setBounds(32, 63, 449, 30);
		matchMenuFrame.getContentPane().add(outcome1);
		
		JLabel outcome2 = new JLabel(matchUps.get(1));
		outcome2.setBounds(32, 105, 449, 30);
		matchMenuFrame.getContentPane().add(outcome2);
		
		JLabel outcome3 = new JLabel(matchUps.get(2));
		outcome3.setBounds(32, 147, 449, 30);
		matchMenuFrame.getContentPane().add(outcome3);
		
		JLabel outcome4 = new JLabel(matchUps.get(3));
		outcome4.setBounds(32, 189, 449, 30);
		matchMenuFrame.getContentPane().add(outcome4);
		
		JLabel outcome5 = new JLabel(matchUps.get(4));
		outcome5.setBounds(32, 231, 449, 30);
		matchMenuFrame.getContentPane().add(outcome5);
		
		JLabel outcome6 = new JLabel(matchUps.get(5));
		outcome6.setBounds(32, 273, 449, 30);
		matchMenuFrame.getContentPane().add(outcome6);
		
		JButton revealOutcome1 = new JButton("Reveal");
		revealOutcome1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome1.setText(outcomes.get(0));
			}
		});
		revealOutcome1.setBounds(511, 63, 117, 30);
		matchMenuFrame.getContentPane().add(revealOutcome1);
		
		
		JButton revealOutcome2 = new JButton("Reveal");
		revealOutcome2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome2.setText(outcomes.get(1));
			}
		});
		revealOutcome2.setBounds(511, 107, 117, 30);
		matchMenuFrame.getContentPane().add(revealOutcome2);
		
		
		JButton revealOutcome3 = new JButton("Reveal");
		revealOutcome3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome3.setText(outcomes.get(2));
			}
		});
		revealOutcome3.setBounds(511, 149, 117, 30);
		matchMenuFrame.getContentPane().add(revealOutcome3);
		
		
		JButton revealOutcome4 = new JButton("Reveal");
		revealOutcome4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome4.setText(outcomes.get(3));
			}
		});
		revealOutcome4.setBounds(511, 191, 117, 30);
		matchMenuFrame.getContentPane().add(revealOutcome4);
		
		
		JButton revealOutcome5 = new JButton("Reveal");
		revealOutcome5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome5.setText(outcomes.get(4));
			}
		});
		revealOutcome5.setBounds(511, 233, 117, 30);
		matchMenuFrame.getContentPane().add(revealOutcome5);
		
		
		JButton revealOutcome6 = new JButton("Reveal");
		revealOutcome6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome6.setText(outcomes.get(5));
			}
		});
		revealOutcome6.setBounds(511, 275, 117, 30);
		matchMenuFrame.getContentPane().add(revealOutcome6);
		
		
		JButton completeMatch = new JButton("Complete match and return");
		completeMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(matchMenuFrame, matchOutcome, "Match Outcome", JOptionPane.INFORMATION_MESSAGE);
				manager.setNextOpponent(null);
				finishedWindow();
				
				// determines if a random event will occur after the game
				if (manager.getCurrentWeek() <= manager.getGameLength()) {
					Random rng = manager.getRandom();
					int num = rng.nextInt(25);
					int playerIndex = rng.nextInt(3);

					if (num == 1) {
						String message = manager.getPlayersTeam().getAttackers().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(matchMenuFrame, message, "Athlete left", JOptionPane.INFORMATION_MESSAGE);

						manager.removeAttacker(playerIndex);
					}
					else if (num == 2) {
						String message = manager.getPlayersTeam().getDefenders().get(playerIndex).getName() + " has decided to leave your club";
						JOptionPane.showMessageDialog(matchMenuFrame, message, "Athlete left", JOptionPane.INFORMATION_MESSAGE);
						
						manager.removeDefender(playerIndex);
					}
					else if (num == 3) {
						Athlete player = manager.getPlayersTeam().getAttackers().get(playerIndex);
						String message = player.getName() + " has been training very hard and their attack power(ATT) has increased by 5";
						JOptionPane.showMessageDialog(matchMenuFrame, message, "Attack increase", JOptionPane.INFORMATION_MESSAGE);
						player.increaseAttack();
						}
					else if (num == 4) {
						Athlete player = manager.getPlayersTeam().getDefenders().get(playerIndex);
						String message = player.getName() + " has been training very hard and their defence power(DEF) has increased by 5";
						JOptionPane.showMessageDialog(matchMenuFrame, message, "Defence increase", JOptionPane.INFORMATION_MESSAGE);
						player.increaseDefence();
					}
					else if (num == 5) {
						Athlete player = manager.newPlayer();
						String message = player.getName() + " has joined your club! They are now in your substitutes.";
						JOptionPane.showMessageDialog(matchMenuFrame, message, "New Athlete", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
		});
		completeMatch.setBounds(177, 359, 304, 29);
		matchMenuFrame.getContentPane().add(completeMatch);
	}
}

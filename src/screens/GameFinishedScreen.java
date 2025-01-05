package screens;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.SwingConstants;

import main.GameManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * The GameFinishedScreen class represents a GUI screen that is displayed when the game is finished.
 * It provides information about the match outcomes from the game, points earned, money earned, and options to close the game or play again.
 * 
 * @author Shyam Hari
 * 
 */
public class GameFinishedScreen {

	private JFrame endGameFrame;
	
	private GameManager manager;
	private JButton playAgainButton;

	
	/**
	 * Closes this window
	 */
	public void closeWindow() {
		endGameFrame.dispose();
	}
	
	/**
	 * Calls the function to close this window and end the game.
	 */
	public void finishedWindow() {
		manager.closeGameFinishedScreen(this);
	}

	

	/**
	 * Create the application. 
	 * 
	 * @param incomingManager The game manager instance of the current game
	 */
	public GameFinishedScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		endGameFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame. Allow the user to play again or close game.
	 */
	private void initialize() {
		endGameFrame = new JFrame();
		endGameFrame.setBounds(100, 100, 650, 450);
		endGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endGameFrame.getContentPane().setLayout(null);
		
		JLabel gameFinishedTitle = new JLabel("Game Finished");
		gameFinishedTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		gameFinishedTitle.setBounds(6, -4, 638, 44);
		gameFinishedTitle.setHorizontalAlignment(0);
		endGameFrame.getContentPane().add(gameFinishedTitle);
		
		DefaultListModel<String> outcomesListModel = new DefaultListModel<String>();
		outcomesListModel.addAll(manager.getSeasonOutcomes());
		
		JList<String> outcomesList = new JList<String>(outcomesListModel);
		outcomesList.setBounds(28, 149, 593, 235);
		endGameFrame.getContentPane().add(outcomesList);
		
		JLabel thanksLabel = new JLabel("Thanks for playing!");
		thanksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thanksLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		thanksLabel.setBounds(6, 21, 638, 44);
		endGameFrame.getContentPane().add(thanksLabel);
		
		JLabel pointsEarnedLabel = new JLabel(manager.getTeamName() + " Points Earned: " + manager.getPlayersPoints());
		pointsEarnedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pointsEarnedLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		pointsEarnedLabel.setBounds(6, 52, 638, 44);
		endGameFrame.getContentPane().add(pointsEarnedLabel);
		
		JLabel moneyEarnedLabe = new JLabel(manager.getTeamName() + " Money Earned: " + manager.getMoneyEarnt());
		moneyEarnedLabe.setHorizontalAlignment(SwingConstants.CENTER);
		moneyEarnedLabe.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		moneyEarnedLabe.setBounds(6, 88, 638, 44);
		endGameFrame.getContentPane().add(moneyEarnedLabe);
		
		JButton closeGameButton = new JButton("Close Game");
		closeGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		closeGameButton.setBounds(189, 386, 117, 29);
		endGameFrame.getContentPane().add(closeGameButton);
		
		playAgainButton = new JButton("Play Again");
		playAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.main(null);
			}
		});
		playAgainButton.setBounds(359, 386, 117, 29);
		endGameFrame.getContentPane().add(playAgainButton);
		
		JLabel matchesPlayedLabel = new JLabel("Matches Played:");
		matchesPlayedLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		matchesPlayedLabel.setBounds(28, 130, 129, 16);
		endGameFrame.getContentPane().add(matchesPlayedLabel);
	}
}

package screens;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import main.GameManager;

/**
 * 
 * The SetupScreen class represents a GUI setup screen for the game.
 * It allows the user to set up the game parameters such as team name, difficulty, and game length.
 * and will afterwards take them to a screen to draft their team
 * 
 * @author Shyam Hari, Benjamin Joli
 * 
 */
public class SetupScreen {
	

	private JFrame frame;
	private GameManager manager;
	private JTextField textField;
	
	/**
	 * Creates a new SetupScreen instance with the specified game manager.
	 * @param gameManager the GameManager instance of the game
	 */
	public SetupScreen(GameManager gameManager) {
		manager = gameManager;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Closes this setup screen window
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * calls the function in the game manager to close this setup screen and then open
	 * up the draft team screen for the user to draft their team
	 */
	public void finishedWindow() {
		manager.closeSetupScreen(this);
	}



	/**
	 * Initialize the contents of the frame and allow the user to setup their team.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel WelcomeMessage = new JLabel("Welcome to FootBall Manager");
		WelcomeMessage.setFont(new Font("Tahoma", Font.BOLD, 15));
		WelcomeMessage.setBounds(199, 11, 231, 34);
		frame.getContentPane().add(WelcomeMessage);
		
		JLabel SelectTeamLabel = new JLabel("Please enter a team name:");
		SelectTeamLabel.setBounds(10, 63, 192, 34);
		frame.getContentPane().add(SelectTeamLabel);
		
		JLabel TeamNameLabel = new JLabel("Team name:");
		TeamNameLabel.setBounds(112, 266, 318, 21);
		frame.getContentPane().add(TeamNameLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamNameLabel.setText(textField.getText());
			}
		});
		textField.setBounds(223, 63, 182, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton ConfirmButton = new JButton("Confirm ");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.getDifficulty() == null || manager.getTeamName() == null || manager.getGameLength() == 0) {
					if (manager.getDifficulty() == null) {
						JOptionPane.showMessageDialog( ConfirmButton, "Please select a difficulty.", null, JOptionPane.INFORMATION_MESSAGE);
					}
					if (manager.getTeamName() == null) {
						JOptionPane.showMessageDialog(ConfirmButton, "Please enter and confirm a team name.", null, JOptionPane.INFORMATION_MESSAGE);
					}
					if (manager.getGameLength() == 0) {
						JOptionPane.showMessageDialog(ConfirmButton, "Please select and confirm the desired amount of weeks.", null, JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
				manager.createPlayersTeam(manager.getTeamName());
				finishedWindow();
			}}
		});
		ConfirmButton.setBounds(230, 370, 200, 34);
		frame.getContentPane().add(ConfirmButton);
		
		JLabel DifficultyLabel = new JLabel("Select Difficulty:");
		DifficultyLabel.setBounds(10, 112, 134, 21);
		frame.getContentPane().add(DifficultyLabel);
		
		JLabel DifficultySelectedLabel = new JLabel("Current Difficulty: ");
		DifficultySelectedLabel.setBounds(112, 306, 507, 21);
		frame.getContentPane().add(DifficultySelectedLabel);
		
		JButton NormalDifficultyButton = new JButton("Normal");
		NormalDifficultyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setDifficulty("Normal");
				manager.setPlayersFunds(1200);
				DifficultySelectedLabel.setText("Current Difficulty: Normal. You will receive $" + manager.getPlayersFunds());
			}
		});
		NormalDifficultyButton.setBounds(223, 108, 89, 29);
		frame.getContentPane().add(NormalDifficultyButton);
		
		JButton HardDifficultyButton = new JButton("Hard");
		HardDifficultyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setPlayersFunds(900);
				manager.setDifficulty("Hard");
				DifficultySelectedLabel.setText("Current Difficulty: Hard. You will receive $" + manager.getPlayersFunds());
			}
		});
		HardDifficultyButton.setBounds(317, 110, 89, 25);
		frame.getContentPane().add(HardDifficultyButton);
		
		JLabel GameLengthLabel = new JLabel("Enter desired season length(Number of weeks):");
		GameLengthLabel.setBounds(10, 191, 309, 21);
		frame.getContentPane().add(GameLengthLabel);
		
		JLabel infoLabel = new JLabel("Note: Season length must be between 5 - 15(inclusive) and if you choose a game seed,");
		infoLabel.setBounds(10, 141, 634, 21);
		frame.getContentPane().add(infoLabel);
		

		
		JSpinner weekSpinner = new JSpinner();
		weekSpinner.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		weekSpinner.setBounds(372, 184, 83, 34);
		frame.getContentPane().add(weekSpinner);
		
	
		
		JLabel CurrentGameLength = new JLabel("Current Season Length:");
		CurrentGameLength.setBounds(112, 345, 343, 21);
		frame.getContentPane().add(CurrentGameLength);
		
		JButton SetTeamNameButton = new JButton("Confirm Team Name");
		SetTeamNameButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	boolean onlyLetters = textField.getText().matches("[a-zA-Z ]+");
		    	boolean desiredLength = true;
				if (textField.getText().length() < 3 || textField.getText().length() > 15) {
					desiredLength = false;
					JOptionPane.showMessageDialog(SetTeamNameButton, "Team name length must be between 3 and 15 characters", "Invalid name", JOptionPane.ERROR_MESSAGE);
				}
				if (onlyLetters == false && textField.getText().length() >= 1) {
					JOptionPane.showMessageDialog(SetTeamNameButton, "Team name cannot contain any numbers or special characters.", "Invalid name", JOptionPane.ERROR_MESSAGE);
				}
				if (onlyLetters == true && desiredLength == true) {
		        String teamName = textField.getText();
		        TeamNameLabel.setText("Team name: " + teamName);
		        manager.setName(teamName);
				}
		    }
		});

		
		JButton ConfirmWeeksButton = new JButton("Confirm Weeks");
		ConfirmWeeksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setCurrentWeek(1);
				int weeks = (int) weekSpinner.getValue();
				manager.setGameLength(weeks);
				CurrentGameLength.setText("Current Season Length: " + weeks);
			}
		});
		
		SetTeamNameButton.setBounds(431, 67, 156, 29);
		frame.getContentPane().add(SetTeamNameButton);

		
		ConfirmWeeksButton.setBounds(463, 189, 156, 27);
		frame.getContentPane().add(ConfirmWeeksButton);
		
		JSpinner seedSpinner = new JSpinner();
		seedSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
		seedSpinner.setBounds(372, 222, 83, 34);
		frame.getContentPane().add(seedSpinner);
		
		JButton confirmSeed = new JButton("Confirm Seed");
		confirmSeed.setBounds(463, 227, 156, 27);
		frame.getContentPane().add(confirmSeed);
		
		JLabel gameSeedLabel = new JLabel("Enter game seed(optional). This could help you:");
		gameSeedLabel.setBounds(10, 227, 309, 21);
		frame.getContentPane().add(gameSeedLabel);
		
		JLabel infoLabel2 = new JLabel("it must be between 1 - 1000(inclusive).");
		infoLabel2.setBounds(50, 163, 570, 21);
		frame.getContentPane().add(infoLabel2);
	}
}

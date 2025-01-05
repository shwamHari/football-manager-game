package screens;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import main.Athlete;
import main.GameManager;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


/**
 * 
 * The DraftTeamScreen is the screen the user will see after the setup screen. In this screen
 * they will draft their team of athletes to be, which will contain a minimum of 6 
 * athletes(3 attacker and 3 defenders) The screen allows the user to select players from the 
 * available list and assigns their positions in the next screen.
 * 
 * @author Shyam Hari
 *
 */
public class DraftTeamScreen {

	private JFrame draftTeamFrame;
	
	private GameManager manager;



	/**
	 * 
	 * Constructs a DraftTeamScreen with the given GameManager.
	 * Initializes the draft team frame and makes it visible.
	 * 
	 * @param incomingManager The GameManager instance of the game
	*/
	public DraftTeamScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		draftTeamFrame.setVisible(true);
	}
	
	/**
	 * Closes this window
	 */
	public void closeWindow() {
		draftTeamFrame.dispose();
	}
	
	/**
	 * Calls the function in the game manager to close this window and open the club menu
	 * for the user to assign positions to their athletes
	 */
	public void finishedWindow() {
		manager.closeDraftTeamScreen(this);
	}

	/**
	 * Initialize the contents of the frame and allows the user to draft athletes to their team
	 */
	private void initialize() {
		draftTeamFrame = new JFrame();
		draftTeamFrame.setBounds(100, 100, 650, 450);
		draftTeamFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draftTeamFrame.getContentPane().setLayout(null);
		
		DefaultListModel<Athlete> availablePlayersListModel = new DefaultListModel<Athlete>();
		availablePlayersListModel.addAll(manager.getPlayersToDraft());
		
		JList<Athlete> playersAvailableList = new JList<Athlete>(availablePlayersListModel);
		playersAvailableList.setBounds(50, 102, 273, 295);
		draftTeamFrame.getContentPane().add(playersAvailableList);
		
		JLabel playersAvailableTitle = new JLabel("Players Available");
		playersAvailableTitle.setBounds(50, 74, 273, 16);
		playersAvailableTitle.setHorizontalAlignment(0);
		draftTeamFrame.getContentPane().add(playersAvailableTitle);
		
		JLabel title = new JLabel("Draft Your Team");
		title.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		title.setBounds(0, 16, 650, 24);
		title.setHorizontalAlignment(0);
		draftTeamFrame.getContentPane().add(title);
		
		JLabel fundsAvailableLabel = new JLabel("Funds Available:");
		fundsAvailableLabel.setBounds(368, 127, 109, 16);
		draftTeamFrame.getContentPane().add(fundsAvailableLabel);
		
		JLabel currentCostLabel = new JLabel("Cost of current selection:");
		currentCostLabel.setBounds(368, 179, 170, 16);
		draftTeamFrame.getContentPane().add(currentCostLabel);
		
		JLabel fundsAvailableAmount = new JLabel("" + manager.getPlayersFunds());
		fundsAvailableAmount.setBounds(568, 127, 61, 16);
		draftTeamFrame.getContentPane().add(fundsAvailableAmount);
		
		JLabel currentCostAmount = new JLabel("0");
		currentCostAmount.setBounds(568, 179, 61, 16);
		draftTeamFrame.getContentPane().add(currentCostAmount);
		
		JLabel infoLabel1 = new JLabel("- Your team must have a minimum of 6 players.");
		infoLabel1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		infoLabel1.setBounds(347, 284, 282, 24);
		draftTeamFrame.getContentPane().add(infoLabel1);
		
		JLabel infoLabel2 = new JLabel("- You will assign their positions in the next screen.");
		infoLabel2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		infoLabel2.setBounds(347, 308, 282, 24);
		draftTeamFrame.getContentPane().add(infoLabel2);
		
		JButton seeCostButton = new JButton("See cost");
		seeCostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int totalCost = 0;
				for (Athlete player : playersAvailableList.getSelectedValuesList()) {
					totalCost += player.getCost();
				}
				currentCostAmount.setText("" + totalCost);
			}
		});
		seeCostButton.setBounds(554, 207, 90, 29);
		draftTeamFrame.getContentPane().add(seeCostButton);
		
		JButton draftTeamButton = new JButton("Draft Team");
		draftTeamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playersAvailableList.getSelectedValuesList().size() >= 6) {
					int totalCost = 0;
					for (Athlete player : playersAvailableList.getSelectedValuesList()) {
						totalCost += player.getCost();
					}
					if (totalCost <= manager.getPlayersFunds()) {
						String message = "Are you sure you would like to draft this team?";
						int answer = JOptionPane.showConfirmDialog(draftTeamFrame, message, "Confirm team", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (answer == JOptionPane.YES_OPTION) {
							manager.deductPlayerFunds(totalCost);
							for (Athlete player : playersAvailableList.getSelectedValuesList()) {
								manager.addToSubstitutes(player);
							}
							finishedWindow();
						}

					}
					else {
						JOptionPane.showMessageDialog(draftTeamFrame, "You have insufficient funds to draft the currently selected team.", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(draftTeamFrame, "number of players drafted must be at least 6", "Not enough players", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		draftTeamButton.setBounds(409, 350, 201, 29);
		draftTeamFrame.getContentPane().add(draftTeamButton);
	}
}

package screens;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.GameManager;
import main.Team;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * 
 * The StadiumMenu class represents the stadium menu screen in the game.
 * It allows the user to select the next opponent for their team to vs.
 * 
 * @author Shyam Hari
 */
public class StadiumMenu {

	private JFrame stadiumMenuFrame;
	private ArrayList<Team> teams;
	private GameManager manager;



	
	/**
	 * Constructs a StadiumMenu object with the given GameManager.
	 *
	 * @param incomingManger the GameManager instance of the game
	 */
	public StadiumMenu(GameManager incomingManger) {
		manager = incomingManger;
		teams = manager.getNextOpponentOptions();
		initialize();
		stadiumMenuFrame.setVisible(true);
	}
	
	/**
	 * Close this stadium menu screen
	 */
	public void closeWindow() {
		stadiumMenuFrame.dispose();
	}
	
	/**
	 * Calls the function in the game manager to close this stadium menu screen
	 * and open back up the main menu
	 */
	public void finishedWindow() {
		
		manager.closeStadiumMenu(this);
	}
	

	/**
	 * Initialize the contents of the frame and allow the user to select their next opponent.
	 */
	private void initialize() {
		stadiumMenuFrame = new JFrame();
		stadiumMenuFrame.setBounds(100, 100, 650, 450);
		stadiumMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stadiumMenuFrame.getContentPane().setLayout(null);
		
		JLabel stadiumTitle = new JLabel("Select next opponent");
		stadiumTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		stadiumTitle.setHorizontalAlignment(SwingConstants.CENTER);
		stadiumTitle.setBounds(184, 22, 294, 25);
		stadiumMenuFrame.getContentPane().add(stadiumTitle);
		
		DefaultListModel<Team> oppponentListModel = new DefaultListModel<Team>();
		oppponentListModel.addAll(teams);
		
		JList<Team> oppponentList = new JList<Team>(oppponentListModel);
		oppponentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		oppponentList.setBounds(161, 93, 333, 179);
		stadiumMenuFrame.getContentPane().add(oppponentList);
		
		JButton confirmButton = new JButton("Confirm selection");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oppponentList.getSelectedIndex() != -1) {
					manager.setNextOpponent(oppponentList.getSelectedValue());
					String confirmMessage = "Next match will be against " + manager.getNextOpponent() + ". Note: This can still be changed.";
					JOptionPane.showMessageDialog(stadiumMenuFrame, confirmMessage, "Item Purchased", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		confirmButton.setBounds(249, 305, 156, 29);
		stadiumMenuFrame.getContentPane().add(confirmButton);
		
		JButton goBackButton = new JButton("Go back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		goBackButton.setBounds(527, 387, 117, 29);
		stadiumMenuFrame.getContentPane().add(goBackButton);
		
		JLabel stadiumTitle2 = new JLabel("(your selection won't matter if you choose to take a bye)");
		stadiumTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		stadiumTitle2.setBounds(136, 50, 384, 16);
		stadiumMenuFrame.getContentPane().add(stadiumTitle2);
	}
}

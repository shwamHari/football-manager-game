package screens;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import Items.Item;
import main.Athlete;
import main.GameManager;

import javax.swing.JTextField;


/**
 * 
 * The ClubMenu class represents a GUI for managing the player's club in a club in a game.
 * Users can make changes to their team, use items, sell athletes, and perform other club-related actions.
 * 
 * @author Shyam Hari
 * 
 */
public class ClubMenu {
	
	private JFrame clubMenuFrame;
	private GameManager manager;
	private JTextField enterNicknameBox;
	   
	
	/**
	 * Disposes this window
	 */
	public void closeWindow() {
		clubMenuFrame.dispose();
	}
	
	/**
	 * Calls the function in the game manager to close this screen and open the main menu
	 */
	public void finishedWindow() {
		manager.closeClubMenuScreen(this);
	}
		

	
	/**
     * Constructs a ClubMenu object with the specified game manager.
     *
     * @param incomingManager The game manager
     */
	public ClubMenu(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		clubMenuFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame and allow the user to make changes to their team and use items. 
	 */
	private void initialize() {
		clubMenuFrame = new JFrame();
		clubMenuFrame.setBounds(100, 100, 650, 450);
		clubMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clubMenuFrame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Club");
		title.setBounds(6, 12, 644, 26);
		title.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		title.setHorizontalAlignment(0);
		clubMenuFrame.getContentPane().add(title);
		
		JLabel attackersLabel = new JLabel("Attackers:");
		attackersLabel.setBounds(16, 50, 83, 16);
		clubMenuFrame.getContentPane().add(attackersLabel);
		
		JLabel defendersLabel = new JLabel("Defenders:");
		defendersLabel.setBounds(16, 150, 83, 16);
		clubMenuFrame.getContentPane().add(defendersLabel);
		
		DefaultListModel<Athlete> attackersListModel = new DefaultListModel<Athlete>();
		if (!manager.getPlayersTeam().getAttackers().isEmpty()) {
			attackersListModel.addAll(manager.getPlayersTeam().getAttackers());
		}
		
		JList<Athlete> attackersList = new JList<Athlete>(attackersListModel);
		attackersList.setBounds(16, 78, 305, 60);
		attackersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clubMenuFrame.getContentPane().add(attackersList);
		
		DefaultListModel<Athlete> substitutesListModel = new DefaultListModel<Athlete>();
		if (!manager.getPlayersTeam().getSubstitutes().isEmpty()) {
			substitutesListModel.addAll(manager.getPlayersTeam().getSubstitutes());
		}

		JList<Athlete> substitutesList = new JList<Athlete>(substitutesListModel);
		substitutesList.setBounds(16, 273, 305, 111);
		substitutesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clubMenuFrame.getContentPane().add(substitutesList);
		
		DefaultListModel<Athlete> defendersListModel = new DefaultListModel<Athlete>();
		if (!manager.getPlayersTeam().getDefenders().isEmpty()) {
			defendersListModel.addAll(manager.getPlayersTeam().getDefenders());
		}

		JList<Athlete> defendersList = new JList<Athlete>(defendersListModel);
		defendersList.setBounds(16, 175, 305, 60);
		defendersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clubMenuFrame.getContentPane().add(defendersList);
		

		JLabel substitutesLabel = new JLabel("Substitutes:");
		substitutesLabel.setBounds(16, 247, 83, 16);
		clubMenuFrame.getContentPane().add(substitutesLabel);
		
		JButton addAsAttackerButton = new JButton("Add as attacker");
		addAsAttackerButton.setBounds(328, 268, 140, 29);
		addAsAttackerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = substitutesList.getSelectedIndex();
				if (selectedIndex != -1) {
					if (manager.getPlayersTeam().getAttackers().size() < 3) {
						manager.addToAttackers(substitutesList.getSelectedValue());
						attackersListModel.addElement(substitutesList.getSelectedValue());
						substitutesListModel.remove(selectedIndex);
						manager.removeSubstitute(selectedIndex);
					}
					else {
						String message = "You can only have 3 attackers";
						JOptionPane.showMessageDialog(clubMenuFrame, message, "Attackers Full", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		clubMenuFrame.getContentPane().add(addAsAttackerButton);
		
		JButton addAsDefenderButton = new JButton("Add as defender");
		addAsDefenderButton.setBounds(328, 296, 140, 29);
		addAsDefenderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = substitutesList.getSelectedIndex();
				if (selectedIndex != -1) {
					if (manager.getPlayersTeam().getDefenders().size() < 3) {
						manager.addToDefenders(substitutesList.getSelectedValue());
						defendersListModel.addElement(substitutesList.getSelectedValue());
						substitutesListModel.remove(selectedIndex);
						manager.removeSubstitute(selectedIndex);
					}
					else {
						String message = "You can only have 3 defenders";
						JOptionPane.showMessageDialog(clubMenuFrame, message, "Defenders Full", JOptionPane.ERROR_MESSAGE);
					}
					
				}

			}
		});
		clubMenuFrame.getContentPane().add(addAsDefenderButton);
		
		JButton removeAttacker = new JButton("Remove");
		removeAttacker.setBounds(330, 97, 77, 29);
		removeAttacker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = attackersList.getSelectedIndex();
				if (selectedIndex != -1) {
					substitutesListModel.addElement(attackersList.getSelectedValue());
					manager.addToSubstitutes(attackersList.getSelectedValue());
					attackersListModel.remove(selectedIndex);
					manager.removeAttacker(selectedIndex);
				}
			}
		});
		clubMenuFrame.getContentPane().add(removeAttacker);
		
		JButton removeDefender = new JButton("Remove");
		removeDefender.setBounds(330, 195, 77, 29);
		removeDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = defendersList.getSelectedIndex();
				if (selectedIndex != -1) {
					substitutesListModel.addElement(defendersList.getSelectedValue());
					manager.addToSubstitutes(defendersList.getSelectedValue());
					defendersListModel.remove(selectedIndex);
					manager.removeDefender(selectedIndex);
				}
			}
		});
		clubMenuFrame.getContentPane().add(removeDefender);
		
		
		DefaultListModel<Item> itemsListModel = new DefaultListModel<Item>();
		if (!manager.getplayersItems().isEmpty()) {
			itemsListModel.addAll(manager.getplayersItems());
		}
		
		JList<Item> itemsList = new JList<Item>(itemsListModel);
		itemsList.setBounds(420, 78, 224, 126);
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clubMenuFrame.getContentPane().add(itemsList);
		
		JLabel itemsLabel = new JLabel("Items:");
		itemsLabel.setBounds(420, 50, 83, 16);
		clubMenuFrame.getContentPane().add(itemsLabel);
		
		JButton viewItemButton = new JButton("View Item Information");
		viewItemButton.setBounds(455, 206, 164, 29);
		viewItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = itemsList.getSelectedIndex();
				if (selectedIndex != -1) {
					String information = itemsList.getSelectedValue().returnInformation();
					JOptionPane.showMessageDialog(clubMenuFrame, information, "Item Description", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		clubMenuFrame.getContentPane().add(viewItemButton);
		
		JButton useItemButton = new JButton("Use Selected Item");
		useItemButton.setBounds(328, 355, 140, 29);
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedPlayerIndex = substitutesList.getSelectedIndex();
				int selectedItemIndex = itemsList.getSelectedIndex();
				if (selectedPlayerIndex != -1 && selectedItemIndex != -1) {
					itemsList.getSelectedValue().useItem(manager, substitutesList.getSelectedValue());
					String message = itemsList.getSelectedValue().getName() + " was used on " + substitutesList.getSelectedValue().getName();
					JOptionPane.showMessageDialog(clubMenuFrame, message, "Item Used", JOptionPane.INFORMATION_MESSAGE);
					manager.removeItem(selectedItemIndex);
					itemsListModel.remove(selectedItemIndex);
				}
				
			}
		});
		clubMenuFrame.getContentPane().add(useItemButton);
		
		JButton sellPlayerButton = new JButton("Sell Athlete");
		sellPlayerButton.setBounds(328, 326, 140, 29);
		sellPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = substitutesList.getSelectedIndex();
				if (selectedIndex != -1) {
					int amount = substitutesList.getSelectedValue().getCost() * 8/10;
					String message = "Are you sure you want to sell " + substitutesList.getSelectedValue().getName() + "?" 
							+ "\n" + "You will only receive $" + amount + ". There is a 20% fee to sell back athletes.";
					int answer = JOptionPane.showConfirmDialog(clubMenuFrame, message, "Confirm sale", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (answer == JOptionPane.YES_OPTION) {
						manager.addToMarket(substitutesList.getSelectedValue());
						manager.increasePlayerFunds(amount);
						manager.removeSubstitute(substitutesList.getSelectedIndex());
						substitutesListModel.remove(substitutesList.getSelectedIndex());
					}	
				}
			}
		});
		clubMenuFrame.getContentPane().add(sellPlayerButton);
		
		JButton nickNameButton = new JButton("Nickname Selected Athlete");
		nickNameButton.setBounds(237, 391, 231, 29);
		nickNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (substitutesList.getSelectedIndex() != -1) {
					boolean onlyLetters = enterNicknameBox.getText().matches("[a-zA-Z ]+");
			    	boolean desiredLength = true;
					if (enterNicknameBox.getText().length() < 3 || enterNicknameBox.getText().length() > 12) {
						desiredLength = false;
						
						JOptionPane.showMessageDialog(clubMenuFrame, "Athlete name length must be between 3 and 12 characters", "Invlaid name", JOptionPane.ERROR_MESSAGE);
					}
					if (onlyLetters == false && enterNicknameBox.getText().length() >= 1) {
						JOptionPane.showMessageDialog(clubMenuFrame, "Athlete name cannot contain any numbers or special characters.", "Invalid name", JOptionPane.ERROR_MESSAGE);
					}
					if (onlyLetters == true && desiredLength == true) {
			        String newName = enterNicknameBox.getText();
			        manager.setAthleteName(substitutesList.getSelectedValue(), newName);
					}
				}
			}
		});
		clubMenuFrame.getContentPane().add(nickNameButton);
		
		enterNicknameBox = new JTextField();
		enterNicknameBox.setBounds(14, 390, 224, 29);
		enterNicknameBox.setText("Enter desired nickname");
		clubMenuFrame.getContentPane().add(enterNicknameBox);
		enterNicknameBox.setColumns(10);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		goBackButton.setBounds(527, 387, 117, 29);
		clubMenuFrame.getContentPane().add(goBackButton);
		
		JButton sellItemButton = new JButton("Sell Item");
		sellItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = itemsList.getSelectedValue().getCost() * 8/10;
				int selectedIndex = itemsList.getSelectedIndex();
				if (selectedIndex != -1) {
					String message = "Are you sure you want to sell " + itemsList.getSelectedValue().getName() + "?" 
							+ "\n" + "You will only receive $" + amount + ". There is a 20% fee to sell back items.";
					int answer = JOptionPane.showConfirmDialog(clubMenuFrame, message, "Confirm sale", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (answer == JOptionPane.YES_OPTION) {
						manager.increasePlayerFunds(amount);
						manager.removeItem(selectedIndex);
						itemsListModel.remove(selectedIndex);
					}
				}
			}
		});
		sellItemButton.setBounds(455, 234, 164, 29);
		clubMenuFrame.getContentPane().add(sellItemButton);
		
		JButton howToUseButton = new JButton("How to use");
		howToUseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Selling athletes, using items on athletes and nicknaming athletes can only be done when the athlete is in the substitutes." + "\n" 
			+ "To use an item on an athlete, select an athlete in substitutes and select an item, then press use selected item." + "\n"
						+ "Athletes can be added to the attackers and defenders as you please and can also be removed back to substitutes.";
				JOptionPane.showMessageDialog(clubMenuFrame, message, "How To Use", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		howToUseButton.setBounds(6, 6, 117, 29);
		clubMenuFrame.getContentPane().add(howToUseButton);
	}
}

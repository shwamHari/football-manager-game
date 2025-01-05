package screens;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Items.Item;
import main.Athlete;
import main.GameManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * 
 * The MarketMenu class represents a graphical user interface for the market menu in a game.
 * It allows the user to purchase items and athletes and stores them in the players club. 
 * 
 * @author Shyam Hari
 * 
 */
public class MarketMenu {

	private JFrame marketMenuFrame;
	
	private GameManager manager;
	private ArrayList<Athlete> playersAvailable;
	private ArrayList<Item> itemsAvailable;
	
	
	/**
	 * Closes the window
	 */
	public void closeWindow() {
		marketMenuFrame.dispose();
	}
	
	/**
	 * Calls the function in game manager to close this window and open the main menu
	 */
	public void finishedWindow() {
		manager.closeMarketMenu(this);
	}
	
	/**
	 * Calls the function in game manager to close this window and open the club menu if the user chooses to 'substitute player now'.
	 */
	public void goToClub() {
		manager.substituteNow(this);
	}

	/**
	 * Creates the MarketMenu class.
	 * 
	 * @param incomingManager The GameManager instance associated with the game.
	 */
	public MarketMenu(GameManager incomingManager) {
		manager = incomingManager;
		playersAvailable = manager.getPlayersAvailable();
		itemsAvailable = manager.getItemsAvailable();
		initialize();
		marketMenuFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame and allow the user to purchase items and athletes.
	 */
	private void initialize() {
		marketMenuFrame = new JFrame();
		marketMenuFrame.setBounds(100, 100, 650, 450);
		marketMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marketMenuFrame.getContentPane().setLayout(null);
		
		
		JLabel athletesTitle = new JLabel("Athletes for sale");
		athletesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		athletesTitle.setBounds(53, 127, 256, 16);
		marketMenuFrame.getContentPane().add(athletesTitle);
		
		JLabel itemsTitle = new JLabel("Items For Sale");
		itemsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		itemsTitle.setBounds(381, 127, 256, 16);
		marketMenuFrame.getContentPane().add(itemsTitle);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		goBackButton.setBounds(527, 387, 117, 29);
		marketMenuFrame.getContentPane().add(goBackButton);
		
		DefaultListModel<Athlete> marketPlayersListModel = new DefaultListModel<Athlete>();
		marketPlayersListModel.addAll(playersAvailable);

		JList<Athlete> marketPlayersList = new JList<Athlete>(marketPlayersListModel);
		marketPlayersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		marketPlayersList.setBounds(19, 155, 328, 161);
		marketMenuFrame.getContentPane().add(marketPlayersList);
		
		DefaultListModel<Item> marketItemsListModel = new DefaultListModel<Item>();
		marketItemsListModel.addAll(itemsAvailable);
		
		JList<Item> marketItemsList = new JList<Item>(marketItemsListModel);
		marketItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		marketItemsList.setBounds(404, 155, 213, 161);
		marketMenuFrame.getContentPane().add(marketItemsList);
		
		JButton buyPlayerButtom = new JButton("Buy Player");
		buyPlayerButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int playerIndex = marketPlayersList.getSelectedIndex();
				if (playerIndex != -1) {
					Athlete player = marketPlayersList.getSelectedValue();
					if (manager.getPlayersFunds() >= player.getCost()) {
						String message = "Are you sure you would like to purchase " + player.getName() + " for $" + player.getCost() + "?";
						int answer = JOptionPane.showConfirmDialog(marketMenuFrame, message, "Confirm choice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						
						if (answer == JOptionPane.YES_OPTION) {
							String nextMessage = "Would you like to add " + player.getName() + " to your current playing team now?";
							int nextAnswer = JOptionPane.showConfirmDialog(marketMenuFrame, nextMessage, "Add to current team?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							 if (nextAnswer == JOptionPane.YES_OPTION) {
								manager.deductPlayerFunds(player.getCost());
								manager.addToSubstitutes(player);
								marketPlayersListModel.remove(playerIndex);
								manager.removePlayerFromMarket(playerIndex);
								goToClub();
							 }
							 else if (nextAnswer == JOptionPane.NO_OPTION) {
								manager.deductPlayerFunds(player.getCost());
								manager.addToSubstitutes(player);
								marketPlayersListModel.remove(playerIndex);
								manager.removePlayerFromMarket(playerIndex);
								String finalMessage = player.getName() + " has been added to your substitutes.";
								JOptionPane.showMessageDialog(marketMenuFrame, finalMessage);
							 }
						}
					}
					else {
						JOptionPane.showMessageDialog(marketMenuFrame, "You have insufficient funds to purchase the selected player.", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		buyPlayerButtom.setBounds(122, 328, 117, 29);
		marketMenuFrame.getContentPane().add(buyPlayerButtom);
		
		
		
		JButton buyItemButton = new JButton("Buy Item");
		buyItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = marketItemsList.getSelectedIndex();
				if (selectedIndex != -1) {
					Item newItem = marketItemsList.getSelectedValue();
					if (manager.getPlayersFunds() > newItem.getCost()) {
						String message = "Are you sure you would like to purchase " + newItem + " for  $" + newItem.getCost() + "?";
						int answer = JOptionPane.showConfirmDialog(marketMenuFrame, message, "Confirm choice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						 if (answer == JOptionPane.YES_OPTION) {
							manager.addToPlayersItems(newItem);
							marketItemsListModel.remove(selectedIndex);
							manager.removeItemFromMarket(selectedIndex);
							String confirmMessage = newItem + " has been added to your inventory. You can view/use items in the club menu";
							JOptionPane.showMessageDialog(marketMenuFrame, confirmMessage, "Item Purchased", JOptionPane.INFORMATION_MESSAGE);
						 }
					}
					else {
						JOptionPane.showMessageDialog(marketMenuFrame, "You have insufficient funds to purchase the selected item.", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		buyItemButton.setBounds(451, 328, 117, 29);
		marketMenuFrame.getContentPane().add(buyItemButton);
		
		JLabel title = new JLabel("Market");
		title.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		title.setBounds(6, 16, 644, 22);
		title.setHorizontalAlignment(0);
		marketMenuFrame.getContentPane().add(title);
		
		JLabel fundsLabel = new JLabel("Funds Available: $" + manager.getPlayersFunds());
		fundsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		fundsLabel.setBounds(37, 62, 291, 29);
		marketMenuFrame.getContentPane().add(fundsLabel);
	}
}

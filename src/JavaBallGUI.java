import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Team Foxtrot JavaBall Referees - JavaBallGUI.java Defines JavaBall GUI that
 * displays referee and match details
 * <p>
 * University of Glasgow MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author Miroslav Pashov, 1005139P
 * @author Andrew Lowson, 0800685L
 * @author Marco Cook, 2152599C
 * @author Raoul Rothfeld, 2164502R
 * 
 * @version 1.1
 * @since 25-01-2015
 */

public class JavaBallGUI extends JFrame implements ActionListener {
	// GUI components as instance variables
	private JPanel navPanel, centrePanel, searchPanel, listPanel;
	private JButton addButton, chartButton, allocateButton, exitButton,
			searchButton;
	private JTextField searchField;
	
	private JTable table;
	// TODO
	private JavaBallController controller;

	/**
	 * Constructor for JavaBallGUI
	 */
	public JavaBallGUI(JavaBallController controller) {
		// initiate GUI and its components
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall Referees");
		setSize(800, 500);
		layoutComponents();
		
		createTableView();
		
		// connect to controller
		this.controller = controller;
	}

	private void layoutComponents() {
		// create JPanels
		navPanel = new JPanel();
		navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.PAGE_AXIS));
		centrePanel = new JPanel(new BorderLayout());
		searchPanel = new JPanel();
		listPanel = new JPanel();

		// set JPanel backgrounds
		navPanel.setBackground(Color.gray);
		centrePanel.setBackground(Color.white);
		searchPanel.setBackground(Color.white);
		listPanel.setBackground(Color.white);

		// add main JPanels to JFrame
		add(navPanel, BorderLayout.WEST);
		add(centrePanel, BorderLayout.CENTER);
		centrePanel.add(searchPanel, BorderLayout.NORTH);
		centrePanel.add(listPanel, BorderLayout.SOUTH);

		// create JTextField
		// length of JTextField measured in amount of visible 'm' characters
		searchField = new JTextField(40); // length of 40 for String input
		searchField.setText("Enter referee name or ID ...");
		searchField.addActionListener(this);
		// Clear the text field if the mouse is clicked in it
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (searchField.getText()
						.equals("Enter referee name or ID ...")) {
					searchField.setText("");
				}
			}
		});
		// create JButtons
		addButton = new JButton("Add Referee");

		chartButton = new JButton("Show Chart");
		chartButton.addActionListener(this);

		allocateButton = new JButton("Allocate Refs");
		allocateButton.addActionListener(this);

		exitButton = new JButton("Save & Exit");
		exitButton.addActionListener(this);

		searchButton = new JButton("Search");
		searchButton.addActionListener(this);

		// add center components to center panels
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		// add navigation components to panelNavigation
		navPanel.add(addButton);
		navPanel.add(chartButton);
		navPanel.add(allocateButton);
		navPanel.add(exitButton);
	}
/**
 * Method to create the main table view for to display the referees
 * 
 */
	public void createTableView() {

		// Column Names for table (Referee attributes)
		String[] columnNames = {"ID", "First Name", "Last Name", "Qualification", "Match Allocations", "Home Region", "Travel Locations"};

		// Referee data displayed in rows (ONLY FOR TESTING)
		Object[][] data = {
				{"JS1", "John", "Smith", "IJB3", "5", "North", "YYN"},
				{"JB1", "Joe", "Bloggs", "NJB1"},
				{"MC1", "Marco", "Cook", "IJB4"},
				{"AL1", "Andrew", "Lowson", "IJB3"},

		};
		// Instantiate table component with referee data and column names
		table = new JTable(data, columnNames);
		
		// Set table dimensions
		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setFillsViewportHeight(true);
		
		// Create new JPane for table view
		JScrollPane tablePane = new JScrollPane(table);
		// Add tablePane to main GUI frame
		add(tablePane);
	}





	/**TODO
	 * change text area to table!!!
	 */



	/**TODO 
	 * create one JFrame that is used for both adding, editing and deleting referees.
	 * If adding a referee then save button is enabled and delete button is disabled.
	 * If if deleting a referee then save is disabled and delete is enabled. 
	 */
	
	/**
	 * TODO
	 * Upon clicking the allocate button, a new small JFrame comes up which contains areas to input the information
	 * of a match (week number, area and level). There is a cancel button, and a get suitable referees
	 * upon clicking this button the small JFrame is disposed and the main referee table is filtered to show the 
	 * listed suitable referees, along with a note saying it is ordered and filtered according to suitability and there is 
	 * a button to reset the table (No filter, and sort by the default ID).
	 * 
	 */
	
	
	
	/**TODO
	 * make tables scrollable
	 */

	/**
	 * TODO
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addButton) {
			controller.execAdd(" ");
		} else if (ae.getSource() == allocateButton) {
			controller.execAllocate(" ");
		} else if (ae.getSource() == chartButton) {
			controller.execChart();
		} else if (ae.getSource() == searchButton) {
			controller.execSearch(" ");
		} else if (ae.getSource() == exitButton) {
			controller.execSaveExit();
		}
	}
}
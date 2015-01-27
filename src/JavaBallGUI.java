import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

	// GUI components for Table
	private JScrollPane tablePane;
	private JTable table;
	// TODO
	private final JavaBallController controller;

	/**
	 * Constructor for JavaBallGUI
	 * @param controller
	 */
	public JavaBallGUI(JavaBallController controller) {
		// initiate GUI and its components
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall Referees");
		setSize(800, 500);
		layoutComponents();

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
		addButton.addActionListener(this);

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


		// Column Names for table (Referee attributes)
		String[] columnNames = {"ID", "First Name", "Last Name", "Qualification", "Match Allocations", "Home Region", "Travel Locations"};

		// Referee data displayed in rows (ONLY FOR TESTING)
		Object[][] data = {
				{"JS1", "John", "Smith", "IJB3", "5", "North", "YYN"},
				{"JB1", "Joe", "Bloggs", "NJB1", "1", "South", "YYY"},
				{"MC1", "Marco", "Cook", "IJB4", "10", "Central", "NYN"},
				{"AL1", "Andrew", "Lowson", "IJB3", "7", "North", "YYN"},

		};
		// Instantiate table component with referee data and column names
		table = new JTable(data, columnNames);

		// Set table dimensions
		table.setPreferredScrollableViewportSize(new Dimension(400, 100));
		table.setFillsViewportHeight(true);

		// Create new JPane for table view
		tablePane = new JScrollPane(table);
		// Add tablePane to main GUI frame
		centrePanel.add(tablePane);
	}



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
	 * @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addButton) {
			
			ViewRefereeFrame addRef = new ViewRefereeFrame();
			addRef.setVisible(true);
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


	/**TODO 
	 * Subclass that contains the frame and components for the add/edit/remove 
	 * referee display.
	 * create one JFrame that is used for both adding, editing and deleting 
	 * referees.
	 * 
	 * If adding a referee then save button is enabled and delete button is 
	 * disabled.If if deleting a referee then save is disabled and delete is 
	 * enabled. 
	 */
	private class ViewRefereeFrame extends JFrame implements ActionListener {
		
		private JButton backButton, saveButton, removeButton;
		private JLabel idLabel, nameLabel, qualificationLabel, matchesLabel,
		homeLabel, visitAreasLabel;
		private JTextField idField, nameField, qualificationField, matchesField,
		homeField, visitAreasField;


		public ViewRefereeFrame() {
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("Add/Edit/Remove Referee");
			setSize(1000, 200);
			setLocation(200, 200);
			
			layoutTop();
			layoutMiddle();
			layoutBottom();

		}

		public void layoutTop() {
			
			JPanel top = new JPanel();
			EmptyBorder border = new EmptyBorder(10, 40, 10, 40);
			
			idLabel = new JLabel();
			idLabel.setText("ID");
			idLabel.setBorder(border);
			top.add(idLabel);
			
			nameLabel = new JLabel();
			nameLabel.setText("Name");
			nameLabel.setBorder(border);
			top.add(nameLabel);
			
			qualificationLabel = new JLabel();
			qualificationLabel.setText("Qualification");
			qualificationLabel.setBorder(border);
			top.add(qualificationLabel);
			
			matchesLabel = new JLabel();
			matchesLabel.setText("Matches Allocated");
			matchesLabel.setBorder(border);
			top.add(matchesLabel);
			
			homeLabel = new JLabel();
			homeLabel.setText("Home Location");
			homeLabel.setBorder(border);
			top.add(homeLabel);
			
			visitAreasLabel = new JLabel();
			visitAreasLabel.setText("Visitable Areas");
			visitAreasLabel.setBorder(border);
			top.add(visitAreasLabel);
			
			
		    
		    add(top, BorderLayout.NORTH);
		
		}
		
		public void layoutMiddle() {
			
			JPanel middle = new JPanel();
			middle.setBackground(Color.gray);

			idField = new JTextField(5);
			idField.setEditable(false);
			middle.add(idField);

			nameField = new JTextField(20);
			nameField.setEditable(false);
			middle.add(nameField);

			qualificationField = new JTextField(7);
			middle.add(qualificationField);

			matchesField = new JTextField(5);
			matchesField.setEditable(false);
			middle.add(matchesField);

			homeField = new JTextField(10);
			middle.add(homeField);
			visitAreasField = new JTextField(10);
			middle.add(visitAreasField);
			
			add(middle, BorderLayout.CENTER);
			
		}
		public void layoutBottom() {

			JPanel bottom = new JPanel();
			
			backButton = new JButton("Back");
			backButton.addActionListener(this);
			bottom.add(backButton);
			saveButton = new JButton("Save Referee");
			saveButton.addActionListener(this);
			bottom.add(saveButton);
			removeButton = new JButton("Remove Referee");
			removeButton.addActionListener(this);
			bottom.add(removeButton);
			
			// Add components to panel
			add(bottom, BorderLayout.SOUTH);

		}

		public void actionPerformed(ActionEvent ae) {

		}

	}
}

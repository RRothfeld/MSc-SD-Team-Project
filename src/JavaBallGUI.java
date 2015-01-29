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
	searchButton, resetSearchButton;
	private JTextField searchField;

	// GUI buttons and textFields for viewRefereeFrame
	private JButton backButton, saveButton, removeButton;
	private JTextField idField, firstNameField, surnameField, 
	qualificationField, matchesField, homeField, visitAreasField;

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

		resetSearchButton = new JButton("Reset Search");
		resetSearchButton.addActionListener(this); 

		// add center components to center panels
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		// add navigation components to panelNavigation
		navPanel.add(addButton);
		navPanel.add(chartButton);
		navPanel.add(allocateButton);
		navPanel.add(resetSearchButton);
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
	 * @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// If add button is pressed
		if (ae.getSource() == addButton) {
			ViewRefereeFrame addRef = new ViewRefereeFrame();
			addRef.setVisible(true);
			removeButton.setEnabled(false);
			saveButton.setText("Add Referee");
			controller.execAdd(" ");

			// If allocate referee to matches button is pressed
		} else if (ae.getSource() == allocateButton) {
			AllocateMatches allocateRef = new AllocateMatches();
			allocateRef.setVisible(true);
			controller.execAllocate(" ");

			// If chart button is pressed
		} else if (ae.getSource() == chartButton) {
			controller.execChart();

			// If search button is pressed
		} else if (ae.getSource() == searchButton) {
			ViewRefereeFrame serachRef = new ViewRefereeFrame();
			serachRef.setVisible(true);
			firstNameField.setEditable(false);
			surnameField.setEditable(false);
			matchesField.setEditable(false);
			controller.execSearch(" ");

			// If save and exit button is pressed
		} else if (ae.getSource() == resetSearchButton) {
			JOptionPane.showMessageDialog(null, "Referee table now ordered by "
					+ "referee ID.");
			resetDisplay();
			
		}else if (ae.getSource() == exitButton) {
			controller.execSaveExit();
		}
	}
	
	/**
	 * A method to reset the table view of the main GUI after a match is
	 * allocated.
	 */
	public void resetDisplay() {
		
		//TODO Reset main table view and sort referees by ID
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
	private final class ViewRefereeFrame extends JFrame implements ActionListener {

		// GUI labels for viewRefereeFrame
		private JLabel idLabel, firstNameLabel, surnameLabel, 
		qualificationLabel, matchesLabel, homeLabel, visitAreasLabel;

		/**
		 * Constructor to add components and create frame.
		 */
		public ViewRefereeFrame() {

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("Add/Edit/Remove Referee");
			setSize(1000, 200);
			setLocation(200, 200);
			// Adds top GUI components
			layoutTop();
			// Adds central GUI components
			layoutMiddle();
			// Adds bottom GUI components
			layoutBottom();

		}
		/**
		 * Method to add labels to GUI.
		 */
		public void layoutTop() {

			JPanel top = new JPanel();

			// Creates a border around the labels so they are spaced apart
			EmptyBorder border = new EmptyBorder(10, 40, 10, 40);

			// Creates and adds ID Label
			idLabel = new JLabel();
			idLabel.setText("ID");
			idLabel.setBorder(border);
			top.add(idLabel);
			// Creates and adds first name Label
			firstNameLabel = new JLabel();
			firstNameLabel.setText("First Name");
			firstNameLabel.setBorder(border);
			top.add(firstNameLabel);
			// Creates and adds first name Label
			surnameLabel = new JLabel();
			surnameLabel.setText("Surname Name");
			surnameLabel.setBorder(border);
			top.add(surnameLabel);
			// Creates and adds qualification Label
			qualificationLabel = new JLabel();
			qualificationLabel.setText("Qualification");
			qualificationLabel.setBorder(border);
			top.add(qualificationLabel);
			// Creates and adds matches allocated Label
			matchesLabel = new JLabel();
			matchesLabel.setText("Matches Allocated");
			matchesLabel.setBorder(border);
			top.add(matchesLabel);
			// Creates and adds referees home location Label
			homeLabel = new JLabel();
			homeLabel.setText("Home Location");
			homeLabel.setBorder(border);
			top.add(homeLabel);
			// Creates and adds visit areas Label
			visitAreasLabel = new JLabel();
			visitAreasLabel.setText("Visitable Areas");
			visitAreasLabel.setBorder(border);
			top.add(visitAreasLabel);

			// Adds components to panel 'top'
			add(top, BorderLayout.NORTH);

		}

		public void layoutMiddle() {

			JPanel middle = new JPanel();
			middle.setBackground(Color.gray);

			idField = new JTextField(5);
			idField.setEditable(false);
			middle.add(idField);

			firstNameField = new JTextField(10);
			middle.add(firstNameField);

			surnameField = new JTextField(10);
			middle.add(surnameField);

			qualificationField = new JTextField(3);
			middle.add(qualificationField);

			matchesField = new JTextField(3);
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
			saveButton = new JButton("Save Changes");
			saveButton.addActionListener(this);
			bottom.add(saveButton);
			removeButton = new JButton("Remove Referee");
			removeButton.addActionListener(this);
			bottom.add(removeButton);

			// Add components to panel
			add(bottom, BorderLayout.SOUTH);

		}

                @Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource() == backButton) {

				// TODO NOT SURE ABOUT THIS!
				setVisible(false);
			}
                        if(ae.getSource() == removeButton) {
                            controller.execRemoveReferee(idField.getText());
			}
                        if(ae.getSource() == saveButton) {
                            /**
                             * TODO, ASK Marco, question! :)
                             * From the requested Controller methods on 
                             * github, I thought matchesField would actually 
                             * be levelField. Could you clarify? 
                             */

                            controller.addReferee(firstNameField.getText(), 
                                    surnameField.getText(), qualificationField.getText(), 
                                    Integer.parseInt(matchesField.getText()), 
                                    JavaBallController.Location.valueOf(homeField.getText().toUpperCase()), 
                                    visitAreasField.getText());
			}
		}

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
	
	private class AllocateMatches extends JFrame implements ActionListener {
		
		private JTextField weekNumber;
		private JComboBox matchLevel, matchArea;
		private JButton allocateReferees, cancelButton;
		
		public AllocateMatches() {

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("Add Match");
			setSize(500, 200);
			setLocation(200, 200);
			topLayout();
			bottomLayout();
		}
		
		public void topLayout() {
			
			JPanel top = new JPanel();
			// Create and add text field for match week number
			weekNumber = new JTextField(5);
			top.add(weekNumber);
			// Create and add JComboBox for selecting match level
			matchLevel = new JComboBox();
			matchLevel.setEditable(false);
			matchLevel.addItem("Junior");
			matchLevel.addItem("Senior");
			top.add(matchLevel);
			// Create and add JComboBox for selecting match location
			matchArea = new JComboBox();
			matchArea.setEditable(false);
			matchArea.addItem("North");
			matchArea.addItem("Central");
			matchArea.addItem("South");
			top.add(matchArea);
			// Add panel 'top' to frame
			add(top, BorderLayout.NORTH);
			
		}
		
		public void bottomLayout() {
			
			JPanel bottom = new JPanel();
			// Create and add 'create match and allocate referees' button
			bottom.setBackground(Color.gray);
			allocateReferees = new JButton("Allocate Referees");
			bottom.add(allocateReferees);
			// Create and add back button
			cancelButton = new JButton("Back");
			cancelButton.addActionListener(this);
			bottom.add(cancelButton);
			// Add panel to frame
			add(bottom, BorderLayout.CENTER);
		}

		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource() == allocateReferees) {
				//TODO Filter list of Referees in main GUI to display suitable 
				// referees for that match.
				JOptionPane.showMessageDialog(null, "Referee table ordered by "
						+ "suitibility for this match.");
			}
			
			if(ae.getSource() == cancelButton) {
				
				// TODO NOT SURE ABOUT THIS!
				setVisible(false);
				
			}
			
		}
	}
}
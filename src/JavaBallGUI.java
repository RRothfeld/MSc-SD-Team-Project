import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

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
	private JLabel tableLabel;

	// GUI buttons and textFields for viewRefereeFrame
	private JButton backButton, saveButton, removeButton; 
	private JTextField idField, firstNameField, lastNameField, matchesField;
	private JComboBox qualificationField, qualificationLevel, homeLocation;
	private JCheckBox visitNorth, visitCentral, visitSouth;

	// GUI components for Table
	private JScrollPane tablePane;
	private JTable table;
	// TODO
	private final JavaBallController controller;
	private boolean orderedBySuitability, addReferee;
	
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 500;

	/**
	 * Constructor for JavaBallGUI
	 * @param controller
	 */
	public JavaBallGUI(JavaBallController controller) {
		// initiate GUI and its components

		// connect to controller
		this.controller = controller;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall Referees");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		layoutComponents();

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

		resetSearchButton = new JButton("Show all");
		resetSearchButton.addActionListener(this); 

		// add center components to center panels
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		tableLabel = new JLabel();
		searchPanel.add(tableLabel);

		// add navigation components to panelNavigation
		navPanel.add(addButton);
		navPanel.add(allocateButton);
		navPanel.add(chartButton);
		// TODO remove button and place somewhere else
		navPanel.add(resetSearchButton);
		//navPanel.add(exitButton);
		// FIXME Place at the bottom of navPanel
		navPanel.add(exitButton, BorderLayout.SOUTH); 
		
		table = new JTable(controller.getTableData());
		table.setFont(new Font("San-Serif", Font.PLAIN, 14));
                table.setAutoCreateRowSorter(true);
		// Set table dimensions
                // TODO make it relative to the frame dimensions
		table.setPreferredScrollableViewportSize(new Dimension(400, 100)); 
		table.setFillsViewportHeight(true);
                table.getModel().addTableModelListener(table);
                
                DefaultTableCellRenderer leftRender = new DefaultTableCellRenderer();
                leftRender.setHorizontalAlignment( JLabel.LEFT );
                // FIXME what is this 4?
                table.getColumnModel().getColumn(4).setCellRenderer(leftRender);

		// Create new JPane for table view
		tablePane = new JScrollPane(table);
		// Add tablePane to main GUI frame
		centrePanel.add(tablePane);
	}

	/**
	 * The main action performed class
	 * @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// If add button is pressed
		if (ae.getSource() == addButton) {
			RefereeFrame addRef = new RefereeFrame();
			addRef.setTitle("Add Referee");
			addReferee = true;
			addRef.setVisible(true);
			removeButton.setEnabled(false);


		} else if (ae.getSource() == allocateButton) {
			// If allocate referee to matches button is pressed
			AllocateMatches allocateRef = new AllocateMatches();
			allocateRef.setVisible(true);


		} else if (ae.getSource() == chartButton) {
			// If chart button is pressed
			controller.openChart();


		} else if (ae.getSource() == searchButton) {
			// If search button is pressed
			Referee ref = controller.getReferee(searchField.getText().trim());
			if (ref != null) {
                            
                            RefereeFrame editRef = new RefereeFrame(ref);
                            editRef.setVisible(true);
                            editRef.setTitle("Edit Referee");
                            
                            idField.setText(ref.getID());
                            firstNameField.setText(ref.getFirstName());
                            lastNameField.setText(ref.getLastName());
                            matchesField.setText(Integer.toString(ref.getAllocations()));
                            
                            firstNameField.setEditable(false);
                            lastNameField.setEditable(false);
                            matchesField.setEditable(false);
                            idField.setEditable(false);
                            
                            visitNorth.setSelected(controller.refTravel(ref, 
                                    JavaBallController.Location.NORTH));
                            visitCentral.setSelected(controller.refTravel(ref, 
                                    JavaBallController.Location.CENTRAL));
                            visitSouth.setSelected(controller.refTravel(ref, 
                                    JavaBallController.Location.SOUTH));
                            searchField.setText("Enter referee name or ID ...");
			}
			else {
				JOptionPane.showMessageDialog(null, "Referee not found");
                                searchField.setText("Enter referee name or ID ...");
			}

		} else if (ae.getSource() == resetSearchButton) {
			JOptionPane.showMessageDialog(null, "Referee table now ordered by "
					+ "referee ID.");
                        searchField.setText("Enter referee name or ID ...");
			// TODO update referee table

		} else if (ae.getSource() == exitButton) {
			// If save and exit button is pressed
			controller.saveExit();
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
	private final class RefereeFrame extends JFrame implements ActionListener {

		// GUI labels for viewRefereeFrame
		private JLabel idLabel, firstNameLabel, lastNameLabel, 
		qualificationLabel, matchesLabel, homeLabel, visitAreasLabel;
		private Referee referee;
		private JPanel labelPanel, inputPanel;


		/**
		 * Constructor to add components and create frame.
		 */
		public RefereeFrame() {

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			//setTitle("Add/Edit/Remove Referee");
			setSize(400, 350);
			setLocation(200, 200);
			setResizable(false);
			
			// Adds top GUI components
			layoutTop();
			// Adds central GUI components
			layoutMiddle();
			// Adds bottom GUI components
			layoutBottom();

		}

		public RefereeFrame(Referee referee) {

			// Calls the default constructor
			this();
			this.referee = referee;
		}

		/**
		 * Method to add labels to GUI.
		 */
		public void layoutTop() {

			labelPanel = new JPanel();
			labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
			// Creates a border around the labels so they are spaced apart
			EmptyBorder border = new EmptyBorder(8, 40, 8, 40);
			EmptyBorder space = new EmptyBorder(16,40,16,40);

			// Creates and adds ID Label
			idLabel = new JLabel();
			idLabel.setText("ID");
			idLabel.setBorder(border);
			labelPanel.add(idLabel);
			// Creates and adds first name Label
			firstNameLabel = new JLabel();
			firstNameLabel.setText("First Name");
			firstNameLabel.setBorder(border);
			labelPanel.add(firstNameLabel);
			// Creates and adds first name Label
			lastNameLabel = new JLabel();
			lastNameLabel.setText("Last Name");
			lastNameLabel.setBorder(border);
			labelPanel.add(lastNameLabel);
			// Creates and adds qualification Label
			qualificationLabel = new JLabel();
			qualificationLabel.setText("Qualification");
			qualificationLabel.setBorder(space);
			labelPanel.add(qualificationLabel);
			// Creates and adds matches allocated Label
			matchesLabel = new JLabel();
			matchesLabel.setText("Matches Allocated");
			matchesLabel.setBorder(border);
			labelPanel.add(matchesLabel);
			// Creates and adds referees home location Label
			homeLabel = new JLabel();
			homeLabel.setText("Home Location");
			homeLabel.setBorder(space);
			labelPanel.add(homeLabel);
			// Creates and adds visit areas Label
			visitAreasLabel = new JLabel();
			visitAreasLabel.setText("Visitable Areas");
			visitAreasLabel.setBorder(border);
			labelPanel.add(visitAreasLabel);

			// Adds components to panel 'top'
			add(labelPanel, BorderLayout.WEST);

		}

		public void layoutMiddle() {

			inputPanel = new JPanel();
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

			inputPanel.setBackground(Color.gray);

			idField = new JTextField(5);
			idField.setEditable(false);
			inputPanel.add(idField);

			firstNameField = new JTextField(10);
			inputPanel.add(firstNameField);

			lastNameField = new JTextField(10);
			lastNameField.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent event) {
				    String firstName = firstNameField.getText().trim();
				    String lastName = lastNameField.getText().trim();
	                            if (!(firstName.equals("") && lastName.equals(""))) {
	                        	RefereeList refList = new RefereeList();
	                        	String ID = refList.createID(firstName, lastName);
	                        	idField.setText(ID);
	                                //TODO 
	                            }
				}
			});
			inputPanel.add(lastNameField);

			qualificationField = new JComboBox();
                        qualificationField.setModel(new DefaultComboBoxModel(Referee.Qualifications.values()));
			inputPanel.add(qualificationField);

			qualificationLevel = new JComboBox();
			for (int i = 1; i <= Referee.MAX_QUAL_LENGTH; i++){
			    qualificationLevel.addItem(i + "");
			}
			inputPanel.add(qualificationLevel);

			matchesField = new JTextField(3);
			inputPanel.add(matchesField);

			homeLocation = new JComboBox();
                        homeLocation.setModel(new DefaultComboBoxModel(JavaBallController.Location.values()));
			inputPanel.add(homeLocation);

			visitNorth = new JCheckBox("North");
			visitSouth = new JCheckBox("South");
			visitCentral = new JCheckBox("Central");
			inputPanel.add(visitNorth);
			inputPanel.add(visitCentral);
			inputPanel.add(visitSouth);

			add(inputPanel, BorderLayout.EAST);

		}
		public void layoutBottom() {

			JPanel bottom = new JPanel();

			saveButton = new JButton("Save Changes");
			saveButton.addActionListener(this);
			bottom.add(saveButton);
			removeButton = new JButton("Remove Referee");
			removeButton.addActionListener(this);
			bottom.add(removeButton);
			backButton = new JButton("Cancel");
			backButton.addActionListener(this);
			bottom.add(backButton);

			// Add components to panel
			add(bottom, BorderLayout.SOUTH);
		}
		
		/**
		 * This method handles events for the the Referee Frame
		 * (i.e. adding, editing and removing referee information)
		 * @param ae
		 */
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource() == backButton) {

				// Close window
				dispose();
			}
			if(ae.getSource() == removeButton) {
				controller.removeReferee(referee);

				// Close window
				dispose();
			}
			if(ae.getSource() == saveButton) {
                            
                            if (controller.indexCounter() == 12){
                                JOptionPane.showMessageDialog(null, "Sorry.\n"
                                        + "The Referee List is full!");
                            } else {
				// Get travel locations for referee
				String north = visitNorth.isSelected() ? "Y" : "N";
				String central = visitCentral.isSelected() ? "Y" : "N";
				String south = visitSouth.isSelected() ? "Y" : "N";
				String travel = north + central + south;
                                
				if (this.referee == null) {

					controller.addReferee(firstNameField.getText(),
							lastNameField.getText(), 
							(Referee.Qualifications) qualificationField.getSelectedItem(), 
							Integer.parseInt(String.valueOf(qualificationLevel.getSelectedItem())),
							Integer.parseInt(matchesField.getText()), 
							(JavaBallController.Location) homeLocation.getSelectedItem(),
							travel);
                                        dispose();
				}

				else {
					controller.editReferee(referee,
							(Referee.Qualifications) qualificationField.getSelectedItem(), 
							Integer.parseInt(String.valueOf(qualificationLevel.getSelectedItem())),
							(JavaBallController.Location) homeLocation.getSelectedItem(), travel);
                                        dispose();
				}
                            }
			}

		}

	}
	/**
	 * TODO Upon clicking the allocate button, a new small JFrame comes up
	 * which contains areas to input the information of a match (week
	 * number, area and level). There is a cancel button, and a get suitable
	 * referees upon clicking this button the small JFrame is disposed and
	 * the main referee table is filtered to show the listed suitable
	 * referees, along with a note saying it is ordered and filtered
	 * according to suitability and there is a button to reset the table (No
	 * filter, and sort by the default ID).
	 * 
	 */

	private class AllocateMatches extends JFrame implements ActionListener {

            private JTextField weekNumber;
            private JComboBox matchLevel, matchArea;
            private JButton allocateReferees, cancelButton;
            private JLabel weekLabel,levelLabel, locationLabel;

            public AllocateMatches() {

                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    setTitle("Add Match");
                    setSize(500, 150);
                    setResizable(false);
                    setLocation(200, 200);
                    layoutTop();
                    layoutMiddle();
                    layoutBottom();
            }

            // This needs to be altered, but I wanted a label.
            private void layoutTop() {
                JPanel top = new JPanel();
                
                weekLabel = new JLabel("Week No.  ");
                levelLabel = new JLabel("Match Level  ");
                locationLabel = new JLabel("Location  ");
                top.add(weekLabel);
                top.add(levelLabel);
                top.add(locationLabel);
                add(top,BorderLayout.NORTH);
            }
            
            private void layoutMiddle() {

                    JPanel middle = new JPanel();
                    // Creates and add text field for match week number
                    weekNumber = new JTextField(5);
                    middle.add(weekNumber);
                    // Creates and add JComboBox for selecting match level
                    matchLevel = new JComboBox();
                    matchLevel.setModel(new DefaultComboBoxModel(Match.Level.values()));
                    matchLevel.setEditable(false);
                    middle.add(matchLevel);
                    // Creates and add JComboBox for selecting match location
                    matchArea = new JComboBox();
                    matchArea.setEditable(false);
                    matchArea.setModel(new DefaultComboBoxModel(JavaBallController.Location.values()));
                    middle.add(matchArea);
                    // Adds panel 'top' to frame
                    add(middle, BorderLayout.CENTER);

            }

            private void layoutBottom() {

                    JPanel bottom = new JPanel();
                    // Create and add 'create match and allocate referees' button
                    bottom.setBackground(Color.gray);
                    allocateReferees = new JButton("Allocate Referees");
                    allocateReferees.addActionListener(this);
                    bottom.add(allocateReferees);
                    // Creates and add back button
                    cancelButton = new JButton("Cancel");
                    cancelButton.addActionListener(this);
                    bottom.add(cancelButton);
                    // Adds panel to frame
                    add(bottom, BorderLayout.SOUTH);
            }

            /**
             * This method is used to handle events related to allocating matches
             * 
             */
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == allocateReferees) {
                    // referees for that match.
                    try {
                        
                        int week = Integer.parseInt(weekNumber.getText());
                        if (week < Season.MIN_WEEK || week > Season.MAX_WEEK)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid Week Number");
                            weekNumber.setText("");
                        } else {
                            Match.Level level = (Match.Level) matchLevel.getSelectedItem();
                            JavaBallController.Location area = 
                                    (JavaBallController.Location) matchArea.getSelectedItem();
                            
                            ArrayList<Referee> suitableRefs = controller.
                                    allocateReferees(week, level, area);			
                            if (suitableRefs == null)
                            {
                                JOptionPane.showMessageDialog(null, "That week is already in use!");
                                weekNumber.setText("");
                            }
                            table = new JTable(controller.allocatedTableData(suitableRefs));
                            
                            dispose();
                        }
                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Please Enter a Number");
                        weekNumber.setText("");
                    }
                }
                else  {
                    // Close window
                    dispose();
                }
            }
	}
}
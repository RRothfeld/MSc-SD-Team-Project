import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
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
	private JPanel menuPanel, navPanel, centrePanel, searchPanel, listPanel;
	private JButton addButton, chartButton, allocateButton, exitButton,
	searchButton, resetSearchButton;
	private JTextField searchField;
	private JLabel titleLabel,tableLabel;

	// GUI components for Table
	private JScrollPane tablePane;
	private JTable table;
	// TODO
	private final JavaBallController controller;
	private final String searchFieldString = "Enter referee name or ID...";
	
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
	    
	    	menuPanel = new JPanel(new GridLayout(6, 1, 1, 5));
	    	menuPanel.setBackground(Color.GRAY);
	    	
		navPanel = new JPanel();
		navPanel.setLayout(new BorderLayout());
		
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
		searchField.setText(searchFieldString);
		searchField.addActionListener(this);
		// Clear the text field if the mouse is clicked in it
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
                            if (searchField.getText()
                                    .equals(searchFieldString)) {
                                searchField.setText("");
                            }
			}
		});

                titleLabel = new JLabel("JavaBall");
                titleLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
                
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
		menuPanel.add(titleLabel);
                menuPanel.add(addButton);
		menuPanel.add(allocateButton);
		menuPanel.add(chartButton);
		// TODO remove button and place somewhere else
		menuPanel.add(resetSearchButton);
		navPanel.add(menuPanel, BorderLayout.NORTH);
		navPanel.add(exitButton, BorderLayout.SOUTH);
		// FIXME Place at the bottom of navPanel
		
		table = new JTable(controller.getTableData());
		table.setFont(new Font("San-Serif", Font.PLAIN, 14));
		table.setBackground(Color.decode("#EEEEEE"));
                table.setAutoCreateRowSorter(true);
		// Setting scroll area
                
                // TODO make it relative to the frame dimensions
		table.setPreferredScrollableViewportSize(new Dimension(400, 100)); 
		table.setFillsViewportHeight(false);
                table.getModel().addTableModelListener(table);
                
                DefaultTableCellRenderer leftRender = new DefaultTableCellRenderer();
                leftRender.setHorizontalAlignment( JLabel.LEFT );
                table.getColumnModel().getColumn(4).setCellRenderer(leftRender);
                
//                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//                table.setDefaultRenderer(int.class, centerRenderer);

		// Create new JPane for table view
		tablePane = new JScrollPane(table);
		table.setSize(centrePanel.getWidth(), centrePanel.getHeight());
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
			RefereeFrame addRef = new RefereeFrame(controller);
			addRef.setTitle("Add Referee");
			addRef.setVisible(true);
			addRef.setRemoveButtonEnabled(false);
                        

		} else if (ae.getSource() == allocateButton) {
			// If allocate referee to matches button is pressed
			AllocateMatches allocateRef = new AllocateMatches(controller);
			allocateRef.setVisible(true);

		} else if (ae.getSource() == chartButton) {
			// If chart button is pressed
			controller.openChart();

	} else if (ae.getSource() == searchButton) {
	    // If search button is pressed
	    String refInfo = searchField.getText().toLowerCase().trim();
	    Referee ref = controller.getReferee(refInfo);
	    if (ref != null) {

		RefereeFrame editRef = new RefereeFrame(controller, ref);
		editRef.setReferee(ref);
		editRef.setVisible(true);
		editRef.setTitle("Edit Referee");

		editRef.setDetails(ref.getID(), ref.getFirstName(),
			ref.getLastName(), ref.getAllocations());
		editRef.setLocations();

		searchField.setText(searchFieldString);
	    } else {
		JOptionPane.showMessageDialog(null, "Referee not found");
		searchField.setText(searchFieldString);
	    }

	} else if (ae.getSource() == resetSearchButton) {
			JOptionPane.showMessageDialog(null, "Referee table now ordered by "
					+ "referee ID.");
                        searchField.setText(searchFieldString);
			// TODO update referee table

		} else if (ae.getSource() == exitButton) {
			// If save and exit button is pressed
			controller.saveExit();
		}
	}
}
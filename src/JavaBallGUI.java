import java.awt.BorderLayout;
import java.awt.Color;
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
 * @version 1.0
 * @since 18-01-2015
 */

public class JavaBallGUI extends JFrame implements ActionListener {
    // GUI components as instance variables
    private JPanel navPanel, centrePanel, searchPanel, listPanel;
    private JButton addButton, chartButton, allocateButton, exitButton,
	    searchButton;
    private JTextField searchField;
    private JTextArea areaRefereeList;

    // TODO
    private JFrame chart;
    private RefereeList referees;

    private final String INPUTFILENAME = "RefereesIn.txt";
    private final RefereeList  refList = new RefereeList();
       
    /**
     * Constructor for JavaBallGUI
     */
    public JavaBallGUI() {
	// initiate GUI and its components
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setTitle("JavaBall Referees");
	setSize(800, 500);
	layoutComponents();
	updateRefereeList();
        initList();
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
        //Clear the text field if the mouse is clicked in it
        searchField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){
                if (searchField.getText().equals("Enter referee name or ID ...")){
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

    private void updateRefereeList() {

    }
        
	/**
	 * Method to read in file, create Referee Obects and fill refList.
	 */
	private void initList() {

		try {
			FileReader refereeFile = new FileReader(INPUTFILENAME);
			Scanner refScanner = new Scanner(refereeFile);

			while (refScanner.hasNextLine()) {
				String newReferee = refScanner.nextLine();
				if (newReferee != null) {
					Referee referee = new Referee(newReferee);
					refList.add(referee);
				}

			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(JavaBallGUI.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		// Temporary testing method in RefereeList to make sure methods work
		refList.debug();
	}

	private void displayChart() {

		chart = new ChartFrame(referees);
		chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		chart.setVisible(true);
	}

    public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == addButton) {
	    System.out.println("Add");
	} else if (ae.getSource() == allocateButton) {
	   System.out.println("Allocate");
	} else if (ae.getSource() == chartButton) {
	    displayChart();
	} else if (ae.getSource() == searchButton) {
	   System.out.println("Search");
	} else if (ae.getSource() == exitButton) {
	    System.exit(0);
	} 
    }
}
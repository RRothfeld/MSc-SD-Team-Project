import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.io.*;

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
    // TODO Raoul
    // DESIGN ACCORDING TO MARCO GUI FRAME #2

    // GUI components as instance variables
    private JPanel navPanel, centrePanel, searchPanel, listPanel;
    private JButton addButton, chartButton, allocateButton, exitButton,
	    searchButton;
    private JTextField searchField;
    private JTextArea areaRefereeList;

    //
    private JFrame chart;
    private RefereeList referees;

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
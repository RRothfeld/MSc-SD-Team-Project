import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.io.*;

/**
 * Team Foxtrot
 * JavaBall Referees - JavaBallGUI.java
 * Defines JavaBall GUI that displays referee and match details
 * <p>
 * University of Glasgow
 * MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author  Miroslav Pashov, 1005139P
 * @author  Andrew Lowson, 0800685L
 * @author  Marco Cook, 2152599C
 * @author  Raoul Rothfeld, 2164502R
 * 
 * @version 1.0
 * @since   18-01-2015
 */

public class JavaBallGUI extends JFrame implements ActionListener {
	// TODO Raoul
	// DESIGN ACCORDING TO MARCO GUI FRAME #2
	
	// GUI components as instance variables
	private JPanel panelNavigation, panelCenter, panelSearch, panelList;
	private JButton buttonAdd, buttonChart, buttonAllocate, buttonExit,
			buttonSearch;
	private JTextField fieldSearch;
	private JTextArea areaRefereeList;
	
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
		panelNavigation = new JPanel();
		panelNavigation.setLayout(new BoxLayout(panelNavigation,
				BoxLayout.PAGE_AXIS));
		panelCenter = new JPanel(new BorderLayout());
		panelSearch = new JPanel();
		panelList = new JPanel();

		// set JPanel backgrounds
		panelNavigation.setBackground(Color.gray);
		panelCenter.setBackground(Color.white);
		panelSearch.setBackground(Color.white);
		panelList.setBackground(Color.white);
			
		// add main JPanels to JFrame
		add(panelNavigation, BorderLayout.WEST);
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.add(panelSearch, BorderLayout.NORTH);
		panelCenter.add(panelList, BorderLayout.SOUTH);
		
		// create JTextField
		// length of JTextField measured in amount of visible 'm' characters
		fieldSearch = new JTextField(40); //length of 40 for String input
		fieldSearch.setText("Enter referee name or ID ...");
		
		// create JButtons
		buttonAdd = new JButton("Add Referee");
		buttonChart = new JButton("Show Chart");
		buttonAllocate = new JButton("Allocate Refs");
		buttonExit = new JButton("Save & Exit");
		buttonSearch = new JButton("Search");
		
		//add center components to center panels
		panelSearch.add(fieldSearch);
		panelSearch.add(buttonSearch);
		
		//add navigation components to panelNavigation
		panelNavigation.add(buttonAdd);
		panelNavigation.add(buttonChart);
		panelNavigation.add(buttonAllocate);
		panelNavigation.add(buttonExit);
	}
	
	private void updateRefereeList() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
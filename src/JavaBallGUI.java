import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.io.*;

/**
 * Team Foxtrot
 * JavaBall Referees
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
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
 * @since   14-01-2015
 */

public class JavaBallGUI extends JFrame implements ActionListener {
	// TODO Raoul
	/*
	 *  JFrame
 2 JPanels, one with scroll bar
 1 JLabel for title
 5 JButtons - Add Referee button, Search Referee button, View Bar Chart button, View and
Allocate Matches button, Exit Program button.
 1 JTextfield for searching a referee
 1 JTextArea for displaying all Referees
 6 JLabels 
	 */
	

	/** GUI JButtons */
	private JButton test;

	/** GUI JTextFields */
	private JTextField test2;

	/** Display of class timetable */
	private JTextArea test3;

	/**
	 * Constructor for JavaBallGUI
	 */
	public JavaBallGUI() {
		// initiate GUI and its components
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall Referees");
		setSize(500, 500);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
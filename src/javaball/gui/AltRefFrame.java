package javaball.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javaball.controller.JavaBallController;
import javaball.enums.Location;
import javaball.enums.MatchLevel;
import javaball.model.Referee;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Window to input or edit referee information; or delete an existing referees
 * <p>
 * University of Glasgow
 * MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author Miroslav Pashov (1005139p)
 * @author Andrew Lowson (0800685l)
 * @author Marco Cook (2152599c)
 * @author Raoul Rothfeld (2164502r)
 * 
 * @version 1.3
 * @since 13-02-2015
 */
public final class AltRefFrame extends JFrame implements ActionListener {

	private Referee referee;
	
	// Dimensions of referee frame
	private final int WIDTH = 350;
	private final int HEIGHT = 500;
	
	/** Predefined set of colours for uniform component colouring */
	private final Color background = Color.decode("0xDDDDDD"),
			highlight = Color.decode("0xFFCCCC"), border = Color.GRAY;
	
	/** JFrame and spacing dimensions in pixels */
	private static final int SPACING = 5;
	
	/** Reference to the JavaBallController */
	private final JavaBallController controller;
	private JButton btnAllocate, btnCancel;
	private JTextField fldPrevAlloc;
	private JTextField fldFirstName;
	private JTextField fldLastName;
	
	/**
	 * Constructor to add components and create frame.
	 * @param controller
	 * @wbp.parser.constructor
	 */
	public AltRefFrame(JavaBallController controller) {

		this.controller = controller;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Add Referee");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null); //centres JFrame on desktop
		setResizable(false);
		
		// Create JPanels
		JPanel topPanel = new JPanel();
		JPanel refDetailsHeaderPanel = new JPanel();
		JPanel refDetailsPanel = new JPanel();
		JPanel innerRefDetailsPanel = new JPanel();
		JPanel refIDPanel = new JPanel();
		JPanel firstNamePanel = new JPanel();
		FlowLayout fl_firstNamePanel = (FlowLayout) firstNamePanel.getLayout();
		fl_firstNamePanel.setHgap(10);
		fl_firstNamePanel.setAlignment(FlowLayout.LEFT);
		JPanel lastNamePanel = new JPanel();
		FlowLayout fl_lastNamePanel = (FlowLayout) lastNamePanel.getLayout();
		fl_lastNamePanel.setHgap(10);
		fl_lastNamePanel.setAlignment(FlowLayout.LEFT);
		JPanel buttonPanel = new JPanel();

		// Set JPanel layouts
		topPanel.setLayout(new BorderLayout(0, 0));
		refDetailsHeaderPanel.setLayout(new BorderLayout(0, 0));
		refDetailsPanel.setLayout(new BorderLayout(0, 0));
		innerRefDetailsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SPACING,
				SPACING));
		setFlowLayout(refIDPanel);
		setFlowLayout(firstNamePanel);
		setFlowLayout(lastNamePanel);
		
		// Set JPanel properties
		refDetailsPanel.setBackground(background);
		refDetailsPanel.setBorder(new LineBorder(border));
		innerRefDetailsPanel.setBackground(background);
	
		// Create spacers
		Component topSpacerTop = Box.createVerticalStrut(SPACING);
		Component refDetailsHeaderSpacer = Box.createHorizontalStrut(SPACING);
		Component topSpacerLeft = Box.createHorizontalStrut(SPACING);
		Component topSpacerRight = Box.createHorizontalStrut(SPACING);
		Component topSpacerBottom = Box.createVerticalStrut(SPACING);
		Component refDetailsSpacerTop = Box.createVerticalStrut(SPACING);
		Component refDetailsSpacerRight = Box.createHorizontalStrut(SPACING);
		Component refDetailsSpacerBottom = Box.createVerticalStrut(SPACING);
		Component refDetailsSpacerLeft = Box.createHorizontalStrut(SPACING);
		
		// Create JLabels
		JLabel lblRefDetailsHeader = new JLabel("Referee Details");
		JLabel lblRefIDLabel = new JLabel("Referee ID");
		JLabel lblFirstName = new JLabel("First Name");
		JLabel lblLastName = new JLabel("Last Name");
		
		// Set JLabel properties
		lblRefIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);

		// Create JButtons
		btnAllocate = new JButton("Save");
		btnCancel = new JButton("Cancel");
		
		// Add action listeners
		btnAllocate.addActionListener(this);
		btnCancel.addActionListener(this);
		
		// Add wrapper JPanels to GUI
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.add(refDetailsHeaderPanel, BorderLayout.NORTH);
		topPanel.add(refDetailsPanel, BorderLayout.CENTER);
		refDetailsPanel.add(innerRefDetailsPanel, BorderLayout.CENTER);
		
		// Add wrapper spacers to GUI
		refDetailsHeaderPanel.add(topSpacerTop, BorderLayout.NORTH);
		refDetailsHeaderPanel.add(refDetailsHeaderSpacer, BorderLayout.WEST);
		topPanel.add(topSpacerLeft, BorderLayout.WEST);
		topPanel.add(topSpacerRight, BorderLayout.EAST);
		topPanel.add(topSpacerBottom, BorderLayout.SOUTH);
		refDetailsPanel.add(refDetailsSpacerTop, BorderLayout.NORTH);
		refDetailsPanel.add(refDetailsSpacerRight, BorderLayout.EAST);
		refDetailsPanel.add(refDetailsSpacerBottom, BorderLayout.SOUTH);
		refDetailsPanel.add(refDetailsSpacerLeft, BorderLayout.WEST);		
	
		// Add match details components (top of JFrame)
		refDetailsHeaderPanel.add(lblRefDetailsHeader, BorderLayout.CENTER);
		innerRefDetailsPanel.add(lblRefIDLabel);
		innerRefDetailsPanel.add(refIDPanel);
		
		JLabel lblRefID = new JLabel("New label");
		refIDPanel.add(lblRefID);
		innerRefDetailsPanel.add(lblFirstName);
		innerRefDetailsPanel.add(firstNamePanel);
		
		fldFirstName = new JTextField();
		firstNamePanel.add(fldFirstName);
		fldFirstName.setColumns(12);
		innerRefDetailsPanel.add(lblLastName);
		innerRefDetailsPanel.add(lastNamePanel);
		
		fldLastName = new JTextField();
		lastNamePanel.add(fldLastName);
		fldLastName.setColumns(12);
		
		JPanel centrePanel = new JPanel();
		getContentPane().add(centrePanel, BorderLayout.CENTER);
		centrePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel centreTopPanel = new JPanel();
		centrePanel.add(centreTopPanel, BorderLayout.NORTH);
		centreTopPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel allocHeaderPanel = new JPanel();
		centreTopPanel.add(allocHeaderPanel, BorderLayout.NORTH);
		allocHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		Component centreTopSpacerTop = Box.createVerticalStrut(5);
		allocHeaderPanel.add(centreTopSpacerTop, BorderLayout.NORTH);
		
		Component allocHeaderSpacer = Box.createHorizontalStrut(5);
		allocHeaderPanel.add(allocHeaderSpacer, BorderLayout.WEST);
		
		JLabel lblAllocHeader = new JLabel("Match Allocations");
		allocHeaderPanel.add(lblAllocHeader, BorderLayout.CENTER);
		
		JPanel allocPanel = new JPanel();
		allocPanel.setBorder(new LineBorder(border));
		allocPanel.setBackground(new Color(221, 221, 221));
		centreTopPanel.add(allocPanel, BorderLayout.CENTER);
		allocPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel innerAllocPanel = new JPanel();
		innerAllocPanel.setBackground(new Color(221, 221, 221));
		allocPanel.add(innerAllocPanel, BorderLayout.CENTER);
		innerAllocPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPreviousAllocations = new JLabel("Previous Allocations");
		lblPreviousAllocations.setHorizontalAlignment(SwingConstants.RIGHT);
		innerAllocPanel.add(lblPreviousAllocations);
		
		JPanel prevAllocPanel = new JPanel();
		FlowLayout fl_prevAllocPanel = (FlowLayout) prevAllocPanel.getLayout();
		fl_prevAllocPanel.setHgap(10);
		fl_prevAllocPanel.setAlignment(FlowLayout.LEFT);
		innerAllocPanel.add(prevAllocPanel);
		
		fldPrevAlloc = new JTextField();
		prevAllocPanel.add(fldPrevAlloc);
		fldPrevAlloc.setColumns(5);
		
		Component allocSpacerBottom = Box.createVerticalStrut(5);
		allocPanel.add(allocSpacerBottom, BorderLayout.SOUTH);
		
		Component allocSpacerLeft = Box.createHorizontalStrut(5);
		allocPanel.add(allocSpacerLeft, BorderLayout.WEST);
		
		Component allocSpacerTop = Box.createVerticalStrut(5);
		allocPanel.add(allocSpacerTop, BorderLayout.NORTH);
		
		Component allocSpacerRight = Box.createHorizontalStrut(5);
		allocPanel.add(allocSpacerRight, BorderLayout.EAST);
		
		Component centreTopSpacerLeft = Box.createHorizontalStrut(5);
		centreTopPanel.add(centreTopSpacerLeft, BorderLayout.WEST);
		
		Component centreTopSpacerRight = Box.createHorizontalStrut(5);
		centreTopPanel.add(centreTopSpacerRight, BorderLayout.EAST);
		
		Component centreTopSpacerBottom = Box.createVerticalStrut(5);
		centreTopPanel.add(centreTopSpacerBottom, BorderLayout.SOUTH);
		
		JPanel centreBottomPanel = new JPanel();
		centrePanel.add(centreBottomPanel, BorderLayout.CENTER);
		centreBottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		centreBottomPanel.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_4 = Box.createVerticalStrut(5);
		panel_9.add(verticalStrut_4, BorderLayout.NORTH);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(5);
		panel_9.add(horizontalStrut_5, BorderLayout.WEST);
		
		JLabel lblRefereeQualification = new JLabel("Referee Qualification");
		panel_9.add(lblRefereeQualification, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(border));
		panel_10.setBackground(new Color(221, 221, 221));
		panel_5.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(221, 221, 221));
		panel_10.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblQualificationType = new JLabel("Qualification Type");
		lblQualificationType.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblQualificationType);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_12.getLayout();
		flowLayout_3.setHgap(10);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_11.add(panel_12);
		
		JComboBox<MatchLevel> comboBox_2 = new JComboBox<MatchLevel>();
		panel_12.add(comboBox_2);
		
		JLabel lblQualificationLevel = new JLabel("Qualification Level");
		lblQualificationLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblQualificationLevel);
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_13.getLayout();
		flowLayout_4.setHgap(10);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_11.add(panel_13);
		
		JComboBox<Location> comboBox_3 = new JComboBox<Location>();
		panel_13.add(comboBox_3);
		
		Component verticalStrut_5 = Box.createVerticalStrut(5);
		panel_10.add(verticalStrut_5, BorderLayout.NORTH);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(5);
		panel_10.add(horizontalStrut_6, BorderLayout.WEST);
		
		Component verticalStrut_6 = Box.createVerticalStrut(5);
		panel_10.add(verticalStrut_6, BorderLayout.SOUTH);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(5);
		panel_10.add(horizontalStrut_13, BorderLayout.EAST);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(5);
		panel_5.add(horizontalStrut_7, BorderLayout.WEST);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(5);
		panel_5.add(horizontalStrut_8, BorderLayout.EAST);
		
		Component verticalStrut_7 = Box.createVerticalStrut(5);
		panel_5.add(verticalStrut_7, BorderLayout.SOUTH);
		
		JPanel panel_8 = new JPanel();
		centreBottomPanel.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(5);
		panel_8.add(horizontalStrut_9, BorderLayout.EAST);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(5);
		panel_8.add(horizontalStrut_10, BorderLayout.WEST);
		
		Component verticalStrut_8 = Box.createVerticalStrut(5);
		panel_8.add(verticalStrut_8, BorderLayout.NORTH);
		
		JPanel panel_14 = new JPanel();
		panel_8.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_9 = Box.createVerticalStrut(5);
		panel_14.add(verticalStrut_9, BorderLayout.NORTH);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(5);
		panel_14.add(horizontalStrut_11, BorderLayout.WEST);
		
		JLabel lblLocationDetails = new JLabel("Location Details");
		panel_14.add(lblLocationDetails, BorderLayout.CENTER);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(border));
		panel_15.setBackground(new Color(221, 221, 221));
		panel_8.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(221, 221, 221));
		panel_15.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_16.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblHomeLocation = new JLabel("Home Location");
		panel_7.add(lblHomeLocation);
		lblHomeLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_17 = new JPanel();
		panel_7.add(panel_17);
		FlowLayout flowLayout_5 = (FlowLayout) panel_17.getLayout();
		flowLayout_5.setHgap(10);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		
		JComboBox<MatchLevel> comboBox_4 = new JComboBox<MatchLevel>();
		panel_17.add(comboBox_4);
		
		JPanel panel_20 = new JPanel();
		panel_16.add(panel_20);
		panel_20.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTravelPreferances = new JLabel("Travel Preferences");
		panel_20.add(lblTravelPreferances);
		lblTravelPreferances.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_21 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_21.getLayout();
		flowLayout_6.setHgap(6);
		panel_20.add(panel_21);
		
		JPanel panel_18 = new JPanel();
		panel_21.add(panel_18);
		panel_18.setLayout(new GridLayout(0, 1, 0, 0));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		panel_18.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		panel_18.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		panel_18.add(chckbxNewCheckBox_2);
		
		Component verticalStrut_10 = Box.createVerticalStrut(5);
		panel_15.add(verticalStrut_10, BorderLayout.SOUTH);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(5);
		panel_15.add(horizontalStrut_12, BorderLayout.WEST);
		
		Component verticalStrut_11 = Box.createVerticalStrut(5);
		panel_15.add(verticalStrut_11, BorderLayout.NORTH);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(5);
		panel_15.add(horizontalStrut_14, BorderLayout.EAST);
		
		// Add button components (bottom of JFrame)
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(btnAllocate);
		
		JButton btnRemove = new JButton("Remove");
		buttonPanel.add(btnRemove);
		buttonPanel.add(btnCancel);
	}
	
	/**
	 *
	 * @param controller
	 * @param referee
	 */
	public AltRefFrame(JavaBallController controller, Referee referee) {

          // Calls the default constructor
          this(controller);
          
          this.referee = referee;
          
          //setDetails();
	}
	
	/**
	 * Applies the defined FlowLayout and colouring to a JPanel
	 * @param panel the JPanel to which the standard FlowLayout is applied
	 */
	private void setFlowLayout(JPanel panel) {
		// Set JPanel background to the defined background colour
		panel.setBackground(background);
		
		// Apply standard FlowLayout settings
		FlowLayout fl_refIDPanel = (FlowLayout) panel.getLayout();
		fl_refIDPanel.setVgap(SPACING + 2);
		fl_refIDPanel.setHgap(SPACING * 2);
		fl_refIDPanel.setAlignment(FlowLayout.LEFT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
package javaball.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javaball.controller.JavaBallController;
import javaball.model.Referee;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Defines the main GUI that displays all referees and their details
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
 * @version 1.7
 * @since 11-02-2015
 */
public class JavaBallGUI extends JFrame implements ActionListener {
	/** JFrame and spacing dimensions in pixels */
	private static final int WIDTH = 800, HEIGHT = 400, SPACING = 5;

	/** Predefined set of colours for uniform component colouring */
	private final Color background = Color.decode("0xDDDDDD");

	/** Reference to the JavaBallController */
	private final JavaBallController controller;


	private final String searchFieldString = "Enter name or ID...";
	private final String tableHeaderString = "Referees ordered by ID";

	private JTextField fldSearch;
	private JButton btnSearch, btnShowAll, btnAddRef, btnAllocRefs, btnChart,
			btnSaveExit;
	private JTable refereesTable;
	private JLabel lblTableHeader;

	/**
	 * Constructor for JavaBallGUI
	 * 
	 * @param controller
	 */
	public JavaBallGUI(JavaBallController controller) {
		// Store reference to JavaBallController
		this.controller = controller;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall RAS");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null); // centres JFrame on desktop
		
		layoutHeader();
		layoutNavigation();
		layoutContent();
		
		addActionListeners();
	}

	private void layoutHeader() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) searchPanel.getLayout();
		flowLayout.setVgap(10);
		searchPanel.setBackground(Color.DARK_GRAY);
		headerPanel.add(searchPanel, BorderLayout.EAST);

		fldSearch = new JTextField();
		fldSearch.setText(searchFieldString);
		searchPanel.add(fldSearch);
		fldSearch.setColumns(15);

		btnSearch = new JButton("Search");
		searchPanel.add(btnSearch);

		Component headerSpacerLeft = Box.createHorizontalStrut(10);
		headerPanel.add(headerSpacerLeft, BorderLayout.WEST);

		JPanel headerTextPanel = new JPanel();
		headerTextPanel.setBackground(Color.DARK_GRAY);
		headerPanel.add(headerTextPanel, BorderLayout.CENTER);
		headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.X_AXIS));

		JLabel lblHeader = new JLabel("JavaBall ");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Dialog", Font.BOLD, 18));
		headerTextPanel.add(lblHeader);

		JLabel lblSubHeader = new JLabel("Referee Allocation System");
		lblSubHeader.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblSubHeader.setForeground(Color.WHITE);
		headerTextPanel.add(lblSubHeader);
	}
	
	private void layoutNavigation() {
		JPanel navPanel = new JPanel();
		navPanel.setBackground(Color.decode("0xDDDDDD"));
		getContentPane().add(navPanel, BorderLayout.WEST);
		navPanel.setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.decode("0xDDDDDD"));
		navPanel.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		JLabel lblNavigation = new JLabel("Navigation");
		buttonPanel.add(lblNavigation);

		Component navBtnSpacerTop = Box.createVerticalStrut(5);
		buttonPanel.add(navBtnSpacerTop);

		btnAddRef = new JButton("Add Referee");
		buttonPanel.add(btnAddRef);

		Component navBtnSpacerMiddle = Box.createVerticalStrut(5);
		buttonPanel.add(navBtnSpacerMiddle);

		btnAllocRefs = new JButton("Allocate Referees");
		buttonPanel.add(btnAllocRefs);

		Component navBtnSpacerBottom = Box.createVerticalStrut(5);
		buttonPanel.add(navBtnSpacerBottom);

		btnChart = new JButton("Show Chart");
		buttonPanel.add(btnChart);

		Component navBtnGlueBottom = Box.createVerticalGlue();
		buttonPanel.add(navBtnGlueBottom);

		btnSaveExit = new JButton("Save and Exit");
		buttonPanel.add(btnSaveExit);

		Component navSpacerLeft = Box.createHorizontalStrut(5);
		navPanel.add(navSpacerLeft, BorderLayout.WEST);

		Component navSpacerRight = Box.createHorizontalStrut(5);
		navPanel.add(navSpacerRight, BorderLayout.EAST);

		Component navSpacerTop = Box.createVerticalStrut(5);
		navPanel.add(navSpacerTop, BorderLayout.NORTH);

		Component navSpacerBottom = Box.createVerticalStrut(5);
		navPanel.add(navSpacerBottom, BorderLayout.SOUTH);
		
	}
	
	private void layoutContent() {

		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel tableHeaderPanel = new JPanel();
		contentPanel.add(tableHeaderPanel, BorderLayout.NORTH);
		tableHeaderPanel.setLayout(new BorderLayout(0, 0));

		lblTableHeader = new JLabel(tableHeaderString);
		lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		// lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		tableHeaderPanel.add(lblTableHeader);

		JPanel tableResetPanel = new JPanel();
		tableHeaderPanel.add(tableResetPanel, BorderLayout.EAST);
		tableResetPanel.setLayout(new BoxLayout(tableResetPanel,
				BoxLayout.X_AXIS));

		btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Dialog", Font.BOLD, 10));
		btnShowAll.setEnabled(false);
		tableResetPanel.add(btnShowAll);

		Component tableHeaderSpacerRight = Box.createHorizontalStrut(5 + 1);
		tableResetPanel.add(tableHeaderSpacerRight);

		Component tableHeaderSpacerLeft = Box.createHorizontalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacerLeft, BorderLayout.WEST);

		Component tableHeaderSpacerBottom = Box.createVerticalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacerBottom, BorderLayout.SOUTH);

		Component tableHeaderSpacerTop = Box.createVerticalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacerTop, BorderLayout.NORTH);

		refereesTable = controller.getTable();
		refereesTable.setBackground(UIManager.getColor("menu"));
		refereesTable.setAutoCreateRowSorter(true);
		refereesTable.setBorder(null);
		refereesTable.setShowVerticalLines(false);
		refereesTable.setRowSelectionAllowed(false);
		refereesTable.setFont(new Font("Dialog", Font.PLAIN, 14));

		DefaultTableCellRenderer leftRender = new DefaultTableCellRenderer();
		leftRender.setHorizontalAlignment(JLabel.LEFT);
		refereesTable.getColumnModel().getColumn(4).setCellRenderer(leftRender);

		// Create new JPane for table view
		refereesTable.setSize(contentPanel.getWidth(), contentPanel.getHeight());
		JScrollPane tablePane = new JScrollPane(refereesTable);
		contentPanel.add(tablePane);

		Component tableSpacerLeft = Box.createHorizontalStrut(5);
		contentPanel.add(tableSpacerLeft, BorderLayout.WEST);

		Component tableSpacerRight = Box.createHorizontalStrut(5);
		contentPanel.add(tableSpacerRight, BorderLayout.EAST);

		Component tableSpacerBottom = Box.createVerticalStrut(5);
		contentPanel.add(tableSpacerBottom, BorderLayout.SOUTH);
	}
	
	private void addActionListeners() {
		// add listeners
		btnSearch.addActionListener(this);
		btnShowAll.addActionListener(this);
		btnAddRef.addActionListener(this);
		btnAllocRefs.addActionListener(this);
		btnChart.addActionListener(this);
		btnSaveExit.addActionListener(this);

		// Clear the text field if the mouse is clicked in it
		fldSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (fldSearch.getText().equals(searchFieldString)) {
					fldSearch.setText("");
				}
			}
		});

	}

	/**
	 * Starts appropriate responses upon user interaction
	 * @param ae the action event
	 */
	public void actionPerformed(ActionEvent ae) {
		// If add button is pressed
		if (ae.getSource() == btnAddRef) {
			RefereeFrame addRef = new RefereeFrame(controller);
			addRef.setTitle("Add Referee");
			addRef.setVisible(true);
			addRef.setRemoveButtonEnabled(false);
		} else if (ae.getSource() == btnAllocRefs) {
			// If allocate referee to matches button is pressed
			AllocationFrame allocateRef = new AllocationFrame(controller);
			allocateRef.setVisible(true);
		} else if (ae.getSource() == btnChart) {
			// If chart button is pressed
			controller.openChart();
		} else if (ae.getSource() == btnSearch) {
			// If search button is pressed
			String refInfo = fldSearch.getText().toLowerCase().trim();
			Referee ref = controller.getReferee(refInfo);
			if (ref != null) {
				RefereeFrame editRef = new RefereeFrame(controller, ref);
				editRef.setVisible(true);
				editRef.setTitle("Edit Referee");
				editRef.setLocations();
				editRef.setHomeLocation();
				fldSearch.setText(searchFieldString);
			} else {
				JOptionPane.showMessageDialog(null, "Referee not found");
				fldSearch.setText(searchFieldString);
			}
		} else if (ae.getSource() == btnShowAll) {
			controller.updateTable();
		} else if (ae.getSource() == btnSaveExit) {
			// If save and exit button is pressed
			controller.saveExit();
		}
	}

	/**
	 * 
	 * @param label
	 */
	public void setTableHeader(String label) {
		btnShowAll.setEnabled(true);
		lblTableHeader.setText(label);
	}

	/**
	 * 
	 */
	public void resetTableHeader() {
		lblTableHeader.setText(tableHeaderString);
		btnShowAll.setEnabled(false);
	}
}
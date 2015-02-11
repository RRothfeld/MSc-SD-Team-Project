import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

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
	
	private final JavaBallController controller;
	private final String searchFieldString = "Enter name or ID...", tableHeaderString = "Referees ordered by ID";

	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 400;
	private JPanel headerPanel, navPanel, searchPanel, tablePanel,
			tableHeaderPanel, tableResetPanel;
	private JTextField fldSearch;
	private JButton btnSearch, btnShowAll;
	private JTable refereesTable;
	private JLabel lblTableHeader;
	private Component headerSpacer, tableHeaderSpacer, tableSpacerLeft, tableSpacerRight, tableSpacerBottom, tableSpacerTop;
	private JPanel innerNavPanel;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JLabel lblNavigation;
	private Component horizontalStrut_2;
	private Component verticalStrut_2;
	private JPanel panel;
	private JLabel lblJavaball;
	private JLabel lblRefereeAllocationSystem;
	private Component verticalGlue;
	private Component verticalStrut_4;
	private Component verticalStrut_5;
	private Component verticalStrut_6;

	/**
	 * Constructor for JavaBallGUI
	 * 
	 * @param controller
	 */
	public JavaBallGUI(JavaBallController controller) {
		// connect to controller
		this.controller = controller;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall Referees");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); //centres JFrame on desktop
		layoutComponents();
		setResizable(false);
	}

	private void layoutComponents() {
		headerPanel = new JPanel();
		headerPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		searchPanel = new JPanel();
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

		headerSpacer = Box.createHorizontalStrut(10);
		headerPanel.add(headerSpacer, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		headerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		lblJavaball = new JLabel("JavaBall ");
		lblJavaball.setForeground(Color.WHITE);
		lblJavaball.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(lblJavaball);
		
		lblRefereeAllocationSystem = new JLabel("Referee Allocation System");
		lblRefereeAllocationSystem.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblRefereeAllocationSystem.setForeground(Color.WHITE);
		panel.add(lblRefereeAllocationSystem);

		navPanel = new JPanel();
		navPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(navPanel, BorderLayout.WEST);
		navPanel.setLayout(new BorderLayout(0, 0));
		
		innerNavPanel = new JPanel();
		innerNavPanel.setBackground(Color.LIGHT_GRAY);
		navPanel.add(innerNavPanel, BorderLayout.CENTER);
		innerNavPanel.setLayout(new BoxLayout(innerNavPanel, BoxLayout.Y_AXIS));
		
		lblNavigation = new JLabel("Navigation");
		innerNavPanel.add(lblNavigation);
		
		verticalStrut_4 = Box.createVerticalStrut(5);
		innerNavPanel.add(verticalStrut_4);
		
		button = new JButton("Add Referee");
		innerNavPanel.add(button);
		
		verticalStrut_5 = Box.createVerticalStrut(5);
		innerNavPanel.add(verticalStrut_5);
		
		button_1 = new JButton("Allocate Referees");
		innerNavPanel.add(button_1);
		
		verticalStrut_6 = Box.createVerticalStrut(5);
		innerNavPanel.add(verticalStrut_6);
		
		button_2 = new JButton("Show Chart");
		innerNavPanel.add(button_2);
		
		verticalGlue = Box.createVerticalGlue();
		innerNavPanel.add(verticalGlue);
		
		button_3 = new JButton("Save and Exit");
		innerNavPanel.add(button_3);
		
		horizontalStrut = Box.createHorizontalStrut(5);
		navPanel.add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(5);
		navPanel.add(horizontalStrut_1, BorderLayout.EAST);
		
		verticalStrut = Box.createVerticalStrut(5);
		navPanel.add(verticalStrut, BorderLayout.NORTH);
		
		verticalStrut_1 = Box.createVerticalStrut(5);
		navPanel.add(verticalStrut_1, BorderLayout.SOUTH);

		tablePanel = new JPanel();
		getContentPane().add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));

		tableHeaderPanel = new JPanel();
		tablePanel.add(tableHeaderPanel, BorderLayout.NORTH);
		tableHeaderPanel.setLayout(new BorderLayout(0, 0));

		lblTableHeader = new JLabel(tableHeaderString);
		lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
//		lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		tableHeaderPanel.add(lblTableHeader);

		tableResetPanel = new JPanel();
		tableHeaderPanel.add(tableResetPanel, BorderLayout.EAST);
		tableResetPanel.setLayout(new BoxLayout(tableResetPanel, BoxLayout.X_AXIS));

		btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Dialog", Font.BOLD, 10));
		btnShowAll.setEnabled(false);
		tableResetPanel.add(btnShowAll);
		
		horizontalStrut_2 = Box.createHorizontalStrut(5);
		tableResetPanel.add(horizontalStrut_2);

		tableHeaderSpacer = Box.createHorizontalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacer, BorderLayout.WEST);
		
		tableSpacerTop = Box.createVerticalStrut(5);
		tableHeaderPanel.add(tableSpacerTop, BorderLayout.SOUTH);
		
		verticalStrut_2 = Box.createVerticalStrut(5);
		tableHeaderPanel.add(verticalStrut_2, BorderLayout.NORTH);

		refereesTable = controller.getTable();
		refereesTable.setBackground(UIManager.getColor("menu"));
		refereesTable.setAutoCreateRowSorter(true);
		refereesTable.setBorder(null);
		refereesTable.setShowVerticalLines(false);
		refereesTable.setRowSelectionAllowed(false);
		refereesTable.setFont(new Font("Dialog", Font.PLAIN, 14));
		
//		refereesTable.setPreferredScrollableViewportSize(new Dimension(400, 100)); 	
//		refereesTable.setFillsViewportHeight(false);
//		refereesTable.getModel().addTableModelListener(table);

		DefaultTableCellRenderer leftRender = new DefaultTableCellRenderer();
		leftRender.setHorizontalAlignment(JLabel.LEFT);
		refereesTable.getColumnModel().getColumn(4).setCellRenderer(leftRender);

		// Create new JPane for table view
		refereesTable.setSize(tablePanel.getWidth(), tablePanel.getHeight());
		JScrollPane tablePane = new JScrollPane(refereesTable);
		tablePanel.add(tablePane);
		
		tableSpacerLeft = Box.createHorizontalStrut(5);
		tablePanel.add(tableSpacerLeft, BorderLayout.WEST);
		
		tableSpacerRight = Box.createHorizontalStrut(5);
		tablePanel.add(tableSpacerRight, BorderLayout.EAST);
		
		tableSpacerBottom = Box.createVerticalStrut(5);
		tablePanel.add(tableSpacerBottom, BorderLayout.SOUTH);

		if (controller.inputTooLarge()) {
			JOptionPane.showMessageDialog(null,
					"Your Refere List was too large.\n"
							+ "Only the first 12 Referees will be shown.");
		}
		
		// add listeners
		btnSearch.addActionListener(this);
		btnShowAll.addActionListener(this);
		button.addActionListener(this);
		button_1.addActionListener(this);
		button_2.addActionListener(this);
		button_3.addActionListener(this);
		
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
	 * The main action performed class
	 * 
	 * @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// If add button is pressed
		if (ae.getSource() == button) {
			RefereeFrame addRef = new RefereeFrame(controller);
			addRef.setTitle("Add Referee");
			addRef.setVisible(true);
			addRef.setRemoveButtonEnabled(false);
		} else if (ae.getSource() == button_1) {
			// If allocate referee to matches button is pressed
			AllocateMatches allocateRef = new AllocateMatches(controller);
			allocateRef.setVisible(true);
		} else if (ae.getSource() == button_2) {
			// If chart button is pressed
			controller.openChart();
		} else if (ae.getSource() == btnSearch) {
			// If search button is pressed
			String refInfo = fldSearch.getText().toLowerCase().trim();
			Referee ref = controller.getReferee(refInfo);
			if (ref != null) {
				RefereeFrame editRef = new RefereeFrame(controller, ref);
				editRef.setReferee(ref);
				editRef.setVisible(true);
				editRef.setTitle("Edit Referee");
				editRef.setLocations();
				fldSearch.setText(searchFieldString);
			} else {
				JOptionPane.showMessageDialog(null, "Referee not found");
				fldSearch.setText(searchFieldString);
			}
		} else if (ae.getSource() == btnShowAll) {
			controller.updateTable();
		} else if (ae.getSource() == button_3) {
			// If save and exit button is pressed
			controller.saveExit();
		}
	}
	
	/**
	 * 
	 * @param label
	 */
	public void displaySuitableReferees(String label) {
		btnShowAll.setEnabled(true);
		lblTableHeader.setText(label);
	}
	
	/**
	 * 
	 * @param label
	 */
	public void resetTableHeader() {
		lblTableHeader.setText(tableHeaderString);
		btnShowAll.setEnabled(false);
	}
}
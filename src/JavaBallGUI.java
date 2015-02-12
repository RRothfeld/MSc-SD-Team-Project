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
import java.awt.FlowLayout;

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

	private final JavaBallController controller;
	private final String searchFieldString = "Enter name or ID...";
	private final String tableHeaderString = "Referees ordered by ID";

	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 400;
	private JPanel headerPanel, navPanel, searchPanel, contentPanel,
			tableHeaderPanel, tableResetPanel;
	private JTextField fldSearch;
	private JButton btnSearch, btnShowAll;
	private JTable refereesTable;
	private JLabel lblTableHeader;
	private Component headerSpacerLeft, tableHeaderSpacerLeft, tableSpacerLeft,
			tableSpacerRight, tableSpacerBottom, tableHeaderSpacerBottom;
	private JPanel buttonPanel;
	private Component navSpacerLeft;
	private Component navSpacerRight;
	private JButton btnAddRef;
	private JButton btnAllocRefs;
	private JButton btnChart;
	private JButton btnSaveExit;
	private Component navSpacerTop;
	private Component navSpacerBottom;
	private JLabel lblNavigation;
	private Component tableHeaderSpacerRight;
	private Component tableHeaderSpacerTop;
	private JPanel headerTextPanel;
	private JLabel lblHeader;
	private JLabel lblSubHeader;
	private Component navBtnGlueBottom;
	private Component navBtnSpacerTop;
	private Component navBtnSpacerMiddle;
	private Component navBtnSpacerBottom;

	/**
	 * Constructor for JavaBallGUI
	 * 
	 * @param controller
	 */
	public JavaBallGUI(JavaBallController controller) {
		// connect to controller
		this.controller = controller;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JavaBall RAS");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null); // centres JFrame on desktop
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

		headerSpacerLeft = Box.createHorizontalStrut(10);
		headerPanel.add(headerSpacerLeft, BorderLayout.WEST);

		headerTextPanel = new JPanel();
		headerTextPanel.setBackground(Color.DARK_GRAY);
		headerPanel.add(headerTextPanel, BorderLayout.CENTER);
		headerTextPanel.setLayout(new BoxLayout(headerTextPanel, BoxLayout.X_AXIS));

		lblHeader = new JLabel("JavaBall ");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Dialog", Font.BOLD, 18));
		headerTextPanel.add(lblHeader);

		lblSubHeader = new JLabel("Referee Allocation System");
		lblSubHeader.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblSubHeader.setForeground(Color.WHITE);
		headerTextPanel.add(lblSubHeader);

		navPanel = new JPanel();
		navPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(navPanel, BorderLayout.WEST);
		navPanel.setLayout(new BorderLayout(0, 0));

		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		navPanel.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		lblNavigation = new JLabel("Navigation");
		buttonPanel.add(lblNavigation);

		navBtnSpacerTop = Box.createVerticalStrut(5);
		buttonPanel.add(navBtnSpacerTop);

		btnAddRef = new JButton("Add Referee");
		buttonPanel.add(btnAddRef);

		navBtnSpacerMiddle = Box.createVerticalStrut(5);
		buttonPanel.add(navBtnSpacerMiddle);

		btnAllocRefs = new JButton("Allocate Referees");
		buttonPanel.add(btnAllocRefs);

		navBtnSpacerBottom = Box.createVerticalStrut(5);
		buttonPanel.add(navBtnSpacerBottom);

		btnChart = new JButton("Show Chart");
		buttonPanel.add(btnChart);

		navBtnGlueBottom = Box.createVerticalGlue();
		buttonPanel.add(navBtnGlueBottom);

		btnSaveExit = new JButton("Save and Exit");
		buttonPanel.add(btnSaveExit);

		navSpacerLeft = Box.createHorizontalStrut(5);
		navPanel.add(navSpacerLeft, BorderLayout.WEST);

		navSpacerRight = Box.createHorizontalStrut(5);
		navPanel.add(navSpacerRight, BorderLayout.EAST);

		navSpacerTop = Box.createVerticalStrut(5);
		navPanel.add(navSpacerTop, BorderLayout.NORTH);

		navSpacerBottom = Box.createVerticalStrut(5);
		navPanel.add(navSpacerBottom, BorderLayout.SOUTH);

		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		tableHeaderPanel = new JPanel();
		contentPanel.add(tableHeaderPanel, BorderLayout.NORTH);
		tableHeaderPanel.setLayout(new BorderLayout(0, 0));

		lblTableHeader = new JLabel(tableHeaderString);
		lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		// lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		tableHeaderPanel.add(lblTableHeader);

		tableResetPanel = new JPanel();
		tableHeaderPanel.add(tableResetPanel, BorderLayout.EAST);
		tableResetPanel.setLayout(new BoxLayout(tableResetPanel,
				BoxLayout.X_AXIS));

		btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Dialog", Font.BOLD, 10));
		btnShowAll.setEnabled(false);
		tableResetPanel.add(btnShowAll);

		tableHeaderSpacerRight = Box.createHorizontalStrut(5 + 1);
		tableResetPanel.add(tableHeaderSpacerRight);

		tableHeaderSpacerLeft = Box.createHorizontalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacerLeft, BorderLayout.WEST);

		tableHeaderSpacerBottom = Box.createVerticalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacerBottom, BorderLayout.SOUTH);

		tableHeaderSpacerTop = Box.createVerticalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacerTop, BorderLayout.NORTH);

		refereesTable = controller.getTable();
		refereesTable.setBackground(UIManager.getColor("menu"));
		refereesTable.setAutoCreateRowSorter(true);
		refereesTable.setBorder(null);
		refereesTable.setShowVerticalLines(false);
		refereesTable.setRowSelectionAllowed(false);
		refereesTable.setFont(new Font("Dialog", Font.PLAIN, 14));

		// refereesTable.setPreferredScrollableViewportSize(new Dimension(400,
		// 100));
		// refereesTable.setFillsViewportHeight(false);
		// refereesTable.getModel().addTableModelListener(table);

		DefaultTableCellRenderer leftRender = new DefaultTableCellRenderer();
		leftRender.setHorizontalAlignment(JLabel.LEFT);
		refereesTable.getColumnModel().getColumn(4).setCellRenderer(leftRender);

		// Create new JPane for table view
		refereesTable.setSize(contentPanel.getWidth(), contentPanel.getHeight());
		JScrollPane tablePane = new JScrollPane(refereesTable);
		contentPanel.add(tablePane);

		tableSpacerLeft = Box.createHorizontalStrut(5);
		contentPanel.add(tableSpacerLeft, BorderLayout.WEST);

		tableSpacerRight = Box.createHorizontalStrut(5);
		contentPanel.add(tableSpacerRight, BorderLayout.EAST);

		tableSpacerBottom = Box.createVerticalStrut(5);
		contentPanel.add(tableSpacerBottom, BorderLayout.SOUTH);

		if (controller.inputTooLarge()) {
			JOptionPane.showMessageDialog(null,
					"Your Refere List was too large.\n"
							+ "Only the first 12 Referees will be shown.");
		}

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
	 * The main action performed class
	 * 
	 * @param ae
	 */
	@Override
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
                                editRef.setQualification();
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
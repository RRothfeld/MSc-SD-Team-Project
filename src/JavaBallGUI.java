import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

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
	private final String searchFieldString = "Enter name or ID...";
	private final String tableHeaderString = "Referees ordered by ID";

	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 500;
	private JPanel headerPanel, navPanel, searchPanel, tablePanel,
			tableHeaderPanel, tableResetPanel;
	private JTextField fldSearch;
	private JButton btnSearch, btnAddReferee, btnAllocateReferees,
			btnShowChart, btnSaveAndExit, btnShowAll;
	private JTable refereesTable;
	private JLabel lblLogo, lblTableHeader;
	private Component headerSpacer, tableHeaderSpacer;
	private Component tableSpacerLeft;
	private Component tableSpacerRight;
	private JTable table;
	private Component tableSpacerBottom;
	private Component tableSpacerTop;

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
		layoutComponents();
		setResizable(false);
	}

	private void layoutComponents() {
		headerPanel = new JPanel();
		headerPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		lblLogo = new JLabel("JavaBall");
		lblLogo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblLogo.setForeground(Color.WHITE);
		headerPanel.add(lblLogo);

		searchPanel = new JPanel();
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

		navPanel = new JPanel();
		navPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(navPanel, BorderLayout.WEST);

		btnAddReferee = new JButton("Add Referee");
		navPanel.setLayout(new MigLayout("", "[121px]",
				"[25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px][25px]"));
		navPanel.add(btnAddReferee, "cell 0 0,growx,aligny center");

		btnAllocateReferees = new JButton("Allocate Referees");
		navPanel.add(btnAllocateReferees, "cell 0 1,alignx left,aligny top");

		btnShowChart = new JButton("Show Chart");
		navPanel.add(btnShowChart, "cell 0 2,growx");

		btnSaveAndExit = new JButton("Save and Exit");
		navPanel.add(btnSaveAndExit, "cell 0 14,growx");

		tablePanel = new JPanel();
		getContentPane().add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));

		tableHeaderPanel = new JPanel();
		tablePanel.add(tableHeaderPanel, BorderLayout.NORTH);
		tableHeaderPanel.setLayout(new BorderLayout(0, 0));

		lblTableHeader = new JLabel(tableHeaderString);
//		lblTableHeader.setVerticalAlignment(SwingConstants.BOTTOM);
		tableHeaderPanel.add(lblTableHeader);

		tableResetPanel = new JPanel();
		tableHeaderPanel.add(tableResetPanel, BorderLayout.EAST);

		btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Dialog", Font.BOLD, 10));
		btnShowAll.setEnabled(false);
		tableResetPanel.add(btnShowAll);

		tableHeaderSpacer = Box.createHorizontalStrut(5);
		tableHeaderPanel.add(tableHeaderSpacer, BorderLayout.WEST);
		
		tableSpacerTop = Box.createVerticalStrut(5);
		tableHeaderPanel.add(tableSpacerTop, BorderLayout.SOUTH);

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
		
		tableSpacerRight = Box.createHorizontalStrut(4);
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
		btnAddReferee.addActionListener(this);
		btnAllocateReferees.addActionListener(this);
		btnShowChart.addActionListener(this);
		btnSaveAndExit.addActionListener(this);
		btnShowAll.addActionListener(this);
		
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
		if (ae.getSource() == btnAddReferee) {
			RefereeFrame addRef = new RefereeFrame(controller);
			addRef.setTitle("Add Referee");
			addRef.setVisible(true);
			addRef.setRemoveButtonEnabled(false);
		} else if (ae.getSource() == btnAllocateReferees) {
			// If allocate referee to matches button is pressed
			AllocateMatches allocateRef = new AllocateMatches(controller);
			allocateRef.setVisible(true);
		} else if (ae.getSource() == btnShowChart) {
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
			lblTableHeader.setText(tableHeaderString);
			controller.updateTable();
			btnShowAll.setEnabled(false);
		} else if (ae.getSource() == btnSaveAndExit) {
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
}
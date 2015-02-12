import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.border.LineBorder;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Window to input match information and request referee allocation
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
 * @version 1.8 - final
 * @since 12-02-2015
 */
public class AllocationFrame extends JFrame implements ActionListener {
	/** Predefined set of colours for uniform component colouring */
	private final Color background = Color.decode("0xDDDDDD"),
			highlight = Color.decode("0xFFCCCC"), border = Color.GRAY;
	
	/** JFrame and spacing dimensions in pixels */
	private static final int WIDTH = 300, HEIGHT = 260, SPACING = 5;
	
	/** Reference to the JavaBallController */
	private final JavaBallController controller;
	
	/** Main/interactive GUI components */
	private JTextField fldWeek;
	private JComboBox<MatchLevel> cmbLevel;
	private JComboBox<Location> cmbLocation;
	private JButton btnAllocate, btnCancel;
	private JLabel lblStatus;

	/**
	 * Constructor for creating an AllocationFrame instance
	 * @param controller from which the AllocationFrame has been called
	 */
	public AllocationFrame(JavaBallController controller) {
		// Bidirectional link to controller
		this.controller = controller;

		// Set JFrame properties
		setTitle("Allocate Referees");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null); // Centres JFrame on desktop
		setResizable(false);

		// Adds GUI components
		layoutComponents();
	}
	
	/**
	 * Lays out the GUI components within the JFrame
	 */
	private void layoutComponents() {
		// Create JPanels
		JPanel outerInputPanel = new JPanel();
		JPanel detailsHeaderPanel = new JPanel();
		JPanel innerInputPanel = new JPanel();
		JPanel matchDetailsPanel = new JPanel();
		JPanel weekPanel = new JPanel();
		JPanel levelPanel = new JPanel();
		JPanel locationPanel = new JPanel();
		JPanel statusPanel = new JPanel();
		JPanel statusHeaderPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		// Set JPanel layouts
		outerInputPanel.setLayout(new BorderLayout(0, 0));
		detailsHeaderPanel.setLayout(new BorderLayout(0, 0));
		innerInputPanel.setLayout(new BorderLayout(0, 0));
		matchDetailsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SPACING,
				SPACING));
		statusPanel.setLayout(new BorderLayout(0, 0));
		statusHeaderPanel.setLayout(new BorderLayout(0, 0));
		setFlowLayout(weekPanel);
		setFlowLayout(levelPanel);
		setFlowLayout(locationPanel);
		
		// Set JPanel properties
		innerInputPanel.setBackground(background);
		innerInputPanel.setBorder(new LineBorder(border));
		matchDetailsPanel.setBackground(background);
	
		// Create spacers
		Component outerSpacerTop = Box.createVerticalStrut(SPACING);
		Component detailsHeaderSpacer = Box.createHorizontalStrut(SPACING);
		Component outerSpacerLeft = Box.createHorizontalStrut(SPACING);
		Component outerSpacerRight = Box.createHorizontalStrut(SPACING);
		Component outerSpacerBottom = Box.createVerticalStrut(SPACING);
		Component innerSpacerTop = Box.createVerticalStrut(SPACING);
		Component innerSpacerRight = Box.createHorizontalStrut(SPACING);
		Component innerSpacerBottom = Box.createVerticalStrut(SPACING);
		Component innerSpacerLeft = Box.createHorizontalStrut(SPACING);
		Component statusHeaderSpacer = Box.createHorizontalStrut(SPACING);
		Component statusSpacerBottom = Box.createVerticalStrut(SPACING);
		Component statusSpacerLeft = Box.createHorizontalStrut(SPACING);
		Component statusSpacerRight = Box.createHorizontalStrut(SPACING);
		
		// Create JLabels
		JLabel lblDetailsHeader = new JLabel("Match Details");
		JLabel lblWeek = new JLabel("Week Number");
		JLabel lblLevel = new JLabel("Match Level");
		JLabel lblLocation = new JLabel("Match Location");
		JLabel lblStatusHeader = new JLabel("Status");
		lblStatus = new JLabel("Please insert match details above.");
		fldWeek = new JTextField();
		
		// Set JLabel properties
		lblWeek.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBorder(new LineBorder(border));
		lblStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		fldWeek.setColumns(SPACING);

		// Create JButtons
		btnAllocate = new JButton("Allocate");
		btnCancel = new JButton("Cancel");
		
		// Create JComboBoxes
		cmbLevel = new JComboBox<MatchLevel>();
		cmbLocation = new JComboBox<Location>();
		
		// Set JComboBox contents
		cmbLevel.setModel(new DefaultComboBoxModel<MatchLevel>(MatchLevel
				.values()));
		cmbLocation.setModel(new DefaultComboBoxModel<Location>(Location
				.values()));
		
		// Add action listeners
		btnAllocate.addActionListener(this);
		btnCancel.addActionListener(this);
		
		// Add wrapper JPanels to GUI
		getContentPane().add(outerInputPanel, BorderLayout.NORTH);
		outerInputPanel.add(detailsHeaderPanel, BorderLayout.NORTH);
		outerInputPanel.add(innerInputPanel, BorderLayout.CENTER);
		innerInputPanel.add(matchDetailsPanel, BorderLayout.CENTER);
		
		// Add wrapper spacers to GUI
		detailsHeaderPanel.add(outerSpacerTop, BorderLayout.NORTH);
		detailsHeaderPanel.add(detailsHeaderSpacer, BorderLayout.WEST);
		outerInputPanel.add(outerSpacerLeft, BorderLayout.WEST);
		outerInputPanel.add(outerSpacerRight, BorderLayout.EAST);
		outerInputPanel.add(outerSpacerBottom, BorderLayout.SOUTH);
		innerInputPanel.add(innerSpacerTop, BorderLayout.NORTH);
		innerInputPanel.add(innerSpacerRight, BorderLayout.EAST);
		innerInputPanel.add(innerSpacerBottom, BorderLayout.SOUTH);
		innerInputPanel.add(innerSpacerLeft, BorderLayout.WEST);		
	
		// Add match details components (top of JFrame)
		detailsHeaderPanel.add(lblDetailsHeader, BorderLayout.CENTER);
		matchDetailsPanel.add(lblWeek);
		matchDetailsPanel.add(weekPanel);
		weekPanel.add(fldWeek);	
		matchDetailsPanel.add(lblLevel);
		matchDetailsPanel.add(levelPanel);
		levelPanel.add(cmbLevel);
		matchDetailsPanel.add(lblLocation);
		matchDetailsPanel.add(locationPanel);
		locationPanel.add(cmbLocation);
		
		// Add status components (centre of JFrame)
		getContentPane().add(statusPanel, BorderLayout.CENTER);
		statusPanel.add(statusHeaderPanel, BorderLayout.NORTH);
		statusHeaderPanel.add(lblStatusHeader, BorderLayout.CENTER);
		statusHeaderPanel.add(statusHeaderSpacer, BorderLayout.WEST);
		statusPanel.add(statusSpacerBottom, BorderLayout.SOUTH);
		statusPanel.add(lblStatus, BorderLayout.CENTER);
		statusPanel.add(statusSpacerLeft, BorderLayout.WEST);
		statusPanel.add(statusSpacerRight, BorderLayout.EAST);
		
		// Add button components (bottom of JFrame)
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(btnAllocate);
		buttonPanel.add(btnCancel);
	}

	/**
	 * Starts appropriate responses upon user interaction
	 * @param ae the action event
	 */
	public void actionPerformed(ActionEvent ae) {
		// Identify which JButton has been pressed
		if (ae.getSource() == btnAllocate)
			// Start allocation process
			allocation();
		else
			// Close window
			dispose();
	}
	
	/**
	 * Test the week number input and allocated the two most suitable referees
	 * to the, via user input defined, match
	 */
	private void allocation() {
		try {
			// Revert week number field's colour (if it has previously been
			// changed due to highlighting)
			fldWeek.setBackground(Color.WHITE);
			
			// Try parsing week number input
			int week = Integer.parseInt(fldWeek.getText());
			
			// Test week number validity [1,52]
			if (week < Season.MIN_WEEK || week > Season.MAX_WEEK) {
				// Show error and reset week number field
				invalidWeek("Week number must be between 1 and 52.");
			} else {
				// Retrieve further match details if number was parsed
				MatchLevel level = (MatchLevel) cmbLevel.getSelectedItem();
				Location area = (Location) cmbLocation.getSelectedItem();

				// Retrieve list of suitable referees
				ArrayList<Referee> suitableRefs = controller
						.allocateReferees(week, level, area);
				
				// Test for sufficient suitable referees
				if (suitableRefs == null) {
					// Show error and reset week number field
					invalidWeek("Week already hosts a match.");
				} else if (suitableRefs.size() < 2) {
					JOptionPane.showMessageDialog(null,
							"Not enough suitable referees available.");
				} else {
					// Update status to show the two referees, to whom the
					// match has been allocated
					lblStatus.setText("Match allocated to referees "
							+ suitableRefs.get(0).getID() + " and "
							+ suitableRefs.get(1).getID() + ".");

					// Disable allocate button as allocation has been
					// successful; change cancel button to close as no
					// process can be cancelled anymore
					btnAllocate.setEnabled(false);
					btnCancel.setText("Close");

					// Update table to display the suitable referees ordered
					// by suitability and the table's description
					controller.allocatedTableData(suitableRefs);
					controller.setTableHeader("Referees ordered by "
							+ "suitability for match in week " + week);
				}
			}
		} catch (NumberFormatException ex) {
			// Show error and reset week number field
			invalidWeek("Please enter an integer for week number.");
		}
	}
	
	/**
	 * Applies the defined FlowLayout and colouring to a JPanel
	 * @param panel the JPanel to which the standard FlowLayout is applied
	 */
	private void setFlowLayout(JPanel panel) {
		// Set JPanel background to the defined background colour
		panel.setBackground(background);
		
		// Apply standard FlowLayout settings
		FlowLayout flow = (FlowLayout) panel.getLayout();
		flow.setHgap(SPACING * 2);
		flow.setAlignment(FlowLayout.LEFT);
	}
	
	/**
	 * Resets and highlights the week number field and shows an error message
	 * @param msg the error message to be displayed
	 */
	private void invalidWeek(String msg) {
		// Show error message
		JOptionPane.showMessageDialog(null, msg);
		
		// Reset week number field
		fldWeek.setText("");
		fldWeek.setBackground(highlight);
	}
}
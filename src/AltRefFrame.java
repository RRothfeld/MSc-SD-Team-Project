import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
 * @version 1.2
 * @since 11-02-2015
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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Constructor to add components and create frame.
	 * @param controller
	 */
	public AltRefFrame(JavaBallController controller) {

		this.controller = controller;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Add Referee");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null); //centres JFrame on desktop
		setResizable(false);
		
		// Create JPanels
		JPanel outerInputPanel = new JPanel();
		JPanel detailsHeaderPanel = new JPanel();
		JPanel innerInputPanel = new JPanel();
		JPanel matchDetailsPanel = new JPanel();
		JPanel weekPanel = new JPanel();
		JPanel levelPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) levelPanel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		JPanel locationPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) locationPanel.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		JPanel buttonPanel = new JPanel();

		// Set JPanel layouts
		outerInputPanel.setLayout(new BorderLayout(0, 0));
		detailsHeaderPanel.setLayout(new BorderLayout(0, 0));
		innerInputPanel.setLayout(new BorderLayout(0, 0));
		matchDetailsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SPACING,
				SPACING));
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
		
		// Create JLabels
		JLabel lblDetailsHeader = new JLabel("Referee Details");
		JLabel lblWeek = new JLabel("Referee ID");
		JLabel lblLevel = new JLabel("First Name");
		JLabel lblLocation = new JLabel("Last Name");
		
		// Set JLabel properties
		lblWeek.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);

		// Create JButtons
		btnAllocate = new JButton("Save");
		btnCancel = new JButton("Cancel");
		
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
		
		JLabel lblNewLabel = new JLabel("New label");
		weekPanel.add(lblNewLabel);
		matchDetailsPanel.add(lblLevel);
		matchDetailsPanel.add(levelPanel);
		
		textField_1 = new JTextField();
		levelPanel.add(textField_1);
		textField_1.setColumns(12);
		matchDetailsPanel.add(lblLocation);
		matchDetailsPanel.add(locationPanel);
		
		textField_2 = new JTextField();
		locationPanel.add(textField_2);
		textField_2.setColumns(12);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panel_2.add(verticalStrut, BorderLayout.NORTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(5);
		panel_2.add(horizontalStrut, BorderLayout.WEST);
		
		JLabel lblMatchAllocations = new JLabel("Match Allocations");
		panel_2.add(lblMatchAllocations, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(border));
		panel_3.setBackground(new Color(221, 221, 221));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(221, 221, 221));
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPreviousAllocations = new JLabel("Previous Allocations");
		lblPreviousAllocations.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblPreviousAllocations);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setHgap(10);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6);
		
		textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(5);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_2, BorderLayout.SOUTH);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		panel_3.add(horizontalStrut_3, BorderLayout.WEST);
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		panel_3.add(verticalStrut_3, BorderLayout.NORTH);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(5);
		panel_3.add(horizontalStrut_4, BorderLayout.EAST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		panel_1.add(horizontalStrut_1, BorderLayout.WEST);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(5);
		panel_1.add(horizontalStrut_2, BorderLayout.EAST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut_1, BorderLayout.SOUTH);
		
		JPanel panel_19 = new JPanel();
		panel.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_19.add(panel_5, BorderLayout.NORTH);
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
		panel_19.add(panel_8, BorderLayout.CENTER);
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
		FlowLayout flow = (FlowLayout) panel.getLayout();
		flow.setVgap(SPACING + 2);
		flow.setHgap(SPACING * 2);
		flow.setAlignment(FlowLayout.LEFT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
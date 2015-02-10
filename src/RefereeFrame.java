import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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

/**TODO 
 * Subclass that contains the frame and components for the add/edit/remove 
 * referee display.
 * create one JFrame that is used for both adding, editing and deleting 
 * referees.
 * 
 * If adding a referee then save button is enabled and delete button is 
 * disabled.If if deleting a referee then save is disabled and delete is 
 * enabled. 
 */

public final class RefereeFrame extends JFrame implements ActionListener {

	// GUI components for referee frame
	private JPanel main, refereePanel, qualificationsPanel, locationsPanel;
	private JLabel idLabel,refIDLabel,fnameLabel,lnameLabel,allocationLabel,qualLevelLabel,homeLabel;
	private JTextField refFname, refSname, refMatches;
	private JComboBox qualLevel;
	private JComboBox homeLoc;
	private JRadioButton njb, ijb;
	private JCheckBox north, central, south;
	private JButton save, remove, cancel;
	private ButtonGroup qualification;

	private String refFrameTitle;

	private Referee referee;
	private final JavaBallController controller;

	private final int FRAME_WIDTH = 400;
	private final int FRAME_HEIGHT = 400;

	/**
	 * Constructor to add components and create frame.
	 * @param controller
	 */
	public RefereeFrame(JavaBallController controller) {

		this.controller = controller;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(refFrameTitle);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(200, 200);
		setResizable(false);
		main = new JPanel(new BorderLayout());
		// Adds top GUI components
		refLayout();
		qualificationsLayout();
		locationsAndButtonsLayout();
		add(main);

	}

	/**
	 *
	 * @param controller
	 * @param referee
	 */
	public RefereeFrame(JavaBallController controller, Referee referee) {

		// Calls the default constructor
		this(controller);
		this.referee = referee;
	}

	/**
	 *
	 * @param referee
	 */
	public void setReferee(Referee referee) {
		this.referee = referee;
		setDetails();
	}

	/**
	 * Method to add labels to GUI.
	 */
	public void refLayout() {


		refereePanel = new JPanel(new BorderLayout());
		JPanel refereeSubPanel = new JPanel(new GridLayout(4,2)); 
		refereeSubPanel.setBackground(Color.lightGray);
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		JPanel south = new JPanel();

		idLabel = new JLabel();
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setText("ID:");
		fnameLabel = new JLabel();
		fnameLabel.setText("First Name:");
		fnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lnameLabel = new JLabel();
		lnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lnameLabel.setText("Second Name:");
		allocationLabel = new JLabel("Match Allocations:");
		allocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		refIDLabel = new JLabel();
		refFname = new JTextField(5);
		refSname = new JTextField(5);
		refMatches = new JTextField(5);

		refSname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String firstName = refFname.getText().trim();
				String lastName = refSname.getText().trim();
				if ((firstName != null && lastName != null)) {
					if (!(firstName.equals("") || lastName.equals(""))) {
						String ID = controller.createID(firstName, lastName);
						refIDLabel.setText(ID);
					}
				}
			}
		});

		refereeSubPanel.add(idLabel);
		refereeSubPanel.add(refIDLabel);
		refereeSubPanel.add(fnameLabel);
		refereeSubPanel.add(refFname);
		refereeSubPanel.add(lnameLabel);
		refereeSubPanel.add(refSname);
		refereeSubPanel.add(allocationLabel);
		refereeSubPanel.add(refMatches);

		// Creates panel for panel title
		JPanel titlePanel = new JPanel();
		// Creates label for title panel
		JLabel title = new JLabel("Referee Details");
		// Adds label to panel
		titlePanel.add(title);


		refereeSubPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		refereePanel.add(east, BorderLayout.EAST);
		refereePanel.add(west, BorderLayout.WEST);
		refereePanel.add(south, BorderLayout.SOUTH);
		refereePanel.add(titlePanel, BorderLayout.NORTH);
		refereePanel.add(refereeSubPanel, BorderLayout.CENTER);
		add(refereePanel, BorderLayout.NORTH);
	}

	/**
	 *
	 */
	public void qualificationsLayout() {

		qualificationsPanel = new JPanel(new BorderLayout());
		JPanel qualSubPanel = new JPanel(new GridLayout(2,2)); 
		qualSubPanel.setBackground(Color.lightGray);
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		JPanel south = new JPanel();

		njb = new JRadioButton(Referee.Qualifications.NJB.toString());
		ijb = new JRadioButton(Referee.Qualifications.IJB.toString());
		qualification = new ButtonGroup();
		qualification.add(njb);
		qualification.add(njb);

		njb.setHorizontalAlignment(SwingConstants.RIGHT);
		njb.setFont(new Font("Monospaced",Font.PLAIN,14));

		ijb.setHorizontalAlignment(SwingConstants.RIGHT);
		ijb.setFont(new Font("Monospaced",Font.PLAIN,14));

		qualLevelLabel = new JLabel("Level");
		qualLevel = new JComboBox<Integer>();

		qualLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);

		ButtonGroup qualButtons = new ButtonGroup();
		qualButtons.add(njb);
		qualButtons.add(ijb);

		for (int i = 1; i <= Referee.MAX_QUAL_LENGTH; i++) {
			qualLevel.addItem(i + "");
		}


		qualSubPanel.add(njb);
		qualSubPanel.add(qualLevelLabel);
		qualSubPanel.add(ijb);
		qualSubPanel.add(qualLevel);

		// Creates panel for panel title
		JPanel titlePanel = new JPanel();
		// Creates label for title panel
		JLabel title = new JLabel("Qualification Details");
		// Adds label to panel
		titlePanel.add(title);

		qualSubPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		qualificationsPanel.add(east, BorderLayout.EAST);
		qualificationsPanel.add(west, BorderLayout.WEST);
		qualificationsPanel.add(south, BorderLayout.SOUTH);
		qualificationsPanel.add(titlePanel, BorderLayout.NORTH);
		qualificationsPanel.add(qualSubPanel, BorderLayout.CENTER);
		main.add(qualificationsPanel, BorderLayout.CENTER);
	}


	/**
	 *
	 */
	public void locationsAndButtonsLayout() {

		locationsPanel = new JPanel(new BorderLayout());
		JPanel locationSubPanel = new JPanel(new BorderLayout());

		JPanel homePanel = new JPanel();
		JPanel visitPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		JPanel eastPanel = new JPanel();
		JPanel westPanel = new JPanel();
		JPanel southPanel = new JPanel();

		homeLabel = new JLabel("Home");
		homeLoc = new JComboBox();
		homeLoc.addItem("North");
		homeLoc.addItem("Central");
		homeLoc.addItem("South");


		north = new JCheckBox("North");
		central = new JCheckBox("Central");
		south = new JCheckBox("South");

		save = new JButton("Save");
		remove = new JButton("Remove");
		cancel = new JButton("Cancel");

		save.addActionListener(this);
		remove.addActionListener(this);
		cancel.addActionListener(this);

		homeLoc.setModel(new DefaultComboBoxModel(
				JavaBallController.Location.values()));

		homeLoc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JavaBallController.Location selectedLocation = 
						(JavaBallController.Location) homeLoc
						.getSelectedItem();
				if (selectedLocation.equals(homeLoc.getItemAt(0))) {
					north.setSelected(true);
				} else if ((selectedLocation.equals(homeLoc.getItemAt(1)))) {
					central.setSelected(true);
				} else {
					south.setSelected(true);
				}
			}
		});

		homePanel.add(homeLabel);
		homePanel.add(homeLoc);
		visitPanel.add(north);
		visitPanel.add(central);
		visitPanel.add(south);
		buttonPanel.add(save);
		buttonPanel.add(remove);
		buttonPanel.add(cancel);

		// Creates panel for panel title
		JPanel titlePanel = new JPanel();
		// Creates label for title panel
		JLabel title = new JLabel("Location Details");
		// Adds label to panel
		titlePanel.add(title);
		
		homePanel.setBackground(Color.lightGray);
		visitPanel.setBackground(Color.lightGray);
		buttonPanel.setBackground(Color.lightGray);

		locationSubPanel.add(homePanel, BorderLayout.NORTH);
		locationSubPanel.add(visitPanel, BorderLayout.CENTER);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		locationSubPanel.add(buttonPanel, BorderLayout.SOUTH);

		locationsPanel.add(titlePanel, BorderLayout.NORTH);
		locationsPanel.add(eastPanel, BorderLayout.EAST);
		locationsPanel.add(westPanel, BorderLayout.WEST);
		locationsPanel.add(southPanel, BorderLayout.SOUTH);

		locationSubPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		locationsPanel.add(locationSubPanel, BorderLayout.CENTER);
		main.add(locationsPanel, BorderLayout.SOUTH);

	}


	/** 
	 * Helper method to set the Remove referee button state
	 * @param state 
	 */
	public void setRemoveButtonEnabled(boolean state) {
		remove.setEnabled(state);
	}

	/**
	 * 
	 */
	private void setDetails(){

		refIDLabel.setText(referee.getID());
		refFname.setText(referee.getFirstName());
		refSname.setText(referee.getLastName());
		refMatches.setText(Integer.toString(referee.getAllocations()));

		refFname.setEditable(false);
		refSname.setEditable(false);
		refMatches.setEditable(false);

	}

	/**
	 *
	 */
	public void setLocations() {
		north.setSelected(controller.refTravel(referee, JavaBallController.Location.NORTH));
		central.setSelected(controller.refTravel(referee, JavaBallController.Location.CENTRAL));
		south.setSelected(controller.refTravel(referee, JavaBallController.Location.SOUTH));
	}


	/**
	 * This method handles events for the the Referee Frame (i.e. adding,
	 * editing and removing referee information)
	 * 
	 * @param ae
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cancel) {

			// Close window
			dispose();
		}
		if (ae.getSource() == remove) {
			controller.removeReferee(referee);
			dispose();
		}
		if (ae.getSource() == save) {

			if (controller.indexCounter() == RefereeList.MAX_REFEREES) {
				JOptionPane.showMessageDialog(null, "Sorry.\n"
						+ "The Referee List is full!");

			} else {

				if (refFname.getText() == null || refFname.getText().equals(""))
				{
					fnameLabel.setForeground(Color.red);
				}  
				if (refSname.getText() == null || refSname.getText().equals(""))
				{
					lnameLabel.setForeground(Color.red);
				}
				if (refMatches.getText() == null || refMatches.getText().equals(""))
				{
					allocationLabel.setForeground(Color.red);
				}
				// Get travel locations for referee
				String n = north.isSelected() ? "Y" : "N";
				String c = central.isSelected() ? "Y" : "N";
				String s = south.isSelected() ? "Y" : "N";
				String travel = n + c + s;
				Referee.Qualifications qual = 
						ijb.isSelected() ? Referee.Qualifications.IJB : Referee.Qualifications.NJB ;

				if (this.referee == null) 
				{
					if (!(refFname.getText().equals("")) && !(refSname.getText().equals("")))
					{
						controller.addReferee(refFname.getText(),
								refSname.getText(),qual,
								Integer.parseInt(String.valueOf(qualLevel.getSelectedItem())), 
								Integer.parseInt(refMatches.getText()),
								(JavaBallController.Location) homeLoc.getSelectedItem(), travel);
						controller.updateTable();
						System.err.println("Success");
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Please enter valid data.\n"+"Invalid Entries are in red");
					}

				}

				else {
					controller.editReferee(referee, qual,
							Integer.parseInt(String.valueOf(qualLevel.getSelectedItem())),
							(JavaBallController.Location) homeLoc.getSelectedItem(), travel);

					controller.updateTable();
					System.err.println("Success");
					dispose();
				}
			}
		}
	}
}
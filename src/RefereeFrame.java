import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
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
	private JLabel id,refID,fname,sname,matches,level,home;
	private JTextField refFname, refSname, refMatches;
	private JComboBox qualLevel;
	private JComboBox homeLoc;
	private JRadioButton njb, ijb;
	private CheckboxGroup travel;
	private JCheckBox north, central, south;
	private JButton save, remove, cancel;
	private ButtonGroup qualification;

	private Referee referee;
	private final JavaBallController controller;
	
	private final int FRAME_WIDTH = 400;
	private final int FRAME_HEIGHT = 300;

	/**
	 * Constructor to add components and create frame.
	 * @param controller
	 */
	public RefereeFrame(JavaBallController controller) {

		this.controller = controller;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setTitle("Add/Edit/Remove Referee");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(200, 200);
		setResizable(false);
		main = new JPanel(new BorderLayout());
		// Adds top GUI components
		refLayout();
		qualificationsLayout();
		locationsLayout();

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


		refereePanel = new JPanel(new GridLayout(3,2));

		id = new JLabel();
		id.setText("ID:");
		fname = new JLabel();
		fname.setText("First Name:");
		sname = new JLabel();
		sname.setText("Second Name:");
		matches = new JLabel("Match Allocations:");
		

		refID = new JLabel();
		refFname = new JTextField(5);
		refSname = new JTextField(5);
		refMatches = new JTextField(5);

		refSname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String firstName = refFname.getText().trim();
				String lastName = refSname.getText().trim();
				if ((firstName != null && lastName != null)) {
					if (!(firstName.equals("") && lastName.equals(""))) {
						RefereeList refList = new RefereeList(); // FIXME 
						String ID = refList.createID(firstName, lastName);
						refID.setText(ID);
					}
				}
			}
		});

		refereePanel.add(id);
		refereePanel.add(refID);
		refereePanel.add(fname);
		refereePanel.add(refFname);
		refereePanel.add(sname);
		refereePanel.add(refSname);

		refereePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(refereePanel, BorderLayout.NORTH);
	}

	/**
	 *
	 */
	public void qualificationsLayout() {

		qualificationsPanel = new JPanel(new BorderLayout());
		JPanel levelPanel = new JPanel();
		JPanel qualPanel = new JPanel();
		
		njb = new JRadioButton(Referee.Qualifications.NJB.toString());
		ijb = new JRadioButton(Referee.Qualifications.IJB.toString());
		qualification = new ButtonGroup();
		qualification.add(njb);
		qualification.add(njb);
		
		level = new JLabel("Level");
		qualLevel = new JComboBox<Integer>();
                
		ButtonGroup qualButtons = new ButtonGroup();
		qualButtons.add(njb);
		qualButtons.add(ijb);


		for (int i = 1; i <= Referee.MAX_QUAL_LENGTH; i++) {
			qualLevel.addItem(i + "");
		}


		qualPanel.add(njb);
		qualPanel.add(ijb);
		levelPanel.add(level);
		levelPanel.add(qualLevel);



		qualificationsPanel.add(levelPanel, BorderLayout.NORTH);
		qualificationsPanel.add(qualPanel, BorderLayout.SOUTH);

		qualificationsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(qualificationsPanel, BorderLayout.CENTER);
	}


	/**
	 *
	 */
	public void locationsLayout() {

		locationsPanel = new JPanel(new BorderLayout());
		JPanel homePanel = new JPanel();
		JPanel visitPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		home = new JLabel("Home");
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

		homePanel.add(home);
		homePanel.add(homeLoc);
		visitPanel.add(north);
		visitPanel.add(central);
		visitPanel.add(south);
		buttonPanel.add(save);
		buttonPanel.add(remove);
		buttonPanel.add(cancel);

		locationsPanel.add(homePanel, BorderLayout.NORTH);
		locationsPanel.add(visitPanel, BorderLayout.CENTER);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		locationsPanel.add(buttonPanel, BorderLayout.SOUTH);

		locationsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(locationsPanel, BorderLayout.SOUTH);

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

		refID.setText(referee.getID());
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
	public void setLocations(){
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
				// Get travel locations for referee
				String n = north.isSelected() ? "Y" : "N";
				String c = central.isSelected() ? "Y" : "N";
				String s = south.isSelected() ? "Y" : "N";
				String travel = n + c + s;

				if (this.referee == null) {

					controller.addReferee(refFname.getText(),
							refSname.getText(),
							Referee.Qualifications.IJB,
                                                        Integer.parseInt(String
									.valueOf(qualLevel
											.getSelectedItem())), Integer
											.parseInt(refMatches.getText()),
											(JavaBallController.Location) homeLoc
											.getSelectedItem(), travel);
					controller.updateTable();
					dispose();
				}

				else {
					controller.editReferee(referee,
							Referee.Qualifications.IJB,
                                                        Integer.parseInt(String
									.valueOf(qualLevel
											.getSelectedItem())),
											(JavaBallController.Location) homeLoc
											.getSelectedItem(), travel);
					controller.updateTable();
					dispose();
				}
			}
		}

	}

}
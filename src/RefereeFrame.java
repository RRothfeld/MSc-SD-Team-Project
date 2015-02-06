import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

    // GUI labels for viewRefereeFrame
    private JLabel idLabel, firstNameLabel, lastNameLabel, qualificationLabel,
	    matchesLabel, homeLabel, visitAreasLabel;
    private Referee referee;
    private JPanel labelPanel, inputPanel;

    private JTextField idField, firstNameField, lastNameField, matchesField;
    private JComboBox qualificationField, qualificationLevel, homeLocation;
    private JCheckBox visitNorth, visitCentral, visitSouth;
    private JButton backButton, saveButton, removeButton;
    
    private static JavaBallController controller;

    /**
     * Constructor to add components and create frame.
     */
    public RefereeFrame(JavaBallController controller) {
	
	this.controller = controller;

	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// setTitle("Add/Edit/Remove Referee");
	setSize(400, 350);
	setLocation(200, 200);
	setResizable(false);

	// Adds top GUI components
	layoutTop();
	// Adds central GUI components
	layoutMiddle();
	// Adds bottom GUI components
	layoutBottom();

    }

    public RefereeFrame(Referee referee) {

	// Calls the default constructor
	this(controller);
	this.referee = referee;
    }

    /**
     * Method to add labels to GUI.
     */
    public void layoutTop() {

	labelPanel = new JPanel();
	labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
	// Creates a border around the labels so they are spaced apart
	EmptyBorder border = new EmptyBorder(8, 40, 8, 40);
	EmptyBorder space = new EmptyBorder(16, 40, 16, 40);

	// Creates and adds ID Label
	idLabel = new JLabel();
	idLabel.setText("ID");
	idLabel.setBorder(border);
	labelPanel.add(idLabel);
	// Creates and adds first name Label
	firstNameLabel = new JLabel();
	firstNameLabel.setText("First Name");
	firstNameLabel.setBorder(border);
	labelPanel.add(firstNameLabel);
	// Creates and adds first name Label
	lastNameLabel = new JLabel();
	lastNameLabel.setText("Last Name");
	lastNameLabel.setBorder(border);
	labelPanel.add(lastNameLabel);
	// Creates and adds qualification Label
	qualificationLabel = new JLabel();
	qualificationLabel.setText("Qualification");
	qualificationLabel.setBorder(space);
	labelPanel.add(qualificationLabel);
	// Creates and adds matches allocated Label
	matchesLabel = new JLabel();
	matchesLabel.setText("Matches Allocated");
	matchesLabel.setBorder(border);
	labelPanel.add(matchesLabel);
	// Creates and adds referees home location Label
	homeLabel = new JLabel();
	homeLabel.setText("Home Location");
	homeLabel.setBorder(space);
	labelPanel.add(homeLabel);
	// Creates and adds visit areas Label
	visitAreasLabel = new JLabel();
	visitAreasLabel.setText("Visitable Areas");
	visitAreasLabel.setBorder(border);
	labelPanel.add(visitAreasLabel);

	// Adds components to panel 'top'
	add(labelPanel, BorderLayout.WEST);

    }

    public void layoutMiddle() {

	inputPanel = new JPanel();
	inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

	inputPanel.setBackground(Color.gray);

	idField = new JTextField(5);
	idField.setEditable(false);
	inputPanel.add(idField);

	firstNameField = new JTextField(10);
	inputPanel.add(firstNameField);

	lastNameField = new JTextField(10);
	lastNameField.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseExited(MouseEvent event) {
		String firstName = firstNameField.getText().trim();
		String lastName = lastNameField.getText().trim();
		if (!(firstName.equals("") && lastName.equals(""))) {
		    RefereeList refList = new RefereeList();
		    String ID = refList.createID(firstName, lastName);
		    idField.setText(ID);
		    // TODO
		}
	    }
	});
	inputPanel.add(lastNameField);

	qualificationField = new JComboBox();
	qualificationField.setModel(new DefaultComboBoxModel(
		Referee.Qualifications.values()));
	inputPanel.add(qualificationField);

	qualificationLevel = new JComboBox();
	for (int i = 1; i <= Referee.MAX_QUAL_LENGTH; i++) {
	    qualificationLevel.addItem(i + "");
	}
	inputPanel.add(qualificationLevel);

	matchesField = new JTextField(3);
	inputPanel.add(matchesField);

	homeLocation = new JComboBox();
	homeLocation.setModel(new DefaultComboBoxModel(
		JavaBallController.Location.values()));
	inputPanel.add(homeLocation);

	visitNorth = new JCheckBox("North");
	visitSouth = new JCheckBox("South");
	visitCentral = new JCheckBox("Central");
	inputPanel.add(visitNorth);
	inputPanel.add(visitCentral);
	inputPanel.add(visitSouth);

	add(inputPanel, BorderLayout.EAST);

    }

    public void layoutBottom() {

	JPanel bottom = new JPanel();

	saveButton = new JButton("Save Changes");
	saveButton.addActionListener(this);
	bottom.add(saveButton);
	removeButton = new JButton("Remove Referee");
	removeButton.addActionListener(this);
	bottom.add(removeButton);
	backButton = new JButton("Cancel");
	backButton.addActionListener(this);
	bottom.add(backButton);

	// Add components to panel
	add(bottom, BorderLayout.SOUTH);
    }

    /**
     * This method handles events for the the Referee Frame (i.e. adding,
     * editing and removing referee information)
     * 
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == backButton) {

	    // Close window
	    dispose();
	}
	if (ae.getSource() == removeButton) {
	    controller.removeReferee(referee);

	    // Close window
	    dispose();
	}
	if (ae.getSource() == saveButton) {

	    if (controller.indexCounter() == 12) {
		JOptionPane.showMessageDialog(null, "Sorry.\n"
			+ "The Referee List is full!");
	    } else {
		// Get travel locations for referee
		String north = visitNorth.isSelected() ? "Y" : "N";
		String central = visitCentral.isSelected() ? "Y" : "N";
		String south = visitSouth.isSelected() ? "Y" : "N";
		String travel = north + central + south;

		if (this.referee == null) {

		    controller.addReferee(firstNameField.getText(),
			    lastNameField.getText(),
			    (Referee.Qualifications) qualificationField
				    .getSelectedItem(), Integer.parseInt(String
				    .valueOf(qualificationLevel
					    .getSelectedItem())), Integer
				    .parseInt(matchesField.getText()),
			    (JavaBallController.Location) homeLocation
				    .getSelectedItem(), travel);
		    dispose();
		}

		else {
		    controller.editReferee(referee,
			    (Referee.Qualifications) qualificationField
				    .getSelectedItem(), Integer.parseInt(String
				    .valueOf(qualificationLevel
					    .getSelectedItem())),
			    (JavaBallController.Location) homeLocation
				    .getSelectedItem(), travel);
		    dispose();
		}
	    }
	}

    }

}
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * TODO Upon clicking the allocate button, a new small JFrame comes up
 * which contains areas to input the information of a match (week
 * number, area and level). There is a cancel button, and a get suitable
 * referees upon clicking this button the small JFrame is disposed and
 * the main referee table is filtered to show the listed suitable
 * referees, along with a note saying it is ordered and filtered
 * according to suitability and there is a button to reset the table (No
 * filter, and sort by the default ID).
 * 
 */

public class AllocateMatches extends JFrame implements ActionListener {

    private JTextField weekNumber;
    private JComboBox matchLevel, matchArea;
    private JButton allocateReferees, cancelButton;
    private JLabel weekLabel, levelLabel, locationLabel;
    
    private JTable table;
    private final JavaBallController controller;

    public AllocateMatches(JavaBallController controller) {
	
	this.controller = controller;

	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setTitle("Add Match");
	setSize(500, 150);
	setResizable(false);
	setLocation(200, 200);
	layoutTop();
	layoutMiddle();
	layoutBottom();
    }

    // This needs to be altered, but I wanted a label.
    private void layoutTop() {
	JPanel top = new JPanel();

	weekLabel = new JLabel("Week No.  ");
	levelLabel = new JLabel("Match Level  ");
	locationLabel = new JLabel("Location  ");
	top.add(weekLabel);
	top.add(levelLabel);
	top.add(locationLabel);
	add(top, BorderLayout.NORTH);
    }

    private void layoutMiddle() {

	JPanel middle = new JPanel();
	// Creates and add text field for match week number
	weekNumber = new JTextField(5);
	middle.add(weekNumber);
	// Creates and add JComboBox for selecting match level
	matchLevel = new JComboBox();
	matchLevel.setModel(new DefaultComboBoxModel(Match.Level.values()));
	matchLevel.setEditable(false);
	middle.add(matchLevel);
	// Creates and add JComboBox for selecting match location
	matchArea = new JComboBox();
	matchArea.setEditable(false);
	matchArea.setModel(new DefaultComboBoxModel(JavaBallController.Location
		.values()));
	middle.add(matchArea);
	// Adds panel 'top' to frame
	add(middle, BorderLayout.CENTER);

    }

    private void layoutBottom() {

	JPanel bottom = new JPanel();
	// Create and add 'create match and allocate referees' button
	bottom.setBackground(Color.gray);
	allocateReferees = new JButton("Allocate Referees");
	allocateReferees.addActionListener(this);
	bottom.add(allocateReferees);
	// Creates and add back button
	cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(this);
	bottom.add(cancelButton);
	// Adds panel to frame
	add(bottom, BorderLayout.SOUTH);
    }

    /**
     * This method is used to handle events related to allocating matches
     * 
     */
    public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == allocateReferees) {
	    // referees for that match.
	    try {

		int week = Integer.parseInt(weekNumber.getText());
		if (week < Season.MIN_WEEK || week > Season.MAX_WEEK) {
		    JOptionPane.showMessageDialog(null, "Invalid Week Number");
		    weekNumber.setText("");
		} else {
		    Match.Level level = (Match.Level) matchLevel
			    .getSelectedItem();
		    JavaBallController.Location area = (JavaBallController.Location) matchArea
			    .getSelectedItem();

		    ArrayList<Referee> suitableRefs = controller
			    .allocateReferees(week, level, area);
		    if (suitableRefs == null) {
			JOptionPane.showMessageDialog(null,
				"That week is already in use!");
			weekNumber.setText("");
		    }
		    table = new JTable(
			    controller.allocatedTableData(suitableRefs));

		    dispose();
		}
	    } catch (NumberFormatException ex) {
		JOptionPane.showMessageDialog(null, "Please Enter a Number");
		weekNumber.setText("");
	    }
	} else {
	    // Close window
	    dispose();
	}
    }
}
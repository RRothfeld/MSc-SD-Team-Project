import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

	private JPanel main, top, match, buttons;
	private JLabel week, level, location, confirmationLabel, titleLabel;
	private JTextField weekNumber;
	private JComboBox matchLevel;
	private JComboBox matchLocation;
	private JButton allocateButton, cancelButton;
    
    private JTable table;
    private final JavaBallController controller;

    /**
     *
     * @param controller
     */
    public AllocateMatches(JavaBallController controller) {
	
	this.controller = controller;
	
	setTitle("Add Match");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// setTitle("Add/Edit/Remove Referee");
	setSize(400, 150);
	setLocation(200, 200);
	setResizable(false);
	main = new JPanel(new BorderLayout());
	// Adds top GUI components
	topLayout();
	matchDetailsLayout();
	buttonLayout();
	
    }
    
    private void topLayout() {
    	
    	top = new JPanel();
    	titleLabel = new JLabel("Add Match");
    	titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	add(top, BorderLayout.NORTH);
    	
    }
    
    private void matchDetailsLayout() {

		match = new JPanel(new GridLayout(3,2));

		week = new JLabel("Week Number:");
		level = new JLabel("Match Level:");
		location = new JLabel("Match Location:");
		weekNumber = new JTextField();
		matchLevel = new JComboBox();
		matchLevel.setModel(new DefaultComboBoxModel(Match.Level.values()));
		matchLocation = new JComboBox();
		matchLocation.setModel(new DefaultComboBoxModel(JavaBallController.Location
		.values()));
		
		confirmationLabel = new JLabel("");

		match.add(week);
		match.add(weekNumber);
		match.add(level);
		match.add(matchLevel);
		match.add(location);
		match.add(matchLocation);
		match.add(confirmationLabel);

		match.setBorder(BorderFactory.createLineBorder(Color.black));
		add(match, BorderLayout.CENTER);

	}

    public void buttonLayout() {

		buttons = new JPanel(new GridLayout(1,2));

		allocateButton = new JButton("Allocate");
		allocateButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		buttons.add(allocateButton);
		buttons.add(cancelButton);

		add(buttons, BorderLayout.SOUTH);
	}

	/**
	 * This method is used to handle events related to allocating matches
	 * 
	 * @param ae
	 */
	public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == allocateButton) {
                // referees for that match.
                try {

                    int week = Integer.parseInt(weekNumber.getText());
                    if (week < Season.MIN_WEEK || week > Season.MAX_WEEK) {
                            JOptionPane.showMessageDialog(null, "Invalid Week Number");
                            weekNumber.setText("");
                    } else if (false) {
                            // TODO test if week number already taken 
                    } else {
                        Match.Level level = (Match.Level) matchLevel
                                        .getSelectedItem();
                        JavaBallController.Location area = (JavaBallController.Location) matchLocation
                                        .getSelectedItem();

                        ArrayList<Referee> suitableRefs = controller
                                        .allocateReferees(week, level, area);
                        if (suitableRefs == null) {
                                JOptionPane.showMessageDialog(null,
                                                "Week already taken!");
                        } else if (suitableRefs.size() < 2) {
                                JOptionPane.showMessageDialog(null,
                                                "Not enough suitable referees available!");
                        } else {

                        confirmationLabel.setText("Successfully allocated: " + 
                                        suitableRefs.get(0).getID() + " " + 
                                        suitableRefs.get(1).getID());
                        cancelButton.setText("OK");
                        allocateButton.setEnabled(false);
                        controller.allocatedTableData(suitableRefs);
                        }
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
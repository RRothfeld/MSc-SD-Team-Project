import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @version 1.7
 * @since 11-02-2015
 */
public class AllocateMatches extends JFrame implements ActionListener {

	private JPanel main, top, match, buttons;
	private JLabel week, level, location, confirmationLabel, titleLabel;
	private JTextField weekNumber;
	private JComboBox<Level> matchLevel;
	private JComboBox<Location> matchLocation;
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
		setSize(400, 220);
		setLocationRelativeTo(null); //centres JFrame on desktop
		setResizable(false);
		main = new JPanel(new BorderLayout());
		add(main);
		// Adds top GUI components
		topLayout();
		matchDetailsLayout();
		buttonLayout();

	}

	private void topLayout() {

		top = new JPanel();
		titleLabel = new JLabel("Add Match");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Monospaced",Font.BOLD,14));
		top.add(titleLabel);

		main.add(top, BorderLayout.NORTH);

	}

	private void matchDetailsLayout() {

		match = new JPanel(new BorderLayout());
		JPanel matchSubPanel = new JPanel(new BorderLayout());
		JPanel weekPanel = new JPanel();
		JPanel levelPanel = new JPanel();
		JPanel locationPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		JPanel westPanel = new JPanel();

		// Set Panel backgrounds
		weekPanel.setBackground(Color.lightGray);
		levelPanel.setBackground(Color.lightGray);
		locationPanel.setBackground(Color.lightGray);


		week = new JLabel("Week Number:");
		level = new JLabel("Match Level:");
		location = new JLabel("Match Location:");
		weekNumber = new JTextField(5);
		matchLevel = new JComboBox<Level>();
		matchLevel.setModel(new DefaultComboBoxModel<Level>(Level.values()));
		matchLocation = new JComboBox<Location>();
		matchLocation.setModel(new DefaultComboBoxModel<Location>(Location.values()));



		weekPanel.add(week);
		weekPanel.add(weekNumber);
		levelPanel.add(level);
		levelPanel.add(matchLevel);
		locationPanel.add(location);
		locationPanel.add(matchLocation);

		matchSubPanel.add(weekPanel, BorderLayout.NORTH);
		matchSubPanel.add(levelPanel, BorderLayout.CENTER);
		matchSubPanel.add(locationPanel, BorderLayout.SOUTH);
		matchSubPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		match.add(matchSubPanel, BorderLayout.CENTER);
		match.add(eastPanel, BorderLayout.EAST);
		match.add(westPanel, BorderLayout.WEST);

		main.add(match, BorderLayout.CENTER);

	}

	public void buttonLayout() {

		buttons = new JPanel(new BorderLayout());
		JPanel confirmationPanel = new JPanel();
		JPanel buttonSubPanel = new JPanel();

		confirmationLabel = new JLabel("");
		confirmationLabel.setForeground(Color.red);

		allocateButton = new JButton("Allocate");
		allocateButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		confirmationPanel.add(confirmationLabel);
		buttonSubPanel.add(allocateButton);
		buttonSubPanel.add(cancelButton);

		buttons.add(confirmationPanel, BorderLayout.NORTH);
		buttons.add(buttonSubPanel, BorderLayout.CENTER);

		main.add(buttons, BorderLayout.SOUTH);
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
				} else {
					Level level = (Level) matchLevel
							.getSelectedItem();
					Location area = (Location) matchLocation
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

						confirmationLabel.setText("Successfully allocated referees: " + 
								suitableRefs.get(0).getID() + " " + 
								suitableRefs.get(1).getID());
						cancelButton.setText("OK");
						allocateButton.setEnabled(false);
						controller.allocatedTableData(suitableRefs);
						
						controller.displaySuitableReferees("Referees ordered by suitability for Match " + week);
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
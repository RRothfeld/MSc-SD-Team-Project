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
import javax.swing.UIManager;
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
 * @version 1.7
 * @since 11-02-2015
 */
public class AllocationFrame extends JFrame implements ActionListener {

	private final JavaBallController controller;
	private JTextField fldWeek;
	private JComboBox<MatchLevel> cmbLevel;
	private JComboBox<Location> cmbLocation;
	private JButton btnAllocate, btnCancel;
	private JLabel lblStatus;
	private final JPanel statusHeaderPanel = new JPanel();

	/**
	 *
	 * @param controller
	 */
	public AllocationFrame(JavaBallController controller) {

		this.controller = controller;

		setTitle("Allocate Referees");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 260);
		setLocationRelativeTo(null); // centres JFrame on desktop
		setResizable(false);

		// Adds top GUI components
		layoutComponents();
	}

	private void layoutComponents() {

		JPanel outerInputPanel = new JPanel();
		getContentPane().add(outerInputPanel, BorderLayout.NORTH);
		outerInputPanel.setLayout(new BorderLayout(0, 0));

		JPanel detailsHeaderPanel = new JPanel();
		outerInputPanel.add(detailsHeaderPanel, BorderLayout.NORTH);
		detailsHeaderPanel.setLayout(new BorderLayout(0, 0));

		Component outerSpacerTop = Box.createVerticalStrut(5);
		detailsHeaderPanel.add(outerSpacerTop, BorderLayout.NORTH);

		JLabel lblDetailsHeader = new JLabel("Match Details");
		detailsHeaderPanel.add(lblDetailsHeader, BorderLayout.CENTER);

		Component detailsHeaderSpacer = Box.createHorizontalStrut(5);
		detailsHeaderPanel.add(detailsHeaderSpacer, BorderLayout.WEST);

		Component outerSpacerLeft = Box.createHorizontalStrut(5);
		outerInputPanel.add(outerSpacerLeft, BorderLayout.WEST);

		Component outerSpacerRight = Box.createHorizontalStrut(5);
		outerInputPanel.add(outerSpacerRight, BorderLayout.EAST);

		Component outerSpacerBottom = Box.createVerticalStrut(5);
		outerInputPanel.add(outerSpacerBottom, BorderLayout.SOUTH);

		JPanel innerInputPanel = new JPanel();
		innerInputPanel.setBackground(Color.decode("0xDDDDDD"));
		innerInputPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		outerInputPanel.add(innerInputPanel, BorderLayout.CENTER);
		innerInputPanel.setLayout(new BorderLayout(0, 0));

		Component innerSpacerTop = Box.createVerticalStrut(5);
		innerInputPanel.add(innerSpacerTop, BorderLayout.NORTH);

		Component innerSpacerRight = Box.createHorizontalStrut(5);
		innerInputPanel.add(innerSpacerRight, BorderLayout.EAST);

		Component innerSpacerBottom = Box.createVerticalStrut(5);
		innerInputPanel.add(innerSpacerBottom, BorderLayout.SOUTH);

		Component innerSpacerLeft = Box.createHorizontalStrut(5);
		innerInputPanel.add(innerSpacerLeft, BorderLayout.WEST);

		JPanel matchDetailsPanel = new JPanel();
		matchDetailsPanel.setBackground(Color.decode("0xDDDDDD"));
		innerInputPanel.add(matchDetailsPanel, BorderLayout.CENTER);

		JLabel lblWeek = new JLabel("Week Number");
		lblWeek.setHorizontalAlignment(SwingConstants.RIGHT);
		matchDetailsPanel.add(lblWeek);
		matchDetailsPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(Color.decode("0xDDDDDD"));
		FlowLayout fl_weekPanel = (FlowLayout) weekPanel.getLayout();
		fl_weekPanel.setHgap(10);
		fl_weekPanel.setAlignment(FlowLayout.LEFT);
		matchDetailsPanel.add(weekPanel);

		fldWeek = new JTextField();
		weekPanel.add(fldWeek);
		fldWeek.setColumns(5);

		JLabel lblLevel = new JLabel("Match Level");
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		matchDetailsPanel.add(lblLevel);

		JPanel levelPanel = new JPanel();
		levelPanel.setBackground(Color.decode("0xDDDDDD"));
		FlowLayout fl_levelPanel = (FlowLayout) levelPanel.getLayout();
		fl_levelPanel.setHgap(10);
		fl_levelPanel.setAlignment(FlowLayout.LEFT);
		matchDetailsPanel.add(levelPanel);

		cmbLevel = new JComboBox<MatchLevel>();
		cmbLevel.setModel(new DefaultComboBoxModel<MatchLevel>(MatchLevel
				.values()));
		levelPanel.add(cmbLevel);

		JLabel lblLocation = new JLabel("Match Location");
		lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		matchDetailsPanel.add(lblLocation);

		JPanel locationPanel = new JPanel();
		locationPanel.setBackground(Color.decode("0xDDDDDD"));
		FlowLayout fl_locationPanel = (FlowLayout) locationPanel.getLayout();
		fl_locationPanel.setHgap(10);
		fl_locationPanel.setAlignment(FlowLayout.LEFT);
		matchDetailsPanel.add(locationPanel);

		cmbLocation = new JComboBox<Location>();
		cmbLocation.setModel(new DefaultComboBoxModel<Location>(Location
				.values()));
		locationPanel.add(cmbLocation);

		JPanel statusPanel = new JPanel();
		getContentPane().add(statusPanel, BorderLayout.CENTER);
		statusPanel.setLayout(new BorderLayout(0, 0));
		statusPanel.add(statusHeaderPanel, BorderLayout.NORTH);
		statusHeaderPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblStatusHeader = new JLabel("Status");
		statusHeaderPanel.add(lblStatusHeader, BorderLayout.CENTER);

		Component statusHeaderSpacer = Box.createHorizontalStrut(5);
		statusHeaderPanel.add(statusHeaderSpacer, BorderLayout.WEST);

		Component statusSpacerBottom = Box.createVerticalStrut(5);
		statusPanel.add(statusSpacerBottom, BorderLayout.SOUTH);

		lblStatus = new JLabel();
		lblStatus.setBorder(new LineBorder(new Color(128, 128, 128)));
		lblStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setText("Please insert match details above.");
		statusPanel.add(lblStatus, BorderLayout.CENTER);

		Component statusSpacerLeft = Box.createHorizontalStrut(5);
		statusPanel.add(statusSpacerLeft, BorderLayout.WEST);

		Component statusSpacerRight = Box.createHorizontalStrut(5);
		statusPanel.add(statusSpacerRight, BorderLayout.EAST);
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAllocate = new JButton("Allocate");
		buttonPanel.add(btnAllocate);
		btnAllocate.addActionListener(this);

		btnCancel = new JButton("Cancel");
		buttonPanel.add(btnCancel);
		btnCancel.addActionListener(this);
	}

	/**
	 * This method is used to handle events related to allocating matches
	 * 
	 * @param ae
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnAllocate) {
			// referees for that match.
			try {
				fldWeek.setBackground(Color.decode("0xEEEEEE"));
				int week = Integer.parseInt(fldWeek.getText());
				if (week < Season.MIN_WEEK || week > Season.MAX_WEEK) {
					JOptionPane.showMessageDialog(null, "Invalid Week Number");
					falseWeekNumber();
				} else {
					MatchLevel level = (MatchLevel) cmbLevel.getSelectedItem();
					Location area = (Location) cmbLocation.getSelectedItem();

					ArrayList<Referee> suitableRefs = controller
							.allocateReferees(week, level, area);
					if (suitableRefs == null) {
						JOptionPane.showMessageDialog(null,
								"Week already taken!");
						falseWeekNumber();
					} else if (suitableRefs.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"Not enough suitable referees available!");
					} else {

						lblStatus.setText("Match allocated to referees "
								+ suitableRefs.get(0).getID() + " and "
								+ suitableRefs.get(1).getID() + ".");

						btnAllocate.setEnabled(false);
						btnCancel.setText("Close");

						controller.allocatedTableData(suitableRefs);

						controller.setTableHeader("Referees ordered by "
								+ "suitability for match in week " + week);
					}
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						"Please enter an integer for week number.");
				falseWeekNumber();
			}
		} else {
			// Close window
			dispose();
		}
	}
	
	private void falseWeekNumber() {
		fldWeek.setText("");
		fldWeek.setBackground(Color.decode("0xFFCCCC"));
	}
}
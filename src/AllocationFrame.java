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

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;

import java.awt.Dimension;

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
	private JTextArea txtrStatus;

	/**
	 *
	 * @param controller
	 */
	public AllocationFrame(JavaBallController controller) {

		this.controller = controller;

		setTitle("Allocate Referees");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 250);
		setLocationRelativeTo(null); // centres JFrame on desktop
		setResizable(false);

		// Adds top GUI components
		layoutComponents();
	}

	private void layoutComponents() {
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAllocate = new JButton("Allocate");
		buttonPanel.add(btnAllocate);
		btnAllocate.addActionListener(this);

		btnCancel = new JButton("Cancel");
		buttonPanel.add(btnCancel);
		btnCancel.addActionListener(this);

		JPanel outerInfoPanel = new JPanel();
		getContentPane().add(outerInfoPanel, BorderLayout.NORTH);
		outerInfoPanel.setLayout(new BorderLayout(0, 0));

		Component outerSpacerLeft = Box.createHorizontalStrut(5);
		outerInfoPanel.add(outerSpacerLeft, BorderLayout.WEST);

		Component outerSpacerRight = Box.createHorizontalStrut(5);
		outerInfoPanel.add(outerSpacerRight, BorderLayout.EAST);

		Component outerSpacerTop = Box.createVerticalStrut(5);
		outerInfoPanel.add(outerSpacerTop, BorderLayout.NORTH);

		Component outerSpacerBottom = Box.createVerticalStrut(5);
		outerInfoPanel.add(outerSpacerBottom, BorderLayout.SOUTH);

		JPanel innerInfoPanel = new JPanel();
		innerInfoPanel.setBackground(Color.LIGHT_GRAY);
		innerInfoPanel.setBorder(new LineBorder(new Color(128, 128, 128)));
		outerInfoPanel.add(innerInfoPanel, BorderLayout.CENTER);
		innerInfoPanel.setLayout(new BorderLayout(0, 0));

		Component innerSpacerRight = Box.createHorizontalStrut(5);
		innerInfoPanel.add(innerSpacerRight, BorderLayout.EAST);

		Component innerSpacerTop = Box.createVerticalStrut(5);
		innerInfoPanel.add(innerSpacerTop, BorderLayout.NORTH);

		Component innerSpacerBottom = Box.createVerticalStrut(5);
		innerInfoPanel.add(innerSpacerBottom, BorderLayout.SOUTH);

		Component innerSpacerLeft = Box.createHorizontalStrut(5);
		innerInfoPanel.add(innerSpacerLeft, BorderLayout.WEST);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.LIGHT_GRAY);
		innerInfoPanel.add(inputPanel, BorderLayout.CENTER);

		JLabel lblWeek = new JLabel("Week Number");
		lblWeek.setHorizontalAlignment(SwingConstants.RIGHT);
		inputPanel.add(lblWeek);
		inputPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel weekPanel = new JPanel();
		weekPanel.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_weekPanel = (FlowLayout) weekPanel.getLayout();
		fl_weekPanel.setHgap(10);
		fl_weekPanel.setAlignment(FlowLayout.LEFT);
		inputPanel.add(weekPanel);

		fldWeek = new JTextField();
		weekPanel.add(fldWeek);
		fldWeek.setColumns(5);

		JLabel lblLevel = new JLabel("Match Level");
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		inputPanel.add(lblLevel);

		JPanel levelPanel = new JPanel();
		levelPanel.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_levelPanel = (FlowLayout) levelPanel.getLayout();
		fl_levelPanel.setHgap(10);
		fl_levelPanel.setAlignment(FlowLayout.LEFT);
		inputPanel.add(levelPanel);

		cmbLevel = new JComboBox<MatchLevel>();
		cmbLevel.setModel(new DefaultComboBoxModel<MatchLevel>(MatchLevel.values()));
		levelPanel.add(cmbLevel);

		JLabel lblLocation = new JLabel("Match Location");
		lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		inputPanel.add(lblLocation);

		JPanel locationPanel = new JPanel();
		locationPanel.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_locationPanel = (FlowLayout) locationPanel.getLayout();
		fl_locationPanel.setHgap(10);
		fl_locationPanel.setAlignment(FlowLayout.LEFT);
		inputPanel.add(locationPanel);

		cmbLocation = new JComboBox<Location>();
		cmbLocation.setModel(new DefaultComboBoxModel<Location>(Location.values()));
		locationPanel.add(cmbLocation);

		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(UIManager.getColor("menu"));
		getContentPane().add(statusPanel, BorderLayout.CENTER);
		statusPanel.setLayout(new BorderLayout(0, 0));

		Component statusSpacerTop = Box.createVerticalStrut(5);
		statusPanel.add(statusSpacerTop, BorderLayout.NORTH);

		Component statusSpacerBottom = Box.createVerticalStrut(5);
		statusPanel.add(statusSpacerBottom, BorderLayout.SOUTH);

		txtrStatus = new JTextArea();
		txtrStatus.setBackground(UIManager.getColor("menu"));
		txtrStatus.setText("Status");
		statusPanel.add(txtrStatus, BorderLayout.CENTER);

		Component statusSpacerLeft = Box.createHorizontalStrut(5);
		statusPanel.add(statusSpacerLeft, BorderLayout.WEST);

		Component statusSpacerRight = Box.createHorizontalStrut(5);
		statusPanel.add(statusSpacerRight, BorderLayout.EAST);
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
					fldWeek.setText("");
				} else {
					MatchLevel level = (MatchLevel) cmbLevel.getSelectedItem();
					Location area = (Location) cmbLocation.getSelectedItem();

					ArrayList<Referee> suitableRefs = controller
							.allocateReferees(week, level, area);
					if (suitableRefs == null) {
						JOptionPane.showMessageDialog(null,
								"Week already taken!");
					} else if (suitableRefs.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"Not enough suitable referees available!");
					} else {

						txtrStatus.setText("Successfully allocated referees: "
								+ suitableRefs.get(0).getID() + " "
								+ suitableRefs.get(1).getID());
						
						btnAllocate.setEnabled(false);
						btnCancel.setText("Close");
						
						controller.allocatedTableData(suitableRefs);

						controller
								.displaySuitableReferees("Referees ordered by suitability for Match "
										+ week);

					}
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Please Enter a Number");
				fldWeek.setText("");
				fldWeek.setBackground(Color.decode("0xFFCCCC"));
			}
		} else {
			// Close window
			dispose();
		}
	}
}
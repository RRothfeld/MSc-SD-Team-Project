import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Team Foxtrot - JavaBall Referees Shows a bar chart of all referees and the
 * number of allocations per referee
 * <p>
 * University of Glasgow MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author Miroslav Pashov, 1005139P
 * @author Andrew Lowson, 0800685L
 * @author Marco Cook, 2152599C
 * @author Raoul Rothfeld, 2164502R
 * 
 * @version 1.1
 * @since 03-02-2015
 */

public class ChartFrame extends JFrame {
	/** RefereeList Object with all Referees to be displayed */
	private final RefereeList refList;

	/** Dimensions of the chart frame */
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 400;
	
	/** Dimensions of each bar */
	private static final int BAR_WIDTH = 50;
	private static final int SPACING = 10;

	/**
	 * Opens a JFrame showing the bar chart with the number of allocations per
	 * referee
	 * @param refList the RefereeList Object containing all referees
	 */
	public ChartFrame(RefereeList refList) {
		// Instantiate referee list
		this.refList = refList;

		// Set chart JFrame properties
		setTitle("Barchart of Referee Allocations");
		setBounds(30, 30, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Create content
		add(new RectangleComponent());
	}

	/**
	 * A component that draws some shapes and displays a message
	 */
	private class RectangleComponent extends JComponent {
		/** @Override */
		public void paint(Graphics g) {
			g.drawLine(10, 10, 10, 300);
			g.drawLine(10, 300, 300, 300);
			g.setColor(Color.GRAY);
			g.setFont(new Font("Verdana", Font.BOLD, 12));
			
			int x = 20, y = 300, height;
			for (Referee ref : refList) {
				g.drawString(ref.getID(), x+12, y+15);
				height = ref.getAllocations() * 5;
				g.fillRect(x, y-height, BAR_WIDTH, height);
				x += BAR_WIDTH + SPACING;
			}
		}
	}
}
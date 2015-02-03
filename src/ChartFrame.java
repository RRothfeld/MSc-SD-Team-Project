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

	/** Dimensions  of the chart frame */
	private static final int FRAME_HEIGHT = 300;
	private int frame_width;
	
	/** Dimensions of each bar */
	private static final int BAR_WIDTH = 50;
	private static final int SPACING = 20;

	/**
	 * Opens a JFrame showing the bar chart with the number of allocations per
	 * referee
	 * @param refList the RefereeList Object containing all referees
	 */
	public ChartFrame(RefereeList refList) {
		// Instantiate referee list
		this.refList = refList;
		
		// width
		frame_width = refList.size() * (BAR_WIDTH + SPACING) + SPACING*2;

		// Set chart JFrame properties
		setTitle("Chart of Allocations per Referee (by ID)");
		setSize(frame_width, FRAME_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		// Create content
		add(new RectangleComponent());
	}

	/**
	 * A component that draws some shapes and displays a message
	 */
	private class RectangleComponent extends JComponent {
		/** @Override */
		public void paint(Graphics g) {
			
			g.setFont(new Font("Verdana", Font.BOLD, 12));
			
			((Graphics2D) g).rotate(-Math.PI/2);
			g.drawString("Number of Allocations", (FRAME_HEIGHT+100)/-2, 15);
			((Graphics2D) g).rotate(Math.PI/2);
			
			g.drawString("Referees by ID", (frame_width-100)/2, FRAME_HEIGHT-30);
			
			g.drawLine(SPACING*2, SPACING, SPACING*2, (FRAME_HEIGHT - 75));
			g.drawLine(SPACING*2, (FRAME_HEIGHT - 75),
					(frame_width - SPACING), (FRAME_HEIGHT - 75));
			
			g.setFont(new Font("Verdana", Font.PLAIN, 12));
			g.setColor(Color.GRAY);
			
			int x = (SPACING*2)+1, y = FRAME_HEIGHT-75, height;
			for (Referee ref : refList) {
				g.drawString(ref.getID(), x+12, y+15);
				height = ref.getAllocations() * (FRAME_HEIGHT-75-SPACING)/refList.getMaxAllocation();
				g.fillRect(x, y-height, BAR_WIDTH, height);
				x += BAR_WIDTH + SPACING;
			}
		}
	}
}
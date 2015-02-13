package javaball.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javaball.model.Referee;
import javaball.model.RefereeList;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Shows a bar chart of all referees and the number of allocations per referee
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
 * @version 1.3 - final
 * @since 11-02-2015
 */
public class ChartFrame extends JFrame {
	/** RefereeList Object with all Referees to be displayed */
	private final RefereeList refList;

	/** Dimensions of the chart frame */
	private static final int FRAME_HEIGHT = 300;
	private int frame_width;

	/** Dimensions of each bar */
	private static final int BAR_WIDTH = 50;
	private static final int SPACING = 30;
	
	/** Margins of the chart */
	private static final int BOTTOM_MARGIN = 65;

	/**
	 * Opens a JFrame showing the column chart with the number of allocations
	 * per referee
	 * @param refList the RefereeList Object containing all referees
	 */
	public ChartFrame(RefereeList refList) {
		// Store passed referee list
		this.refList = refList;
		this.refList.sort();

		// Calculate JFrame width according to the number of referees and
		// the necessary margins (left and right); minimum width of 150 px
		int widthPerReferee = BAR_WIDTH + SPACING;
		int margins = SPACING * 2;
		frame_width = Math.max(refList.size() * widthPerReferee + margins, 300);

		// Set JFrame properties
		setTitle("Referee Chart");
		setSize(frame_width, FRAME_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); // centres JFrame on desktop

		// Display column chart
		add(new ChartComponent());
	}

	/**
	 * A component that draws the column chart and displays all axis and titles
	 */
	private class ChartComponent extends JComponent {
		/** @Override paint method */
		public void paint(Graphics g) {
			// Set font properties for chart and axis titles; create FontMetric
			// to retrieve lengths of titles as to horizontally centre titles
			g.setFont(new Font("Arial", Font.BOLD, 12));
			FontMetrics fm_bold = g.getFontMetrics();

			// Print centred chart title above chart
			String title = "Allocations per Referee (ordered by ID)";
			g.drawString(title, (frame_width - fm_bold.stringWidth(title)) / 2,
					25);

			// Print rotated y-axis description
			((Graphics2D) g).rotate(-Math.PI / 2); // Rotate orientation -90°
			String yAxisTitle = "Number of Allocations";
			g.drawString(yAxisTitle, (FRAME_HEIGHT + 120) / -2, 20);
			((Graphics2D) g).rotate(Math.PI / 2); // Revert rotation to normal

			// Print centred x-axis description
			String xAxisTitle = "Referees by ID";
			g.drawString(xAxisTitle,
					(frame_width - fm_bold.stringWidth(xAxisTitle)) / 2,
					FRAME_HEIGHT - 30);

			// Set font properties for axis labels
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			FontMetrics fm_plain = g.getFontMetrics();

			// Retrieve the maximum number of allocations to provide flexible,
			// vertically scaling of the chart
			int maxAllocations = refList.getMaxAllocation();

			// Calculate the edges of the chart
			int chartTop = SPACING + 10;
			int chartBottom = FRAME_HEIGHT - BOTTOM_MARGIN;
			int chartLeft = SPACING * 2;
			int chartRight = frame_width - SPACING;

			// Print gridline and y-axis labels (with interval of two)
			int yLabel = SPACING + 5;
			// For each second interval up to the maximum number of allocations
			for (int i = 0; i <= maxAllocations; i += 2) {
				// Calculate height of next y-axis label and gridline
				int yLabelHeight = chartBottom - i
						* (chartBottom - SPACING - 10) / maxAllocations;

				// Print y-axis label (number of allocations) with tick marking
				// where on the y-axis the label is placed
				g.drawString("" + i, yLabel, yLabelHeight);
				g.drawLine(yLabel * 2 - 15, yLabelHeight, chartLeft,
						yLabelHeight);

				// Print gridline (except for 0 as that gridline is the x-axis)
				if (i != 0) {
					// Display gridline in light gray
					g.setColor(Color.LIGHT_GRAY);
					g.drawLine(chartLeft, yLabelHeight, chartRight,
							yLabelHeight);
				}

				// Revert colour to black
				g.setColor(Color.BLACK);
			}
			
			// Print y- and x-axes after the gridline to ensure that the axes
			// are overlaying the gridline
			g.drawLine(chartLeft, chartTop, chartLeft, chartBottom); // y-axis
			g.drawLine(chartLeft, chartBottom, chartRight, chartBottom);
			
			// Print column chart for each referee
			// Calculate left edge of first bar with one pixel offset as to
			// avoid overlaying the y-axis
			int columnLeft = chartLeft + 1;
			
			// Print column according to number of allocations per referee for
			// each referee in the provided RefereeList
			for (Referee ref : refList) {
				// Print referee ID as x-axis label (centred below ref's bar)
				g.setColor(Color.BLACK);
				String xLabel = ref.getID();
				int xLabelLeft = columnLeft
						+ (BAR_WIDTH - fm_plain.stringWidth(xLabel)) / 2;
				g.drawString(xLabel, xLabelLeft, chartBottom + 15);
				
				// Print bar according to number of allocations
				g.setColor(Color.GRAY);
				int barHeight = ref.getAllocations()
						* (chartBottom - SPACING - 10) / maxAllocations;
				g.fillRect(columnLeft, chartBottom - barHeight, BAR_WIDTH,
						barHeight);
				
				// Advance columnLeft for next bar to be printed
				columnLeft += BAR_WIDTH + SPACING;
			}
		}
	}
}
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;

import javax.swing.*;

/**
 * Team Foxtrot JavaBall Referees XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 * <p>
 * University of Glasgow MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author Miroslav Pashov, 1005139P
 * @author Andrew Lowson, 0800685L
 * @author Marco Cook, 2152599C
 * @author Raoul Rothfeld, 2164502R
 * 
 * @version 1.0
 * @since 14-01-2015
 */

public class ChartFrame extends JFrame {

    private RefereeList referees;
    private JTextArea textArea;

    // Dimensions of the text area
    private final int ROWS = 3;
    private final int COLUMNS = 7;

    // Dimensions of the frame
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 180;

    /**
     * A constructor with a FitnessProgram parameter used to initialise the
     * FitnessProgram instance variable and add the JTextArea component to the
     * window.
     */
    public ChartFrame(RefereeList referees) {
	this.referees = referees;

//	JScrollPane scrollPane = addScrollPane();
//	add(scrollPane, BorderLayout.CENTER);
	RectangleComponent component = new RectangleComponent();
	add(component);
	setTitle("Barchart of Referee Allocations");
	setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    /** A method to build the scroll pane */
    public JScrollPane addScrollPane() {
	JScrollPane scrollPane = new JScrollPane(textArea);
	return scrollPane;
    }

    /**
     * A component that draws some shapes and displays a message
     */
    private static class RectangleComponent extends JComponent {
	/**
	 * Constructor for RectangleComponent object
	 */
	public RectangleComponent() {
	}

	public void paintComponent(Graphics g) {
	    int rectWidth = 30;
	    int rectHeight = 50;

	    // Recover Graphics2D
	    Graphics2D g2 = (Graphics2D) g;

	    // set background colour to be blue
	    g2.setColor(Color.WHITE);
	    g2.fillRect(0, 0, this.getWidth(), this.getHeight());

	    int x = 50;
	    int y = 50;
	    // blocks
	    g2.setColor(Color.GRAY);
	    for (int i = 1; i <= 5; i++) {
		g2.fillRect(x, y, rectWidth, rectHeight);
		x += rectWidth + 10;
		y -= 10;
		rectHeight += 10;
	    }

	    // text
	    x = 50; // reset x back to start
	    y = 120; // set y underneath the blocks
	    g2.setColor(Color.GRAY);
	    g2.setFont(new Font("Monospaced", Font.BOLD, 18));
	    for (int i = 1; i <= 5; i++) {
		g2.drawString("" + x, x, y);
		x += rectWidth + 10;
	    }
	}
    }
}
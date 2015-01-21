import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

    private RefereeAssociation referees;
    private JTextArea textArea;

    // Dimensions of the text area
    private final int ROWS = 3;
    private final int COLUMNS = 7;

    // Dimensions of the frame
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;

    /**
     * A constructor with a FitnessProgram parameter used to initialise the
     * FitnessProgram instance variable and add the JTextArea component to the
     * window.
     */
    public ChartFrame(RefereeAssociation referees) {
	this.referees = referees;

	// order by ref ID
	// initialise an array

	JScrollPane scrollPane = addScrollPane();
	add(scrollPane, BorderLayout.CENTER);

	setTitle("Barchart of Referee Allocations");
	setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    /** A method to build the scroll pane */
    public JScrollPane addScrollPane() {
	textArea = new JTextArea(ROWS, COLUMNS);
	textArea.setText("Hello");
	JScrollPane scrollPane = new JScrollPane(textArea);
	return scrollPane;
    }

    /** A method to render the barchart in the frame */
    private void drawChart() {

    }

    /**
     * Updates the display
     * @param g the Graphics object
     */
    public void paintComponent(Graphics g) {
	// Recover Graphics2D
	//Graphics2D g2 = (Graphics2D) g;
    }
}
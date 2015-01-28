import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Team Foxtrot - JavaBall Referees
 * The main class
 * <p>
 * University of Glasgow
 * MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author  Miroslav Pashov, 1005139P
 * @author  Andrew Lowson, 0800685L
 * @author  Marco Cook, 2152599C
 * @author  Raoul Rothfeld, 2164502R
 * 
 * @version 1.1 (final)
 * @since   25-01-2015
 */

public class JavaBall {
	/** The name of the referee input file */
	public static final String INPUT_FILE = "RefereesIn.txt";
	
	/**
	 * The main method
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// initialise model (Season for Matches, RefereeList for Referees)
		Season season = new Season();
		RefereeList refList = new RefereeList();
		
		// initialise controller
		JavaBallController controller = new JavaBallController(season,
				refList);
		
		// initialise view
		JavaBallGUI view = new JavaBallGUI(controller);
		view.setVisible(true);
	}
}
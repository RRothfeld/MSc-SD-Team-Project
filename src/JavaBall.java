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
	/**
	 * The main method
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// initialise view
		JavaBallGUI view = new JavaBallGUI();
		view.setVisible(true);
		
		// initialise model (Season for Matches, RefereeList for Referees)
		Season season = new Season();
		RefereeList refList = new RefereeList();
		
		// fill season 

		// initialise controller
		JavaBallController controller = new JavaBallController(view, season,
				refList);
	}
}

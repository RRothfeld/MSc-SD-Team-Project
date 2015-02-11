/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * The main class
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
public class JavaBall {
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
		
		// pass GUI reference to controller
		controller.setView(view);
	}
}
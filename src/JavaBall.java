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
	private static final String INPUT_FILE = "RefereesIn.txt";
	
	/**
	 * The main method
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// initialise model (Season for Matches, RefereeList for Referees)
		Season season = new Season();
		RefereeList refList = new RefereeList();
		
		// load refList with data from input file
		initRefereeList(refList);
		
		// initialise controller
		JavaBallController controller = new JavaBallController(season,
				refList);
		
		// initialise view
		JavaBallGUI view = new JavaBallGUI(controller);
		view.setVisible(true);
	}
	
	/**
	 * Reads in provided file and populates RefereeList
	 * @param refList the RefereeList to be populated
	 */
	private static void initRefereeList(RefereeList refList) {
		try {
			// set scope of FileReader
			FileReader refereeFile = null;
			try {
				// initialise FileReader with input file and initialise scanner
				refereeFile = new FileReader(INPUT_FILE);
				Scanner refScanner = new Scanner(refereeFile);

				// read every line of input file and create referees
				while (refScanner.hasNextLine()) {
					String newReferee = refScanner.nextLine();
					if (newReferee != null) {
						Referee referee = new Referee(newReferee);
						refList.add(referee);
					}
				}
				// close scanner after usage
				refScanner.close();
			} finally {
				// close input file if it has been opened
				if (refereeFile != null)
					refereeFile.close();
			}
		} catch (IOException e) {} // do nothing if file not found
		
		// Temporary testing method in RefereeList to make sure methods work
		refList.debug(true); // TODO DELETE
	}
}
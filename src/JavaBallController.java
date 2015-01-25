import javax.swing.WindowConstants;

/**
 * Team Foxtrot - JavaBall Referees
 * The controller class
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
 * @version 1.0
 * @since   21-01-2015
 */

public class JavaBallController {
	private final Season season;
	private final RefereeList refList;
	private ChartFrame chart;
	
	/** TODO */
    public static enum Location {
	NORTH, CENTRE, SOUTH
    }
	
	public JavaBallController(Season season, RefereeList refList) {
		this.season = season;
		this.refList = refList;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void execAdd(String s) {
		
	}

	/**
	 * 
	 * @param s
	 */
	public void execAllocate(String s) {
		
	}

	/**
	 * 
	 */
	public void execChart() {
		chart = new ChartFrame(refList);
		chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		chart.setVisible(true);
	}
	
	/**
	 * 
	 * @param s
	 */
	public void execSearch(String s) {
		
	}
	
	/**
	 * 
	 */
	public void execSaveExit() {
		
	}
}

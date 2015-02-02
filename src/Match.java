import java.util.ArrayList;

/**
 * Team Foxtrot JavaBall Referees 
 * Class which holds information relating to ma
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
 * @since 25-01-2015
 */

public class Match {
    /** The level of play in a match */
    public enum Level {
	JUNIOR, SENIOR
    }
    
    private final int MIN_WEEK = 1;
    private final int MAX_WEEK = 52;

    /** The week when a match is held also serves as an ID */
    private int week; // week when the match is held 1-52

    /** The area where the match is held. */
    private JavaBallController.Location area;
    
    /** The level of the match */
    private Level level;
    
    /** Suitable referees for the match */
    private Referee[] suitableReferees;
    
    // TODO delete
    public Match() {}

    /**
     * Instantiates a new Match given a calendar week, area the match is held
     * in, level of the match
     * @param week the number of the calendar match is held in (1-52)
     * @param level of the match (Junior or Senior)
     * @param area that the match is held in
     */
    public Match(int week, Level level, JavaBallController.Location area) {
    	if (week >= MIN_WEEK && week <= MAX_WEEK) {
    		this.week = week;
    	}
		this.level = level;
		this.area = area;
		this.suitableReferees = new Referee[2];
    }
    
    /** Returns the week number which is also match ID */
    public int getWeek() {
	return week;
    }

    /** Returns the area where the match is held
     * @return  */
    public JavaBallController.Location getArea() {
	return area;
    }

    /** Returns match level (i.e. Senior or Junior
     * @return  */
    public Level getLevel() {
	return level;
    }
    
    /** Set the week number which is also match ID
     * @param week */
    public boolean setWeek(int week) {
	if (week >= MIN_WEEK && week <= MAX_WEEK) {
	    this.week = week;
	    return true;
	}
	return false;
    }

    /** Set the area where match is held
     * @param area */
    public void setArea(JavaBallController.Location area) {
	this.area = area;
    }
    
    /** Set match level (i.e. Senior or Junior
     * @param level */
    public void getLevel(Level level) {
	this.level = level;
    }
    
    /**
     * Method to set the Suitable Referees once created by RefereeList
     * @param referees - ArrayList containing 2 suitable referees
     */
    public void setReferees(Referee[] referees){
	suitableReferees = referees;
    }
    
    public Referee[] getReferees() {
        return this.suitableReferees;
    }
    
    /** Returns match details
     * @return  */
    public String report() {
	// TODO this will print to MatchAllocs.txt
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[0], suitableReferees[1]);
	return match;
    }

    /**  */
    @Override
    public String toString() {
	// TODO this method seems redundant
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[0], suitableReferees[1]);
	return match;
    }
}
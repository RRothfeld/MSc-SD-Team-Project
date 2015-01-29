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
    private final Referee[] suitableReferees;

    /**
     * Instantiates a new Match given a calendar week, area the match is held
     * in, level of the match
     * @param week the number of the calendar match is held in (1-52)
     * @param level of the match (Junior or Senior)
     * @param suitableReferees
     * @param area that the match is held in
     */
    public Match(int week, Level level, Referee[] suitableReferees, JavaBallController.Location area) {
	this.week = week;
	this.level = level;
	this.area = area;
	this.suitableReferees = suitableReferees;
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
    public void setWeek(int week) {
	if ((week >= MIN_WEEK) && (week <= MAX_WEEK)){
	    this.week = week;
	}
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
    
// TODO RR said remove the method, but then don't we need getters for iv-s?
//    /** Get two suggested referees for the match */
//    public Referee[] getReferees() {
//	return suitableReferees;
//    }

    /** Returns match details
     * @return  */
    public String matchReport() {
	// TODO this will print to MatchAllocs.txt
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[1], suitableReferees[2]);
	return match;
    }

    /**  */
    @Override
    public String toString() {
	// TODO this method seems redundant
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[1], suitableReferees[2]);
	return match;
    }
}
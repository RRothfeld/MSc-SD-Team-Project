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
    
    public enum Week {
	MIN (1), MAX (52);
	
	private final int number; // week number
	Week(int number) {
	    this.number = number;
	}
    }

    /** The week when a match is held also serves as an ID */
    public int week; // week when the match is held 1-52

    /** The area where the match is held. */
    public JavaBallController.Location area;
    
    /** The level of the */
    public Level level;
    public Referee[] suitableReferees;

    /**
     * Instantiates a new Match given a calendar week, area the match is held
     * in, level of the match
     * @param week the number of the calendar match is held in (1-52)
     * @param level of the match (Junior or Senior)
     * @param area that the match is held in
     */
    public Match(int week, Level level, Referee[] suitableReferees, JavaBallController.Location area) {
	this.week = week;
	this.level = level;
	this.area = area;
	this.suitableReferees = suitableReferees;
    }
    
    /** Get week number which is also match ID */
    public int getWeek() {
	return week;
    }

    /** Get the area where the match is held */
    public JavaBallController.Location getArea() {
	return area;
    }

    /** Get match level (i.e. Senior or Junior */
    public Level getLevel() {
	return level;
    }
    
    /** Set the week number which is also match ID */
    public void setWeek(int week) {
	// TODO we need to have constraint 1-52
	// enums are another way
	// will get rid of magic vars
	if ((week >= Match.Week.MIN.number) && (week <= Match.Week.MAX.number)){
	    this.week = week;
	}
	// do nothing or display msg?
    }
    
    /** Set the area where match is held */
    public void setArea(JavaBallController.Location area) {
	
    }
    
// TODO RR said remove the method, but then don't we need getters for iv-s?
//    /** Get two suggested referees for the match */
//    public Referee[] getReferees() {
//	return suitableReferees;
//    }

    /** Gets match details */
    public String matchReport() {
	// TODO this will print to MatchAllocs.txt
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[1], suitableReferees[2]);
	return match;
    }

    /**  */
    public String toString() {
	// TODO this method feels redundant
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[1], suitableReferees[2]);
	return match;
    }

}
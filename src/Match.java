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
 * @version 1.0
 * @since 18-01-2015
 */

public class Match {
    
    /** Possible locations for the match */
    public enum Location {
	NORTH, CENTRE, SOUTH
    }
    
    /** The level of play in a match */
    public enum Level {
	JUNIOR, SENIOR
    }
    

    public static final int NORTH = -1;
    public static final int CENTRE = -2;
    public static final int SOUTH = -3;

    private static final int NOT_SCHEDULED = -1;
    public int week; // week when the match is held 1-52

    public String area; // area where the match is held
    public Level level;
    public ArrayList<Referee> suitableReferees;

    /**
     * Instantiates a new Match (default constructor)
     */
    public Match() {
	week = NOT_SCHEDULED;
	area = "";
	initReferees();
    }

    /**
     * Instantiates a new Match given a calendar week, area the match is held
     * in, level of the match
     * 
     * @param week
     *            the number of the calendar match is held in (1-52)
     * @param level
     *            of the match (Junior or Senior)
     * @param area
     *            that the match is held in
     */
    public Match(int week, Level level, String area) {
	this.week = week;
	this.level = level;
	this.area = area;
	initReferees();
	// TODO this method could get the 2 suitable refs and get them from
	// RefAss
    }


    /** Store the two suitable referees for the match */
    public void initReferees() {
	// TODO take in a ref association
	// then
	// suitableReferees = refAss.getSuitableRefs();
    }
    /** Get week number which is also match ID */
    public int getWeek() {
	return week;
    }

    /** Get match area */
    public String getArea() {
	return area;
    }

    /** Get match level (i.e. Senior or Junior */
    public Level getLevel() {
	return level;
    }

    /** Get two suggested referees for the match */
    public ArrayList<Referee> getReferees() {
	return suitableReferees;
    }

    /** Gets match details */
    public String matchReport() {
	// this will print to MatchAllocs.txt
	// could have toString method for match?

	String ref1 = "John Doe"; // ref.get(1).get
	String ref2 = "Jane Gray";
	String match = String.format("%d %s %s %s %s", week, level, area, ref1,
		ref2);
	return match;
    }

    /**  */
    public String toString() {
	return "hello";
    }

}

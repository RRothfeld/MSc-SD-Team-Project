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
<<<<<<< HEAD
<<<<<<< Updated upstream
    
    /** Possible locations for the match */
    public enum Location {
	NORTH, CENTRE, SOUTH
    }
    
=======
>>>>>>> b36d49982444e8aaf83db6cdc2f5117c005ee53b
    /** The level of play in a match */
    public enum Level {
	JUNIOR, SENIOR
    }

    /** TODO comment */
    private static final int NOT_SCHEDULED = -1;
    public int week; // week when the match is held 1-52

    public JavaBallController.Location area; // area where the match is held
    public Level level;
    public ArrayList<Referee> suitableReferees;

    /**
     * Instantiates a new Match (default constructor)
     */
    public Match() {
	week = NOT_SCHEDULED;
	area = null;
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
    public Match(int week, Level level, JavaBallController.Location area) {
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
    public JavaBallController.Location getArea() {
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
=======

	public int week; // week when the match is held 1-52
	public String area; // area where the match is held
	public String level; // "Junior" or "Senior"

	//public RefereeAssociation referees; // list of all suitable referees
	public ArrayList<Referee> referees;
	// referees per season 12
	// public Association referees = new RefereeAssociation();
	// ^ same principle as List ls = new ArrayList(); ?
	/*
	 * Brownie points for
	 * + reusability
	 * + use of inheritance
	 */

	/**
	 * Instantiates a new Match (default constructor)
	 */
	public Match() {
		week = 0; //TODO sentinel value as a CONSTANT?
		area = "";
		level = "";
		initReferees();
	}

	/**
	 * Instantiates a new Match given a calendar week, 
	 * area the match is held in, level of the match
	 * @param week the number of the calendar match is held in (1-52)
	 * @param level of the match (Junior or Senior)
	 * @param area that the match is held in
	 */
	public Match(int week, String level, String area) {
		this.week = week;
		this.level = level;
		this.area = area;
		initReferees();
	}

	public Match(RefereeAssociation refAssociation) {

	}

	/** Initiate Referees Array */
	public void initReferees () {
		referees = new ArrayList<Referee>();
		//TODO should I store null values?
	}

	/** Get week number which is also match ID */
	public int getWeek() {
		return week;
	}

	/** Get match area*/
	public String getArea() {
		return area;
	}

	/** Get match level (i.e. Senior or Junior */
	public String getLevel() {
		return level;
	}

	/** Get all suggested referees for the match */
	public String getReferees() {
		RefereeAssociation rf = new RefereeAssociation();
		for (Referee ref: referees) {
			ref.getFullName();
			ref.getAllocations();
			//TODO selection of 2 most suitable refs
		}
		return "";
	}

	/** Get match details */
	public String matchDetails() {
		// this will print to MatchAllocs.txt
		// could have toString method for match?

		// 
		String ref1 = "John Doe";
		String ref2 = "Jane Gray";
		String match = 
				String.format("%d %s %s %s %s", week, level, area, ref1, ref2);
		return match;
	}
>>>>>>> Stashed changes

}

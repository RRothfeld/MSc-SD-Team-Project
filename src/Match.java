import java.util.ArrayList;

/**
 * Team Foxtrot
 * JavaBall Referees
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
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
 * @since   14-01-2015
 */

public class Match {
    
	// week can be used as Match ID -rr
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
    
    /** Initiate Referees Array */
    public void initReferees () {
	referees = new ArrayList();
	//TODO should I store null values?
    }
    
    /** */
    public int getWeek() {
	return week;
    }
    
    /** */
    public String getArea() {
	return area;
    }
    
    /** */
    public String getLevel() {
	return level;
    }
    
    /** */
    public String getReferees() {
	for (Referee rf: referees) {
	    //rf.getName();
	    //rf.getAllocations();
	    //TODO selection of 2 most suitable refs
	}
	return "";
    }
    
    /** */
    public String matchDetails() {
	// this will print to MatchAllocs.txt
	// could have toString method for match?
	String ref1 = "Derek Riordan";
	String ref2 = "Jane Gray";
	String match = String.format("%d %s %s % %s %s",week, level, area, ref1, ref2);
	return match;
    }
    
}

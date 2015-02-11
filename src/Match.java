/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Class for storing match related information
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
 * @version 1.2
 * @since 11-02-2015
 */
public class Match {
    /** The week when a match is held also serves as an ID */
    private int week; // week when the match is held 1-52

    /** The area where the match is held. */
    private Location area;
    
    /** The level of the match */
    private MatchLevel level;
    
    /** Suitable referees for the match */
    private Referee[] suitableReferees;
    
    /**
     * Instantiates a new Match given a calendar week, area the match is held
     * in, level of the match
     * @param week the number of the calendar match is held in (1-52)
     * @param level of the match (Junior or Senior)
     * @param area that the match is held in
     */
    public Match(int week, MatchLevel level, Location area) {
	setWeek(week);
	this.level = level;
	this.area = area;
	this.suitableReferees = new Referee[2];
    }
    
    /** Returns the week number which is also match ID
     * @return  */
    public int getWeek() {
	return week;
    }

    /** Returns the area where the match is held
     * @return  */
    public Location getArea() {
	return area;
    }

    /** Returns match level (i.e. Senior or Junior
     * @return  */
    public MatchLevel getLevel() {
	return level;
    }
    
    /** Set the week number which is also match ID
     * @param week
     * @return  */
    public boolean setWeek(int week) {
	if (week >= Season.MIN_WEEK && week <= Season.MAX_WEEK) {
	    this.week = week;
	    return true;
	}
	return false;
    }

    /** Set the area where match is held
     * @param area */
    public void setArea(Location area) {
	this.area = area;
    }
    
    /** Set match level (i.e. Senior or Junior
     * @param level */
    public void getLevel(MatchLevel level) {
	this.level = level;
    }
    
    /**
     * Method to set the Suitable Referees once created by RefereeList
     * @param referees - ArrayList containing 2 suitable referees
     */
    public void setReferees(Referee[] referees){
	suitableReferees = referees;
    }
    
//    /**
//     *
//     * @return
//     */
//    public Referee[] getReferees() {
//        
//        String match = String.format("%d %s %s %s %s %s %s", week, level, area,
//            suitableReferees[0].getFirstName(), suitableReferees[0].getLastName(), 
//            suitableReferees[1].getFirstName(), suitableReferees[1].getLastName());
//	
//        System.out.println(match);
//        return this.suitableReferees;
//    }
    
    /** Returns match details
     * @return  */
    public String report() {   
        String match = String.format("%d\t%s\t%s\t%s %s\t%s %s", week, level, area,
		suitableReferees[0].getFirstName(), suitableReferees[0].getLastName(), 
                suitableReferees[1].getFirstName(),suitableReferees[1].getLastName());
	return match;
    }

    /**
     * @return   */
    @Override
    public String toString() {
	// TODO this method seems redundant
	String match = String.format("%d %s %s %s %s", week, level, area,
		suitableReferees[0].getID(), suitableReferees[1].getID());
	return match;
    }
}
package javaball.model;

import javaball.enums.Location;
import javaball.enums.MatchLevel;

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
 * @version 1.5 - final
 * @since 14-02-2015
 */
public class Match {
    /** The week when a match is held also serves as an ID */
    private int week;

    /** The area where the match is held. */
    private Location area;
    
    /** The level of the match */
    private MatchLevel level;
    
    /** Suitable referees for the match */
    private Referee[] suitableReferees;
    
	/**
	 * Instantiates a new Match given a calendar week, area the match is held
	 * in, and a level of the match
	 * @param week the number of the calendar match is held in (1-52)
	 * @param level of the match (Junior or Senior)
	 * @param area that the match is held in
	 */
	public Match(int week, MatchLevel level, Location area) {
		this.week = week;
		this.level = level;
		this.area = area;
		
		// Array to store suitable referees
		this.suitableReferees = new Referee[2];
	}

	/**
	 * Returns the week number which is also match ID
	 * @return the week number
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * Returns the area where the match is held
	 * @return the area
	 */
	public Location getArea() {
		return area;
	}

	/**
	 * Returns match level (i.e. Senior or Junior)
	 * @return the match level
	 */
	public MatchLevel getLevel() {
		return level;
	}

	/**
	 * Set the area where match is held
	 * @param area where match is to be held
	 */
	public void setArea(Location area) {
		this.area = area;
	}
    
	/**
	 * Set match level (i.e. Senior or Junior
	 * @param level the level of the match
	 */
	public void getLevel(MatchLevel level) {
		this.level = level;
	}
    
    /**
     * Populates the match's suitable referees array
     * @param referees, an array containing the two suitable referees
     */
	public void setReferees(Referee[] referees) {
		suitableReferees = referees;
	}
    
    /**
     * Returns a string representation of the contents of the specified match.
     * The format is as follows "2 Junior North Dave Gray Tim Toms", where 2 is
     * the week number, Junior is the level of the match, Dave Gray is the name 
     * of the first suitable referee and Tim Toms is the name of the second 
     * suitable referee. The week is a decimal number while the rest of the 
     * information are strings. Everything is separated by tabs and the line 
     * is followed by a carriage return.
     * @return a string representation of a match
     */
    @Override
	public String toString() {
		String match = String.format("%-7s %-3s  %-3s %-3s %s%10s %s%n", Integer.toString(week), level,
				area, suitableReferees[0].getFirstName(),
				suitableReferees[0].getLastName(),
				suitableReferees[1].getFirstName(),
				suitableReferees[1].getLastName());
		return match;
	}
}
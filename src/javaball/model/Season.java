package javaball.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Class for organising the matches of a season
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
 * @version 1.3 - final
 * @since 14-02-2015
 */
public class Season implements Iterable<Match> {
    
    /** Total number of matches per season */
    public static final int MIN_WEEK = 1;
    public static final int MAX_WEEK = 52; // also represents max matches
    
    /** Matches to be played this season */
    public ArrayList<Match> matches;
    
    /** Default constructor */
	public Season() {
		matches = new ArrayList<>();
	}
    
    /** 
     * Returns all matches in the season
     * @return array list of matches in the season
     */
	public ArrayList<Match> getMatches() {
		return matches;
	}
    
    /** 
     * Method for adding a match to a season
     * @param match object
     */
	public void addMatch(Match match) {
		matches.add(match);
	}
    
    /** 
     * Return match based on week number
     * @param week number from 1 to 52
     * @return match that is played doing that week
     */
	public Match getMatch(int week) {
		// find match with desired ID
		for (Match match : matches) {
			if (match.getWeek() == week)
				return match;
		}
		return null;
	}

    /** 
     * Returns the number of scheduled matches
     * @return  
     */
	public int getNumMatches() {
		return matches.size();
	}
    
	/**
	 * Returns an iterator of elements of type Match, thus allows for 
	 * generalised for-loops
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Match> iterator() {
		return matches.iterator();
	}
}
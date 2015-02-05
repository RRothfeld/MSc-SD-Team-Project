import java.util.ArrayList;
import java.util.Iterator;

/**
 * Team Foxtrot - JavaBall Referees
 * Season class organising the matches of a season.
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

public class Season implements Iterable<Match> {
    
    /** Total number of matches per season */
    //public static final int MAX_MATCHES = 52;
    protected static final int MIN_WEEK = 1;
    protected static final int MAX_WEEK = 52; // represents max matches
    
    /** Matches to be played this season */
    public ArrayList<Match> matches;
    
    /** Default constructor */
    public Season() {
	matches = new ArrayList<>();
    }
    
    /** 
     * Returns all matches
     * @return  
     */
    public ArrayList<Match> getMatches() {
	return matches;
    }
    
    /** 
     * Method for adding a match to a season
     * @param match 
     */
    public void addMatch(Match match) {
	matches.add(match);
    }
    
    /** 
     * Return match based on week number
     * @param week
     * @return  
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
    
    @Override
    public Iterator<Match> iterator() {
        return matches.iterator();
    }
}
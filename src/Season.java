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
    public static final int MAX_MATCHES = 52;
    // TODO think about the relationship
    // between MAX_WEEKS and MAX_MATCHES
    
    /** Matches to be played this season */
    public ArrayList<Match> matches;
    
    /** Default constructor */
    public Season() {
	// TODO let me know if you need to pass something
    }
    
    /** 
     * Returns all t
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
	return matches.get(week);
    }
    
    /** 
     * Returns the number of scheduled matches
     * @return  
     */
    public int getNumMatches() {
	return matches.size();
    }
    
    public Iterator<Match> iterator() {
        return matches.iterator();
    }
}
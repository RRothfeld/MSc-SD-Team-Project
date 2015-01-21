import java.util.ArrayList;

/**
 * Team Foxtrot
 * JavaBall Referees
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

public class Season {
    
    public final int MAXMATCHES = 52;
    
    public ArrayList<Match> matches;
    // public int numMatches; // no need if we use ArrayList
    
    /** Return match based on week number */
    public Match getMatchByWeek(int week) {
	return matches.get(week);
    }
    
    /** Returns the number of matches for a season */
    public int getNumMatches() {
	return matches.size();
    }
}
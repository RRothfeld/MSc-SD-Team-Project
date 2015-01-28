import java.util.ArrayList;

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

public class Season {
    
    /** Total number of matches per season */
    public static final int MAX_MATCHES = 52;
    
    /** Matches to be played this season */
    public ArrayList<Match> matches;
    
    // Constructor und accessor methods missing. -rr
    public Season(Match match) {
	matches.add(match);
    }
    
    /** Returns all t */
    public ArrayList<Match> getMatches() {
	return matches;
    }
    
    /** Return match based on week number */
    public Match getMatch(int week) {
	return matches.get(week);
    }
    
    /** Returns the number of scheduled matches */
    public int getNumMatches() {
	return matches.size();
    }
}
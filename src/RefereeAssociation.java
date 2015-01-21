import java.util.ArrayList;

/**
 * Team Foxtrot - JavaBall Referees
 * Aggregation class for all available and registered referees
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
 * @version 1.1
 * @since   21-01-2015
 */

public class RefereeAssociation {
	/** maximum number of listed referees */
	private final static int MAX_REFEREES = 12;
	
	/** list of all registered referees */
	private final ArrayList<Referee> listedReferees;

	/** Default constructor */
	public RefereeAssociation() {
		listedReferees = new ArrayList<Referee>();
	}
	
	/**
	 * Adds a referee to the list of registered referees and makes him/her
	 * available for match allocation.
	 * @param ref the Referee Object to be registered
	 * @return indication of successful referee registration
	 */
	public boolean add(Referee ref) {
		// test if another referee may be added
		if (listedReferees.size() <= MAX_REFEREES)
			return listedReferees.add(ref);
		else
			return false;
	}
	
	/**
	 * Removes a referee from the lost of registered referees
	 * @param ref the Referee Object to be removed from the list
	 * @return indication of successful referee removal
	 */
	public boolean removeReferee(Referee ref) {   
		return listedReferees.remove(ref); // FIXME trimToSize()?
	}

	public ArrayList<Referee> getReferees(int n) {
		// test for qualification level or region code
		if (1 <= n && n <= 4) {
			
		}
		else if (n == Match.NORTH || n == Match.CENTRE || n == Match.SOUTH)
		
		return null;
		// TODO either level or region
	}
	
	public ArrayList<Referee> getReferees(String name) {
		return null;
		// TODO either fname or sname
	}
	
	public Referee getReferee(String fname, String sname) {
		return null; // TODO
	}
	
	public Referee getReferee(String ID) {
		return null; // TODO
	}

	public Referee[] getSuitableReferees(String location, String type)
	{
		String matchLocation = location;
		String matchType = type; // TODO
		
		return null;
	}
}
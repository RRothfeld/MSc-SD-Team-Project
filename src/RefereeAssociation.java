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
	
	
	/**
	 * 
	 * @param ID
	 * @return
	 */
	public Referee getReferee(String id) {
		// find referee with desired ID
		for (Referee ref : listedReferees) {
			if (ref.getID().equals(id))
				return ref;
		}
		
		// if ID was not found
		return null;
	}

	/**
	 * 
	 * @param location
	 * @param type
	 * @return
	 */
	public Referee[] getSuitableReferees(String location, String type) {
		// TODO change to enum
		int n = 0, m = 0;
		
		// if senior dann min lvl 2
		// 
		
		Referee [] suitableReferees = {listedReferees.get(n), listedReferees.get(m)};
		return suitableReferees;
	}

	/**
	 * 
	 * @param level
	 * @return
	 */
	public ArrayList<Referee> getReferees(int level) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<Referee>();
		
		// add all referees with the desired qualification level
		for (Referee ref : listedReferees) {
			if (ref.getQualificationLevel() == level)
				filteredReferees.add(ref);
		}
		
		return filteredReferees;
	}
	
	// TODO get refs for LOCATIONs
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<Referee> getReferees(String name) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<Referee>();
		
		// add all referees with either the desired fore- or surname
		for (Referee ref : listedReferees) {
			if (ref.getForename().equals(name) || ref.getSurname().equals(name))
				filteredReferees.add(ref);
		}
		
		return filteredReferees;
	}
	
	/**
	 * 
	 * @param fname
	 * @param sname
	 * @return
	 */
	public ArrayList<Referee> getReferees(String fname, String sname) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<Referee>();
		
		// add all referees with either the desired fore- and surname
		for (Referee ref : listedReferees) {
			if (ref.getForename().equals(fname) && ref.getSurname().equals(sname))
				filteredReferees.add(ref);
		}
		
		return filteredReferees;
	}
}
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
 * @version 1.2
 * @since   25-01-2015
 */

public class RefereeList {
	/** maximum number of listed referees */
	private final static int MAX_REFEREES = 12;
	
	/** list of all registered referees */
	private final ArrayList<Referee> listedReferees;

	/** Default constructor */
	public RefereeList() {
		listedReferees = new ArrayList<>();
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
	public boolean remove(Referee ref) {   
		return listedReferees.remove(ref);
	}
	
	/**
	 * Returns the number of currently listed referees
	 * @return the number of listed referees (int)
	 */
	public int size() {
		return listedReferees.size();
	}
	
	/**
	 * Returns the referee given a specific ID
	 * @param id the ID of the desired referee
	 * @return the referee if found (otherwise null)
	 */
	public Referee getReferee(String id) {
		// find referee with desired ID
		for (Referee ref : listedReferees) {
			if (ref.getID().equals(id))
				return ref;
		}
		return null;
	}
	
	/**
	 * Returns a list of referees given a qualification level
	 * @param level the desired qualification
	 * @return ArrayList of matching referees
	 */
	public ArrayList<Referee> getReferees(Match.Level level) {
		// if it is a junior match, all referees are applicable
		if (level.equals(Match.Level.JUNIOR))
			return listedReferees;
		
		// otherwise filter non-applicable referees out
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<>();
		
		// add all referees with minimum qualification level two
		for (Referee ref : listedReferees) {
			if (ref.getQualificationLevel() > 1)
				filteredReferees.add(ref);
		}
		// return filtered list
		return filteredReferees;
	}
	
	/**
	 * Returns a list of referees with matching forename/surname
	 * @param name the forename or surname of a referee
	 * @return ArrayList of matching referees
	 */
	public ArrayList<Referee> getReferees(String name) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<>();

		// add all referees with either the desired fore- or surname
		for (Referee ref : listedReferees) {
			if (ref.getForename().equals(name) || ref.getSurname().equals(name))
				filteredReferees.add(ref);
		}

		return filteredReferees;
	}
	
	/**
	 * Returns a list of referees with matching full name
	 * @param fname the forename of a referee
	 * @param sname the surname of a referee
	 * @return ArrayList with matching referees
	 */
	public ArrayList<Referee> getReferees(String fname, String sname) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<>();

		// add all referees with either the desired fore- and surname
		for (Referee ref : listedReferees) {
			if (ref.getForename().equals(fname)
					&& ref.getSurname().equals(sname))
				filteredReferees.add(ref);
		}

		return filteredReferees;
	}

	/**
	 * Returns a list of referees with matching home location
	 * @param home the desired home location
	 * @return ArrayList with matching referees
	 */
	public ArrayList<Referee> getRefereesByHome(
			JavaBallController.Location home) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<>();

		// add all referees with the home location
                // Can you do .equals() on different enums?
		for (Referee ref : listedReferees) {
			if (ref.getHomeLocation().equals(home))
				filteredReferees.add(ref);
		}

		return filteredReferees;
	}
	
	/**
	 * Returns a list of referees with matching travel preferences
	 * @param travel the desired travel location
	 * @return ArrayList with matching referees
	 */
	public ArrayList<Referee> getRefereesByTravel(
			JavaBallController.Location travel) {
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<>();

		// add all referees with the desired travel preference
		for (Referee ref : listedReferees) {
			if (ref.getTravelLocation(travel))
				filteredReferees.add(ref);
		}

		return filteredReferees;
	}
	
    
	/**
	 * Returns an array of the two most suitable referees for any given match
	 * @param match the match which requires two referees
	 * @return an array of the two most suitable referees
	 */
	public Referee[] getSuitableReferees(Match match) {
		// DUMMY RETURN
		int n = 0, m = 1;
		Referee[] suitableReferees= new Referee[2];
		return suitableReferees;


		
		// list to hold referees after filtering
		// if senior dann min lvl 2
		// ArrayList<Referee> filteredReferees = getReferees(match.getLevel());

		
		// least number of allocations of all refs living in match area
		// after that: least # of allocs of all refs who live adjacent there
		// after that: least # of allocs of all refs who trave there
		// if 2 are equally good, which one does not matter
		// TODO
		
	}

	/**
	 * Method to test Referee and RefList class methods. TODO DELETE
	 */
	public void debug() {
		int counter = 1;
		for (Referee ref : listedReferees) {
			String forename = ref.getForename();
			String info = String.format("Info for Referee %d. %s:\tID %s", counter,
					forename, ref.getID());
			System.err.println(info);
			counter++;

		}

		System.err.println("List size: " + listedReferees.size());
		System.err.println("Adding Referee");

		String uniqueID = "AL1";
		String forename = "Andrew";
		String surname = "Lowson";

		String homeLocation = "North";

		int alloc = 12;
		// convert travel locations to boolean
		String travel = "YYN";
		String qual = "IJB3";

		Referee referee = new Referee(uniqueID, forename, surname, qual, alloc,
				homeLocation, travel);
		listedReferees.add(referee);
		Referee testRef = listedReferees.get(6);

		String info = String.format("Info for Referee %d. %s", counter,
				testRef.getForename());
		System.err.println(info);

		System.err.println();
		System.err.println("List size: " + listedReferees.size());
		System.err.println("Removing Referee");
		listedReferees.remove(0);
		System.err.println("List size now: " + listedReferees.size());
	}
}

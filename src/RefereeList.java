import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Iterator;

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

public class RefereeList implements Iterable<Referee> {
	/** The name of the referee input file */
	public static final String INPUT_FILE = "RefereesIn.txt";
	
	/** maximum number of listed referees */
	private final static int MAX_REFEREES = 12;
	
	/** list of all registered referees */
	private final ArrayList<Referee> listedReferees;

	/** Default constructor */
	public RefereeList() {
		this.listedReferees = new ArrayList<>();
		initFromFile(listedReferees);
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
	 * @return ArrayList of matching referees TODO DELETE THIS METHOD?!
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
	public ArrayList<Referee> getSuitableReferees(Match match) {
		// TODO
		ArrayList<Referee> suitableReferees = getRefereesByTravel(match.getArea());
		
		// least important: least # of allocs of all refs who trave there
		// if senior dann min lvl 2 and go to the area
		if (match.getLevel().equals(Match.Level.SENIOR)) {
			for (Referee ref : suitableReferees) {
				if (ref.getQualificationLevel() < 2) // TODO Magic number?
					suitableReferees.remove(ref);
			}
		}
		
		
		// FIXME TEST; DELETE!!!!!!
		for (Referee ref : suitableReferees) {
			System.err.println(ref.getID() + " " + ref.getAllocations() + " "
					+ ref.getHomeLocation());
			System.err.println("------------");
		}
		
		int adjacentReferees = 0, localReferees = 0;
		
		// after that: least # of allocs of all refs who live adjacent there
		// skip first one!
		if (!match.getArea().equals(JavaBallController.Location.CENTRAL)) {
			for (Referee ref : suitableReferees) {
				if (ref.getHomeLocation().equals(
						JavaBallController.Location.CENTRAL)) {
					suitableReferees.set(0, ref);
					adjacentReferees++;
				}
			}
		}
		
		// least number of allocations of all refs living in match area
		for (Referee ref : suitableReferees) {
			if (ref.getHomeLocation().equals(match.getArea())) {
				suitableReferees.set(0, ref);
				localReferees++;
			}
		}

		// sort by allocations ascending for local referees (home in match area)
		Collections.sort(suitableReferees.subList(0, localReferees - 1));

		// sort by allocations ascending for adjacent referees only
		Collections.sort(suitableReferees.subList(localReferees,
				adjacentReferees - 1));

		// sort by allocations ascending for non-adjacent, non-local referees
		Collections.sort(suitableReferees.subList(adjacentReferees,
				suitableReferees.size() - 1));

		// @Override sorting to sort according to the number of allocations
		Collections.sort(suitableReferees , new Comparator<Referee>() {
			public int compare(Referee ref1, Referee ref2) {
				int allocRef1 = ref1.getAllocations();
				int allocRef2 = ref2.getAllocations();
				
				if (allocRef1 < allocRef2)
					return -1;
				else if (allocRef1 == allocRef2)
					return 0;
				else
					return 1;
			}
		});
		
		// FIXME TEST; DELETE!!!!!!
		for (Referee ref : suitableReferees) {
			System.err.println(ref.getID() + " " + ref.getAllocations() + " "
					+ ref.getHomeLocation());
		}
		
		return suitableReferees;
	}
	
        /**
         *
         * @param index
         * @return
         */
        public Referee get(int index)
        {            
            return listedReferees.get(index);
        }
        
        public String createID(String fname, String sname)
        {
            char id  = fname.toUpperCase().charAt(0);
            char id2 = sname.toUpperCase().charAt(0);
            String refID = String.format("%c%c", id, id2);
            int idNumber = 1;
            for (Referee ref : listedReferees)
            {
                if (ref.getID().substring(0, 2).equals(refID))
                {
                    idNumber++;
                }
            }
            String refereeID = String.format("%s%d",refID,idNumber);
            return refereeID;
        }
        
	/**
	 * Reads in provided file and populates RefereeList
	 * @param refList the RefereeList to be populated
	 */
	private static void initFromFile(ArrayList<Referee> refList) {
            
            try {
                    // set scope of FileReader
                    FileReader refereeFile = null;
                    try {
                            // initialise FileReader with input file and initialise scanner
                            refereeFile = new FileReader(INPUT_FILE);
                            Scanner refScanner = new Scanner(refereeFile);

                            // read every line of input file and create referees
                            while (refScanner.hasNextLine()) {
                                    String newReferee = refScanner.nextLine();
                                    if (newReferee != null) {
                                            Referee referee = new Referee(newReferee);
                                            refList.add(referee);
                                    }
                            }
                            // close scanner after usage
                            refScanner.close();
                    } finally {
                            // close input file if it has been opened
                            if (refereeFile != null) {
                                    refereeFile.close();
                            }
                    }
            } catch (IOException e) {} // do nothing if file not found
        }
	@Override
	public Iterator<Referee> iterator() {
		return listedReferees.iterator();
	}
}
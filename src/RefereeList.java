import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * Aggregation class for all available and registered referees
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
 * @version 1.2
 * @since 11-02-2015
 */
public class RefereeList implements Iterable<Referee> {
	/** The name of the referee input file */
	private static final String INPUT_FILE = "RefereesIn.txt";
	
	/** maximum number of listed referees */
	public final static int MAX_REFEREES = 12;
	
	/** list of all registered referees */
	private final ArrayList<Referee> listedReferees;
        private boolean inputFileTooLarge;

	/** Default constructor */
	public RefereeList() {
            this.listedReferees = new ArrayList<Referee>();
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
	 * 
	 * @return
	 */
	public ArrayList<Referee> getReferees() {
		return listedReferees;
	}
	
	/**
	 * Returns the referee given a specific ID
	 * @param id the ID of the desired referee
	 * @return the referee if found (otherwise null)
	 */
	public Referee getReferee(String id) {
		// find referee with desired ID
		for (Referee ref : listedReferees) {
		    String refID = ref.getID().toLowerCase();
			if (refID.equals(id))
				return ref;
		}
		return null;
	}
	
	/**
	 * Linear search for a referee which has a matching first and last name
	 * @param fname the first name of the desired referee
	 * @param lname the last name of the desired referee
	 * @return the desired referee if existent
	 */
	public Referee getReferee(String fname, String lname) {
		// Add all referees with either the desired fore- and surname
		for (Referee ref : listedReferees) {
			if (ref.getFirstName().toLowerCase().equals(fname)
					&& ref.getLastName().toLowerCase().equals(lname))
				return ref;
		}
		return null;
	}
	
	/**
	 * Returns a list of referees given a qualification level
	 * @param level the desired qualification
	 * @return ArrayList of matching referees TODO DELETE THIS METHOD?!
	 */
	public ArrayList<Referee> getReferees(Level level) {
		// if it is a junior match, all referees are applicable
		if (level.equals(Level.JUNIOR))
			return listedReferees;
		
		// otherwise filter non-applicable referees out
		// list to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<Referee>();
		
		// add all referees with minimum qualification level two
		for (Referee ref : listedReferees) {
			if (ref.getQualificationLevel() > 1)
				filteredReferees.add(ref);
		}
		// return filtered list
		return filteredReferees;
	}

	/**
	 * Returns a list of referees with matching home location
         * @param location 
	 * @param home the desired home location
	 * @return ArrayList with matching referees
	 */
	public ArrayList<Referee> getReferees(Location location,
			boolean home) {
		// List to hold referees after filtering
		ArrayList<Referee> filteredReferees = new ArrayList<Referee>();

		// Check whether provided is asking for home or travel locations
		if (home) {
			// Add all referees with the home location
			for (Referee ref : listedReferees) {
				if (ref.getHomeLocation().equals(location))
					filteredReferees.add(ref);
			}
		} else {
			// Add all referees with the desired travel preference
			for (Referee ref : listedReferees) {
				if (ref.getTravelLocation(location))
					filteredReferees.add(ref);
			}
		}

		// Return ArrayList with referees having the desired properties
		return filteredReferees;
	}
    
	/**
	 * Returns an array of the two most suitable referees for any given match
	 * @param match the match which requires two referees
	 * @return an array of the two most suitable referees
	 */
	public ArrayList<Referee> getSuitableReferees(Match match) {
		// Get all referees which travel to the match area
		ArrayList<Referee> availableReferees = getReferees(match.getArea(),
				false);

		// Remove non-senior referees if the match requires senior qualification
		if (match.getLevel().equals(Level.SENIOR)) {
			seniorRefereesOnly(availableReferees);
		}
		
		// LOCAL
		ArrayList<Referee> localReferees = new ArrayList<Referee>();
		for (int i = 0; i < availableReferees.size();) {
			Referee ref = availableReferees.get(i);
			if (ref.getHomeLocation().equals(match.getArea())) {
				availableReferees.remove(i);
				localReferees.add(ref);
			} else {
				i++;
			}
		}

		// ADJACENT
		ArrayList<Referee> adjacentReferees = new ArrayList<Referee>();
		for (int i = 0; i < availableReferees.size();) {
			Referee ref = availableReferees.get(i);
			if ((match.getArea().equals(Location.CENTRAL) && !ref
					.getHomeLocation().equals(Location.CENTRAL))
					|| (!match.getArea().equals(Location.CENTRAL) && ref
					.getHomeLocation().equals(Location.CENTRAL))) {
				availableReferees.remove(i);
				adjacentReferees.add(ref);
			} else {
				i++;
			}
		}

		Comparator<Referee> byAllocations = new Comparator<Referee>() {
			@Override
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
		};
		
		// Sort by allocations ascending for local referees (home in match area)
		Collections.sort(localReferees, byAllocations);
		Collections.sort(adjacentReferees, byAllocations);
		Collections.sort(availableReferees, byAllocations);

		// COMBINE
		ArrayList<Referee> suitableReferees = new ArrayList<Referee>();
		suitableReferees.addAll(localReferees);
		suitableReferees.addAll(adjacentReferees);
		suitableReferees.addAll(availableReferees);
		
		return suitableReferees;
	}
	
	/**
	 * 
	 * @param referees
	 */
	private void seniorRefereesOnly(ArrayList<Referee> referees) {
		// Define unsuitable qualification level
		int unsuitableQualLevel = 1;

		// Remove unqualified referees
		for (int i = 0; i < referees.size();) {
			// Retrieve next referee
			Referee ref = referees.get(i);

			// Check the referee's qualification level for suitability
			if (ref.getQualificationLevel() == unsuitableQualLevel) {
				referees.remove(i);
			} else
				// Progress counter only if the current referee has not been
				// removed, otherwise referees will be skipped as the
				// ArrayList of suitable referees has been shortened
				i++;
		}
	}
	
	/**
	 *
	 * @param index
	 * @return
	 */
	public Referee get(int index) {
		return listedReferees.get(index);
	}
	
	/**
	 * Creates an ID for a new Referee
	 * @param fname - First Name of Referee
	 * @param sname - Surname of Referee 
	 * @return - UniqueID as 3 character String
	 */
	public String createID(String fname, String sname) {
		// Retrieve first characters of first and last name
		char id1 = fname.toUpperCase().charAt(0);
		char id2 = sname.toUpperCase().charAt(0);
		String refID = String.format("%c%c", id1, id2);
		
		// Add number to ID according to previous occurrences of same initials
		int idNumber = 1;
		for (Referee ref : listedReferees) {
			if (ref.getID().substring(0, 2).equals(refID)) {
				idNumber++;
			}
		}
		
		// Combine initials with number for referee ID
		String refereeID = String.format("%s%d", refID, idNumber);
		return refereeID;
	}
        
	/**
	 * Reads in provided file and populates RefereeList
	 * @param refList the RefereeList to be populated
	 */
	private void initFromFile(ArrayList<Referee> refList) {
		try {
			// Set scope of FileReader
			FileReader refereeFile = null;
			try {
				// Initialise FileReader with input file and initialise scanner
				refereeFile = new FileReader(INPUT_FILE);
				Scanner refScanner = new Scanner(refereeFile);
                                
				// Read every line of input file and create referees
                                int counter = 1;
				while (refScanner.hasNextLine()) {
					String newReferee = refScanner.nextLine();
					if (newReferee != null) {
                                            if (counter < MAX_REFEREES)
                                            {
                                                Referee referee = new Referee(newReferee);
                                                referee.setID(createID(referee.getFirstName(),
                                                        referee.getLastName()));
						refList.add(referee);
                                                counter++;
                                            }
                                            else {
                                                inputFileTooLarge = true;
                                                break;
                                            }
					}
				}
				// Close scanner after usage
				refScanner.close();
			} finally {
				// Close input file if it has been opened
				if (refereeFile != null) {
					refereeFile.close();
				}
			}
		} catch (IOException e) {
		} // Do nothing if file not found
	}
	
	/**
	 * Sorts the the referee list by ID
	 */
	public void sort() {
		Collections.sort(listedReferees);
	}

	/**
	 * 
	 */
	public int getMaxAllocation() {
		int max = 0;
		for (Referee ref : listedReferees)
			max = ref.getAllocations() > max ? ref.getAllocations() : max;
		return max;
	}
	
        public boolean getFileSize()
        {
            return inputFileTooLarge;
        }
	/**
	 * Returns an iterator of elements of type Referee
	 */
	@Override
	public Iterator<Referee> iterator() {
		return listedReferees.iterator();
	}
}
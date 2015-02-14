package javaball.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javaball.enums.Location;
import javaball.enums.MatchLevel;

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
 * @version 1.3
 * @since 14-02-2015
 */
public class RefereeList implements Iterable<Referee> {

	/** The name of the referee input file */
	private static final String INPUT_FILE = "RefereesIn.txt";

	/** Maximum number of listed referees */
	public final static int MAX_REFEREES = 12;

	/** List of all registered referees */
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
		// Test if another referee may be added
		if (listedReferees.size() < MAX_REFEREES)
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
	 * Returns a list of all registered referees
	 * @return an array list containing referees
	 */
	public ArrayList<Referee> getReferees() {
		return listedReferees;
	}

	/**
	 * Returns the referee given a specific ID
	 * @param the ID of the desired referee
	 * @return the referee if found (otherwise null)
	 */
	public Referee getReferee(String id) {
		// find referee with desired ID
		for (Referee ref : listedReferees) {
			// normalise string (lower case)
			String refID = ref.getID().toLowerCase();
			
			// if ID found, return the referee
			if (refID.equals(id))
				return ref;
		}
		
		// if not found return null
		return null;
	}

	/**
	 * Linear search for a referee which has a matching first and last name
	 * @param fname the first name of the desired referee
	 * @param lname the last name of the desired referee
	 * @return the desired referee if existent
	 */
	public Referee getReferee(String fname, String lname) {
		for (Referee ref : listedReferees) {
			// return found referee if both first and last name match
			if (ref.getFirstName().toLowerCase().equals(fname)
					&& ref.getLastName().toLowerCase().equals(lname))
				return ref;
		}
		
		// if not found return null
		return null;
	}

	/**
	 * Returns a list of referees with matching home location
	 * @param location the desired location
	 * @param home whether or not the location describes home or travel pref.
	 * @return ArrayList with matching referees
	 */
	public ArrayList<Referee> getReferees(Location location, boolean home) {
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
		if (match.getLevel().equals(MatchLevel.SENIOR))
			seniorRefereesOnly(availableReferees);

		// Create temporary list of local referees
		ArrayList<Referee> localReferees = new ArrayList<Referee>();
		// Linear search over all remaining referees
		for (int i = 0; i < availableReferees.size();) {
			// Retrieve referee at position i
			Referee ref = availableReferees.get(i);
			// Check if he/she is local to the match
			if (ref.getHomeLocation().equals(match.getArea())) {
				// If he/she is local:
				availableReferees.remove(i); // Remove from available list
				localReferees.add(ref); // Add to local list
			} else
				// If referee is not local increment counter (only increment if
				// referee is not local as if a referee is local he/she is
				// removed from the list of available referees and the next
				// referee is now at the position the removed referee was in)
				i++;
		}

		// Create temporary list of adjacent referees
		ArrayList<Referee> adjacentReferees = new ArrayList<Referee>();
		// Linear search over all remaining referees
		for (int i = 0; i < availableReferees.size();) {
			// Retrieve referee at position i
			Referee ref = availableReferees.get(i);
			// Check if he/she is adjacent to the match (i.e. if the match is
			// held in CENTRAL, then all referees not living in CENTRAL are
			// adjacent; if the match is not held in CENTRAL then all referees
			// from CENTRAL are adjacent)
			if ((match.getArea().equals(Location.CENTRAL) && !ref
					.getHomeLocation().equals(Location.CENTRAL))
					|| (!match.getArea().equals(Location.CENTRAL) && ref
							.getHomeLocation().equals(Location.CENTRAL))) {
				// If he/she is adjacent:
				availableReferees.remove(i); // Remove from available list
				adjacentReferees.add(ref); // Add to adjacent list
			} else {
				// If referee is not adjacent increment counter (see above)
				i++;
			}
		}

		// Create new comparator to be able to compare referees by their total
		// number of allocations
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

		// Sort the local, adjacent, and remaining referees ascending by allocs.
		Collections.sort(localReferees, byAllocations);
		Collections.sort(adjacentReferees, byAllocations);
		Collections.sort(availableReferees, byAllocations);

		// Concatenate the local, adjacent, and remaining referees into a new
		// list which is now sorted by suitability for a given match
		ArrayList<Referee> suitableReferees = new ArrayList<Referee>();
		suitableReferees.addAll(localReferees);
		suitableReferees.addAll(adjacentReferees);
		suitableReferees.addAll(availableReferees);

		// Return the sorted list
		return suitableReferees;
	}

	/**
	 * Removes all non-Senior-qualified referees from a list of referees
	 * @param referees the ArrayList to be filtered for Senior-qual. referees
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
	 * Returns the element at the specified position in this list.
	 * @param index index of the element to return
	 * @return the element at the specified position
	 */
	public Referee get(int index) {
		return listedReferees.get(index);
	}

	/**
	 * Creates an ID for a new Referee
	 * @param fname first name of referee
	 * @param sname surname of referee
	 * @return unique ID as 3 character String
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
		// Initialise FileReader with input file and initialise scanner
		try (FileReader refereeFile = new FileReader(INPUT_FILE)) {
			try (Scanner refScanner = new Scanner(refereeFile)) {

				// Read every line of input file and create referees
				while (refScanner.hasNextLine()) {
					String newReferee = refScanner.nextLine();
					Referee referee = new Referee(newReferee);
					refList.add(referee);
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					INPUT_FILE + "could not be read.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Sorts the referee list in ascending order by the referee's ID
	 */
	public void sort() {
		Collections.sort(listedReferees);
	}

	/**
	 * Returns the maximum number of allocations for a registered referee.
	 * @return decimal value of max allocations
	 */
	public int getMaxAllocation() {
		// Temporary storage of the current maximum allocations of all referees
		int max = 0;
		
		// Iterate through all referees to find the maximum allocations
		for (Referee ref : listedReferees)
			max = ref.getAllocations() > max ? ref.getAllocations() : max;
			
		// Return the maximum allocations found
		return max;
	}

	/**
	 * Returns an iterator of elements of type Referee, thus allows for
	 * generalised for-loops
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Referee> iterator() {
		return listedReferees.iterator();
	}
}
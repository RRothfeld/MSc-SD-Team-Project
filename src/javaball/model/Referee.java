package javaball.model;

import java.util.ArrayList;

import javaball.enums.Location;
import javaball.enums.RefQualification;

/**
 * Team Foxtrot JavaBall
 * Referee Allocation System
 * <p>
 * Class for storing referee related information
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
 * @version 1.3 - final
 * @since 14-02-2015
 */
public class Referee implements Comparable<Referee> {

	/** Referees unique identifier */
    private String uniqueID;
    	
    /** Referees first and last name */
	private String firstName;
	private String lastName;
	
	/** Qualification type for the referee (such as NJB or IJB) */
	private RefQualification qualification;
	
	/** Associated level of the qualification */
	private int qualificationLevel;

	/** The area where the referee is based */
	private Location homeLocation;

	/** Travel preferences associated associated with the referee */
	private boolean visitNorth;
	private boolean visitCentral;
	private boolean visitSouth;

	/** List of matches by week that a referee has been allocated to */
	private ArrayList<Match> allocatedMatchesList;

	/** Number of previous allocations (before the current season) */
	private int preSeasonAllocations;

	/**
	 * Constructor to be used if ReadLine has not been split before
	 * instantiating new Referee object. String is split, verified for validity
	 * and values assigned.
	 * @param fileLine  Long line with all Referee Information
	 */
	public Referee(String fileLine) {
		String[] refereeDetails = fileLine.split("[ ]+");

		// Check to make sure line split properly and has adequate items
		if (refereeDetails != null && refereeDetails.length == 7) {
			this.uniqueID = refereeDetails[0];
			this.firstName = refereeDetails[1];
			this.lastName = refereeDetails[2];

			// Set the home location of the referee based on the info provided
			switch (refereeDetails[5]) {
			case "North":
				setHomeLocation(Location.NORTH);
				break;
			case "Central":
				setHomeLocation(Location.CENTRAL);
				break;
			case "South":
				setHomeLocation(Location.SOUTH);
				break;
			}
			
			preSeasonAllocations = Integer.parseInt(refereeDetails[4]);
			allocatedMatchesList = new ArrayList<>();

			// convert travel locations to boolean
			setTravelLocation(refereeDetails[6]);
			setQualifications(refereeDetails[3]);
		}
	}

	/**
	 * Constructor to be used if passed either all referee information from GUI
	 * when adding new referee, or if ReadLine splits details up before creating
	 * a Referee object.
	 * @param id - Referee ID, format XY1
	 * @param firstName - Referee first name
	 * @param lastName - Referee last name
	 * @param qual - Qualification type and level
	 * @param allocCount - amount of matches allocated to referee
	 * @param homeLocality - home area for referee
	 * @param travel - string Y/N for areas Referee will travel too
	 */
	public Referee(String id, String firstName, String lastName, String qual,
			int allocCount, String homeLocality, String travel) {
	    // Call constructor with a line of strings
		this(id + " " + firstName + " " + lastName + " " + qual + " "
				+ allocCount + " " + homeLocality + " " + travel);
	}

	/**
	 * Return first name of the specified referee
	 * @return first name of this referee
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Return last name of specified referee
	 * @return last name of this referee
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Return qualification type of the specified referee.
	 * @return three character qualification type
	 */
	public RefQualification getQualification() {
		return qualification;
	}

	/**
	 * Return qualification level of the the specified referee.
	 * @return decimal representing the qualification level
	 */
	public int getQualificationLevel() {
		return this.qualificationLevel;
	}

	/**
	 * Return the unique identifier for the specified referee
	 * @return id for this referee
	 */
	public String getID() {
		return this.uniqueID;
	}

	/**
	 * Return the home area of the specified referee
	 * @return Home Location as Location enum
	 */
	public Location getHomeLocation() {
		return homeLocation;
	}

	/**
	 * Return total amount of allocations (sum of current and previous)
	 * @return number of allocations
	 */
	public int getAllocations() {
		return allocatedMatchesList.size() + preSeasonAllocations;
	}

	/**
	 * Adds match to referee allocations list
	 * @param match specified match object
	 */
	public void addMatch(Match match) {
		allocatedMatchesList.add(match);
	}

	/**
	 * Returns the preferred travel locations for the 
	 * specified referee as a three character string.
	 * @return three character string of the travel preferences
	 */
	public String getTravelLocations() {
		// Get travel locations for referee and combine to three-letter code
		String n = visitNorth ? "Y" : "N";
		String c = visitCentral ? "Y" : "N";
		String s = visitSouth ? "Y" : "N";
		String travel = n + c + s;
		
		// Return three-letter code
		return travel;
	}

	/**
	 * Returns a boolean value referring to whether the referee will travel to
	 * that area or not.
	 * @param location location required.
	 * @return - boolean for particular location
	 */
	public boolean getTravelLocation(Location location) {
		if (location.equals(Location.NORTH))
			return this.visitNorth;
		else if (location.equals(Location.CENTRAL))
			return this.visitCentral;
		else
			return this.visitSouth;
	}
	
	/**
	 * Method to convert Referee Area Options to boolean
	 * @param travel the three character String eg. 'YYY'
	 */
	public void setTravelLocation(String travel) {
		// Temporarily store boolean values for each travel option
		boolean[] visits = new boolean[travel.length()];
		
		// Set the travel preference to true or false
		for (int i = 0; i < travel.length(); i++)
		    visits[i] = travel.charAt(i) == 'Y';

		// Store boolean values in instance variables
		this.visitNorth = visits[0];
		this.visitCentral = visits[1];
		this.visitSouth = visits[2];
	}
	
	/**
	 * Method to set Qualification for Referee
	 * @param qualifications value of qualification type and/or level
	 */
	private void setQualifications(String qualifications) {
		// Set scope of string to store qualification type
		String qualType;

		// Check if input is the maximum length for a qualification (i.e. is the
		// qualification type AND level)
		if (qualifications.length() < RefQualification.MAXIMUM)
			// Input only contains type
			qualType = qualifications;
		else {
			// Input contains type and level
			qualType = qualifications.substring(0, 3);
			Integer qualLevel = Integer.parseInt(qualifications.substring(3));
			setQualificationLevel(qualLevel);
		}
		
		// Set qualification type accordingly
		switch (qualType.trim().toUpperCase()) {
		case "NJB":
			this.qualification = RefQualification.NJB;
			break;
		case "IJB":
			this.qualification = RefQualification.IJB;
			break;
		}
	}
	
	/**
	 * Set the three letter qualification type for the specified referee
	 * @param qualification
	 */
	public void setQualification(RefQualification qualification) {
		this.qualification = qualification;
	}

	/**
	 * Integer value for qualification level independent of other info.
	 * @param qualification full qualification String eg. IJB1
	 */
	public void setQualificationLevel(int qualification) {
		this.qualificationLevel = qualification;
	}
	
	/**
	 * Sets the home location of a referee to a given location
	 * @param location
	 */
	public void setHomeLocation(Location location) {
		this.homeLocation = location;
	}
	
	/**
	 * Compares two referees. The comparison is based on the Unicode value of
	 * each character of the referee's unique identifier
	 * @param ref
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Referee ref) {
		return this.getID().compareTo(ref.getID());
	}

	/**
	 * Returns a string representation of the details of the specified referee.
	 * The format is as follows DG1 Dave Gray NJB2 3 Central NYY, where 'DG1' is
	 * the unique identifier, 'Dave' is the first and 'Gray' the last names,
	 * 'NJB2' is the three letter qualification followed by the level, 'Central'
	 * is the home location and NYY - travel prefs for North, Central, South
	 * respectively and where N is no Y is yes.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String report = String.format("%s %s %s %s%d %d %s %s\n", this.getID(),
				this.getFirstName(), this.getLastName(),
				this.getQualification(), this.getQualificationLevel(),
				this.getAllocations(), this.getHomeLocation(),
				this.getTravelLocations());
		return report;
	}
}
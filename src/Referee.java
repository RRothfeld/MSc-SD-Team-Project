import java.util.ArrayList;

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
 * @version 1.2
 * @since 11-02-2015
 */
public class Referee implements Comparable<Referee> {

	// Basic information about Referee from the input file/user.
	private String firstName;
	private String lastName;
	private String uniqueID;

	private String travelLocations;
	private RefQualification qualification;
	private int qualificationLevel;

	private Location homeLocation;

	/**
	 * boolean values that represent whether or not Referee will visit this
	 * location
	 */
	private boolean visitNorth;
	private boolean visitCentre;
	private boolean visitSouth;

	// List of matches by matchID referee has been allocated to
	private ArrayList<Match> allocatedMatchesList;

	private int index; // Integer used by TableModel in Controller
	private int preSeasonAllocations;

	// /**
	// * Default Constructor.
	// */
	// public Referee() {
	// this.uniqueID = "";
	// this.firstName = "";
	// this.lastName = "";
	//
	// this.qualification = "";
	//
	// this.preSeasonAllocations = 0;
	// this.allocatedMatchesList = new ArrayList<Match>();
	// }

	/**
	 * Constructor to be used if ReadLine has not been split before
	 * instantiating new Referee object. String is split, verified for validity
	 * and values assigned.
	 * 
	 * @param fileLine
	 *            - Long line with all Referee Information
	 */
	public Referee(String fileLine) {
		String[] refereeDetails = fileLine.split(" ");

		// Check to make sure line split properly and has adequate items
		if (refereeDetails != null && refereeDetails.length == 7) {
			this.uniqueID = refereeDetails[0];
			this.firstName = refereeDetails[1];
			this.lastName = refereeDetails[2];

			// TODO I want to use this one liner
			//setHomeLocation(Location.valueOf(refereeDetails[5]).name());
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
			setTravelLocations(refereeDetails[6]);
			setQualifications(refereeDetails[3]);
		}
	}

	/**
	 * Constructor to be used if passed either all referee information from GUI
	 * when adding new ref, or if ReadLine splits details up before creating a
	 * Referee object.
	 * 
	 * @param id
	 *            - Referee ID, format XY1
	 * @param firstName
	 *            - Referee first name
	 * @param lastName
	 *            - Referee last name
	 * @param qual
	 *            - Qualification type and level
	 * @param allocCount
	 *            - amount of matches allocated to referee
	 * @param homeLocality
	 *            - home area for referee
	 * @param travel
	 *            - string Y/N for areas Referee will travel too
	 */
	public Referee(String id, String firstName, String lastName, String qual,
			int allocCount, String homeLocality, String travel) {
		this(id + " " + firstName + " " + lastName + " " + qual + " "
				+ allocCount + " " + homeLocality + " " + travel);
	}

	/**
	 * Method to return first name of Referee
	 * 
	 * @return - first name of this Referee
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Method to return last name of Referee
	 * 
	 * @return - last name of this Referee
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Method to return Qualification Type
	 * 
	 * @return - Three character qualification type
	 */
	public RefQualification getQualification() {
		return qualification;
	}

	// /**
	// *
	// * @return - Integer value for this Referee used by TableModel
	// */
	// public int getIndex()
	// {
	// return this.index;
	// }
	/**
	 * Integer value for qualification level independent of other info.
	 * 
	 * @return
	 */
	public int getQualificationLevel() {
		return this.qualificationLevel;
	}

	/**
	 * Pass back refereeID
	 * 
	 * @return - ID For This Referee
	 */
	public String getID() {
		return this.uniqueID;
	}

	/**
	 * 
	 * @return Home Location as Location enum
	 */
	public Location getHomeLocation() {
		return homeLocation;
	}

	/**
	 * Method to return amount of allocations
	 * 
	 * @return - number of allocations
	 */
	public int getAllocations() {
		int totalAllocations = allocatedMatchesList.size()
				+ preSeasonAllocations;
		return totalAllocations;
	}

	/**
	 * Method to add Match to Referee Allocations List
	 * 
	 * @param match
	 */
	public void addMatch(Match match) {
		allocatedMatchesList.add(match);
	}

	/**
	 * Return preferences related to travel.
	 * 
	 * @return
	 */
	public String getTravelLocations() {
		return travelLocations;
	}

	/**
	 * Returns a boolean value referring to whether the referee will travel to
	 * that area or not.
	 * 
	 * @param location
	 *            - location required.
	 * @return - boolean for particular location
	 */
	public boolean getTravelLocation(Location location) {
		if (location.equals(Location.NORTH)) {
			return this.visitNorth;
		} else if (location.equals(Location.CENTRAL)) {
			return this.visitCentre;
		} else {
			return this.visitSouth;
		}
	}

	// /** FIXME This method seems unused?
	// * Method to Change whether a Referee will travel to an Area
	// * @param location - the location to be flipped
	// */
	// private void flipTravel(JavaBallController.Location location) {
	// if (location.equals(JavaBallController.Location.NORTH)) {
	// this.visitNorth = !visitNorth;
	// } else if (location.equals(JavaBallController.Location.CENTRAL)) {
	// this.visitCentre = !visitCentre;
	// } else if (location.equals(JavaBallController.Location.SOUTH)) {
	// this.visitSouth = !visitSouth;
	// }
	// }
	
	/**
	 * Method to convert Referee Area Options to boolean
	 * @param travel the three character String eg. 'YYY'
	 */
	private void setTravelLocations(String travel) {

		travelLocations = travel;
		boolean[] visits = new boolean[travel.length()];

		for (int i = 0; i < travel.length(); i++) {
			visits[i] = travel.charAt(i) == 'Y';
		}

		this.visitNorth = visits[0];
		this.visitCentre = visits[1];
		this.visitSouth = visits[2];
	}
	
	/**
	 * Method to set Qualification for Referee
	 * @param qualifications String value of qualification type, qualification level or both
	 */
	private void setQualifications(String qualifications) {
		String refQual;
		if (qualifications.length() < RefQualification.MAXIMUM) {
			refQual = qualifications;
		} else {
			refQual = qualifications.substring(0, 3);
			setQualificationLevel(Integer.parseInt(qualifications.substring(3)));
		}
		
		switch (refQual.trim().toUpperCase()) {
			case "NJB": this.qualification = RefQualification.NJB; break;
			case "IJB": this.qualification = RefQualification.IJB; break;
		}
	}
	
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
	 * Method to update all three locations in one step Referee can change
	 * travel preferences. Takes in one string, same format as initial
	 * preference input.
	 * @param travel is the three character string eg. "YYN"
	 */
	public void setTravelLocation(String travel) {
		// Does the same as setTravelAreas
		setTravelLocations(travel);
	}
	
	/**
	 * 
	 * @param location
	 */
	public void setHomeLocation(Location location) {
		this.homeLocation = location;
	}
	
	/**
	 * 
	 * @param ID
	 */
	public void setID(String ID) {
		this.uniqueID = ID;
	}
	
	/**
	 *
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Compares two referees by ID.
	 * 
	 * @param ref
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Referee ref) {
		return this.getID().compareTo(ref.getID());
	}

	/**
	 * Returns referee details.
	 * 
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
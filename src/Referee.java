import java.util.ArrayList;

/**
 * Team Foxtrot - JavaBall Referees Aggregation class for all available and
 * registered referees
 * <p>
 * University of Glasgow MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author Miroslav Pashov, 1005139P
 * @author Andrew Lowson, 0800685L
 * @author Marco Cook, 2152599C
 * @author Raoul Rothfeld, 2164502R
 * 
 * @version 1.1
 * @since 21-01-2015
 */

public class Referee implements Comparable<Referee> {

    // Basic information about Referee from the input file/user.
    private String firstName;
    private String lastName;
    private String uniqueID;

    private int qualificationLevel;
    private String qualification, travelLocations;

    private JavaBallController.Location homeLocation;

    /** TODO */
    public enum Qualifications {
        NJB, IJB
    }

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
    protected static final int MAX_QUAL_LENGTH = 4;

    /**
     * Default Constructor.
     */
    public Referee() {
	this.uniqueID = "";
	this.firstName = "";
	this.lastName = "";

	this.qualification = "";

	this.preSeasonAllocations = 0;
	this.allocatedMatchesList = new ArrayList();
    }

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

	    setHomeLocation(refereeDetails[5]);

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
     * @param id - Referee ID, format XY1
     * @param firstName - Referee first name
     * @param lastName - Referee last name
     * @param qual - Qualification type and level
     * @param allocCount - amount of matches allocated to referee
     * @param homeLocality - home area for referee
     * @param travel - string Y/N for areas Referee will travel too
     */
    public Referee(String id, String firstName, String lastName, String qual,
	    int allocCount, String homeLocality, String travel) 
    {
        this(id + " " + firstName + " " + lastName + " " + qual + " "
		+ allocCount + " " + homeLocality + " " + travel);
    }

    /**
     * Method to convert Referee Area Options to boolean     
     * @param travel - the three character String eg. 'YYY'
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
     * Method to set Qualification for Referee
     * 
     * @param qualifications - String value of qualification type, 
     *                         qualification level or both
     */
    private void setQualifications(String qualifications) {
	if (qualifications.length() < MAX_QUAL_LENGTH) {
	    this.qualification = qualifications;
	} else {
	    this.qualification = qualifications.substring(0, 3);
	    setQualificationLevel(qualifications);
	}
    }

    /**
     * Integer value for qualification level independent of other info.
     * 
     * @param qualification
     *            - full qualification String eg. IJB1
     */
    public void setQualificationLevel(String qualification) {
	String lastChar = qualification.substring(qualification.length() - 1);
	this.qualificationLevel = Integer.parseInt(lastChar);
    }

    /**
     * Integer value for qualification level independent of other info.
     * 
     * @param qualification
     *            - full qualification String eg. IJB1
     */
    public void setQualificationLevel(int qualification) {

	this.qualificationLevel = qualification;
    }

    /**
     * Method to return Qualification Type
     * @return - Three character qualification type
     */
    public String getQualifications() {
	return qualification;
    }
    
    //Methods used by TableModel to place referees
    /**
     *
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public void setID(String ID)
    {
        this.uniqueID = ID;
    }
    /**
     * 
     * @return - Integer value for this Referee used by TableModel
     */
    public int getIndex()
    {
        return this.index;
    }
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
     * @return - ID For This Referee
     */
    public String getID() {
	return this.uniqueID;
    }

    /**
     * 
     * @param location
     */
    private void setHomeLocation(String location) {
	this.homeLocation = JavaBallController.Location.valueOf(location
		.toUpperCase());
    }

    /**
     * 
     * @return Home Location as Location enum
     */
    public JavaBallController.Location getHomeLocation() {
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
     * This will take the three characters 'YYN'
     * 
     * @param travel
     */
    public void setTravelAreas(String travel) {
	setTravelLocations(travel);
    }

    /**
     * Return preferences related to travel.
     * @return
     */
    public String getTravelLocations() {
	return travelLocations;
    }

    /**
     * Returns a boolean value referring to whether the referee will travel to
     * that area or not.
     * 
     * @param location - location required.
     * @return - boolean for particular location
     */
    public boolean getTravelLocation(JavaBallController.Location location) {
	if (location.equals(JavaBallController.Location.NORTH)) {
	    return this.visitNorth;
	} else if (location.equals(JavaBallController.Location.CENTRAL)) {
	    return this.visitCentre;
	} else {
	    return this.visitSouth;
	}
    }

//    /** FIXME This method seems unused?
//     * Method to Change whether a Referee will travel to an Area
//     * @param location - the location to be flipped
//     */
//    private void flipTravel(JavaBallController.Location location) {
//	if (location.equals(JavaBallController.Location.NORTH)) {
//	    this.visitNorth = !visitNorth;
//	} else if (location.equals(JavaBallController.Location.CENTRAL)) {
//	    this.visitCentre = !visitCentre;
//	} else if (location.equals(JavaBallController.Location.SOUTH)) {
//	    this.visitSouth = !visitSouth;
//	}
//    }

    /**
     * Method to update all three locations in one step Referee can change
     * travel preferences. Takes in one string, same format as initial
     * preference input.
     * 
     * @param travel
     *            is the three character string eg. "YYN"
     */
    public void updateTravelLocations(String travel) {
	// Does the same as setTravelAreas
	setTravelLocations(travel);
    }

    /**
     * Compares two referees by ID.
     * @param ref
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Referee ref) {
	return this.getID().compareTo(ref.getID());
    }
    
    /**
     * Returns referee details.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String id = "ID: " + uniqueID;
	String name = "Name: " + firstName +" "+ lastName;
	String qual = "Qualification: " + qualification;
	String alloc = "Allocations: " + getAllocations();
	String home = "Home: " + getHomeLocation();
	String travel = "Travel: " + getTravelLocations();
	
	String report = String.format("%s%n%s%n%s%n%s%n%s%n%s%n", id, name, qual, alloc, home, travel);
	return report;
    }
}
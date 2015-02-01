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
    private String forename;
    private String surname;
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

    private int preSeasonAllocations;
    private final int MAX_QUAL_LENGTH = 4;

    /**
     * Default Constructor.
     */
    public Referee() {
	this.uniqueID = "";
	this.forename = "";
	this.surname = "";

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
	    this.forename = refereeDetails[1];
	    this.surname = refereeDetails[2];

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
     * @param id
     *            - Referee ID, format XY1
     * @param forename
     *            - Referee forename
     * @param surname
     *            - Referee surname
     * @param qual
     * @param allocCount
     *            - amount of matches allocated to referee
     * @param homeLocality
     *            - home area for referee
     * @param travel
     *            - string Y/N for areas Referee will travel too
     */
    public Referee(String id, String forename, String surname, String qual,
	    int allocCount, String homeLocality, String travel) {
	this(id + " " + forename + " " + surname + " " + qual + " "
		+ allocCount + " " + homeLocality + " " + travel);
    }

    /**
     * Method to convert Referee Area Options to boolean
     * 
     * @param travel
     *            - the three character String eg. 'YYY'
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
     * Method to return Forename of Referee
     * 
     * @return - forename
     */
    public String getForename() {
	return this.forename;
    }

    /**
     * Method to return Surname of Referee
     * 
     * @return - Surname
     */
    public String getSurname() {
	return this.surname;
    }

    /**
     * Method to set Qualification
     * 
     * @param qualifications
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
     * 
     * @return
     */
    public String getQualifications() {
	return qualification;
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
     * 
     * @return
     */
    public String getID() {
	return uniqueID;
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
     * @return
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
    public boolean getTravelLocation(JavaBallController.Location location) {
	if (location.equals(JavaBallController.Location.NORTH)) {
	    return this.visitNorth;
	} else if (location.equals(JavaBallController.Location.CENTRAL)) {
	    return this.visitCentre;
	} else {
	    return this.visitSouth;
	}
    }

    /**
     * Method to Change whether a Referee will travel to an Area
     * 
     * @param location
     *            the location to be flipped
     */
    private void flipTravel(JavaBallController.Location location) {
	if (location.equals(JavaBallController.Location.NORTH)) {
	    this.visitNorth = !visitNorth;
	} else if (location.equals(JavaBallController.Location.CENTRAL)) {
	    this.visitCentre = !visitCentre;
	} else if (location.equals(JavaBallController.Location.SOUTH)) {
	    this.visitSouth = !visitSouth;
	}
    }

    /** Returns the referee details */
    public String[] report() {

	String ID = this.uniqueID;
	String forename = this.forename;
	String surname = this.surname;
	String qualification = this.qualification;
	String allocations = Integer.toString(getAllocations());
	String homeLocation = getHomeLocation().toString();
	String travelLocations = getTravelLocations();

	String[] details = { ID, forename, surname, qualification, allocations,
		travelLocations };

	return details;
    }

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

    @Override
    public int compareTo(Referee ref) {
	// this has not been tested but StackOverflow said it would work
	// Will test in a separate programme.
	return this.getID().compareTo(ref.getID());
    }
}

import java.util.ArrayList;

public class Referee implements Comparable<Referee> {


	// TODO Andrew (Helped by Marco)
    /**
     * @author Andrew Lowson & Marco Cook
     * This early version of the Referee Class tries to account for 
     * as many possible implementations of other classes as possible.
     * 
     * There will be many methods here that end up becoming redundant
     * but we wanted to try and accommodate different thoughts initially
     * before deciding on final implementation.
     */
    
    //-------------------------------------------------------//
    
    //Basic information about Referee from the input file/user.
    private String forename;
    private String surname;
    private String uniqueID;
    private String qualification; //maybe as int (without NJB/IJB)? -rr 
    private String homeLocation;
    private int    qualificationLevel;
    
    /* 
     * boolean values that represent whether or not Referee 
     * will visit this location
     */
    private boolean visitNorth;
    private boolean visitCentre;
    private boolean visitSouth;
    
    // List of matches by matchID referee has been allocated to
    private ArrayList<Match> allocatedMatchesList;
    
    private int preSeasonAllocations;
    
    //-------------------------------------------------------//
    
    /**
     * Default Constructor.
     */
    public Referee()
    {
        this.uniqueID = "";
        this.forename = "";
        this.surname  = "";        
        
        this.qualification  = "";
        this.homeLocation   = "";
        this.preSeasonAllocations = 0;
    }
    /**
     * Constructor to be used if passed either all referee information from 
     * GUI when adding new ref, or if ReadLine splits details up before
     * creating a Referee object. 
     * 
     * @param id - Referee ID, format XY1
     * @param forename - Referee forename
     * @param surname - Referee surname
     * @param qual 
     * @param allocCount - amount of matches allocated to referee
     * @param homeLocality - home area for referee
     * @param travel - string Y/N for areas Referee will travel too
     */
    public Referee(String id, String forename, String surname, 
            String qual, String homeLocality, int allocCount, String travel) 
    {
        //TODO SPLIT QUALIFICATION INTO TYPE AND LEVEL
        //Convert travel parameter to boolean values for area.
        
        setTravelLocations(travel); 
             
        this.uniqueID = id;
        this.forename = forename;
        this.surname  = surname;
        
        this.qualification = qual;
        this.homeLocation  = homeLocality;
        
        this.preSeasonAllocations = allocCount;
        
        allocatedMatchesList = new ArrayList();                   
    }
    
    //-------------------------------------------------------//
    
    /**
     * Constructor to be used if ReadLine has not been split before 
     * instantiating new Referee object.
     * String is split, verified for validity and values assigned.
     * @param fileLine - Long line with all Referee Information
     */
    public Referee(String fileLine)
    {
        String [] refereeDetails = fileLine.split(" ");
        
        // TODO call the next constructor
        // Check to make sure line split properly and has adequate items
        if (refereeDetails != null && refereeDetails.length == 7)
        {
            this.uniqueID = refereeDetails[0];
            this.forename = refereeDetails[1];
            this.surname  = refereeDetails[2];
            
            allocatedMatchesList = new ArrayList(52);

            this.qualification = refereeDetails[3];
            this.homeLocation  = refereeDetails[5];
           
            allocatedMatchesList  = new ArrayList();

            //convert travel locations to boolean
            setTravelLocations(refereeDetails[6]);
        }
    }
    
    //-------------------------------------------------------//
    
    /**
     * TOSDO CHANGE TO GET RID OF ARRAY
     * Method to convert Referee Area Options to boolean
     * @param travel - the three character String eg. 'YYY'
     */   
    private void setTravelLocations(String travel)
    {
        // TODO using three seperate variables.
        // remove any possible whitespace
        travel = travel.trim();
        
        boolean[] visitArea = new boolean[3];
        // if we go with boolean array.
        for (int i=0;i<travel.length();i++)
        {
            visitArea[i] = travel.charAt(i) == 'Y';
        }   
        
        // Other implementation
        this.visitNorth  = visitArea[0];
        this.visitCentre = visitArea[1];
        this.visitSouth  = visitArea[2];
        
    }
    
    //-------------------------------------------------------//
    /**
     * Return forename
     * @return
     */
        
    public String getForename()
    {
        return forename;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public String getSurname()
    {
        return surname;
    }
    
    
    //-------------------------------------------------------//
    /**
     * qualifications
     * need to be able to take 3 character string, 4 character and int.
     * 
     */
    
    /**
     * 
     * @param qualifications
     */
    public void setQualifications(String qualifications)
    {
        
        //if (qualifications.length()<)
        this.qualification = qualifications;
    }
    
    //-------------------------------------------------------//
    
    
    /**
     * Integer value for qualification level independent of other info.
     * @param qualification - full qualification String eg. IJB1
     */
    public void setQualificationLevel(String qualification)
    {
        int length = qualification.length()-1;
        
        this.qualificationLevel = (int) qualification.charAt(length);
    }
    
    //-------------------------------------------------------//
    
    /**
     * Integer value for qualification level independent of other info.
     * @param qualification - full qualification String eg. IJB1
     */
    public void setQualificationLevel(int qualification)
    {
        this.qualificationLevel = qualification;
    }
    
    //-------------------------------------------------------//
    
    /**
     * Pass back refereeID
     * @return
     */
    public String getID()
    {
        return uniqueID;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */ 
    public String getQualifications()
    {
        return qualification;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param location
     */
    public void setHomeLocation(String location)
    {
        this.homeLocation = location;
    }
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public String getHomeLocation()
    {
        return homeLocation;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public int getAllocations()
    {
        int total = allocatedMatchesList.size() + preSeasonAllocations;
        return total;
    }
    
    //-------------------------------------------------------//
    
    /**
     * 
     * @param match
     */
    public void addMatch(Match match)
    {
        allocatedMatchesList.add(match);
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param travel
     */  
    public void setTravelAreas(String travel)
    {
        setTravelLocations(travel);
    }
    
    //-------------------------------------------------------//
    
    /**
     * Returns a boolean value referring to whether the referee will travel
     * to that area or not.
     * @param location - location required.
     * @return 
     */  
    public boolean getTravelLocation(String location)
    {
        if (location.equals("North"))
        {
            return visitNorth;
        } 
        else if (location.equals("Centre")) 
        {
            return visitCentre;
        } 
        else 
        {
            return visitSouth;
        }
    }
    
    //-------------------------------------------------------//
    
    // TODO add method to flip boolean value of 
    
    /**
     * Method to update all three locations in one step
     * Referee can change travel preferences. 
     * Takes in one string, same format as initial preference input.
     * @param travel - is the three character string eg. "YYN"
     * 
     */        
    public void updateTravelLocations(String travel)
    {
        // swaps boolean for String of area
        
        //remove any possible whitespace
        travel = travel.trim();
        
        // if we go with boolean array.
        for (int i=0;i<travel.length();i++)
        {
            //visitArea[i] = travel.charAt(i)=='Y';
        }   
    }
    
    //-------------------------------------------------------//

    @Override
    public int compareTo(Referee ref) 
    {
        // this has not been tested but StackOverflow said it would work
        // Will test in a separate programme.
        return this.getID().compareTo(ref.getID());
    }

    //-------------------------------------------------------//
}

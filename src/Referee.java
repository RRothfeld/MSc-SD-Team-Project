
import java.io.FileReader;

/**
 * Team Foxtrot
 * JavaBall Referees
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
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
 * @version 1.0
 * @since   14-01-2015
 */

//<<<<<<< Updated upstream
public class Referee {
	// TODO Andrew (Helped by Marco)
    //-------------------------------------------------------//
    
    //Basic information about Referee from the input file/user.
    private String forename;
    private String surname;
    private String fullname; // Decide later: delete -rr
    private String uniqueID;
    private String qualification; //maybe as int (without NJB/IJB)? -rr
    private String homeLocality;
    
    /* 
     *  boolean values that represent whether or not Referee 
     * will visit this location
     */
    private boolean visitNorth;
    private boolean visitCentre;
    private boolean visitSouth;
    
    private boolean[] visitArea;
    
    private int matchesRefereed; // allocatedMatches -rr
    
    //-------------------------------------------------------//
    
    /**
     * Default Constructor.
     */
    public Referee()
    {
    
    }
    /**
     *
     * @param id
     * @param forename
     * @param surname
     * @param qualification
     * @param matchesRefereed
     * @param homeLocality
     * @param travel
     */
    public Referee(String id, String forename, String surname, String qualification,
                    String homeLocality, int matchesRefereed, String travel) 
    {
        String name = forename + " " + surname;
        setTravelLocations(travel);
             
        this.uniqueID        = id;
        this.forename        = forename;
        this.surname         = surname;
        this.fullname        = name;
        this.qualification   = qualification;
        this.homeLocality    = homeLocality;
        this.matchesRefereed = matchesRefereed;
                   
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param fileLine
     */
        
    public Referee(String fileLine)
    {
        String [] refereeDetails = fileLine.split(" ");
        
        if (refereeDetails != null && refereeDetails.length == 7)
        {
            this.uniqueID        = refereeDetails[0];
            this.forename        = refereeDetails[1];
            this.surname         = refereeDetails[2];
            this.qualification   = refereeDetails[3];
            this.matchesRefereed = Integer.parseInt(refereeDetails[4]);
            this.homeLocality    = refereeDetails[5];
            
            setTravelLocations(refereeDetails[6]);
        }
    }
    
    private void setTravelLocations(String travel)
    {
        //remove any possible whitespace
        travel = travel.trim();
        
        // if we go with boolean array.
        for (int i=0;i<travel.length();i++)
        {
            visitArea[i] = travel.charAt(i)=='Y';
        }   
        
        // Other implimentation
        visitNorth  = visitArea[0];
        visitCentre = visitArea[1];
        visitSouth  = visitArea[2];
        
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param ID
     */
        
    public void setID(String ID)
    {
        this.uniqueID = ID;
    }
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public String getID()
    {
        return uniqueID;
    }
    
    //-------------------------------------------------------//

    /**
     *
     * @param fullname
     */
        public void setName(String fullname)
    {
        String [] names = fullname.split(" ");
        
        forename = names[0];
        surname  = names[1];
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param forename
     * @param surname
     */
        
    public void setFullName(String forename, String surname)
    {
        this.fullname = forename+" "+surname;   
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public String getFullName()
    {
        return fullname;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param forename
     */
    public void setForename(String forename)
    {
        this.forename = forename;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
        
    public String getForename()
    {
        return forename;
    }
    
    //-------------------------------------------------------//
    /**
     *
     * @param surname
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
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
     *
     * @param qualifications
     */
        public void setQualifications(String qualifications)
    {
        this.qualification = qualifications;
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
        this.homeLocality = location;
    }
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public String getHomeLocation()
    {
        return homeLocality;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param matches
     */
    public void setMatchesRefereed(int matches)
    {
        matchesRefereed = matches;
    }
    
//-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public int getMatchesRefereed()
    {
        return matchesRefereed;
    }
    
    //-------------------------------------------------------//
    
    /**
     * 
     */
    public void increaseMatchesRefereed()
    {
        matchesRefereed++;
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
     *
     * @return
     */
        
    public boolean[] getTravelLocations()
    {
        return visitArea;
    }
    //-------------------------------------------------------//
    
    /**
     *
     * @param index
     * @return
     */
    public boolean getTravelLocationAtIndex(int index)
    {
        return visitArea[index];
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param location
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
    
    /**
     * Method to change a particular travel 
     * @param index
     * @param willTravel
     */
        
    public void changeLocationAtIndex(int index, boolean willTravel)
    {
        visitArea[index] = willTravel;
    }
    
    //-------------------------------------------------------//
    
    /**
     * Method to change all three locations in one step
     * @param travel - is the three character string eg. "YYN"
     * 
     */        
    public void updateTravelLocations(String travel)
    {
        //remove any possible whitespace
        travel = travel.trim();
        
        // if we go with boolean array.
        for (int i=0;i<travel.length();i++)
        {
            visitArea[i] = travel.charAt(i)=='Y';
        }   
    }
    
}

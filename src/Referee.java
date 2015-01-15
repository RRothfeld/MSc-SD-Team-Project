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
    private String uniqueID;
    private String qualification;
    private String homeLocality;
    
    /* 
     *  boolean values that represent whether or not Referee 
     * will visit this location
     */
    private boolean visitNorth;
    private boolean visitCentre;
    private boolean visitSouth;
    
    private boolean[] visitArea;
    
    // char[] to store Y/N 3 times, one for each locality.
    private char[] travelLocations;
    
    private int matchesRefereed;
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param id
     * @param name
     * @param qual
     * @param home
     * @param travel
     */
    public Referee(String id, String name, String qual,
                    String home, String travel) 
    {
        setName(name);
        setTravelLocations(travel);
        
             uniqueID = id;
        qualification = qual;
        homeLocality  = home;
        
    }
    
    //-------------------------------------------------------//
    
    /**
     * Default Constructor.
     */
    public Referee()
    {
    
    }
    
    //-------------------------------------------------------//
    
    private void setTravelLocations(String travel)
    {
        //remove any possible whitespace
        travel = travel.trim();
        
        // If we go with char array
        travelLocations = travel.toCharArray();
        
        // if we go with boolean array.
        for (int i=0;i<travel.length();i++)
        {
            visitArea[i] = travel.charAt(i)=='Y';
        }   
    }
    
    //-------------------------------------------------------//
    
    private void setName(String fullname)
    {
        String [] names = fullname.split(" ");
        
        forename = names[0];
        surname  = names[1];
        
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @return
     */
    public String getFullName()
    {
        String fullname = forename + " " + surname;
        
        return fullname;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param fn
     */
    public void setForename(String fn)
    {
        forename = fn;
    }
    
    //-------------------------------------------------------//
    
    /**
     *
     * @param fn
     */
    public void setSurname(String fn)
    {
        forename = fn;
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
    
}

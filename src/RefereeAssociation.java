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

public class RefereeAssociation {
//<<<<<<< Updated upstream
	// TODO Marco
//=======
    private final int MAXREFEREES = 12;
    private final Referee[] RefereeAssociation;
    
    private int activeReferees;
    
    public RefereeAssociation()
    {
        RefereeAssociation = new Referee[MAXREFEREES];
        activeReferees = 0;
    }
    
    public void addReferee(Referee referee)
    {
        if (referee!=null)
        {
            RefereeAssociation[activeReferees] = referee;
            activeReferees++;
        }
        
    }
    
    public void removeReferee(String refereeID)
    {   
        for (int i =0; i<MAXREFEREES;i++ )
        {
            if (RefereeAssociation[i].getID().equals(refereeID))
            {
                RefereeAssociation[i] = null;
            }
        }    
    }
    
    public void removeRefereeAtIndex(int index)
    {
        RefereeAssociation[index] = null;
    }
    
    public Referee getRefereeAtIndex(int index)
    {
        return RefereeAssociation[index];
    }
    
    public Referee[] getSuitableReferees(String location, String type)
    {
        String matchLocation = location;
        String matchType = type;
        
        
        
        Referee[] suitableReferees = new Referee[2];
        
        //This requires thought
        //Get referees with top two qualifications
        //Compare Match area with referees areas
        return null;
    }
    
    /**
     * Method to create the ID for a new referee based on their initials
     * and the sequence number 1. If the initials already exist, they are 
     * allocated the next number in the sequence. 
     * @param forename - the forename of the new referee
     * @param surname - the surname of the new referee
     * @return
     */
    public String createNewID(String forename, String surname) {
    	
    	String refereeForename = forename;
    	String refereeSurname = surname;
    	
    	//Get first characters (initials) of the referee's 
    	//forename and surname
    	char f = refereeForename.charAt(0);
		char s = refereeSurname.charAt(0);
		
		StringBuilder initials = new StringBuilder();
		//Convert characters to strings and add to StringBuilder
		initials.append(Character.toString(f) + Character.toString(s));
		
		
    	return null;
    }
    
//>>>>>>> Stashed changes
}
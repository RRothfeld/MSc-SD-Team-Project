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
        
        Referee[] suitableReferees = new Referee[2];
        
        //This requires thought
        return null;
    }
    
//>>>>>>> Stashed changes
}
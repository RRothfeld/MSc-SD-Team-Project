import javax.swing.WindowConstants;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Team Foxtrot - JavaBall Referees
 * The controller class
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
 * @version 1.1
 * @since   01-02-2015
 */
public class JavaBallController {
	
    private final Season season;
    private final RefereeList refList;
    private ChartFrame chart;

    private final String REFEREE_FILE = "RefereesOut.txt";
    private final String MATCH_FILE   = "MatchAllocs.txt";
    private final int TABLE_FIELDS = 7;
	
    /** TODO */
    public enum Location {
    	NORTH, CENTRAL, SOUTH
    }
	
    /**
     *
     * @param season
     * @param refList
     */
    public JavaBallController(Season season, RefereeList refList) {
		this.season = season;
		this.refList = refList;
	}

	/**
	 * 
	 * @param week
	 * @param level
	 * @param location
	 */
	public boolean allocateReferees(int week, Match.Level level,
			Location location) {
		// Create new match without referees
		Match match = new Match(week, level, location);

		// Check if match ID is already in use
		if (season.getMatch(week) != null) {
			//  Return indication of unsuccessful referee allocations
			return false;
		} else {
			// Retrieve all suitable Referees for that match
			ArrayList<Referee> availableReferees = refList
					.getSuitableReferees(match);

			// Select the two most suitable referees and pass them to the match
			Referee[] suitableReferees = { availableReferees.get(0),
					availableReferees.get(1) };
			match.setReferees(suitableReferees);

			// Add the fully filled in match to the current season
			season.addMatch(match);
			
			// Return indication of successful referee allocation
			return true;
		}
	}

    /**
     * 
     */
    public void execChart() {
            chart = new ChartFrame(refList);
            chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            chart.setVisible(true);
    }

    /**
     * Method to edit fields of Referee
     * @param id - ID of referee to edit
     * @param info - Info being edited.
     */
    public void editReferee(String id, String info) {
        Referee referee = refList.getReferee(id);
    }

    /**
     * Method to return a Referee to be displayed in Ref Window from Search
     * @param input - User provided String referring to the Referee 
     * @return Referee required or null if not found
     */
    public Referee execSearch(String input) {

        // Assuming full name...
        for (Referee ref : refList)
        {
            if ((ref.getForename()+" "+ref.getSurname()).equals(input))
            {
                // Always returns first Referee with that name
                return ref;
            } 
        }
        return null;
    }

    /**
     * 
     */
    public void execSaveExit() {
        writeOutputFile();
        System.exit(0);		
    }
        
    /**
     *
     * @param id
     * @return
     */
    public Referee getReferee(String id)
        {
            return refList.getReferee(id);
        }
        
    /**
     *
     * @param fname
     * @param sname
     * @param qual
     * @param allocations
     * @param home
     * @param travel
     */
    public void addReferee(String fname, String sname, String qual, 
                int allocations, Location home, String travel) {
            
        String qualification = qual;
        String homeLocal;
        if (home == Location.NORTH)
        {
            homeLocal = "North";
        } else if (home == Location.CENTRAL) {
            homeLocal = "Central";
        } else {
            homeLocal = "South";
        }
        refList.add(new Referee(refList.createID(fname, sname), fname, 
                sname, qualification, allocations, homeLocal, travel));
    }  
        
    /**
     *
     * @param id
     */
    public void execRemoveReferee(String id)
        {
            refList.remove(refList.getReferee(id));
        }
        
    /**
     *
     * @param id
     */
    public void editReferee(String id) 
        {
            // TODO
            // (b/c passed object via getReferee can directly edit data)
        }
        
        //openChartFrame() --> returns void
        
        //addMatch(int week, Match.Level level, Controller.Location area) --> returns void
        
    /**
     *
     */
    public void updateRefereeList() {
        orderByID();
    }
        
    /**
     *
     * @return
     */
    public String[][] execTable()
    {
        orderByID();
        String[] refDetails = new String[TABLE_FIELDS];
        String[][] table = new String[refList.size()][TABLE_FIELDS];
        
        for (int row = 0; row < refList.size(); row++)
        {
            Referee referee = refList.get(row);
            
            refDetails[0] = referee.getID();
            refDetails[1] = referee.getForename();
            refDetails[2] = referee.getSurname();
            refDetails[3] = referee.getQualifications();
            refDetails[4] = Integer.toString(referee.getAllocations());
            refDetails[5] = referee.getHomeLocation().toString();
            refDetails[6] = referee.getTravelLocations(); 
                
            System.arraycopy(refDetails, 0, table[row], 0, TABLE_FIELDS);
        }
        return table;
    }

    /**
     *
     * @return
     */
    public ArrayList<Referee> orderByID() 
    {
        ArrayList<Referee> sorted = new ArrayList();
        for (Referee ref : refList)
        {
            sorted.add(ref);
        }
        Collections.sort(sorted);
        return sorted;
    }

	/**
	 * Write report Files
	 */
	private void writeOutputFile() {
		try {
			FileWriter matchFile;
			try (FileWriter refereeFile = new FileWriter(REFEREE_FILE)) {

				matchFile = new FileWriter(MATCH_FILE);
				String[] referees = new String[refList.size()];
				// Throws null pointer
				String[] matches = new String[season.getNumMatches()];
				int refCounter = 0;
				updateRefereeList();
				for (Referee ref : refList) {
					String details = String.format("%s %s %s %s%d %d %s %s\n",
							ref.getID(), ref.getForename(), ref.getSurname(),
							ref.getQualifications(),
							ref.getQualificationLevel(), ref.getAllocations(),
							ref.getHomeLocation(), ref.getTravelLocations());
					referees[refCounter] = details;
					refCounter++;
				}
				int counter = 0;
				for (Match match : season) {
					matches[counter] = (match.matchReport());
				}
				for (String s : referees) {
					refereeFile.write(s);
				}
				for (String s : matches) {
					matchFile.write(s + "\n");
				}
			}
			matchFile.close();
		} catch (IOException ex) {
			// TODO
		}
	}
}
import javax.swing.WindowConstants;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
 * @version 1.0
 * @since   21-01-2015
 */

public class JavaBallController {
	private final Season season;
	private final RefereeList refList;
	private ChartFrame chart;
        
        private final String REFEREEFILE = "RefereesOut.txt";
        private final String MATCHFILE   = "MatchAllocs.txt";
	
	/** TODO */
    public enum Location {
	NORTH, CENTRAL, SOUTH
    }
	
	public JavaBallController(Season season, RefereeList refList) {
		this.season = season;
		this.refList = refList;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void execAdd(String s) {
		
	}

	/**
	 * 
	 * @param s
	 */
	public void execAllocate(String s) {
		
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
	 * 
	 * @param s
	 */
	public void execSearch(String s) {
		
	}
	
	/**
	 * 
	 */
	public void execSaveExit() {
            writeOutputFile();
            
            System.exit(0);
		
	}
        
        public Referee getReferee(String id)
        {
            Referee referee = refList.getReferee(id);
            return referee;
        }
        
        
        public void addReferee(String fname, String sname, String qual, 
                int level, Location home, String travel) {
            
            String qualification = qual+level;
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
                    sname, qualification, 0, homeLocal, travel));
        }  
        
        public void removeReferee(String id)
        {
            refList.remove(refList.getReferee(id));
        }
        
        public void editReferee(String id) 
        {
            // TODO
            // (b/c passed object via getReferee can directly edit data)
        }
        
        //openChartFrame() --> returns void
        
        //addMatch(int week, Match.Level level, Controller.Location area) --> returns void
        
        public void updateRefereeList() {
            // TODO
            // --> returns sorted (by ID) RefereeList Object
        }
        /**
         * Write report Files
         */
        private void writeOutputFile()
        {
            try {
                FileWriter refereeFile = new FileWriter(REFEREEFILE);
                FileWriter matchFile   = new FileWriter(MATCHFILE);
                
                String[] referees = new String[refList.size()];
                String[] matches  = new String[season.getNumMatches()];
                int refCounter = 0;
                
                updateRefereeList();
                            
                for (Referee ref : refList)
                {
                    String details = String.format("%s %s %s %s %d %s %s\n", 
                            ref.getID(), ref.getForename(), ref.getSurname(),
                            ref.getQualifications(), ref.getAllocations(), 
                            ref.getHomeLocation(), ref.getTravelLocations());
                    referees[refCounter] = details;    
                    refCounter++;
                }
                
                int counter = 0;
                for (Match match : season)
                {
                    matches[counter] = (match.matchReport());
                }
                
                for (String s : referees)
                {
                    refereeFile.write(s+"\n");
                }
                for (String s : matches)
                {
                    matchFile.write(s+"\n");
                }
                
            } catch (IOException ex) {
                
            }
            
        }
        
}
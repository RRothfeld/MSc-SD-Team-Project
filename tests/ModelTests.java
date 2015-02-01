import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import JavaBallController.Location;


public class ModelTests {
    
    private Season season;
    private Match match;
    private RefereeList listedReferees;
    
    @Before
    public void setUp() {
	season = new Season();
	match = new Match();
	System.out.println("@Before - setUp");
    }
    
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }

    @Test
    public void tesCreatingASeason() {
	match.setWeek(32);
	season.addMatch(match);
	System.out.println("@Test - testEmptyCollection");
    }
    
    @Test
    public void testAssertTrue() {
      org.junit.Assert.assertTrue("failure - should be true", true);
    }
    
    @Test
    public void testReferees() {

	    System.err.println("List size: " + listedReferees.size());
	    System.err.println("Adding Referee");

	    String uniqueID = "AL1";
	    String forename = "Andrew";
	    String surname = "Lowson";

	    String homeLocation = "North";

	    int alloc = 12;
	    // convert travel locations to boolean
	    String travel = "YYN";
	    String qual = "IJB3";

	    Referee referee = new Referee(uniqueID, forename, surname, qual,
		    alloc, homeLocation, travel);
	    listedReferees.add(referee);
	    //Referee testRef = listedReferees.get(6);

	 //   String info = String.format("Info for Referee %d. %s", counter,
	//	    testRef.getForename());
	//    System.err.println(info);

	    System.err.println();
	    System.err.println("List size: " + listedReferees.size());
	    System.err.println("Removing Referee");
	    //listedReferees.remove(0);
	    System.err.println("List size now: " + listedReferees.size());
    }

}

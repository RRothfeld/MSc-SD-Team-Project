import static org.junit.Assert.*;
import javaball.enums.*;
import javaball.model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTests {
    
    private Season season;
    private Match match;
    private RefereeList referees;
    
    @Before
    public void setUp() {
	season = new Season();
	
	int week;
	for (int i = 1; i < 10; i++) {
	    week = i;
	    for (MatchLevel lvl : MatchLevel.values()){
		for (Location loc : Location.values()){
		    match = new Match(week, lvl, loc);
		}
	    }
	    season.addMatch(match);
	}
	System.out.println("@Before - setUp");
    }
    
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }

    @Test
    public void tesMatchesInSeason() {
	System.out.println("@Test - testMatchesInSeason");
	for (Match mat : season) {
	    System.out.println("ID " + mat.getWeek() + " Level " + "Area " + mat.getArea());
	}
    }
    
    @Test
    public void testAssertTrue() {
      org.junit.Assert.assertTrue("failure - should be true", true);
    }

}

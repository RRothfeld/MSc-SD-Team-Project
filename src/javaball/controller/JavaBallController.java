package javaball.controller;

import javaball.gui.*;
import javaball.model.*;
import javaball.enums.*;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultRowSorter;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Team Foxtrot
 * JavaBall Referee Allocation System
 * <p>
 * The controller class
 * <p>
 * University of Glasgow
 * MSc/PGDip Information Technology/Software Development
 * Team Project 2014/15
 *
 * @author Miroslav Pashov (1005139p)
 * @author Andrew Lowson (0800685l)
 * @author Marco Cook (2152599c)
 * @author Raoul Rothfeld (2164502r)
 * 
 * @version 1.5 - final
 * @since 14-02-2015
 */
public class JavaBallController {
	/** References to model components */
	private final RefereeList refList;
    private final Season season;

    /** References to view components */
    private ChartFrame chart;
	private JavaBallGUI view;
    
	/** File names for text output files */
    private final String REFEREE_FILE = "RefereesOut.txt";
    private final String MATCH_FILE   = "MatchAllocs.txt";
    
	/** JTable as interface between referee storage and display */
    private JTable table;
	
    /**
     * Constructor for the JavaBallController storing references to the model
     * @param season the season to store matches
     * @param refList the referee list to store referees
     */
    public JavaBallController(Season season, RefereeList refList) {
    	this.refList = refList;
    	this.season = season;
    	
    	// Populate table with referees
    	this.table = new JTable(refereeTableModel());
    }

    /**
     * Opens a chart frame if there are referees to be shown
     */
	public void openChart() {
		// Check if referees are existent
		if (refList.size() > 0) {
			// Open chart frame
			chart = new ChartFrame(refList);
			chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			chart.setVisible(true);
		} else
			// Otherwise show error message
			JOptionPane.showMessageDialog(null, "There are no "
					+ "referees listed.");
	}
        
    /**
     * Retrieves a referee based on a search query for an ID or full name
     * @param search the user input for searching a referee
     * @return referee with matching ID or first and last name
     */
    public Referee getReferee(String search) {
    	// Split search input by the space character
    	String[] query = search.split("[ ]+");
    	
    	// Check how many splits have been created
    	if (query.length == 1) // One split: search by referee ID
            return refList.getReferee(query[0]);
    	else if (query.length == 2) // Two splits: search by referee full name
    		return refList.getReferee(query[0], query[1]);
    	else // More than two splits: invalid search
    		return null;
    }
    
    /**
     * Creates an unique ID given a full name
     * @param firstName the first name of a referee
     * @param lastName the last name of a referee
     * @return a unique ID for the given referee
     */
	public String createID(String firstName, String lastName) {
		return refList.createID(firstName, lastName);
	}
    
    /**
     * Checks if a referee travels to a given location
     * @param referee the referee in question for his/her travel preferences
     * @param location the location which is in question if he/she travels to
     * @return whether or not the referee travels to the given location
     */
    public boolean travelPreference(Referee referee, Location location) {
        return (referee.getTravelLocation(location));
    }

    /**
     * Adds a new referee
     * @param firstName the first name of the referee
     * @param lastName the last name of the referee
     * @param qualType the qualification type of the referee (NJB/IJB)
     * @param qualLevel the qualification level of the referee (1..4)
     * @param allocations the previous match allocations for the referee
     * @param home the home location of the referee
     * @param travel the travel preferences of the referee
     */
	public void addReferee(String firstName, String lastName,
			RefQualification qualType, int qualLevel, int allocations,
			Location home, String travel) {
		// Call upon the RefereeList's add method to add a referee with all
		// details given as parameters
		refList.add(new Referee(refList.createID(firstName, lastName),
				firstName, lastName, qualType.name() + qualLevel, allocations,
				home.toString(), travel));
	}

    /**
     * Edits details of an existent referee
     * @param referee the referee to be edited
     * @param qualType the qualification type of the referee (NJB/IJB)
     * @param qualLevel the qualification level of the referee (1..4)
     * @param home the home location of the referee
     * @param travel the travel preferences of the referee
     */
	public void editReferee(Referee referee, RefQualification qualType,
			int qualLevel, Location home, String travel) {
		// Call upon setter methods for changing the referee details
		referee.setQualification(qualType);
		referee.setQualificationLevel(qualLevel);
		referee.setHomeLocation(home);
		referee.setTravelLocation(travel);
	}

    /**
     * Removes a referee from the RefereeList
     * @param referee the referee to be removed
     */
	public void removeReferee(Referee referee) {
		refList.remove(referee);
	}

	/**
	 * Retrieve a list of suitable referees for a given match and allocate the
	 * two most suitable referees to said match; Return suitability list for
	 * display within the referee table
	 * @param week the week in which the match shall take place
	 * @param level the match's level (Junior or Senior)
	 * @param location the match's location
	 * @return a list of referees ordered by suitability for the given match
	 */
    public ArrayList<Referee> allocateReferees(int week, MatchLevel level,
                    Location location) {
        // Create new match without referees
        Match match = new Match(week, level, location);
        
        // Check if match ID is already in use
        if (season.getMatch(week) == null) {
            // Retrieve all suitable Referees for that match
			ArrayList<Referee> availableReferees = refList
					.getSuitableReferees(match);

            // Select the two most suitable referees and pass them to the match
			if (availableReferees.size() > 1) {
				// Retrieve the two most suitable referees
				Referee[] suitableReferees = {availableReferees.get(0),
						availableReferees.get(1)};
				
				// Allocate these referees to the match and add the fully
				// filled in match to the current season
				match.setReferees(suitableReferees);
				season.addMatch(match);

				// Pass reference of that match to the allocated referees
				availableReferees.get(0).addMatch(match);
				availableReferees.get(1).addMatch(match);
			}
            
            // Return the list of referees ordered by suitability
            return availableReferees;
        }
        else {
        	// Otherwise return error (week already in use)
            return null;
        }
    }
    
    /**
     * Sets the table header in the main GUI to a specified text
     * @param label the text to be displayed as the table header
     */
    public void setTableHeader(String label) {
		view.setTableHeader(label);
	}
	
    /**
     * Stores a reference to the view within the JavaBallController
     * @param view the main GUI
     */
    public void setView(JavaBallGUI view) {
		this.view = view;
	}
    
    /**
     * Writes output file and quits the programme
     */
    public void saveExit() {
    	// Exit programme if data has been successfully saved
        if (writeOutputFile())
        	System.exit(0);
    }
    
	/**
	 * Writes the match and referee output files
	 * @return whether or not the saving (output writing) was successful
	 */
	private boolean writeOutputFile() {
		// Try creating the two output files
		try (FileWriter matchFile = new FileWriter(MATCH_FILE);
				FileWriter refereeFile = new FileWriter(REFEREE_FILE)) {

			// Write headline for match output file
			matchFile.write("Week\tLevel\tArea\tReferee 1\tReferee 2\n");

			// Report on all matches in the match file
			for (Match match : season)
				matchFile.write(match.toString());

			// report on all referees in the referee file
			for (Referee ref : refList)
				refereeFile.write(ref.toString());

			// Return indication of success if no exceptions have been thrown
			return true;

		} catch (IOException ex) {
			// In case of an exception display error message
			JOptionPane.showMessageDialog(null,
					"Error writing to file.", "Error",
					JOptionPane.ERROR_MESSAGE);
			
			// Return indication that saving was not successful
			return false;
		}
	}
    
    /**
     * Returns the referee table
     * @return table with referees
     */
    public JTable getTable() {        
        return table;
    }
    
    /**
     * Creates and returns TableModel derived from referee list
     * @return the TableModel with the referees
     */
    public TableModel refereeTableModel() {
        return new RefereeTableModel(refList.getReferees());
    }
    
	/**
	 * Overwrites the current table with a table with referees suitable for a
	 * specific match and ordered by suitability
	 * @param allocatedReferees list of suitable referees ordered by ID
	 */
    public void allocatedTableData(ArrayList<Referee> allocatedReferees) {
        table.setModel(new RefereeTableModel(allocatedReferees)); 
    }
    
    /**
     * Sets the TableModel to the current full list of Referees and
     * order that list by ID.
     */
    public void updateTable() {
        table.setModel(refereeTableModel());
        
        DefaultTableCellRenderer leftRender = new DefaultTableCellRenderer();
        leftRender.setHorizontalAlignment( JLabel.LEFT );
        table.getColumnModel().getColumn(4).setCellRenderer(leftRender);
        
        DefaultRowSorter sorter = ((DefaultRowSorter)table.getRowSorter()); 
        ArrayList list = new ArrayList();
        list.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(list);
        sorter.sort();
        
        // reset the table's description
        view.resetTableHeader();
    }
    
    /**
     * Returns the current size of RefereeList 
     * (Raoul: Used to be used by rowCount in TableModel, no longer is)
     * 
     * @return Current size of RefereeList.
     */
    public int indexCounter()  {
        return refList.size();
    }
    
    /**
     * Nested Class to construct and a TableModel to populate the main display 
     * JTable based on a given ArrayList of Referees.
     * 
     * It defines columns and populates the rows with Referee data specific to 
     * the ArrayList passed to it, either Allocations or Full List
     */
    private static class RefereeTableModel extends AbstractTableModel {

		private final static int COLUMN_ID = 0;
		private final static int COLUMN_FNAME = 1;
		private final static int COLUMN_SNAME = 2;
		private final static int COLUMN_QUAL = 3;
		private final static int COLUMN_ALLOC = 4;
		private final static int COLUMN_HOME = 5;
		private final static int COLUMN_TRAVEL = 6;

        private final String[] columnNames;

        private final ArrayList<Referee> listReferees;

        public RefereeTableModel(ArrayList<Referee> referees) {
            this.listReferees = referees;
            this.columnNames  = new String[]{"ID", "First Name", "Last Name", 
                "Qualification", "Allocations", "Home", "Travel Areas"};     
        }

        @Override
        public int getRowCount() {
            return listReferees.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (listReferees.isEmpty())
            {
                return Object.class;
            }
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Referee referee = listReferees.get(rowIndex);
            Object returnValue = null;
            if (referee != null)
            {    
                switch (columnIndex) {
                case COLUMN_ID:
                    returnValue = referee.getID();
                    break;
                case COLUMN_FNAME:
                    returnValue = referee.getFirstName();
                    break;
                case COLUMN_SNAME:
                    returnValue = referee.getLastName();
                    break;
                case COLUMN_QUAL:
                    returnValue = referee.getQualification().name() + referee.getQualificationLevel();
                    break;
                case COLUMN_ALLOC:
                    returnValue = referee.getAllocations();
                    break;
                case COLUMN_HOME:
                    returnValue = referee.getHomeLocation();
                    break;
                case COLUMN_TRAVEL:
                    returnValue = referee.getTravelLocations();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column index");
                }     
            return returnValue;
            }
            return returnValue;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            Referee referee = listReferees.get(rowIndex);
            if (columnIndex == COLUMN_ID)
            {
                referee.setIndex((int) value);
            }
        }
    }
}
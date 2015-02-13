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
 * @version 1.2
 * @since 11-02-2015
 */
public class JavaBallController {
	
	private JavaBallGUI view;
    private final Season season;
    private final RefereeList refList;
    private ChartFrame chart;

    private final String REFEREE_FILE = "RefereesOut.txt";
    private final String MATCH_FILE   = "MatchAllocs.txt";
    
    private final int MAX_ID_SIZE = 4;
    	
    private TableModel tableData;
    
    private JTable table;
	
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
     */
	public void openChart() {
		if (refList.size() > 0) {
			chart = new ChartFrame(refList);
			chart.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			chart.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "There are no referees listed.");
		}
	}
    
    /**
     * REFEREE OPERATION SECTION.
     */
        
    /**
     * Retrieves a referee based on a search query for an ID or full name
     * @param search the user input for searching a referee
     * @return referee with matching ID or first and last name
     */
    public Referee getReferee(String search) {
    	// If search query resembles an ID, retrieve referee per ID
    	if (search.length() <= MAX_ID_SIZE)    	
            return refList.getReferee(search);
    	
    	// Else divide search query into first and last name, retrieve referee
    	// per first and last name
    	else {
    		String[] names = search.split("[ ]+");
    		
    		// If input does not resemble a first and last name, return null
    		if (names.length != 2)
    			return null;
    		
    		// Retrieve referee by first and last name
    		return refList.getReferee(names[0], names[1]);
    	}
    }
    
    public String createID(String firstName, String lastName)
    {
        return refList.createID(firstName,lastName);
    }
    
    public boolean inputTooLarge()
    {
        return refList.getFileSize();
    }
    /**
     * Method to return 
     * @param referee - The Referee We Want to Know about
     * @param location - The location we want to know if they travel to
     * @return - whether or not referee travels to Location
     */
    public boolean travelPreference(Referee referee, Location location) {
        return (referee.getTravelLocation(location));
    }

    /**
     * Add a new Referee
     * @param fname
     * @param sname
     * @param qualification
     * @param qualLevel
     * @param allocations
     * @param home
     * @param travel
     */
	public void addReferee(String fname, String sname,
			RefQualification qualification, int qualLevel, int allocations,
			Location home, String travel) {
		refList.add(new Referee(refList.createID(fname, sname), fname, sname,
				qualification.name() + qualLevel, allocations, home
						.toString(), travel));
	}

    /**
     * Method to edit fields of Referee
     * 
     * @param referee
     * @param travel
     * @param qualification
     * @param qualLevel
     * @param home
     */
	public void editReferee(Referee referee, RefQualification qualification,
			int qualLevel, Location home, String travel) {
		referee.setTravelLocation(travel);
		referee.setHomeLocation(home);
		referee.setQualification(qualification);
		referee.setQualificationLevel(qualLevel);
	}

    /**
     * Removes referee for RefereeList
     * @param ref - Referee to be removed
     */
    public void removeReferee(Referee ref) {
            refList.remove(ref);
            updateTable();
    }

    /**
     * 
     * @param week
     * @param level
     * @param location
     * @return 
     */
    public ArrayList<Referee> allocateReferees(int week, MatchLevel level,
                    Location location) {
        // Create new match without referees
        Match match = new Match(week, level, location);
        
        // Check if match ID is already in use
        if (season.getMatch(week) == null) {
            // Retrieve all suitable Referees for that match
            ArrayList<Referee> availableReferees = refList.getSuitableReferees(match);
            // Select the two most suitable referees and pass them to the match
            if (availableReferees.size() > 1)
            {
                Referee[] suitableReferees = { availableReferees.get(0),
                            availableReferees.get(1) };
                match.setReferees(suitableReferees);
                season.addMatch(match);
                // Add the fully filled in match to the current season
            
                availableReferees.get(0).addMatch(match);
                availableReferees.get(1).addMatch(match);
                // Return indication of successful referee allocation
            }
            
            return availableReferees;
        }
        else {
            return null;
        }
    }
    
	public void setTableHeader(String label) {
		view.setTableHeader(label);
	}
	
	public void setView(JavaBallGUI view) {
		this.view = view;
	}
        
        public boolean idChange() {
            return refList.idChange();
        }

    /**
     * PROGRAM CLOSE SECTION.
     */
    
    /**
     * Method to write output file and quit the program.
     */
    public void saveExit() {
    	// Exit programme if data has been successfully saved
        if (writeOutputFile())
        	System.exit(0);
    }
    
    /**
     * Write report and return if it worked or not.
     */
    private boolean writeOutputFile() {
        try (FileWriter matchFile = new FileWriter(MATCH_FILE)) {
            
            try (FileWriter refereeFile = new FileWriter(REFEREE_FILE)) {
                String[] referees = new String[refList.size()];
                String[] matches = new String[season.getNumMatches()];
                
                String matchHeader = "Week\tLevel\tArea\tReferee 1\tReferee 2\n";
                int refCounter = 0;
                for (Referee ref : refList) {
                    referees[refCounter] = ref.toString();
                    refCounter++;
                }
                int counter = 0;
                for (Match match : season) {
                        matches[counter] = (match.report());
                        counter++;
                }
                for (String s : referees) {
                        refereeFile.write(s);                                        
                }
                matchFile.write(matchHeader);
                for (String s : matches) {
                        matchFile.write(s + "\n");
                }
            }
            return true;
        } catch (IOException ex) {
	    JOptionPane.showMessageDialog(null,
		    "Error writing to file. Check your privileges", "",
		    JOptionPane.ERROR_MESSAGE);
                return false;
        } 
    }
    
    /**
     * TABLE SECTION.
     */
    
    /**
     * Method to create table.
     * Brought to this class to add update logic.
     * @return table with default TableModel
     */
    public JTable getTable()
    {
        table = new JTable(refereeTableModel());                
        return table;
    }
    
    /**
     * Method to create TableModel object for refList and return it to the GUI
     * @return - Full TableModel
     */
    public TableModel refereeTableModel()
    {
        tableData = new RefereeTableModel(refList.getReferees());
        return tableData;
    }
    
    /**
     *
     * @param allocatedReferees
     */
    public void allocatedTableData(ArrayList<Referee> allocatedReferees)
    {
        table.setModel(new RefereeTableModel(allocatedReferees)); 
    }
    
    /**
     * Method to set Table Model to RefereeList if it isn't already
     * and to sort by Referee ID.
     */
    public void updateTable()
    {
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
     *
     * @return
     */
    public int indexCounter()
    {
        return refList.size();
    }
    // TODO we need to rethink this
    private static class RefereeTableModel extends AbstractTableModel {

        private final static int COLUMN_ID     = 0;
        private final static int COLUMN_FNAME  = 1;
        private final static int COLUMN_SNAME  = 2;
        private final static int COLUMN_QUAL   = 3;
        private final static int COLUMN_ALLOC  = 4;
        private final static int COLUMN_HOME   = 5;
        private final static int COLUMN_TRAVEL = 6;

        private final String[] columnNames;

        private final ArrayList<Referee> listReferees;

        public RefereeTableModel(ArrayList<Referee> referees) {
            this.listReferees = referees;
            this.columnNames  = new String[]{"ID", "First Name", "Last Name", 
                "Qualification", "Allocations", "Home", "Travel Areas"};     

//            int index = 1;
//            for (Referee referee : listReferees)
//            {
//                referee.setIndex(index++);
//            }
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
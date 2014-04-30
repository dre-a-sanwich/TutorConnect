package databaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import event.Event;

public class Database
{
	private Connection c;
	
	
	
	//who needs comments
	public Database(){
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/Users/andreasanchez/Desktop/FinalTutorConnect/TutorConnect/tutorConnect.db");
			c.setAutoCommit(true);
			System.out.println("Opened database successfully");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			e.printStackTrace(System.out);
			c = null;
			//System.exit(0);
		}
		
	}
	
	public void exterminate(){
		try {
			c.close();
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			c = null;
			//System.exit(0);
		}
	}
	
/**
 * Creates the initial table
 * Modified from code from http://www.tutorialspoint.com/sqlite/sqlite_java.htm
 */
  public void createTable( )
  {
    Statement stmt = null;
    try {
      stmt = c.createStatement();
      String sql = "CREATE TABLE EVENT(" +
    		  	" TITLE			TEXT,"	+
    		  	" NAME			TEXT, " + 
    		  	" DAY			TEXT	DEFAULT CURRENT_DATE, " + 
    		  	" LOCATION		TEXT, " + 
    		  	" DESCRIPTION	TEXT, "+
    		  	" RSVP			INT, " + 
    		  	" VOTE			INT)";
      
      stmt.executeUpdate(sql);
      stmt.close();
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      e.getStackTrace();
      //System.exit(0);
    }
    System.out.println("EVENT Table created successfully");
    
    try {
        stmt = c.createStatement();
        String sql = "CREATE TABLE USER(" +
      		  	" NAME			TEXT, " + 
      		  	" PASSWORD		TEXT)";
        
        stmt.executeUpdate(sql);
        stmt.close();
        
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        e.getStackTrace();
        //System.exit(0);
      }
      System.out.println("EVENT Table created successfully");
      
    
  }
  
  /**
   * Adds a new event to the table
   **/
  public void createEntry(Event event)
  {
		  Statement stmt = null;
		  try {
			  stmt = c.createStatement();
			  String sql = "INSERT INTO EVENT (TITLE, NAME, DAY, LOCATION, DESCRIPTION, RSVP, VOTE)"
					  + "VALUES ('" + event.getTitle()
					  + "', '" + event.getCreatorName()
					  + "', '" + event.getDatetime()
					  + "', '" + event.getLocation()
					  + "', '" + event.getDescription() + "', 0, 0);"; 
			  System.out.println(sql);
			  stmt.executeUpdate(sql);
			  stmt.close();
		      
		  } catch ( Exception e ) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  e.getStackTrace();
			  //System.exit(0);
		  }
		  System.out.println("Entry created successfully");
	  }
  
  public boolean deleteEntry(String username, int id) {
		  Statement stmt = null;
		  try {
			  stmt = c.createStatement();
			  String sql = "DELETE FROM EVENT WHERE ID IS " + id + " AND NAME IS '" + username + "';"; 
			  System.out.println(sql);
			  stmt.executeUpdate(sql);
			  stmt.close();
			  
		      
		  } catch ( Exception e ) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  e.getStackTrace();
			  //System.exit(0);
			  return false;
		  }
		  System.out.println("deleted event successfully");
		  return true;
  	}
  
  /**
   * Deletes an event from the table
   */
  public ArrayList<Event> selectEntry()
  {
	  ArrayList<Event> eventList = new ArrayList<Event>();  
	  Statement stmt = null;
	  try {
		  stmt = c.createStatement();
		  String sql = "SELECT * FROM EVENT"; 
		  ResultSet result = stmt.executeQuery(sql);
		  
		  while(result.next()) {
			  eventList.add(new Event(result.getString("TITLE").toString(),result.getString("DAY").toString(), result.getString("NAME"),
					  					result.getString("LOCATION"), result.getString("DESCRIPTION"),
					  					result.getInt("RSVP"), result.getInt("VOTE")));
		  }
		  
		  stmt.close();
	      
	  } catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  e.getStackTrace();
		  //System.exit(0);
	  }
	  System.out.println("Entries collected.");
	  
	  return eventList;
  }
  
  public ArrayList<Event> searchedEntry(String searchString) {
	  ArrayList<Event> searchList = new ArrayList<Event>();  
	  Statement stmt = null;
	  try {
		  stmt = c.createStatement();
		  String sql = "SELECT * FROM EVENT WHERE TITLE LIKE '%" + searchString + "%';"; 
		  ResultSet result = stmt.executeQuery(sql);
		  
		  while(result.next()) {
			  searchList.add(new Event(result.getString("TITLE").toString(),result.getString("DAY").toString(), result.getString("NAME"),
					  					result.getString("LOCATION"), result.getString("DESCRIPTION"),
					  					result.getInt("RSVP"), result.getInt("VOTE")));
		  }
		  
		  stmt.close();
	      
	  } catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  e.getStackTrace();
		  //System.exit(0);
	  }
	  System.out.println("Searched events collected");
	  
	  return searchList;
  }
  
  public ArrayList<Event> myEntries(String username) {
	  ArrayList<Event> myEventList = new ArrayList<Event>();  
	  Statement stmt = null;
	  try {
		  stmt = c.createStatement();
		  String sql = "SELECT * FROM EVENT WHERE NAME LIKE '" + username + "';"; 
		  ResultSet result = stmt.executeQuery(sql);
		  
		  while(result.next()) {
			  myEventList.add(new Event(result.getInt("ID"), result.getString("TITLE").toString(),result.getString("DAY").toString(), result.getString("NAME"),
					  					result.getString("LOCATION"), result.getString("DESCRIPTION"),
					  					result.getInt("RSVP"), result.getInt("VOTE")));
		  }
		  
		  stmt.close();
	      
	  } catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  e.getStackTrace();
		  //System.exit(0);
	  }
	  System.out.println("My events collected");
	  
	  return myEventList;
  }
  
  /**
   * Edits an event in the table
   */
  public void editEntry() {
	  
  }
  
  protected boolean addUser(User user){
	  return User.addUser(c, user);	  
  }
  
  protected boolean userExist(User user){
	  return User.checkUser(c, user);
  }
  

}
package event;

import java.util.GregorianCalendar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Responsible for parsing through data the user gives the server and checking for errors
 *
 */
public class Filters {
	/**
	 * used to tell what part, if any, of the date was input incorrectly 
	 *
	 */
	
	/**
	 * Checks whether the given data can be stored in an Event (e.g., valid date).
	 * @return True if the data is valid, false otherwise.
	 */
	public static String validData(String eventInfo) {
		System.out.println("Checking data: " + eventInfo);
		String[] data = eventInfo.split("#!");
		String dateErr = null;
		
		if(checkName(data[0]) ){
			if( checkLocation(data[1])){
				if (checkDescription(data[2]) ){
					if ( (dateErr = checkDate(data[3])) == null) {
						System.out.println("Inside method, data is shiny");
						return "SUCCESS";
					}
					else{
						return dateErr;
					}
				}
				else{
					return "DESCRIPTION"; 
				}
			}
			else{
				return "LOCATION";
			}
		}
		else{
			return "NAME";
		}
	}
	
	/**
	 * Checks whether the Name parameter is valid
	 * @param name Name to check
	 * @return True if valid
	 */
	private static boolean checkName(String name) {
		System.out.println("Name: " + name);
		if(name.compareTo("Name") == 0 && name.compareTo("") == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Checks whether the Location parameter is valid
	 * @param loc Location to check
	 * @return True if valid
	 */
	private static boolean checkLocation(String loc) {
		System.out.println("Loc: " + loc);
		if(loc.compareTo("Location") == 0 && loc.compareTo("") == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Checks whether the Location parameter is valid
	 * @param desc Description to check
	 * @return True if valid
	 */
	private static boolean checkDescription(String desc) {
		System.out.println("desc: " + desc);
		if(desc.compareTo("Description") == 0 && desc.compareTo("") == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Checks whether the Location parameter is valid
	 * @param date Date to check. Expected format: YYYY-MM-DD HH:MM:SS
	 * @return True if valid
	 */
	private static String checkDate(String dateTime) {
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		
		System.out.println("Date: " + dateTime);
		if(dateTime.compareTo("YYYY-MM-DD HH:MM:SS") == 0 )
			return "DEFAULTVALUE";
		if(dateTime.compareTo("") == 0)
			return "EMPTY";
		
		/* Parse the date */
		String[] dateTimeSplit = dateTime.split(" ");  // Break into "YYYY-MM-DD" and "HH:MM:SS" sections.
		String[] date = dateTimeSplit[0].split("-"); // Break date into "YYYY", "MM", and "DD" sections.
		String[] time = dateTimeSplit[1].split(":"); // Break time into "HH" and "MM" sections.
		
		try {
			/* Convert date to integers */
			year = Integer.parseInt(date[0]);
		} catch(NumberFormatException ex) {
			return "YEAR";
		}
		try{
			month = Integer.parseInt(date[1]);
		}catch(NumberFormatException ex){
			return "MONTH";
		}
		try{
			day = Integer.parseInt(date[2]);
		}catch(NumberFormatException ex){
			return "DAY";
		}
		try{
			hour = Integer.parseInt(time[0]);
		}catch(NumberFormatException ex){
			return "HOUR";
		}
		try{
			minute = Integer.parseInt(time[1]);
		}catch(NumberFormatException ex){
			return "MINUTE";
		}
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setLenient(false);
		
		try{
			cal.set(year, month, day, hour, minute);
		} catch(ParseException ex) {
			return "GREGORIAN";
		}
		
		return null; 
	}
}

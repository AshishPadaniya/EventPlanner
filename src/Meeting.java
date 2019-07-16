import java.util.*;
/**   This class models a meeting event - it's superclass is Event.
 *	  Author:  Ashish Padaniya
 *    
 *	  Data fields:   location:String - meeting place
				
 *    Methods:  default constructor
 *				initial constructors
 *              eventInput (Scanner, String) - prompts (if char parameter is 'k') input 
 *                                             from Scanner parameter  for all data fields
 *              toString: String - displays meeting event to a String
 */
public class Meeting extends Event {
      private String location = new String();
      
      public Meeting() {
      }
      public Meeting  (OurDate date, OurTime time, String description, String location){
		super (date, time, description);
		this.location = new String (location);
	}
	public Meeting (int day, int month, int year, int hour, int minute, String description){
		super ( month,day,  year, hour, minute, description);
		this.location = new String (location);
	}
	
	
	
	public String toString() {
		return super.toString() + " at " + location;
	}
	public boolean eventInput(Scanner in, char prompt) {
		if (!super.eventInput(in, prompt))
				return false;
		if (prompt == 'k')
			System.out.print ("Enter location :");
		location = in.next();
		return true;
	}
     
}

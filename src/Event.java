/**   This class models an event.
 *	  Author:  Ashish Padaniya
 *    
 *	  Data fields:   date:  OurDate - the day/month/year of the event
 *                   time: Time - the hour/minute that event starts at
 *					 description: String - a description of event
 *				
 *    Methods:  default constructor
 *				initial constructors
 *              getDate: OurDate - returns date of event
 *				getTime: Time - returns time of event
 *			
 *              eventInput (Scanner, String) - prompts (if char parameter is 'k') input 
 *                                             from Scanner parameter  for all data fields
 *              isEqual (Event): boolean - compares date and time in two objects and returns true/false if they are equal 
 *           ** isGreater (Event): boolean - compares date and time in two objects and returns true if object in class (this)
 *                                           is greater than parameter object; else returns false       
 *              toString: String - displays event to a String                        
 */
import java.util.*;
public class Event {
	
	private OurDate date = new OurDate();
	private OurTime time = new OurTime();
	private String description = new String();
	
	public Event() {
	}
	
	public Event (OurDate date, OurTime time, String description){
		this.date = new OurDate(date);
		this.time = new OurTime (time);
		this.description = new String (description);
	}
	
	public Event (int day, int month, int year, int hour, int minute, String description){
		this.date = new OurDate ( month,day, year);
		this.time = new OurTime (hour, minute);
		this.description = new String (description);
	}
	
	
	public OurDate getDate() { 
		return date;
	}
	public OurTime getTime() { 
		return time; 
	}
	

	
	public boolean eventInput(Scanner in, char prompt) {
		
		if (!date.readDate(in, prompt)) {
			return false;
		}
		if (!time.readTime(in,prompt)) {
			return false;
		}
		if (prompt == 'k')
			System.out.print ("Enter event description: ");
		this.description = in.next();
		return true;
	}
	
	public boolean isEqual (Event evnt) {
		return (this.date.isEqual(evnt.date) && this.time.isEqual(evnt.time));
	}
	
	public boolean isGreater (Event evnt) {
		if (this.date.isGreater (evnt.date))
			return true;
		else if (this.date.isEqual(evnt.date) && this.time.isGreater (evnt.time))
			return true;
		return false;
	
	}
	
	public String toString() {
		return new String("     " + date + " " + time + " " + description);
	}
	

}

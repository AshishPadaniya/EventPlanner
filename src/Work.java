import java.util.*;
/**   This class models a work event - it's superclass is Event.
 *	  Author: Ashish Padaniya
 *    
 *	  Data field:   shiftLength:int - length of work shift
 *	  Data field:	Location:String - location of work place	
 *    Methods:  default constructor
 *				initial constructors
 *              inputEvent (Scanner, String) - prompts (if char parameter is 'k') input 
 *                                             from Scanner parameter  for all data fields
 *              toString: String - displays assignment event to a String
 */
public class Work extends Event {
	private int shiftLength;
	private String location = new String();

	public Work() {
	}
	
	public Work  (OurDate date, OurTime time, String description, int shiftLength, String location){
		super (date, time, description);
		this.location = new String (location);
		this.shiftLength = shiftLength;
	}
	
	public Work (int day, int month, int year, int hour, int minute, String description, int shiftLength,String location){
		super ( month, day, year, hour, minute, description);
		this.location = new String (location);
		this.shiftLength = shiftLength;
	}

	
	public boolean eventInput(Scanner in, char prompt) {
		if (!super.eventInput(in, prompt))
			return false;
		if (prompt == 'k')
			System.out.println("Enter work location: ");
			location = in.next();
		do {
			if (prompt == 'k')
				System.out.print ("Enter shiftLength - greater than 0 :");
			if (in.hasNextInt())
				shiftLength = in.nextInt();
			else {
				System.out.println ("Invalid value for shift length");
				in.next();
				if (prompt != 'k')
					return false;
			}
		}while (shiftLength == 0);

		return true;
	}
	
	public String toString() {
		return super.toString() + " at "+ location + " for " + shiftLength+ " hours";
	}


}


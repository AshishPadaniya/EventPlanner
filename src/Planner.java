import java.util.Scanner;
import java.util.ArrayList;

/**   This class models an planner that will keep track of events.
 *	  Author: Ashish Padaniya
 *	  Data fields:   activities - ArrayList of Events
 *                   numEvents - int - how many events are stored in the activities array
 *    Methods:  default constructor
 *				eventInput(Scanner)- adds an Event to the activities array if there is room and if there is no other activity for that date and time
 *						parameter indicates what type of Event will be added to the array 
 *           ** binarySearch (Event): int - returns index of where Event parameter is found in activities structure
 *                      otherwise returns -1 (not found)
 *           ** findIndex (Event): int - returns index of first object greater than parameter in the activities structure      
 *				displayOneDay(Date) - looks through array and displays all events for the parameter date
 *				displaySevenDays (Date) - displays events for the seven days starting at parameter date
 *				deleteEvent (Date, Time) - looks through array for an event at parameter date and time, and deletes it (if found)
 *				
 */
public class Planner {
	final private int MEETING = 1;
	final private int SCHOOL = 2;
	final private int WORK = 3;
	final private int GYM = 4;
	final private int SOCIAL = 5;

	private  ArrayList<Event> activities = new ArrayList<Event>();

	public Planner () {
	}

	public void inputEvent(Scanner in, char prompt) {

		int menuEvent = 0;

		while (menuEvent < MEETING || menuEvent > SOCIAL ) {
			if (prompt == 'k') {
				System.out.print ("\nEnter the event type to add:  ");
				System.out.print ("\n1 ... Meeting Event");
				System.out.print ("\n2 ... School Event ");
				System.out.print ("\n3 ... Work Event ");
				System.out.print ("\n4 ... Gym Event ");
				System.out.println ("\n5 ... Social Event ");
			}
			if (in.hasNextInt())
				menuEvent = in.nextInt();
			else {
				System.out.println ("Invalid event type..reenter");
				in.next();
			}
		}

		Event temp;
		switch (menuEvent) {
		case MEETING:  temp= new Meeting(); break;
		case SCHOOL: temp = new School(); break;
		case WORK: temp = new Work(); break;
		case GYM:  temp = new Gym(); break;
		case SOCIAL:  temp = new Social();
		default:   temp = new Event();
		}
		if (!temp.eventInput(in,prompt))
			return;

		int found = binSearch (temp);

		if (found != -1) {
			System.out.println ("You already have an activity for that date and time...cannot be entered");
		}else  {
			found = findIndex(temp);
			activities.add( found, temp);  
		} 
	}

	public int binSearch (Event temp) {
		int search = -1;
		int high = activities.size()-1;
		int low = 0;

		while (low <= high) {
			int middle = (high+low)/2;
			if (activities.get(middle).isEqual(temp)) {
				search = middle;
				break;
			}
			else if (activities.get(middle).isGreater(temp)) 
				high = middle-1;
			else low = middle+1;
		}
		return search;
	}

	public int findIndex(Event temp) {
		if (activities.size() == 0)
			return 0;
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).isGreater (temp))
				return i;
		}
		return activities.size();


	}


	public void displayOneDay (OurDate displayDate) {
		System.out.println ("Your activities for " + displayDate + " are: ");
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getDate().isEqual(displayDate))
				System.out.println (activities.get(i));
			if (activities.get(i).getDate().isGreater (displayDate))
				break;
		}
	}

	public void displaySevenDays(OurDate displayDate) {
		System.out.println ("Your activities for the week starting " + displayDate + " are: ");
		OurDate currentDate = displayDate;
		OurDate lastDate = new OurDate(displayDate);
		for (int j = 0; j < 6; j++) {
			lastDate.addOne();
		}
		System.out.println (lastDate);
		int i=0;
		for(i = 0; i < activities.size(); i++) {
			if (activities.get(i).getDate().isGreater (lastDate))
				break;
			if (activities.get(i).getDate().isEqual(displayDate) || activities.get(i).getDate().isGreater (displayDate) ) 
				System.out.println (activities.get(i));

		}
	}

	public void deleteEvent(Scanner in, char prompt) {
		OurDate date = new OurDate();
		OurTime time = new OurTime();
		boolean check = false;

		if (prompt == 'k')
			System.out.println ("Enter date and time of event to delete:");

		do {
			check = date.readDate(in, prompt);	
		} while (!check);
		check = false;
		do {
			check = time.readTime(in, prompt);
		} while (!check);

		Event temp = new Event (date, time, "");
		int search = binSearch(temp);

		if (search!= -1){
			activities.remove(search);
			System.out.println ("Event deleted");
		}
		else {
			System.out.println("There is no activity for that date and time...cannot be deleted");
		}
	}

	public String toString() {
		String str = new String();
		for (int i=0; i < activities.size(); i++) {
			str += activities.get(i).toString() + "\n";
		}
		return str;
	}
}

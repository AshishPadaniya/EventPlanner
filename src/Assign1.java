/**  
 * 	  
 *	  Author:  Ashish Padaniya
 *					
 *    Methods:  readFile(Scanner in)
 *    
 *    
 */

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assign1 {
	public static void main(String[] args) {


		Planner planner = new Planner();
		OurDate displayDate = new OurDate();
		Scanner inFile = null;
		Scanner in = new Scanner (System.in);

		int menuOption = 1;


		while (menuOption != 7 ) {

			do {

				System.out.print( "1... Add an event from keyboard ");
				System.out.print( "\n2 ... Display events of a day");
				System.out.print( "\n3 ... Display events of a week  ");
				System.out.print("\n4 ... Delete an event ");
				System.out.print( "\n5 ... Add events from a file");
				System.out.print("\n6 ... Display all events");
				System.out.print( "\n7 ... Quit");
				System.out.println("\nEnter your option: ");
				if (in. hasNextInt())
					menuOption = in.nextInt();
				else {
					in.next();
					System.out.println("Invalid choice..reenter: ");
					menuOption = -1;
				}
			} while (menuOption < 0 || menuOption > 7);


			if (menuOption == 1) {
				planner.inputEvent(in, 'k');
				System.out.println (planner);
			} else if (menuOption == 2) {
				System.out.println ("Enter a date to display: ");
				if (displayDate.readDate(in, 'k')) 
					planner.displayOneDay(displayDate);
			} else if (menuOption == 3) {
				System.out.println ("Enter a date to display: ");
				if (displayDate.readDate(in, 'k'))
					planner.displaySevenDays(displayDate);
			} else if (menuOption == 4) {
				planner.deleteEvent(in, 'k');
			} else if (menuOption == 5) {
				inFile = fileRead(in);
				if (inFile != null) {
					while (inFile.hasNext())
						planner.inputEvent(inFile, 'n');
					System.out.println (planner);
				}
			} else if (menuOption == 6 ){
				System.out.println("LIST OF EVENTS\n**************");
				System.out.println(planner);
			} else if (menuOption == 7){
				System.out.println("Good bye... Have a nice day");
			}

		}


	}


	public static Scanner fileRead(Scanner in) {
		
		Scanner inFile = null;
		File file = new File("Events.txt");

		try {
			if (file.exists()) {
				inFile = new Scanner(file);
			}
			else System.out.println ("File does not exist.....");
			return inFile;
		} catch (IOException e) {
			System.out.println("Could not open file....");
			return null;
		}
	}

}

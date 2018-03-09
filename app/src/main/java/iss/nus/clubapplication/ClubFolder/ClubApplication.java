/**
 * 
 */
package iss.nus.clubapplication.ClubFolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kunal
 *
 */
public class ClubApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		Club club = new Club();
		club.addMember("Nair","Neelima",null);
		club.addMember("Sharma","Shwetha","R");
		club.addFacility("Conference Room","NUS Alumini Conference Room");
		club.addFacility("Badminton","Badminton Court");
		club.show();
		
		Booking booking1 = null;
		Booking booking2 = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy hh:mm a");
		try{
			Date startDate = format.parse("12/12/17 12:00 pm");
			Date endDate = format.parse("12/12/17 02:00 pm");
			try{
				booking1 = new Booking(startDate,endDate, new Facility("Conference Room","NUS Alumini Conference Room"),new Member("Sharma","Shwetha","R",2));
				System.out.println(booking1.toString());
				booking2 = new Booking(endDate,startDate, new Facility("Conference Room","NUS Alumini Conference Room"),new Member("Sharma","Shwetha","R",2));
				booking2.overlaps(booking1);
			} catch(BadBookingException be){
				System.out.println(be.getMessage());
			}
			
			
			
		
		} catch(ParseException pe){
			System.out.println("Parsing exception for Date");
		}
		
		
		
		
	}

}

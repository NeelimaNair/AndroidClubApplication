package iss.nus.clubapplication;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import iss.nus.clubapplication.ClubFolder.Member;
import iss.nus.clubapplication.ClubFolder.Facility;
import iss.nus.clubapplication.ClubFolder.Booking;
import iss.nus.clubapplication.ClubFolder.BadBookingException;

public class BookingTest {
	
	private Booking bookingObj;
	private static final int HOUR = 60*60*1000; 
	
	@Before
	public void setUpBooking(){
		Date currentDate = new Date();
		Date endDate = new Date(currentDate.getTime() + 2* HOUR); 
		try{
			bookingObj = new Booking(currentDate,endDate,new Facility("Table Tennis", "Table Tennis"),new Member("Nair","Nitin",null,1));
		} catch(BadBookingException be){
			System.out.println(be.getMessage());
		}
			
			
	}

	@Test
	public void testOverlap(){
		Date currentDate = new Date();
		Booking newBooking = null;
		currentDate = new Date(currentDate.getTime() + 1* HOUR); 
		Date endDate = new Date(currentDate.getTime() + 3* HOUR); 
		try{
			newBooking =  new Booking(currentDate,endDate,new Facility("Table Tennis", "Table Tennis"),new Member("Khan","Salman",null,1));
		} catch(BadBookingException be){
			System.out.println(be.getMessage());
		}
		
		assertTrue("Bookings don't overlap",bookingObj.overlaps(newBooking));		
	}
	
	@Test
	public void testToString(){
		System.out.println(bookingObj.toString());
	}
}

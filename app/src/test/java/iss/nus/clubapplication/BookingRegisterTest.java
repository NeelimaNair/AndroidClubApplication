package iss.nus.clubapplication;


import java.util.Date;
import java.util.List;

import org.junit.Test;

import junit.framework.AssertionFailedError;

import org.junit.Before;

import static org.junit.Assert.*;
import iss.nus.clubapplication.ClubFolder.BookingRegister;
import iss.nus.clubapplication.ClubFolder.BadBookingException;
import iss.nus.clubapplication.ClubFolder.Member;
import iss.nus.clubapplication.ClubFolder.Facility;
import iss.nus.clubapplication.ClubFolder.Booking;

public class BookingRegisterTest {
	
	BookingRegister bookingReg = null;
	private static final int HOUR = 60*60*1000;
	
	@Before
	public void setUp(){
		bookingReg = new BookingRegister();
	}

	@Test
	public void testAddBooking(){
		Date currentDate = new Date();
		Date currentDatePlus2 = new Date(currentDate.getTime() + 2* HOUR); 
		Date startDate = new Date(currentDate.getTime() + 3* HOUR); 
		Date endDate = new Date(currentDate.getTime() + 5* HOUR); 
	
		try{
			bookingReg.addBooking(new Member("Nair","Nitin",null,1),new Facility("Table Tennis", "Table Tennis"), currentDate, currentDatePlus2);
			bookingReg.addBooking(new Member("Khan","Salman",null,2),new Facility("Table Tennis", "Table Tennis"), startDate, endDate);
		} catch(BadBookingException be){
			
			System.out.println("Booking failed:"+be.getMessage());
		}
			
	}
	
	@Test
	public void testGetBooking(){
		Date currentDate = new Date();
		Date currentDatePlus2 = new Date(currentDate.getTime() + 2* HOUR); 
		Date startDate = new Date(currentDate.getTime() + 2* HOUR); 
		Date endDate = new Date(currentDate.getTime() + 4* HOUR); 
		Date startDate1 = new Date(currentDate.getTime() + 4* HOUR); 
		Date endDate1 = new Date(currentDate.getTime() + 6* HOUR); 
	
		try{
			bookingReg.addBooking(new Member("Nair","Nitin",null,1),new Facility("Table Tennis", "Table Tennis"), currentDate, currentDatePlus2);
			bookingReg.addBooking(new Member("Khan","Salman",null,2),new Facility("Table Tennis", "Table Tennis"), startDate, endDate);
			bookingReg.addBooking(new Member("Khan","Salman",null,2),new Facility("Badminton", "Badminton"), startDate, endDate);
			bookingReg.addBooking(new Member("Khan","Amir",null,3),new Facility("Table Tennis", "Table Tennis"), startDate1, endDate1);
			
			List<Booking> bookings = bookingReg.getBooking(new Facility("Table Tennis", "Table Tennis"), currentDate, endDate);
			assertEquals(2,bookings.size());
		} catch(BadBookingException be){
			System.out.println("Booking failed:"+be.getMessage());
		}
	}
	
	@Test
	public void testRemoveBooking(){
		Date currentDate = new Date();
		Date currentDatePlus2 = new Date(currentDate.getTime() + 2* HOUR); 
		Date startDate = new Date(currentDate.getTime() + 2* HOUR); 
		Date endDate = new Date(currentDate.getTime() + 4* HOUR); 
		Date startDate1 = new Date(currentDate.getTime() + 4* HOUR); 
		Date endDate1 = new Date(currentDate.getTime() + 6* HOUR); 
	
		try{
			bookingReg.addBooking(new Member("Nair","Nitin",null,1),new Facility("Table Tennis", "Table Tennis"), currentDate, currentDatePlus2);
			bookingReg.addBooking(new Member("Khan","Salman",null,2),new Facility("Table Tennis", "Table Tennis"), startDate, endDate);
			bookingReg.addBooking(new Member("Khan","Salman",null,2),new Facility("Badminton", "Badminton"), startDate, endDate);
			bookingReg.addBooking(new Member("Khan","Amir",null,3),new Facility("Table Tennis", "Table Tennis"), startDate1, endDate1);
			
			Booking toremove = new Booking(currentDate, currentDatePlus2,new Facility("Table Tennis", "Table Tennis"),new Member("Nair","Nitin",null,1));
			List<Booking> bookings = bookingReg.getBooking(new Facility("Table Tennis", "Table Tennis"), currentDate, endDate1);
			assertEquals(3,bookings.size());
			
			bookingReg.removeBooking(toremove);
			bookings = bookingReg.getBooking(new Facility("Table Tennis", "Table Tennis"), currentDate, endDate1);
			assertEquals(2,bookings.size());
		} catch(BadBookingException be){
			System.out.println("Booking failed:"+be.getMessage());
		}
	}
}

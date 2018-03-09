package iss.nus.clubapplication.ClubFolder;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

public class BookingRegister {
	
	Map<Facility,List<Booking>> register;
	
	public BookingRegister(){
		super();
		this.register = new HashMap<Facility,List<Booking>>();
	}

	public void addBooking(Member member, Facility facility, Date startDate, Date endDate) throws BadBookingException{
		try{
			Booking booking = new Booking(startDate,endDate, facility, member);
			List<Booking> bookings = this.register.get(facility);
			if (bookings == null){
				bookings = new ArrayList<Booking>();				
			} else{
				for(Booking b: bookings){
					if(b.overlaps(booking)){
						throw new BadBookingException("The requested booking overlaps with the following booking:"+b.toString());
					}
				}					
			}			
			bookings.add(booking);
			this.register.put(facility, bookings);
			List<Booking> bookings1 = this.register.get(facility);
		}catch(BadBookingException be){
			System.out.println(be.getMessage());
		}
	}
	
	public List<Booking> getBooking(Facility facility, Date startDate, Date endDate){
		List<Booking> bookings = this.register.get(facility);
		List<Booking> bookingsInDateRange = new ArrayList<Booking>();
		for(Booking booking: bookings){
				if((startDate.equals(booking.getStartDate()) || startDate.before(booking.getStartDate())) &&
						(endDate.equals(booking.getEndDate()) || endDate.after(booking.getEndDate()))){
					bookingsInDateRange.add(booking);
				}
		}
		return bookingsInDateRange;
	}
	
	public void removeBooking(Booking remBooking){
		List<Booking> bookings = this.register.get(remBooking.getFacility());
		for(int index = 0; index < bookings.size(); index++){
			if(bookings.get(index).equals(remBooking))
				bookings.remove(index);
		}
		this.register.put(remBooking.getFacility(), bookings);
	}
}

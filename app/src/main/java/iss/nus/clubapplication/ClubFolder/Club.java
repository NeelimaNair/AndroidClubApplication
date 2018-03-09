package iss.nus.clubapplication.ClubFolder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
public class Club {

	
	private int numMembers;
	private ArrayList<Member> members;
	private HashMap<String,Facility> facilities;
	private BookingRegister register;
	
	public Club(){
		members = new ArrayList<Member>();
		facilities = new HashMap<String, Facility>();
		register = new BookingRegister();
		numMembers = 0;
	}
	
	public Facility getFacility(String name){
		Facility facility = null;
		if(facilities != null){
			facility = facilities.get(name);
		}
		return facility;
	}
	
	public HashMap<String, Facility> getFacilities(){
		return (HashMap<String, Facility>)this.facilities.clone();
	}
	
	public Facility addFacility(String name, String description){
		Facility fac = new Facility(name, description);
		facilities.put(name,fac);
		return fac;
	}
	
	public void removeFacility(String name){
		facilities.remove(name);
	}
	
	public void showFacilities(){
		for(String name:facilities.keySet()){
			facilities.get(name).show();
		}
	}
	
	public void show(){
		showMembers();
		showFacilities();
	}
	
	public Member getMember(int memberNum){
		Member seectedMember = null;
		for(Member mem: members){
			if(mem.getMemberNumber() == memberNum){
				seectedMember = mem;
			}
		}
		return seectedMember;
	}
	
	public Member addMember(String surName, String firstName, String secondName){
		
		Member member =new Member(surName, firstName, secondName,this.numMembers); 
		
		members.add(member);
		this.numMembers = this.numMembers + 1;
		return member;
	}
		
	public ArrayList<Member> getMembers(){
		return (ArrayList<Member>)this.members.clone();		
	}
	
	public void showMembers(){
		for (Member member:this.getMembers()){
			member.show();
		}		
	}
	
	public void removeMember(int memNum){
		for(int i=0; i < members.size(); i++){
			if(members.get(i).getMemberNumber() == memNum){
				members.remove(i);
			}
		}
	}
	
	public void addBooking(int memberNo, String facilityName, Date startDate, Date endDate) throws BadBookingException{
		try{
			Member mem = getMember(memberNo);
			Facility fac = getFacility(facilityName);
			register.addBooking(mem, fac, startDate, endDate);
		}catch(BadBookingException be){
			System.out.println(be.getMessage());
		}
	}
	
	public List<Booking> getBooking(Facility fac, Date startDate, Date endDate){
		List<Booking> bookings = null;
		bookings = register.getBooking(fac, startDate, endDate);
		return bookings;
	}
	
	public void showBooking(Facility fac, Date startDate, Date endDate){
		List<Booking> bookings = getBooking(fac,startDate,endDate);
		for (Iterator iterator = bookings.iterator(); iterator.hasNext();) {
			Booking booking = (Booking) iterator.next();
			System.out.println(booking.toString());
		}
	}

	public void setMembers(ArrayList<Member> members){
		this.members = members;
	}

	public void setFacilities(HashMap<String,Facility> facilities){
		this.facilities = facilities;
	}
}

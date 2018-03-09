package iss.nus.clubapplication.ClubFolder;

import java.util.Date;

public class Booking {
	
	private Date startDate;	
	private Date endDate;	
	private Facility facility;	
	private Member member;
	
	public Booking(Date startDate,Date endDate,Facility facility,Member member) throws BadBookingException{
		if(facility == null || member == null || startDate == null || endDate == null){
			throw new BadBookingException("Facility, member or Dates is not available!");
		} else if((endDate.before(startDate))){
			throw new BadBookingException("End Date is earlier than Start Date!");			
		} else{
			this.startDate = startDate;
			this.endDate = endDate;
			this.facility = facility;
			this.member = member;
		}		
	}
	
	public boolean overlaps(Booking booking){
		if(this.facility != null && this.facility.getName() != null &&
				this.facility.getName().equalsIgnoreCase(booking.getFacility().getName())){
			if(this.startDate != null && booking.startDate != null && this.endDate != null && booking.endDate != null &&
					(this.startDate.equals(booking.startDate) 
							||(this.endDate.before(booking.endDate)&& this.endDate.after(booking.startDate)
							|| (this.endDate.equals(booking.endDate)) 
							|| (this.startDate.after(booking.startDate) && this.startDate.before(booking.endDate))))){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean flag = false;
		if(obj instanceof Booking){
			Booking boo = (Booking)obj;
			if(this.startDate.equals(boo.startDate) &&
					this.endDate.equals(boo.endDate) &&
						this.facility.equals(boo.facility) &&
							this.member.equals(boo.member)){
				flag = true;
			}
		}
		return flag;
	}
	
	public String toString(){
		return this.facility.toString()+":"+this.member.toString()+":"+this.startDate.toString()+":"+this.endDate.toString();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}	

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	

}

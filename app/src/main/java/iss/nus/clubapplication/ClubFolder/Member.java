package iss.nus.clubapplication.ClubFolder;

public class Member extends Person {

	private int memberNumber;
	
	public Member(String surname, String firstname, String secondname,int memberNumber) {
		super(surname, firstname, secondname);
		this.memberNumber = memberNumber;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object obj){
		boolean flag = false;
		if(obj instanceof Member){
			Member mem = (Member)obj;
			if(this.getMemberNumber() == mem.getMemberNumber()){
				flag = true;
			}
				
		}
		return flag;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	public String toString(){
		return getSurname()+", "+getFirstname()+" "+getSecondname()+"-"+getMemberNumber();
	}

}

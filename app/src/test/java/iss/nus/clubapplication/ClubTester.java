package iss.nus.clubapplication;


import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import iss.nus.clubapplication.ClubFolder.Club;
import iss.nus.clubapplication.ClubFolder.Member;
import iss.nus.clubapplication.ClubFolder.Facility;

public class ClubTester {
	
	private static Club clubObj;

	@Before
	public void setUpClub(){
		clubObj = new Club();	
		clubObj.addMember("Nair","Nitin",null);
		clubObj.addMember("Nair","Shanthi",null);
		clubObj.addMember("Nair","Rayansh",null);
		clubObj.addFacility("Badminton", "Badminton court");
		clubObj.addFacility("Table Tennis", "Table Tennis");
	}
	
	@Test
	public void testGetMember(){
		assertEquals("Wrong member returned!","Rayansh",clubObj.getMember(2).getFirstname());
	}
	
	@Test
	public void testAddMember(){				
		assertEquals("Wrong member returned!","Nitin",clubObj.getMember(0).getFirstname());
	}
	
	@Test
	public void testGetMembers(){
		assertEquals("Wrong size for member list",3,clubObj.getMembers().size());
	//	ArrayList<Member> clonnedList = (ArrayList<Member>)clubObj.getMembers();
		//clonnedList.add(new Member("asjdla","amsdnmna",null,4));
		clubObj.getMembers().add(new Member("asjdla","amsdnmna",null,4));
		assertEquals("Wrong size for member list",3,clubObj.getMembers().size());
	}
	
	@Test
	public void testShowMember(){
		clubObj.showMembers();
	}

	@Test
	public void testRemoveMember(){
		clubObj.removeMember(1);
		clubObj.showMembers();
		System.out.println("*****************************");
		clubObj.addMember("Arun", "Nia", null);
		clubObj.showMembers();
		System.out.println("*****************************");
		for(Member mem: clubObj.getMembers()){
			mem.show();
		}
		clubObj.removeMember(1);
		clubObj.addMember("Arun", "Noan", null);
		clubObj.showMembers();
		clubObj.removeMember(5);
		clubObj.addMember("Arun", "Sundar", null);
		clubObj.showMembers();
		
	}
	
	@Test
	public void testGetFacility(){
		assertEquals("Wrong facility returned!","Badminton",clubObj.getFacility("Badminton").getName());
	}
	
	@Test
	public void testAddFacility(){	
		
		assertEquals("Wrong facility returned!","Squash",clubObj.addFacility("Squash","Squash court").getName());
	}
	
	@Test
	public void testGetFacilities(){
		assertEquals("Wrong size for facility list",2,clubObj.getFacilities().size());
		clubObj.getFacilities().put("asjdla",new Facility("asjdla","amsdnmna"));
		assertEquals("Wrong size for facility list",2,clubObj.getFacilities().size());
	}
	
	@Test
	public void testShowFacility(){
		clubObj.showFacilities();
	}

	@Test
	public void testRemoveFacility(){
		clubObj.removeFacility("Badminton");
		clubObj.showFacilities();
		System.out.println("*****************************");
		clubObj.addFacility("Billiards", "Billiards Room");
		clubObj.showFacilities();
		System.out.println("*****************************");		
		clubObj.removeFacility("Billiards");
		clubObj.showFacilities();
		
	}
	
	@Test
	public void testShow(){
		System.out.println("*****************************");
		clubObj.show();
		System.out.println("*****************************");
	}
}

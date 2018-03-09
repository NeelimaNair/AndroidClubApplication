/**
 * 
 */
package iss.nus.clubapplication.ClubFolder;



/**
 * @author kunal
 *
 */
public class Facility {
	private String name;
	private String description;
	
	public Facility(String name){
		this.name = name;
	}
	
	public Facility(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void show(){			
		System.out.println(this.toString());
	}
	public String toString(){
		StringBuilder display = new StringBuilder("Facility: "); 
		display.append(getName());
		if(getDescription() != null){
			display.append("(");
			display.append(getDescription());
			display.append(")");
		}
		return display.toString();
	}
	
	//Override the equals method
	@Override
	public boolean equals(Object obj){
		boolean flag = false;
		if(obj instanceof Facility ){
			Facility facility = (Facility)obj;
			flag = this.getName().equals(facility.getName());
		}
		return flag;
	}
	
	@Override
	public int hashCode(){
		return this.getName().hashCode();
	}

}

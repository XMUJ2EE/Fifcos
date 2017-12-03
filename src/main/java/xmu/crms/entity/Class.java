package xmu.crms.entity;

public class Class {
	private int id;
	private String name;
	private int numStudent;
	public Class(int id, String name, int numStudent) {
		super();
		this.id = id;
		this.name = name;
		this.numStudent = numStudent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumStudent() {
		return numStudent;
	}
	public void setNumStudent(int numStudent) {
		this.numStudent = numStudent;
	}
	
}

package xmu.crms.entity;

public class Course {
	
	private int id;
	private String name;
	private int numClass;
	private int numStudent;
	private String startTime;
	private String endTime;
	private String description;
	
	public Course() {
		super();
	}

	public Course(int id, String name, int numClass, int numStudent, String startTime, String endTime, String description) {
		super();
		this.id = id;
		this.name = name;
		this.numClass = numClass;
		this.numStudent = numStudent;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
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

	public int getNumClass() {
		return numClass;
	}

	public void setNumClass(int numClass) {
		this.numClass = numClass;
	}

	public int getNumStudent() {
		return numStudent;
	}

	public void setNumStudent(int numStudent) {
		this.numStudent = numStudent;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}

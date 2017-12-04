package xmu.crms.entity;

public class Course {
	
	private int id;
	private String name;
	private String startTime;
	private String endTime;
	private String description;
	private Proportions proportions;
	
	public Course() {
		super();
	}

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", description='" + description + '\'' +
                ", proportions=" + proportions +
                '}';
    }

    public Course(int id, String name, String startTime,
                  String endTime, String description, Proportions proportions) {
		super();
		this.id = id;
		this.name = name;
		this.proportions = proportions;
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

	public Proportions getProportions() {
		return proportions;
	}

	public void setProportions(Proportions proportions) {
		this.proportions = proportions;
	}
}

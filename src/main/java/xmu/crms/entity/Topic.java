package xmu.crms.entity;

public class Topic {
	private int id;
	private String serial;
	private String name;
	private String description;
	private int groupLimit;
	private int groupMenmberLimit;
	private int groupLeft;
	public Topic(int id, String serial, String name, 
			String description, int groupLimit, int groupMenmberLimit,
			int groupLeft) {
		super();
		this.id = id;
		this.serial = serial;
		this.name = name;
		this.description = description;
		this.groupLimit = groupLimit;
		this.groupMenmberLimit = groupMenmberLimit;
		this.groupLeft = groupLeft;
	}

	public Topic() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
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
	public int getGroupLimit() {
		return groupLimit;
	}
	public void setGroupLimit(int groupLimit) {
		this.groupLimit = groupLimit;
	}
	public int getGroupMenmberLimit() {
		return groupMenmberLimit;
	}
	public void setGroupMenmberLimit(int groupMenmberLimit) {
		this.groupMenmberLimit = groupMenmberLimit;
	}
	public int getGroupLeft() {
		return groupLeft;
	}
	public void setGroupLeft(int groupLeft) {
		this.groupLeft = groupLeft;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", serial=" + serial + 
				", name=" + name + ", description=" + description + 
				", groupLimit=" + groupLimit + ", groupMenmberLimit=" + 
				groupMenmberLimit + ", groupLeft=" + groupLeft + "]";
	}
	
}

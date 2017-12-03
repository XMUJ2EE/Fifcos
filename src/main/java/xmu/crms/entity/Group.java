package xmu.crms.entity;

public class Group {
	private int id;
	private String name;
	private User leader;
	private User members[];
	private Topic topics;
	private String report;
	private SeminarGrade grade;
	public Group(int id, String name, User leader, 
			User[] members, Topic topics, 
			String report, SeminarGrade grade) {
		super();
		this.id = id;
		this.name = name;
		this.leader = leader;
		this.members = members;
		this.topics = topics;
		this.report = report;
		this.grade = grade;
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
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
	public User[] getMembers() {
		return members;
	}
	public void setMembers(User[] members) {
		this.members = members;
	}
	public Topic getTopics() {
		return topics;
	}
	public void setTopics(Topic topics) {
		this.topics = topics;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public SeminarGrade getGrade() {
		return grade;
	}
	public void setGrade(SeminarGrade grade) {
		this.grade = grade;
	}
	
}

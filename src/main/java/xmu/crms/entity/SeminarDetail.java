package xmu.crms.entity;

public class SeminarDetail {
	private int id;
	private String name;
	private String site;
	private String startTime;
	private String endTime;
	private String teacherName;
	private String teacherEmail;
	public SeminarDetail(int id, String name, 
			String site, String startTime, 
			String endTime, String teacherName,
			String teacherEmail) {
		super();
		this.id = id;
		this.name = name;
		this.site = site;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teacherName = teacherName;
		this.teacherEmail = teacherEmail;
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
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	@Override
	public String toString() {
		return "SeminarDetail [id=" + id + 
				", name=" + name + ", site=" + site + 
				", startTime=" + startTime + ", endTime=" + 
				endTime + ", teacherName=" + teacherName + 
				", teacherEmail=" + teacherEmail + "]";
	}
	
}

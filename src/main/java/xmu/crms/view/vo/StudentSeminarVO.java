package xmu.crms.view.vo;

/**
 * @author mads
 */


public class StudentSeminarVO {
    private int id;
    private String name;
    private String groupingMethod;
    private String courseName;
    private String startTime;
    private String endTime;
    private int classColling;
    private Boolean isLeader;
    private Boolean areTopicsSeletced;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
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

    public int getClassColling() {
        return classColling;
    }

    public void setClassColling(int classColling) {
        this.classColling = classColling;
    }

    public Boolean getLeader() {
        return isLeader;
    }

    public void setLeader(Boolean leader) {
        isLeader = leader;
    }

    public Boolean getAreTopicsSeletced() {
        return areTopicsSeletced;
    }

    public void setAreTopicsSeletced(Boolean areTopicsSeletced) {
        this.areTopicsSeletced = areTopicsSeletced;
    }

    public StudentSeminarVO(int id, String name, String groupingMethod, String courseName, String startTime, String endTime, int classColling, Boolean isLeader, Boolean areTopicsSeletced) {
        this.id = id;
        this.name = name;
        this.groupingMethod = groupingMethod;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classColling = classColling;
        this.isLeader = isLeader;
        this.areTopicsSeletced = areTopicsSeletced;
    }

    public StudentSeminarVO() {
    }
}

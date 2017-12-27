package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;

import java.text.SimpleDateFormat;

public class SeminarAndGradeVO {
    private int id;
    private String name;
    private String description;
    private String groupingMethod;
    private String startTime;
    private String endTime;
    private int grade;

    public SeminarAndGradeVO(SeminarGroup seminarGroup) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(seminarGroup != null){
            this.id = seminarGroup.getId().intValue();
            this.name = seminarGroup.getSeminar().getName();
            this.description = seminarGroup.getSeminar().getDescription();
            this.groupingMethod = seminarGroup.getSeminar().getFixed() ? "fixed" : "random";
            this.startTime = simpleDateFormat.format(seminarGroup.getSeminar().getStartTime());
            this.endTime = simpleDateFormat.format(seminarGroup.getSeminar().getEndTime());
            this.grade = seminarGroup.getFinalGrade();
        }
    }

    public SeminarAndGradeVO(int id, String name, String description, String groupingMethod, String startTime, String endTime, int grade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupingMethod = groupingMethod;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

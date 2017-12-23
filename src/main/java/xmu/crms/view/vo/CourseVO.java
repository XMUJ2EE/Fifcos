package xmu.crms.view.vo;

public class CourseVO {
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private proportions proportions;

    public CourseVO(String name, String description, String startTime, String endTime, proportions proportions) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.proportions = proportions;
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

    public proportions getProportions() {
        return proportions;
    }

    public void setProportions(proportions proportions) {
        this.proportions = proportions;
    }
}

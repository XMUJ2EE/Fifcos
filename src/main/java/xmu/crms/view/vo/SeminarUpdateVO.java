package xmu.crms.view.vo;

public class SeminarUpdateVO {
    private String name;
    private String description;
    private String groupingMethod;
    private String startTime;
    private String endTime;
    private proportions proportions;

    public SeminarUpdateVO(String name, String description, String groupingMethod, String startTime, String endTime, xmu.crms.view.vo.proportions proportions) {
        this.name = name;
        this.description = description;
        this.groupingMethod = groupingMethod;
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

    public xmu.crms.view.vo.proportions getProportions() {
        return proportions;
    }

    public void setProportions(xmu.crms.view.vo.proportions proportions) {
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "SeminarUpdateVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupingMethod='" + groupingMethod + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", proportions=" + proportions +
                '}';
    }
}

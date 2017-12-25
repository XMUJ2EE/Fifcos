package xmu.crms.view.vo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class SeminarUpdateVO {
    private String name;
    private String description;
    private String groupingMethod;
    private String startTime;
    private String endTime;

    public SeminarUpdateVO(String name, String description, String groupingMethod, String startTime, String endTime) {
        this.name = name;
        this.description = description;
        this.groupingMethod = groupingMethod;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SeminarUpdateVO() {

    }

    public SeminarUpdateVO(String seminarJson) throws IOException {
        Map<String , Object> seminar = new ObjectMapper().readValue(seminarJson, Map.class);
        this.name = (String) seminar.get("name");
        this.description = (String) seminar.get("description");
        this.groupingMethod = (String) seminar.get("groupingMethod");
        this.startTime = (String) seminar.get("startTime");
        this.endTime = (String) seminar.get("endTime");
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

    @Override
    public String toString() {
        return "SeminarUpdateVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupingMethod='" + groupingMethod + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

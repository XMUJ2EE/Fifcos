package xmu.crms.view.vo;

import java.math.BigInteger;

public class StudentSeminarBriefVO {
    private BigInteger seminarId;
    private String name;
    private String groupingMethod;

    public StudentSeminarBriefVO() {
    }

    public StudentSeminarBriefVO(BigInteger seminarId, String name, String groupingMethod) {
        this.seminarId = seminarId;
        this.name = name;
        this.groupingMethod = groupingMethod;
    }

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
    }

    public BigInteger getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(BigInteger seminarId) {
        this.seminarId = seminarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentSeminarBriefVO{" +
                "seminarId=" + seminarId +
                ", name='" + name + '\'' +
                '}';
    }
}

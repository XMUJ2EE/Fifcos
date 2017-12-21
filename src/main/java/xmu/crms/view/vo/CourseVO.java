package xmu.crms.view.vo;

import java.math.BigInteger;

public class CourseVO {
    private BigInteger id;
    private String name;
    private BigInteger numClass;
    private BigInteger numStudent;
    private String startTime;
    private String endTime;

    public CourseVO(BigInteger id, String name, BigInteger numClass, BigInteger numStudent, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.numClass = numClass;
        this.numStudent = numStudent;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getNumClass() {
        return numClass;
    }

    public void setNumClass(BigInteger numClass) {
        this.numClass = numClass;
    }

    public BigInteger getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(BigInteger numStudent) {
        this.numStudent = numStudent;
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
}

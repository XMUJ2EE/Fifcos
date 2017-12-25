package xmu.crms.entity;

import xmu.crms.view.vo.SeminarVO;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seminar {
    private BigInteger id;
    private String name;
    private String description;
    private Course course;
    private Boolean fixed;
    private Date startTime;
    private Date endTime;

    public Seminar(SeminarVO seminarVO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = null;
        this.name = seminarVO.getName();
        this.description = seminarVO.getDescription();
        this.course = null;
        this.fixed = seminarVO.getGroupingMethod().equals("fixed");
        this.startTime = seminarVO.getStartTime();
        this.endTime = seminarVO.getEndTime();
    }

    public Seminar() {


    }

    public Seminar(BigInteger id, String name, String description, Course course, Boolean fixed, Date startTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.course = course;
        this.fixed = fixed;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Seminar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", course=" + course +
                ", fixed=" + fixed +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

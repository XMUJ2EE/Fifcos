package xmu.crms.view.vo;

import xmu.crms.entity.ClassInfo;

import java.math.BigInteger;

/**
 * @author mads
 * @date 2017/12/24 21:34
 */
public class UserClassVO {
    private BigInteger id;
    private String name;
    private Integer numStudent;
    private String time;
    private String site;
    private String courseName;
    private String courseTeacher;

    public UserClassVO(ClassInfo classInfo, int numStudent) {
        this.id = classInfo.getId();
        this.name = classInfo.getName();
        this.numStudent = numStudent;
        this.time = classInfo.getClassTime();
        this.site = classInfo.getSite();
        this.courseName = classInfo.getCourse().getName();
        this.courseTeacher = classInfo.getCourse().getTeacher().getName();
    }

    public UserClassVO(BigInteger id, String name, Integer numStudent, String time, String site, String courseName, String courseTeacher) {
        this.id = id;
        this.name = name;
        this.numStudent = numStudent;
        this.time = time;
        this.site = site;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
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

    public Integer getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(Integer numStudent) {
        this.numStudent = numStudent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    @Override
    public String toString() {
        return "UserClassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numStudent=" + numStudent +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseTeacher='" + courseTeacher + '\'' +
                '}';
    }
}

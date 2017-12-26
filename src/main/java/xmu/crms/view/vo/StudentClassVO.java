package xmu.crms.view.vo;

import xmu.crms.entity.ClassInfo;

import java.math.BigInteger;

public class StudentClassVO {
    private String courseName;
    private BigInteger courseId;
    private String courseTeacher;
    private String className;
    private BigInteger classId;
    private String site;

    public StudentClassVO() {
    }

    public StudentClassVO(ClassInfo classInfo) {
        if(classInfo.getCourse() != null){
            this.courseName = classInfo.getCourse().getName();
            this.courseId = classInfo.getCourse().getId();
            this.courseTeacher = classInfo.getCourse().getTeacher().getName();
        }else{
            this.className = null;
            this.classId = null;
            this.courseName = null;
        }
        this.className = classInfo.getName();
        this.classId = classInfo.getId();
        this.site = classInfo.getSite();
    }


    public StudentClassVO(String courseName, BigInteger courseId, String courseTeacher, String className, BigInteger classId, String site) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseTeacher = courseTeacher;
        this.className = className;
        this.classId = classId;
        this.site = site;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BigInteger getClassId() {
        return classId;
    }

    public void setClassId(BigInteger classId) {
        this.classId = classId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "StudentClassVO{" +
                "courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                ", courseTeacher='" + courseTeacher + '\'' +
                ", className='" + className + '\'' +
                ", classId=" + classId +
                ", site='" + site + '\'' +
                '}';
    }
}

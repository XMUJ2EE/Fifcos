package xmu.crms.view.vo;

import java.math.BigInteger;
import java.util.List;

public class StudentCourseVO {
    private BigInteger courseId;
    private String courName;
    private String className;
    private String description;
    private BigInteger fixGroup;
    private List<StudentSeminarBriefVO> seminarList;

    public StudentCourseVO() {
    }

    public StudentCourseVO(BigInteger courseId, String courName, String className, String description, BigInteger fixGroup, List<StudentSeminarBriefVO> seminarList) {
        this.courseId = courseId;
        this.courName = courName;
        this.className = className;
        this.description = description;
        this.fixGroup = fixGroup;
        this.seminarList = seminarList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourName() {
        return courName;
    }

    public void setCourName(String courName) {
        this.courName = courName;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getFixGroup() {
        return fixGroup;
    }

    public void setFixGroup(BigInteger fixGroup) {
        this.fixGroup = fixGroup;
    }

    public List<StudentSeminarBriefVO> getSeminarList() {
        return seminarList;
    }

    public void setSeminarList(List<StudentSeminarBriefVO> seminarList) {
        this.seminarList = seminarList;
    }

    @Override
    public String toString() {
        return "StudentCourseVO{" +
                "courseId=" + courseId +
                ", courName='" + courName + '\'' +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", fixGroup=" + fixGroup +
                ", seminarList=" + seminarList +
                '}';
    }
}

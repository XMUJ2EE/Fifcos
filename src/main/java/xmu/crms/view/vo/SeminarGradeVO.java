package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.Topic;


public class SeminarGradeVO {
    private String topicName;
    private String groupName;
    private String leaderName;
    private Integer presentationGrade;
    private Integer reportGrade;
    private Integer grade;

    public SeminarGradeVO() {
    }

    public SeminarGradeVO(SeminarGroup seminarGroup, Topic topic) {
        this.topicName = topic.getSerial();
        if(seminarGroup != null){
            this.groupName = seminarGroup.getId().toString();
            if(seminarGroup.getLeader() != null){
                this.leaderName = seminarGroup.getLeader().getName();
            }
            this.presentationGrade = seminarGroup.getPresentationGrade();
            this.reportGrade = seminarGroup.getReportGrade();
            this.grade = seminarGroup.getFinalGrade();
        }
    }

    public SeminarGradeVO(String topicName, String groupName, String leaderName, Integer presentationGrade, Integer reportGrade, Integer grade) {
        this.topicName = topicName;
        this.groupName = groupName;
        this.leaderName = leaderName;
        this.presentationGrade = presentationGrade;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }

    public String getSeminarName() {
        return topicName;
    }

    public void setSeminarName(String topicName) {
        this.topicName = topicName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getPresentationGrade() {
        return presentationGrade;
    }

    public void setPresentationGrade(Integer presentationGrade) {
        this.presentationGrade = presentationGrade;
    }

    public Integer getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(Integer reportGrade) {
        this.reportGrade = reportGrade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SeminarGradeVO{" +
                "topicName='" + topicName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", presentationGrade=" + presentationGrade +
                ", reportGrade=" + reportGrade +
                ", grade=" + grade +
                '}';
    }
}

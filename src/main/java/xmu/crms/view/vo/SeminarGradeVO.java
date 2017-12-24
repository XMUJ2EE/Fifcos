package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;

public class SeminarGradeVO {
    private String seminarName;
    private String groupName;
    private String leaderName;
    private Integer presentationGrade;
    private Integer reportGrade;
    private Integer grade;

    public SeminarGradeVO(SeminarGroup seminarGroup) {
        this.seminarName = seminarGroup.getSeminar().getName();
        this.groupName = seminarGroup.getId().toString();
        this.leaderName = seminarGroup.getLeader().getName();
        this.presentationGrade = seminarGroup.getPresentationGrade();
        this.reportGrade = seminarGroup.getReportGrade();
        this.grade = seminarGroup.getFinalGrade();
    }

    public SeminarGradeVO(String seminarName, String groupName, String leaderName, Integer presentationGrade, Integer reportGrade, Integer grade) {
        this.seminarName = seminarName;
        this.groupName = groupName;
        this.leaderName = leaderName;
        this.presentationGrade = presentationGrade;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
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
}

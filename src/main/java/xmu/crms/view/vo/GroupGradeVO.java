package xmu.crms.view.vo;

import java.util.List;

public class GroupGradeVO {
    private List<PresentationGradeVO> presentationGradeVO;
    private int reportGrade;
    private int grade;

    public GroupGradeVO(List<PresentationGradeVO> presentationGradeVO, int reportGrade, int grade) {
        this.presentationGradeVO = presentationGradeVO;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }

    public List<PresentationGradeVO> getPresentationGradeVO() {
        return presentationGradeVO;
    }

    public void setPresentationGradeVO(List<PresentationGradeVO> presentationGradeVO) {
        this.presentationGradeVO = presentationGradeVO;
    }

    public int getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(int reportGrade) {
        this.reportGrade = reportGrade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

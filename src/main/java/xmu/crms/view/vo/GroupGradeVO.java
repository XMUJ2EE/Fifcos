package xmu.crms.view.vo;

public class GroupGradeVO {
    private PresentationGradeVO presentationGradeVO;
    private int reportGrade;
    private int grade;

    public GroupGradeVO(PresentationGradeVO presentationGradeVO, int reportGrade, int grade) {
        this.presentationGradeVO = presentationGradeVO;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }

    public PresentationGradeVO getPresentationGradeVO() {
        return presentationGradeVO;
    }

    public void setPresentationGradeVO(PresentationGradeVO presentationGradeVO) {
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

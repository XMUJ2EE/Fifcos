package xmu.crms.entity;

public class Grade {
    private PresentationGrade presentationGrade[];
    private int reportGrade;
    private int grade;

    public Grade(PresentationGrade[] presentationGrade, int reportGrade, int grade) {
        this.presentationGrade = presentationGrade;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }

    public PresentationGrade[] getPresentationGrade() {
        return presentationGrade;
    }

    public void setPresentationGrade(PresentationGrade[] presentationGrade) {
        this.presentationGrade = presentationGrade;
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

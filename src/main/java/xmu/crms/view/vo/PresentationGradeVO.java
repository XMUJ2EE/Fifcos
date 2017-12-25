package xmu.crms.view.vo;

public class PresentationGradeVO {
    private int topicId;
    private int grade;

    public PresentationGradeVO(int topicId, int grade) {
        this.topicId = topicId;
        this.grade = grade;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "PresentationGradeVO{" +
                "topicId=" + topicId +
                ", grade=" + grade +
                '}';
    }
}

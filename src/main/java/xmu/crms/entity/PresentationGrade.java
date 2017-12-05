package xmu.crms.entity;

public class PresentationGrade {
    private int topicId;
    private int grade;

    public PresentationGrade(int topicId, int grade) {
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
}

package xmu.crms.view.VO;

public class GroupDetailsVO {
    private int id;
    private UserIdNameVO leader;
    private UserIdNameVO members[];
    private TopicIdNameVO topicIdNameVO;
    private String report;

    public GroupDetailsVO(int id, UserIdNameVO leader, UserIdNameVO[] members, TopicIdNameVO topicIdNameVO, String report) {
        this.id = id;
        this.leader = leader;
        this.members = members;
        this.topicIdNameVO = topicIdNameVO;
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserIdNameVO getLeader() {
        return leader;
    }

    public void setLeader(UserIdNameVO leader) {
        this.leader = leader;
    }

    public UserIdNameVO[] getMembers() {
        return members;
    }

    public void setMembers(UserIdNameVO[] members) {
        this.members = members;
    }

    public TopicIdNameVO getTopicIdNameVO() {
        return topicIdNameVO;
    }

    public void setTopicIdNameVO(TopicIdNameVO topicIdNameVO) {
        this.topicIdNameVO = topicIdNameVO;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}

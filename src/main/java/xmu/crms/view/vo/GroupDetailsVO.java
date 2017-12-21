package xmu.crms.view.vo;

public class GroupDetailsVO {
    private int id;
    private UserUpdateVO leader;
    private UserUpdateVO members[];
    private TopicIdNameVO topicIdNameVO;
    private String report;

    public GroupDetailsVO(int id, UserUpdateVO leader, UserUpdateVO[] members, TopicIdNameVO topicIdNameVO, String report) {
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

    public UserUpdateVO getLeader() {
        return leader;
    }

    public void setLeader(UserUpdateVO leader) {
        this.leader = leader;
    }

    public UserUpdateVO[] getMembers() {
        return members;
    }

    public void setMembers(UserUpdateVO[] members) {
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

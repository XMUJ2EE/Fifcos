package xmu.crms.view.vo;

import java.util.List;

public class GroupMemberTopicGradeVO {
    private int id;
    private String name;
    private UserVO leader;
    private List<UserVO> members;
    private List<TopicVO> topics;
    private GroupGradeVO group;
    private String report;

    public GroupMemberTopicGradeVO(int id, String name, UserVO leader, List<UserVO> members, List<TopicVO> topics, GroupGradeVO group, String report) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.members = members;
        this.topics = topics;
        this.group = group;
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserVO getLeader() {
        return leader;
    }

    public void setLeader(UserVO leader) {
        this.leader = leader;
    }

    public List<UserVO> getMembers() {
        return members;
    }

    public void setMembers(List<UserVO> members) {
        this.members = members;
    }

    public List<TopicVO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicVO> topics) {
        this.topics = topics;
    }

    public GroupGradeVO getGroup() {
        return group;
    }

    public void setGroup(GroupGradeVO group) {
        this.group = group;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}

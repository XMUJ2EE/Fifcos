package xmu.crms.view.vo;

import xmu.crms.entity.User;

import java.util.Arrays;

/**
 * @author mads
 */
public class GroupDetailVO {
    private int id;
    private String name;
    private UserVO leader;
    private UserVO[] members;
    private TopicVO[] topics;

    public GroupDetailVO() {
    }

    public GroupDetailVO(int id, String name, UserVO leader, UserVO[] members, TopicVO[] topics) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.members = members;
        this.topics = topics;
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

    public UserVO[] getMembers() {
        return members;
    }

    public void setMembers(UserVO[] members) {
        this.members = members;
    }

    public TopicVO[] getTopics() {
        return topics;
    }

    public void setTopics(TopicVO[] topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "GroupDetailVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader=" + leader +
                ", members=" + Arrays.toString(members) +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }
}

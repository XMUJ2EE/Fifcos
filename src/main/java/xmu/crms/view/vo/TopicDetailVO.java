package xmu.crms.view.vo;

import xmu.crms.entity.Topic;

import java.math.BigInteger;

/**
 * @author mads
 */
public class TopicDetailVO {
    private BigInteger id;
    private String name;
    private String description;
    private int groupLimit;
    private int groupMemberLimit;
    private int groupLeft;

    public TopicDetailVO() {
    }

    public TopicDetailVO(Topic topic, int groupLeft) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.description = topic.getDescription();
        this.groupLimit = topic.getGroupNumberLimit();
        this.groupMemberLimit = topic.getGroupStudentLimit();
        this.groupLeft = groupLeft;
    }

    public TopicDetailVO(BigInteger id, String name, String description, int groupLimit, int groupMemberLimit, int groupLeft) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupLimit = groupLimit;
        this.groupMemberLimit = groupMemberLimit;
        this.groupLeft = groupLeft;
    }

    public int getGroupLeft() {
        return groupLeft;
    }

    public void setGroupLeft(int groupLeft) {
        this.groupLeft = groupLeft;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(int groupLimit) {
        this.groupLimit = groupLimit;
    }

    public int getGroupMemberLimit() {
        return groupMemberLimit;
    }

    public void setGroupMemberLimit(int groupMemberLimit) {
        this.groupMemberLimit = groupMemberLimit;
    }
}
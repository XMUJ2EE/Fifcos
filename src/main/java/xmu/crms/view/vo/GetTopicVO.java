package xmu.crms.view.vo;

import java.math.BigInteger;
import java.util.List;

public class GetTopicVO {
    private BigInteger id;
    private String serial;
    private String name;
    private String description;
    private int groupLimit;
    private int groupMemberLimit;
    private List<String> groupList;

    public GetTopicVO() {

    }

    public GetTopicVO(TopicDetailVO topicDetailVO, List<String> groupList) {
        this.id = topicDetailVO.getId();
        this.serial = topicDetailVO.getSerial();
        this.name = topicDetailVO.getName();
        this.description = topicDetailVO.getDescription();
        this.groupLimit = topicDetailVO.getGroupLimit();
        this.groupMemberLimit = topicDetailVO.getGroupMemberLimit();
        this.groupList = groupList;
    }

    public GetTopicVO(BigInteger id, String serial, String name, String description, int groupLimit, int groupMemberLimit, List<String> groupList) {
        this.id = id;
        this.serial = serial;
        this.name = name;
        this.description = description;
        this.groupLimit = groupLimit;
        this.groupMemberLimit = groupMemberLimit;
        this.groupList = groupList;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public List<String> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }
}

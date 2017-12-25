package xmu.crms.view.vo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class TopicUpdateVO {
    private String serial;
    private String name;
    private String description;
    private int groupLimit;
    private int groupMemberLimit;

    public TopicUpdateVO() {

    }

    public TopicUpdateVO(String topicJson) throws IOException {
        Map<String , Object> topic = new ObjectMapper().readValue(topicJson, Map.class);
        this.serial = null;
        this.name = (String) topic.get("name");
        this.description = (String) topic.get("description");
        this.groupLimit = (Integer) topic.get("groupLimit");
        this.groupMemberLimit = (Integer) topic.get("groupMemberLimit");
    }

    public TopicUpdateVO(String serial, String name, String description, int groupLimit, int groupMemberLimit) {
        this.serial = serial;
        this.name = name;
        this.description = description;
        this.groupLimit = groupLimit;
        this.groupMemberLimit = groupMemberLimit;
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
}

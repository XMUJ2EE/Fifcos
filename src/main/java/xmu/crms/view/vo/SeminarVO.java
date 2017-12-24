package xmu.crms.view.vo;

import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mads
 * @date 2017/12/24 16:21
 */
public class SeminarVO {
    private BigInteger id;
    private String name;
    private String description;
    private String groupingMethod;
    private Date startTime;
    private Date endTime;
    private List<TopicVO> topics;

    public SeminarVO() {
    }

    public SeminarVO(Seminar seminar, List<Topic> topics) {
        this.id = seminar.getId();
        this.name = seminar.getName();
        this.description = seminar.getDescription();
        this.groupingMethod = seminar.getFixed().equals(0)?"random":"fixed";
        this.startTime = seminar.getStartTime();
        this.endTime = seminar.getEndTime();

        List<TopicVO> topicVOS = new ArrayList<TopicVO>();
        for(Topic topic:topics){
            topicVOS.add(new TopicVO(topic));
        }
        this.topics = topicVOS;
    }

    public SeminarVO(BigInteger id, String name, String description, String groupingMethod, Date startTime, Date endTime, List<TopicVO> topics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupingMethod = groupingMethod;
        this.startTime = startTime;
        this.endTime = endTime;
        this.topics = topics;
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

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<TopicVO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicVO> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "SeminarVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupingMethod='" + groupingMethod + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", topics=" + topics +
                '}';
    }
}

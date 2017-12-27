package xmu.crms.view.vo;

import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
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
    private String startTime;
    private String endTime;
    private List<TopicVO> topics;

    public SeminarVO() {
    }

    public SeminarVO(Seminar seminar, List<Topic> topics) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = seminar.getId();
        this.name = seminar.getName();
        this.description = seminar.getDescription();
        this.groupingMethod = seminar.getFixed()?"fixed":"random";
        this.startTime = simpleDateFormat.format(seminar.getStartTime());
        this.endTime = simpleDateFormat.format(seminar.getEndTime());
        List<TopicVO> topicVOS = new ArrayList<TopicVO>();
        for(Topic topic:topics){
            topicVOS.add(new TopicVO(topic));
        }
        this.topics = topicVOS;
    }

    public SeminarVO(BigInteger id, String name, String description, String groupingMethod, String startTime, String endTime, List<TopicVO> topics) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

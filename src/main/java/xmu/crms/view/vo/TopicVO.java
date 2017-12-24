package xmu.crms.view.vo;

import xmu.crms.entity.Topic;

import java.math.BigInteger;

/**
 * @author mads
 */
public class TopicVO {

    private BigInteger id;
    private String name;

    public TopicVO() {
    }

    public TopicVO(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public TopicVO(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
    }

    @Override
    public String toString() {
        return "TopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}

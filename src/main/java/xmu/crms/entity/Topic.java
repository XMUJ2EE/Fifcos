package xmu.crms.entity;

import xmu.crms.view.vo.AddTopicVO;
import xmu.crms.view.vo.TopicDetailVO;
import xmu.crms.view.vo.TopicUpdateVO;

import java.math.BigInteger;

/**
 * @author mads
 */
public class Topic {
	private BigInteger id;
	private String serial;
	private String name;
	private String description;
	private Integer groupNumberLimit;
	private Integer groupStudentLimit;
	private Seminar seminar;

	public Topic() {
	}

	public Topic(TopicUpdateVO topicUpdateVO) {
		this.id = null;
		this.serial = null;
		this.name = topicUpdateVO.getName();
		this.description = topicUpdateVO.getDescription();
		this.groupNumberLimit = topicUpdateVO.getGroupLimit();
		this.groupStudentLimit = topicUpdateVO.getGroupMemberLimit();
		this.seminar = new Seminar();
	}

	public Topic(AddTopicVO addTopicVO) {
		this.id = null;
		this.serial = addTopicVO.getSerial();
		this.name = addTopicVO.getName();
		this.description = addTopicVO.getDescription();
		this.groupNumberLimit = addTopicVO.getGroupLimit();
		this.groupStudentLimit = addTopicVO.getGroupMemberLimit();
		this.seminar = new Seminar();
	}

	public Topic(TopicDetailVO topicDetailVO) {
		this.id = null;
		this.serial = topicDetailVO.getSerial();
		this.name = topicDetailVO.getName();
		this.description = topicDetailVO.getDescription();
		this.groupNumberLimit = topicDetailVO.getGroupLimit();
		this.groupStudentLimit = topicDetailVO.getGroupMemberLimit();
		this.seminar = new Seminar();
	}

	public Topic(BigInteger id, String serial, String name, String description, Integer groupNumberLimit, Integer groupStudentLimit, Seminar seminar) {
		this.id = id;
		this.serial = serial;
		this.name = name;
		this.description = description;
		this.groupNumberLimit = groupNumberLimit;
		this.groupStudentLimit = groupStudentLimit;
		this.seminar = seminar;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
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

	public Integer getGroupNumberLimit() {
		return groupNumberLimit;
	}
	public void setGroupNumberLimit(Integer groupNumberLimit) {
		this.groupNumberLimit = groupNumberLimit;
	}
	public Integer getGroupStudentLimit() {
		return groupStudentLimit;
	}
	public void setGroupStudentLimit(Integer groupStudentLimit) {
		this.groupStudentLimit = groupStudentLimit;
	}
	public Seminar getSeminar() {
		return seminar;
	}
	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	@Override
	public String toString() {
		return "Topic{" +
				"id=" + id +
				", serial='" + serial + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", groupNumberLimit=" + groupNumberLimit +
				", groupStudentLimit=" + groupStudentLimit +
				", seminar=" + seminar +
				'}';
	}
}

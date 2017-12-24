package xmu.crms.entity;

import java.math.BigInteger;

/**
 * @author mads
 */
public class Topic {
	private BigInteger id;
	private String name;
	private String description;
	private Integer groupNumberLimit;
	private Integer groupStudentLimit;
	private Seminar seminar;

	public Topic() {
	}

	public Topic(BigInteger id, String name, String description, Integer groupNumberLimit, Integer groupStudentLimit, Seminar seminar) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.groupNumberLimit = groupNumberLimit;
		this.groupStudentLimit = groupStudentLimit;
		this.seminar = seminar;
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
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", groupNumberLimit=" + groupNumberLimit +
				", groupStudentLimit=" + groupStudentLimit +
				", seminar=" + seminar +
				'}';
	}
}

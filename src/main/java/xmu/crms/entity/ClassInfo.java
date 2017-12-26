package xmu.crms.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import xmu.crms.view.vo.ClassCreateVO;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class ClassInfo {
	private BigInteger id;
	private String name;
	private Course course;
	private String site;
	private String classTime;
	private String description;
	private Integer reportPercentage;
	private Integer presentationPercentage;
	private Integer fivePointPercentage;
	private Integer fourPointPercentage;
	private Integer threePointPercentage;

	public ClassInfo(ClassCreateVO classCreateVO) {
		this.id = null;
		this.name = classCreateVO.getName();
		this.course = null;
		this.site = classCreateVO.getSite();
		this.classTime = classCreateVO.getTime();
		this.description = null;
		this.reportPercentage = classCreateVO.getProportions().getReport();
		this.presentationPercentage = classCreateVO.getProportions().getPresentation();
		this.fivePointPercentage = classCreateVO.getProportions().getA();
		this.fourPointPercentage = classCreateVO.getProportions().getB();
		this.threePointPercentage = classCreateVO.getProportions().getC();
	}

	public ClassInfo() {

	}

	public ClassInfo(String classJson) throws IOException {
		Map<String, Object> classinfo = new ObjectMapper().readValue(classJson, Map.class);
		this.id = null;
		this.name = (String) classinfo.get("name");
		this.course = null;
		this.site = (String) classinfo.get("site");
		this.classTime = (String) classinfo.get("classTime");
		this.reportPercentage = Integer.parseInt((String) classinfo.get("reportPercentage"));
		this.presentationPercentage = Integer.parseInt((String) classinfo.get("presentationPercentage"));
		this.fivePointPercentage = Integer.parseInt((String) classinfo.get("fivePointPercentage"));
		this.fourPointPercentage = Integer.parseInt((String) classinfo.get("fourPointPercentage"));
		this.threePointPercentage = Integer.parseInt((String) classinfo.get("threePointPercentage"));
	}

	public ClassInfo(BigInteger id, String name, Course course, String site,
					 String classTime, String description, Integer reportPercentage,
					 Integer presentationPercentage, Integer fivePointPercentage,
					 Integer fourPointPercentage, Integer threePointPercentage) {

		this.id = id;
		this.name = name;
		this.course = course;
		this.site = site;
		this.classTime = classTime;
		this.description = description;
		this.reportPercentage = reportPercentage;
		this.presentationPercentage = presentationPercentage;
		this.fivePointPercentage = fivePointPercentage;
		this.fourPointPercentage = fourPointPercentage;
		this.threePointPercentage = threePointPercentage;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReportPercentage() {
		return reportPercentage;
	}

	public void setReportPercentage(Integer reportPercentage) {
		this.reportPercentage = reportPercentage;
	}

	public Integer getPresentationPercentage() {
		return presentationPercentage;
	}

	public void setPresentationPercentage(Integer presentationPercentage) {
		this.presentationPercentage = presentationPercentage;
	}

	public Integer getFivePointPercentage() {
		return fivePointPercentage;
	}

	public void setFivePointPercentage(Integer fivePointPercentage) {
		this.fivePointPercentage = fivePointPercentage;
	}

	public Integer getFourPointPercentage() {
		return fourPointPercentage;
	}

	public void setFourPointPercentage(Integer fourPointPercentage) {
		this.fourPointPercentage = fourPointPercentage;
	}

	public Integer getThreePointPercentage() {
		return threePointPercentage;
	}

	public void setThreePointPercentage(Integer threePointPercentage) {
		this.threePointPercentage = threePointPercentage;
	}

	@Override
	public String toString() {
		return "ClassInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", course=" + course +
				", site='" + site + '\'' +
				", classTime='" + classTime + '\'' +
				", description='" + description + '\'' +
				", reportPercentage=" + reportPercentage +
				", presentationPercentage=" + presentationPercentage +
				", fivePointPercentage=" + fivePointPercentage +
				", fourPointPercentage=" + fourPointPercentage +
				", threePointPercentage=" + threePointPercentage +
				'}';
	}
}
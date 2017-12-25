package xmu.crms.view.vo;

import xmu.crms.entity.ClassInfo;

import java.math.BigInteger;

/**
 * @author mads
 */
public class ClassDetailVO {
    private String name;
    private Integer numStudent;
    private String time;
    private String site;
    private String classTime;
    private Integer reportPercentage;
    private Integer presentationPercentage;
    private Integer fivePointPercentage;
    private Integer fourPointPercentage;
    private Integer threePointPercentage;

    public ClassDetailVO() {
    }

    public ClassDetailVO(ClassInfo classInfo, int numStudent) {
        this.name = classInfo.getName();
        this.numStudent = numStudent;
        this.time = classInfo.getClassTime();
        this.site = classInfo.getSite();
        this.classTime = classInfo.getClassTime();
        this.reportPercentage = classInfo.getReportPercentage();
        this.presentationPercentage = classInfo.getPresentationPercentage();
        this.fivePointPercentage = classInfo.getFivePointPercentage();
        this.fourPointPercentage = classInfo.getFourPointPercentage();
        this.threePointPercentage = classInfo.getThreePointPercentage();
    }

    public ClassDetailVO(String name, int numStudent, String time, String site, String classTime, Integer reportPercentage, Integer presentationPercentage, Integer fivePointPercentage, Integer fourPointPercentage, Integer threePointPercentage) {
        this.name = name;
        this.numStudent = numStudent;
        this.time = time;
        this.site = site;
        this.classTime = classTime;
        this.reportPercentage = reportPercentage;
        this.presentationPercentage = presentationPercentage;
        this.fivePointPercentage = fivePointPercentage;
        this.fourPointPercentage = fourPointPercentage;
        this.threePointPercentage = threePointPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "ClassDetailVO{" +
                "name='" + name + '\'' +
                ", numStudent=" + numStudent +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", classTime='" + classTime + '\'' +
                ", reportPercentage=" + reportPercentage +
                ", presentationPercentage=" + presentationPercentage +
                ", fivePointPercentage=" + fivePointPercentage +
                ", fourPointPercentage=" + fourPointPercentage +
                ", threePointPercentage=" + threePointPercentage +
                '}';
    }
}

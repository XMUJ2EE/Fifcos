package xmu.crms.view.vo;

import java.util.Properties;

public class ClassCreateVO {
    private String name;
    private String site;
    private String time;
    private Proportions proportions;

    public ClassCreateVO(String name, String site, String time, Proportions proportions) {
        this.name = name;
        this.site = site;
        this.time = time;
        this.proportions = proportions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Proportions getProportions() {
        return proportions;
    }

    public void setProportions(Proportions proportions) {
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "ClassCreateVO{" +
                "name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", time='" + time + '\'' +
                ", proportions=" + proportions +
                '}';
    }
}

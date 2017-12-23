package xmu.crms.view.vo;

public class ClassCreateVO {
    private String name;
    private String site;
    private String time;
    private proportions proportions;

    public ClassCreateVO(String name, String site, String time, xmu.crms.view.vo.proportions proportions) {
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

    public xmu.crms.view.vo.proportions getProportions() {
        return proportions;
    }

    public void setProportions(xmu.crms.view.vo.proportions proportions) {
        this.proportions = proportions;
    }
}

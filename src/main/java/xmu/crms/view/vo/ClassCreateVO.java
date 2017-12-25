package xmu.crms.view.vo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    public ClassCreateVO() {

    }

    public ClassCreateVO(String classJson) throws IOException {
        Map<String , Object> classcreate = new ObjectMapper().readValue(classJson, Map.class);
        this.name = (String) classcreate.get("name");
        this.site = (String) classcreate.get("site");
        this.time = (String) classcreate.get("time");
        HashMap<String, Object> proportion = (HashMap) classcreate.get("proportions");
        System.out.println(proportion.toString());
        Proportions p = new Proportions( Integer.parseInt(proportion.get("report").toString()), Integer.parseInt(proportion.get("presentation").toString()),
                Integer.parseInt(proportion.get("c").toString()), Integer.parseInt(proportion.get("b").toString()),
                Integer.parseInt(proportion.get("a").toString()));
        this.proportions = new Proportions(p);
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

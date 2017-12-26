package xmu.crms.view.vo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class AddSchoolVO {
    private String name;
    private String province;
    private String city;

    public AddSchoolVO(String name, String province, String city) {
        this.name = name;
        this.province = province;
        this.city = city;
    }

    public AddSchoolVO() {

    }

    public AddSchoolVO(String schoolJson) throws IOException {
        Map<String, Object> school = new ObjectMapper().readValue(schoolJson, Map.class);
        this.name = (String) school.get("name");
        this.province = (String) school.get("province");
        this.city = (String) school.get("city");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

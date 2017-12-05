package xmu.crms.view.vo;

public class AddSchoolVO {
    private String name;
    private String province;
    private String city;

    public AddSchoolVO(String name, String province, String city) {
        this.name = name;
        this.province = province;
        this.city = city;
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

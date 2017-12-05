package xmu.crms.view.vo;

import xmu.crms.entity.Gender;
import xmu.crms.entity.Type;

/**
 * @author mads
 */
public class UserDetailVO {

    private int id;
    private Type type;
    private String name;
    private String number;
    private String phone;
    private String email;
    private Gender gender;
    private SchoolVO schoolVO;
    private String title;
    private String avator;

    public UserDetailVO() {
    }

    public UserDetailVO(int id, Type type, String name, String number, String phone, String email, Gender gender, SchoolVO schoolVO, String title, String avator) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.schoolVO = schoolVO;
        this.title = title;
        this.avator = avator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SchoolVO getSchoolVO() {
        return schoolVO;
    }

    public void setSchoolVO(SchoolVO schoolVO) {
        this.schoolVO = schoolVO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    @Override
    public String toString() {
        return "UserDetailVO{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", schoolVO=" + schoolVO +
                ", title='" + title + '\'' +
                ", avator='" + avator + '\'' +
                '}';
    }
}

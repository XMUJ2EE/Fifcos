package xmu.crms.view.vo;


import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * @author mads
 */
public class UserDetailVO {

    private BigInteger id;
    private String type;
    private String name;
    private String number;
    private String phone;
    private String email;
    private String gender;
    private SchoolVO school;
    private String title;
    private String education;
    private String avator;

    public UserDetailVO(User user) {
        this.education = user.getEducation() == null ? "未设置" :
                        (user.getEducation() == 1 ? "本科" :
                        (user.getEducation() == 2 ? "研究生" : "博士"));
        this.id = user.getId();
        this.type = user.getType() == 0 ? "student" : "teacher";
        this.name = user.getName();
        this.number = user.getNumber();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.gender = (user.getGender() == null || user.getGender() == 0) ? "男" : "女";
        this.school = new SchoolVO(user.getSchool().getId(), user.getSchool().getName());
        this.title = (user.getTitle() == null || user.getTitle() == 0) ? "非教授" : "教授";
        this.avator = user.getAvatar();
    }

    public UserDetailVO(BigInteger id, String type, String name, String number, String phone, String email, String gender, String school, String title, String education, String avator) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.school = new SchoolVO(null,school);
        this.title = title;
        this.education = education;
        this.avator = avator;
    }

    public UserDetailVO(BigInteger id, String type, String name, String number, String phone, String email, String gender, SchoolVO school, String title, String education, String avator) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.school = school;
        this.title = title;
        this.education = education;
        this.avator = avator;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public SchoolVO getSchool() {
        return school;
    }

    public void setSchool(SchoolVO school) {
        this.school = school;
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
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", school=" + school +
                ", title='" + title + '\'' +
                ", avator='" + avator + '\'' +
                '}';
    }
}

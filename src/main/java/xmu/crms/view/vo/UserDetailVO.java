package xmu.crms.view.vo;


/**
 * @author mads
 */
public class UserDetailVO {

    private int id;
    private int type;
    private String name;
    private String number;
    private String phone;
    private String email;
    private int gender;
    private SchoolVO school;
    private String title;
    private String avator;

    public UserDetailVO() {
    }

    public UserDetailVO(int id, int type, String name, String number, String phone, String email, int gender, SchoolVO school, String title, String avator) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.school = school;
        this.title = title;
        this.avator = avator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public SchoolVO getSchool() {
        return school;
    }

    public void setSchool(SchoolVO schoolVO) {
        this.school = schoolVO;
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
                ", schoolVO=" + school +
                ", title='" + title + '\'' +
                ", avator='" + avator + '\'' +
                '}';
    }
}

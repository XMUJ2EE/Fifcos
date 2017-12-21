package xmu.crms.view.vo;

public class UserUpdateVO {
    private String name;
    private String number;
    private String email;
    private String gender;
    private String title;
    private String avatar;

    public UserUpdateVO(String name, String number, String email, String gender, String title, String avatar) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.gender = gender;
        this.title = title;
        this.avatar = avatar;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

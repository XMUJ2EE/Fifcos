package xmu.crms.entity;

import xmu.crms.view.vo.SchoolVO;
import xmu.crms.view.vo.UserDetailVO;

import java.math.BigInteger;
import java.util.Map;

public class User {
	private BigInteger id;
	private String phone;
	private String wechatId;
	private String openid;
	private String avatar;
	private String password;
	private String name;
	private School school;
	private Integer gender;
	private Integer type;
	private String number;
	private Integer education;
	private Integer title;
	private String email;

	public User() {
	}
	public User(Map<String,Object> jsonUser){
		this.password = (String)(jsonUser.get("password"));
		this.id = null;
		this.type = (Integer) jsonUser.get("type");
		this.name = (String)jsonUser.get("name");
		this.number = (String)jsonUser.get("id");
		this.phone = (String)jsonUser.get("phone");
		this.email = (String)jsonUser.get("email");
		if(jsonUser.get("gender")!=null){
			this.gender = (((String)jsonUser.get("gender")).equals("男"))?0:1;
		}
		this.school = new School(new SchoolVO(null, (String)jsonUser.get("school")));
		if(jsonUser.get("title")!=null){
			this.title = ((String)jsonUser.get("title")).equals("教授")?1:0;
		}
		this.education = null;
		this.avatar = (String)jsonUser.get("avatar");
	}
	public User(String openid, Integer type){
		this.openid = openid;
		this.type = type;
	}
	public User(UserDetailVO userDetailVO) {
		this.id = null;
		this.phone = null;
		this.wechatId = null;
		this.openid = null;
		this.avatar = userDetailVO.getAvatar();
		this.password = null;
		this.name = userDetailVO.getName();
		this.school = new School(userDetailVO.getSchool());
		this.gender = userDetailVO.getGender().equals("男")?0:1;
		this.type = null;
		this.number = null;
		this.education =
				(userDetailVO.getEducation().equals("本科"))?1:
						(userDetailVO.getEducation().equals("研究生"))?2:
								(userDetailVO.getEducation().equals("博士"))?3:0;
	}

	public User(BigInteger id, String phone, String wechatId, String openid, String avatar, String password, String name, School school, Integer gender, Integer type, String number, Integer education, Integer title, String email) {
		this.id = id;
		this.phone = phone;
		this.wechatId = wechatId;
		this.openid = openid;
		this.avatar = avatar;
		this.password = password;
		this.name = name;
		this.school = school;
		this.gender = gender;
		this.type = type;
		this.number = number;
		this.education = education;
		this.title = title;
		this.email = email;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", phone='" + phone + '\'' +
				", wechatId='" + wechatId + '\'' +
				", openid='" + openid + '\'' +
				", avatar='" + avatar + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", school=" + school +
				", gender=" + gender +
				", type=" + type +
				", number='" + number + '\'' +
				", education=" + education +
				", title=" + title +
				", email='" + email + '\'' +
				'}';
	}
}

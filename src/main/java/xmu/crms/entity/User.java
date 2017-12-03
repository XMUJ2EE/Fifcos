package xmu.crms.entity;

public class User {
	private int id;
	private Type type;
	private String number;
	private String name;
	private String phone;
	private String email;
	private Gender gender;
	private School school;
	private String title;
	private String password;
	private String unionId;
	private String avatar;

	public User(int id, Type type, String number, 
			String name, String phone, String email, 
			Gender gender, School school,
			String title, String password, 
			String unionId, String avatar) {
		this.id = id;
		this.type = type;
		this.number = number;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.school = school;
		this.title = title;
		this.password = password;
		this.unionId = unionId;
		this.avatar = avatar;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}

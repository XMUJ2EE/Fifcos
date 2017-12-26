package xmu.crms.entity;

import xmu.crms.view.vo.AddSchoolVO;
import xmu.crms.view.vo.SchoolVO;

import java.math.BigInteger;

public class School {
	private BigInteger id;
	private String name;
	private String province;
	private String city;

	public School() {
	}

	public School(AddSchoolVO addSchoolVO) {
		this.id = null;
		this.name = addSchoolVO.getName();
		this.province = addSchoolVO.getProvince();
		this.city = addSchoolVO.getCity();
	}

	public School(SchoolVO schoolVO) {
		this.id = schoolVO.getId();
		this.name = schoolVO.getName();
		this.province = null;
		this.city = null;
	}

	public School(BigInteger id, String name, String province, String city) {
		this.id = id;
		this.name = name;
		this.province = province;
		this.city = city;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "School{" +
				"id=" + id +
				", name='" + name + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}

package xmu.crms.entity;

import xmu.crms.view.vo.LocationVO;

import java.math.BigInteger;

public class Location {
	private BigInteger id;
	private ClassInfo classInfo;
	private Seminar seminar;
	private Double longitude;
	private Double latitude;
	private Integer status;
	public final static Integer END=0;
	public final static Integer CALLING=1;
	public final static Integer BREAK=2;

	public Location() {
	}

	public Location(LocationVO locationVO, ClassInfo classInfo, Seminar seminar){
		this.id = null;
		this.classInfo = classInfo;
		this.seminar = seminar;
		this.longitude = locationVO.getLongitude();
		this.latitude = locationVO.getLatitude();
		this.status = Location.CALLING;
	}

	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}
	public Seminar getSeminar() {
		return seminar;
	}
	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Location{" +
				"id=" + id +
				", classInfo=" + classInfo +
				", seminar=" + seminar +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", status=" + status +
				'}';
	}
}

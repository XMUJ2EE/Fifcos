package xmu.crms.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
	STUDENT("student"),
	TEACHER("teacher");


	public String getValue() {
		return value;
	}

	private final String value;


	Type(String value){
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}

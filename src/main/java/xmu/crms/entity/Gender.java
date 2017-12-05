package xmu.crms.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE("male"),
	FEMALE("female");

	private final String value;

	@JsonValue
	public String getValue() {
		return value;
	}

	Gender(String value){
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}

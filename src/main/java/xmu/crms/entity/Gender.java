package xmu.crms.entity;

public enum Gender {
	MALE("male"),
	FEMALE("female");

	private final String value;

	Gender(String value){
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}

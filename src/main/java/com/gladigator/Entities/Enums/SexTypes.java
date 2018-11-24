package com.gladigator.Entities.Enums;

public enum SexTypes {
	NONE, MALE, FEMALE;

	public static SexTypes valueOf(Integer id) {
		switch (id) {
		case 1:
			return SexTypes.NONE;
		case 2:
			return SexTypes.MALE;
		case 3:
			return SexTypes.FEMALE;
		default:
			return SexTypes.NONE;
		}
	}
	
	public int getIndex() {
		return this.ordinal() + 1;
	}
}

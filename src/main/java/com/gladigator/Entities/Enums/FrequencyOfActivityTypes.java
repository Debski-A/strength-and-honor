package com.gladigator.Entities.Enums;

public enum FrequencyOfActivityTypes {
	NONE, VERY_LOW, LOW, MEDIUM, HIGH, VERY_HIGH;

	public static FrequencyOfActivityTypes valueOf(Integer id) {
		switch (id) {
		case 1:
			return FrequencyOfActivityTypes.NONE;
		case 2:
			return FrequencyOfActivityTypes.VERY_LOW;
		case 3:
			return FrequencyOfActivityTypes.LOW;
		case 4:
			return FrequencyOfActivityTypes.MEDIUM;
		case 5:
			return FrequencyOfActivityTypes.HIGH;
		case 6:
			return FrequencyOfActivityTypes.VERY_HIGH;
		default:
			return FrequencyOfActivityTypes.NONE;
		}
	}
	
	public int getIndex() {
		return this.ordinal() + 1;
	}
}

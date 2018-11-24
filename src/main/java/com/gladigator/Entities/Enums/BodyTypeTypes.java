package com.gladigator.Entities.Enums;

public enum BodyTypeTypes {
	NONE, ECTOMORPHIC, MESOMORPHIC, ENDOMORPHIC;
	
	public int getIndex() {
		return this.ordinal() + 1;
	}
}

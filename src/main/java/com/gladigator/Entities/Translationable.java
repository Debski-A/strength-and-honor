package com.gladigator.Entities;

import java.util.List;

public interface Translationable<T extends Translation> {
	
	public List<T> getTranslations();
	
	public void setContent(String content);

}

package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex_translations")
public class SexTranslation implements Translation {
	
	public SexTranslation() {
	}
	
	public SexTranslation(Integer id, String lang, Boolean isDefault, String translatedDescription) {
		this.sexTranslationId = id;
		this.language = lang;
		this.isDefault = isDefault;
		this.translatedDescription = translatedDescription;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sex_translations")
	private Integer sexTranslationId;
	
	@Column(name="language")
	private String language;
	
	@Column(name="is_default")
	private Boolean isDefault;
	
	@Column(name="description")
	private String translatedDescription;
	

	public Integer getSexTranslationId() {
		return sexTranslationId;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public Boolean getIsDefault() {
		return isDefault;
	}

	@Override
	public String getTranslatedContent() {
		return translatedDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((translatedDescription == null) ? 0 : translatedDescription.hashCode());
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((sexTranslationId == null) ? 0 : sexTranslationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SexTranslation other = (SexTranslation) obj;
		if (translatedDescription == null) {
			if (other.translatedDescription != null)
				return false;
		} else if (!translatedDescription.equals(other.translatedDescription))
			return false;
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (sexTranslationId == null) {
			if (other.sexTranslationId != null)
				return false;
		} else if (!sexTranslationId.equals(other.sexTranslationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SexTranslation [sexTranslationId=" + sexTranslationId + ", language=" + language + ", isDefault="
				+ isDefault + ", translatedDescription=" + translatedDescription + "]";
	}
	
	

}

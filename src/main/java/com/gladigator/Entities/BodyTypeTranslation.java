package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "body_type_translations")
public class BodyTypeTranslation implements Translation {
	
	public BodyTypeTranslation() {
	}
	
	public BodyTypeTranslation(Integer sexTranslationId, String language, Boolean isDefault, String translatedDescription) {
		this.sexTranslationId = sexTranslationId;
		this.language = language;
		this.isDefault = isDefault;
		this.translatedDescription = translatedDescription;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bt_translations")
	private Integer sexTranslationId;
	
	@Column(name="language")
	private String language;
	
	@Column(name="is_default")
	private Boolean isDefault;
	
	@Column(name="description")
	private String translatedDescription;

	
	@Override
	public String getLanguage() {
		return this.getLanguage();
	}

	@Override
	public Boolean getIsDefault() {
		return this.isDefault;
	}

	@Override
	public String getTranslatedDescription() {
		return this.getTranslatedDescription();
	}

	public Integer getSexTranslationId() {
		return sexTranslationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((sexTranslationId == null) ? 0 : sexTranslationId.hashCode());
		result = prime * result + ((translatedDescription == null) ? 0 : translatedDescription.hashCode());
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
		BodyTypeTranslation other = (BodyTypeTranslation) obj;
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
		if (translatedDescription == null) {
			if (other.translatedDescription != null)
				return false;
		} else if (!translatedDescription.equals(other.translatedDescription))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BodyTypeTranslations [sexTranslationId=");
		builder.append(sexTranslationId);
		builder.append(", language=");
		builder.append(language);
		builder.append(", isDefault=");
		builder.append(isDefault);
		builder.append(", translatedDescription=");
		builder.append(translatedDescription);
		builder.append("]");
		return builder.toString();
	}
	
	

}

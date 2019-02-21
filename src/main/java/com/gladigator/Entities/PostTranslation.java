package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts_translations")
public class PostTranslation implements Translation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_post_translations")
	private Integer postTranslationId;
	
	@Column(name="language")
	private String language;
	
	@Column(name="is_default")
	private Boolean isDefault;
	
	@Column(name="content")
	private String translatedContent;

	@Override
	public String getLanguage() {
		return this.language;
	}

	@Override
	public Boolean getIsDefault() {
		return this.isDefault;
	}

	@Override
	public String getTranslatedContent() {
		return this.translatedContent;
	}

	public Integer getPostTranslationId() {
		return postTranslationId;
	}

	public void setTranslatedContent(String translatedContent) {
		this.translatedContent = translatedContent;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((postTranslationId == null) ? 0 : postTranslationId.hashCode());
		result = prime * result + ((translatedContent == null) ? 0 : translatedContent.hashCode());
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
		PostTranslation other = (PostTranslation) obj;
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
		if (postTranslationId == null) {
			if (other.postTranslationId != null)
				return false;
		} else if (!postTranslationId.equals(other.postTranslationId))
			return false;
		if (translatedContent == null) {
			if (other.translatedContent != null)
				return false;
		} else if (!translatedContent.equals(other.translatedContent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostTranslation [btTranslationId=");
		builder.append(postTranslationId);
		builder.append(", language=");
		builder.append(language);
		builder.append(", isDefault=");
		builder.append(isDefault);
		builder.append(", translatedContent=");
		builder.append(translatedContent);
		builder.append("]");
		return builder.toString();
	}

}

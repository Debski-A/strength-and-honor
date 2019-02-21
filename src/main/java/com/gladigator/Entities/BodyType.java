package com.gladigator.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "body_type")
public class BodyType implements Translationable<BodyTypeTranslation>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bt")
	private Integer bodyTypeId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_bt")
	private List<BodyTypeTranslation> bodyTypeTranslations;
	
	@Transient
	private String description;


	public String getDescription() {
		return description;
	}

	public Integer getBodyTypeId() {
		return bodyTypeId;
	}
	
	public void setBodyTypeTranslations(List<BodyTypeTranslation> bodyTypeTranslations) {
		this.bodyTypeTranslations = bodyTypeTranslations;
	}

	public void setBodyTypeId(Integer bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}

	@Override
	public List<BodyTypeTranslation> getTranslations() {
		return bodyTypeTranslations;
	}

	@Override
	public void setContent(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyTypeId == null) ? 0 : bodyTypeId.hashCode());
		result = prime * result + ((bodyTypeTranslations == null) ? 0 : bodyTypeTranslations.hashCode());
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
		BodyType other = (BodyType) obj;
		if (bodyTypeId == null) {
			if (other.bodyTypeId != null)
				return false;
		} else if (!bodyTypeId.equals(other.bodyTypeId))
			return false;
		if (bodyTypeTranslations == null) {
			if (other.bodyTypeTranslations != null)
				return false;
		} else if (!bodyTypeTranslations.equals(other.bodyTypeTranslations))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BodyType [bodyTypeId=");
		builder.append(bodyTypeId);
		builder.append(", bodyTypeTranslations=");
		builder.append(bodyTypeTranslations);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	

	
}

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
@Table(name = "sex")
public class Sex implements Translationable<SexTranslation>{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sex")
	private Integer sexId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_sex")
	private List<SexTranslation> sexTranslations;
	
	@Transient
	private String description;
	

	public String getDescription() {
		return description;
	}

	public void setContent(String description) {
		this.description = description;
	}

	public Integer getSexId() {
		return sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	@Override
	public List<SexTranslation> getTranslations() {
		return sexTranslations;
	}

	public void setSexTranslations(List<SexTranslation> sexTranslations) {
		this.sexTranslations = sexTranslations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sexId == null) ? 0 : sexId.hashCode());
		result = prime * result + ((sexTranslations == null) ? 0 : sexTranslations.hashCode());
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
		Sex other = (Sex) obj;
		if (sexId == null) {
			if (other.sexId != null)
				return false;
		} else if (!sexId.equals(other.sexId))
			return false;
		if (sexTranslations == null) {
			if (other.sexTranslations != null)
				return false;
		} else if (!sexTranslations.equals(other.sexTranslations))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sex [sexId=");
		builder.append(sexId);
		builder.append(", sexTranslations=");
		builder.append(sexTranslations);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

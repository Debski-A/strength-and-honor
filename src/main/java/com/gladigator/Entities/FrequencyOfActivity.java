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
@Table(name = "frequency_of_activity")
public class FrequencyOfActivity implements Translationable<FrequencyOfActivityTranslation>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_foa")
	private Integer frequencyOfActivityId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_foa")
	private List<FrequencyOfActivityTranslation> foaTranslations;
	
	@Transient
	private String description;

	public Integer getFrequencyOfActivityId() {
		return frequencyOfActivityId;
	}

	public void setFrequencyOfActivityId(Integer frequencyOfActivityId) {
		this.frequencyOfActivityId = frequencyOfActivityId;
	}
	
	@Override
	public List<FrequencyOfActivityTranslation> getTranslations() {
		return foaTranslations;
	}

	public void setFoaTranslations(List<FrequencyOfActivityTranslation> foaTranslations) {
		this.foaTranslations = foaTranslations;
	}

	public String getDescription() {
		return description;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FrequencyOfActivity [frequencyOfActivityId=");
		builder.append(frequencyOfActivityId);
		builder.append(", foaTranslations=");
		builder.append(foaTranslations);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((foaTranslations == null) ? 0 : foaTranslations.hashCode());
		result = prime * result + ((frequencyOfActivityId == null) ? 0 : frequencyOfActivityId.hashCode());
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
		FrequencyOfActivity other = (FrequencyOfActivity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (foaTranslations == null) {
			if (other.foaTranslations != null)
				return false;
		} else if (!foaTranslations.equals(other.foaTranslations))
			return false;
		if (frequencyOfActivityId == null) {
			if (other.frequencyOfActivityId != null)
				return false;
		} else if (!frequencyOfActivityId.equals(other.frequencyOfActivityId))
			return false;
		return true;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}

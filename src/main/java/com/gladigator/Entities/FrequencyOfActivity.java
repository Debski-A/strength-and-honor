package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "frequency_of_activity")
public class FrequencyOfActivity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_foa")
	private Integer frequencyOfActivityId;
	
	@Column(name = "frequency")
	private String frequency;

	public Integer getFrequencyOfActivityId() {
		return frequencyOfActivityId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequencyOfActivityId(Integer frequencyOfActivityId) {
		this.frequencyOfActivityId = frequencyOfActivityId;
	}
	
	public void setFrequencyOfActivityFrequencyName(String frequencyName) {
		this.frequency = frequencyName;
	}

	@Override
	public String toString() {
		return "FrequencyOfActivity [frequencyOfActivityId=" + frequencyOfActivityId + ", frequency=" + frequency + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
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
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (frequencyOfActivityId == null) {
			if (other.frequencyOfActivityId != null)
				return false;
		} else if (!frequencyOfActivityId.equals(other.frequencyOfActivityId))
			return false;
		return true;
	}
	
	
	
	
	
}

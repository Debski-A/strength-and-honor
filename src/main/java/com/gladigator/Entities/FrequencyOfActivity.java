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

	@Override
	public String toString() {
		return "FrequencyOfActivity [frequencyOfActivityId=" + frequencyOfActivityId + ", frequency=" + frequency + "]";
	}
	
	
	
}

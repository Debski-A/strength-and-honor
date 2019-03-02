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

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "frequency_of_activity")
public class FrequencyOfActivity implements Translationable<FrequencyOfActivityTranslation>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_foa")
	private Integer frequencyOfActivityId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_foa")
	@Getter(AccessLevel.PRIVATE)
	private List<FrequencyOfActivityTranslation> foaTranslations;
	
	@Transient
	@Setter(AccessLevel.PRIVATE)
	private String description;

	@Override
	public List<FrequencyOfActivityTranslation> getTranslations() {
		return foaTranslations;
	}

	@Override
	public void setTranslatedContent(String description) {
		this.description = description;
	}
	
}

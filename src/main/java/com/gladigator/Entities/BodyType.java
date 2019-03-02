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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "body_type")
public class BodyType implements Translationable<BodyTypeTranslation>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bt")
	@Getter
	@Setter
	private Integer bodyTypeId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_bt")
	@Setter
	private List<BodyTypeTranslation> bodyTypeTranslations;
	
	@Transient
	@Getter
	private String description;

	@Override
	public List<BodyTypeTranslation> getTranslations() {
		return bodyTypeTranslations;
	}

	@Override
	public void setTranslatedContent(String content) {
		this.description = content;
	}

}

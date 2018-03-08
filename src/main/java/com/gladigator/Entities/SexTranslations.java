package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex_translations")
public class SexTranslations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sex_translations")
	private Integer sexTranslationId;
	
	@Column(name="language")
	private String language;
	
	@Column(name="is_default")
	private Boolean isDefault;
	
	@Column(name="description")
	private String description;

}

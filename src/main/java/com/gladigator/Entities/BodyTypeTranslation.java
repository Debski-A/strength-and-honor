package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "body_type_translations")
public class BodyTypeTranslation implements Translation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bt_translations")
	private Integer btTranslationId;
	
	@Column(name="language")
	private String language;
	
	@Column(name="description")
	private String translatedContent;

}

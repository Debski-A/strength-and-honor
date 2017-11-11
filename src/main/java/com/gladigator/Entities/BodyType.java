package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "body_type")
public class BodyType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bt")
	private Integer bodyTypeId;
	
	@Column(name = "type")
	private String type;

	public Integer getBodyTypeId() {
		return bodyTypeId;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "BodyType [bodyTypeId=" + bodyTypeId + ", type=" + type + "]";
	}

	
}

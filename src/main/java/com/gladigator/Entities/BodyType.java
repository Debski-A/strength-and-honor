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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBodyTypeId() {
		return bodyTypeId;
	}

	@Override
	public String toString() {
		return "BodyType [type=" + type + "]";
	}
	
	public BodyType() {}
	
	public BodyType(Integer bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}

}

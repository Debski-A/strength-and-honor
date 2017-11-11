package com.gladigator.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sex")
	private Integer sexId;
	
	@Column(name = "type")
	private String type;

	public Integer getSexId() {
		return sexId;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Sex [sexId=" + sexId + ", type=" + type + "]";
	}

	
	
}

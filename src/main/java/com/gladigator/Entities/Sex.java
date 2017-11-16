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

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sexId == null) ? 0 : sexId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Sex other = (Sex) obj;
		if (sexId == null) {
			if (other.sexId != null)
				return false;
		} else if (!sexId.equals(other.sexId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sex [sexId=" + sexId + ", type=" + type + "]";
	}

	
	
}

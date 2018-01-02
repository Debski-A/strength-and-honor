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

	public void setBodyTypeId(Integer bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}
	
	public void setBodyTypeType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "BodyType [bodyTypeId=" + bodyTypeId + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyTypeId == null) ? 0 : bodyTypeId.hashCode());
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
		BodyType other = (BodyType) obj;
		if (bodyTypeId == null) {
			if (other.bodyTypeId != null)
				return false;
		} else if (!bodyTypeId.equals(other.bodyTypeId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
}

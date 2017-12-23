package com.gladigator.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "users_details")
public class UserDetails {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = {
	@Parameter(name = "property", value = "user") }) // Konieczne aby pobierac ID z tabeli users
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id_user")
	private Integer userId;

	@Column(name = "height")
	@NotNull
	@Min(140)
	@Max(250)
	private Integer height;

	@Column(name = "weight")
	@NotNull
	@Min(40)
	@Max(300)
	private Integer weight;

	@Column(name = "age")
	@NotNull
	@Min(14)
	@Max(100)
	private Integer age;

	@Column(name = "BMI")
	private Integer bmi; // Body Mass Index

	@Column(name = "BMR")
	private Integer bmr; // Basal Metabolic Rate

	@OneToOne
	@PrimaryKeyJoinColumn   
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_foa")
	private FrequencyOfActivity frequencyOfActivity;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_bt")
	private BodyType bodyType;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_sex")
	private Sex sex;
	
	public UserDetails() {}
	
	public UserDetails(UserDetailsBuilder builder) {
		this.userId = builder.userId;
		this.age = builder.age;
		this.height = builder.height;
		this.weight = builder.weight;
		this.bmi = builder.bmi;
		this.bmr = builder.bmr;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHeight() {
		return height;
	}


	public void setHeight(Integer height) {
		this.height = height;
	}



	public Integer getWeight() {
		return weight;
	}



	public void setWeight(Integer weight) {
		this.weight = weight;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public Integer getBmi() {
		return bmi;
	}



	public void setBmi(Integer bmi) {
		this.bmi = bmi;
	}



	public Integer getBmr() {
		return bmr;
	}



	public void setBmr(Integer bmr) {
		this.bmr = bmr;
	}



	public FrequencyOfActivity getFrequencyOfActivity() {
		return frequencyOfActivity;
	}



	public void setFrequencyOfActivity(FrequencyOfActivity frequencyOfActivity) {
		this.frequencyOfActivity = frequencyOfActivity;
	}



	public BodyType getBodyType() {
		return bodyType;
	}



	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}



	public Sex getSex() {
		return sex;
	}
 


	public void setSex(Sex sex) {
		this.sex = sex;
	}



	public Integer getUserId() {
		return userId;
	}



	public User getUser() {
		return user;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((bmi == null) ? 0 : bmi.hashCode());
		result = prime * result + ((bmr == null) ? 0 : bmr.hashCode());
		result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
		result = prime * result + ((frequencyOfActivity == null) ? 0 : frequencyOfActivity.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		UserDetails other = (UserDetails) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (bmi == null) {
			if (other.bmi != null)
				return false;
		} else if (!bmi.equals(other.bmi))
			return false;
		if (bmr == null) {
			if (other.bmr != null)
				return false;
		} else if (!bmr.equals(other.bmr))
			return false;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
			return false;
		if (frequencyOfActivity == null) {
			if (other.frequencyOfActivity != null)
				return false;
		} else if (!frequencyOfActivity.equals(other.frequencyOfActivity))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", height=" + height + ", weight=" + weight + ", age=" + age + ", bmi="
				+ bmi + ", bmr=" + bmr + ", frequencyOfActivity=" + frequencyOfActivity + ", bodyType=" + bodyType
				+ ", sex=" + sex + "]";
	}

	public static class UserDetailsBuilder {
		
		private Integer userId;
		private Integer height;
		private Integer weight;
		private Integer age;
		private Integer bmi; // Body Mass Index
		private Integer bmr; // Basal Metabolic Rate
		
		public UserDetailsBuilder setUserId(Integer userId) {
			this.userId = userId;
			return this;
		}
		
		public UserDetailsBuilder setHeight(Integer height) {
			this.height = height;
			return this;
		}
		
		public UserDetailsBuilder setWeight(Integer weight) {
			this.weight = weight;
			return this;
		}
		
		public UserDetailsBuilder setAge(Integer age) {
			this.age = age;
			return this;
		}
		
		public UserDetailsBuilder setBmi(Integer bmi) {
			this.bmi = bmi;
			return this;
		}
		
		public UserDetailsBuilder setBmr(Integer bmr) {
			this.bmr = bmr;
			return this;
		}
		
		
		public UserDetails build() {
			return new UserDetails(this);
		}
		
		
	}


	
}

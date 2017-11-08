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
	private Integer height;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "age")
	private Integer age;

	@Column(name = "BMI")
	private Integer bmi; // Body Mass Index

	@Column(name = "BMR")
	private Integer bmr; // Basal Metabolic Rate

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_foa")
	private FrequencyOfActivity frequencyOfActivity;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_bt")
	private BodyType bodyType;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_sex")
	private Sex sex;

	public UserDetails() {
	}

	public UserDetails(UserDetailsBuilder builder) {
		this.userId = builder.userId;
		this.height = builder.height;
		this.weight = builder.weight;
		this.age = builder.age;
		this.bmi = builder.bmi;
		this.bmr = builder.bmr;
		this.frequencyOfActivity = builder.frequencyOfActivity;
		this.bodyType = builder.bodyType;
		this.sex = builder.sex;
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

	public Integer getUserId() {
		return userId;
	}

	public FrequencyOfActivity getFrequencyOfActivity() {
		return frequencyOfActivity;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public Sex getSex() {
		return sex;
	}

	public void setFrequencyOfActivity(FrequencyOfActivity frequencyOfActivity) {
		this.frequencyOfActivity = frequencyOfActivity;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "UserDetails [height=" + height + ", weight=" + weight + ", age=" + age + ", bmi=" + bmi + ", bmr=" + bmr
				+ ", frequencyOfActivity=" + frequencyOfActivity + ", bodyType=" + bodyType + ", sex=" + sex + "]";
	}

	public static class UserDetailsBuilder {

		private Integer userId;
		private Integer height;
		private Integer weight;
		private Integer age;
		private Integer bmi;
		private Integer bmr;
		private FrequencyOfActivity frequencyOfActivity;
		private BodyType bodyType;
		private Sex sex;

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

		public UserDetailsBuilder setFrequencyOfActivity(FrequencyOfActivity frequencyOfActivity) {
			this.frequencyOfActivity = frequencyOfActivity;
			return this;
		}

		public UserDetailsBuilder setBodyType(BodyType bodyType) {
			this.bodyType = bodyType;
			return this;
		}

		public UserDetailsBuilder setSex(Sex sex) {
			this.sex = sex;
			return this;
		}

		public UserDetails build() {
			return new UserDetails(this);
		}

	}

}

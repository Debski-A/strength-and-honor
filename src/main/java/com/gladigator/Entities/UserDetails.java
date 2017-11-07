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

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = {@Parameter(name = "property", value = "user")}) //Konieczne aby pobierac ID z tabeli users
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id_user", unique = true, nullable = false)
	private Integer userId;

	
	@Column(name = "height")
	private Integer height;
	
	@Column(name = "weight")
	private Integer weight;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "BMI")
	private Integer bmi; //Body Mass Index
	
	@Column(name = "BMR")
	private Integer bmr; //Basal Metabolic Rate
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@PrimaryKeyJoinColumn
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_foa")
	private FrequencyOfActivity frequencyOfActivity;
	
	@OneToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_bt")
	private BodyType bodyType;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_sex")
	private Sex sex;

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

	public User getUser() {
		return user;
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

	
	
 
}

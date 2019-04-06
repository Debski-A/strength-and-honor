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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;

import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_details")
public class UserDetails {
	
	@Id
	@Column(name = "id_user")
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user") )
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
	private Double bmi; // Body Mass Index

	@Column(name = "BMR")
	private Double bmr; // Basal Metabolic Rate

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_foa")
	private FrequencyOfActivity frequencyOfActivity;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_bt")
	private BodyType bodyType;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_sex")
	private Sex sex;

}

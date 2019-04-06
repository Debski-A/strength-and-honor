package com.gladigator.Entities;

import lombok.*;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	@Setter(AccessLevel.NONE)
	private Integer userId;
	
	@NotBlank
	@Column(name="username", unique = true)
	private String username;
	
	@Transient
	private String password;
	
	@Column(name="password")
	private String encryptedPassword;
	
	@Email
	@NotBlank
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name ="confirmation_token")
	private String confirmationToken;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private UserDetails userDetails;													//Zaladowane od razu bo - EAGER, 
																						//Operacje PERSIST, REMOVE, REFRESH, MERGE, DETACH (DML - data manipulation language) 
																						//beda rowniez wykonane na powiazanych encjach
																						//w tym przypadku na UserDetails

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "users_has_roles",
	            joinColumns = @JoinColumn(name = "id_user"),
	            inverseJoinColumns = @JoinColumn(name = "id_role"))
	private List<Role> roles;
	
}

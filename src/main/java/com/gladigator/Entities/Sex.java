package com.gladigator.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex {
	
	
	//TODO:ZROB INTERNACJONALIZACJE BAZY DANYCH, ABY NP. BODYTYPE.TYPE ZAWIERALO INFORMACJE W ROZNYCH JEZYKACH (A NIE TYLKO W JEZ. ANG. - TAK JEST OBECNIE)

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sex")
	private Integer sexId;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_sex")
	private List<SexTranslations> sexTranslations;
	

	public Integer getSexId() {
		return sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}
	
	
	
}

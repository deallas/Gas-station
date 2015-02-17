package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@TypeDef(name="enumClientPeopleGender", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumClientPeopleGender.class)
@Entity
@Table(name="client_people")
public class ClientPeople implements Serializable {

	private static final long serialVersionUID = -9062502119169559646L;
	
	public enum Gender {
		UNKNOWN,
		MALE,
		FEMALE;
	}
	
	@Id
	@Column(name="CLIPk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CLIP_Name", length=50, unique=false, nullable=false)
	private String name;
	
	@Column(name="CLIP_Surname", length=50, unique=false, nullable=false)
	private String surname;
	
	@Column(name="CLIP_PESEL", length=11, unique=true, nullable=false)
	private String pesel;
	
	@Type(type="enumClientPeopleGender")
	@Column(name="CLIP_Gender", nullable=false, columnDefinition="SB_CLIENT_PEOPLE_GENDER")
	private Gender gender;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLI_1_Id", nullable=false)
	private Client client;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}

package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="regions")
public class Region implements Serializable  {

	private static final long serialVersionUID = 950041353196592313L;
	
	@Id
	@Column(name="REGk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="REG_Name", length=100, unique=false, nullable=false)
	private String name;
	
	@Column(name="REG_FIPScode", length=2, unique=false, nullable=false)
	private String fipsCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COU_1_Id", nullable = false)
	private Country country;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.City.class, 
			mappedBy="region")
    private Set<City> cities;

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

	public String getFipsCode() {
		return fipsCode;
	}

	public void setFipsCode(String fipsCode) {
		this.fipsCode = fipsCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}	
}

package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country implements Serializable {

	private static final long serialVersionUID = 5836074375885282971L;
	
	@Id
	@Column(name="COUk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="COU_Name", length=50, unique=false, nullable=false)
	private String name;
	
	@Column(name="COU_Iso2Name", length=2, unique=false, nullable=false)
	private String iso2Name;
	
	@Column(name="COU_Iso3Name", length=3, unique=false, nullable=false)
	private String iso3Name;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Region.class, 
			mappedBy="country")
    private Set<Region> regions;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.City.class, 
			mappedBy="country")
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

	public String getIso2Name() {
		return iso2Name;
	}

	public void setIso2Name(String iso2Name) {
		this.iso2Name = iso2Name;
	}

	public String getIso3Name() {
		return iso3Name;
	}

	public void setIso3Name(String iso3Name) {
		this.iso3Name = iso3Name;
	}

	public Set<Region> getRegions() {
		return regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	
	
}

package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="cities")
public class City implements Serializable  {

	private static final long serialVersionUID = 5140894228012273071L;
	
	@Id
	@Column(name="CITk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CIT_Name", length=100, unique=false, nullable=true)
	private String name;
	
	@Column(name="CIT_Urlname", length=100, unique=false, nullable=true)
	private String urlname;
	
	@Column(name="CIT_AccentCityName", length=100, unique=false, nullable=true)
	private String accentCityName;
	
	@Column(name="CIT_Latitude", precision = 8, scale = 5, unique=false, nullable=true) //JAK DECIMAL??
	private BigDecimal latitude;
	
	@Column(name="CIT_Longitude", precision = 8, scale = 5, unique=false, nullable=true) //JAK DECIMAL??
	private BigDecimal longitude;
	
	@Column(name="CIT_Population", unique=false, nullable=true)
	private int population;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REG_1_Id", nullable = false)
	private Region region;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COU_2_Id", nullable = false)
	private Country country;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.PetrolProvider.class, 
			mappedBy="city")
    private Set<PetrolProvider> petrolProviders;

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

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public String getAccentCityName() {
		return accentCityName;
	}

	public void setAccentCityName(String accentCityName) {
		this.accentCityName = accentCityName;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<PetrolProvider> getPetrolProviders() {
		return petrolProviders;
	}

	public void setPetrolProviders(Set<PetrolProvider> petrolProviders) {
		this.petrolProviders = petrolProviders;
	}
	
	
}

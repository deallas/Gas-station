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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@TypeDef(name="enumPetrolProviderStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumPetrolProviderStatus.class)
@Entity
@Table(name="petrol_providers")
public class PetrolProvider implements Serializable {

	private static final long serialVersionUID = -8558414818126000170L;
	
	public enum Status{
		INACTIVE,
		ACTIVE;
	}
	
	@Id
	@Column(name="PETPk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="PETP_CompanyName", length=50, unique=false, nullable=false)
	private String companyName;
	
	@Column(name="PETP_NIP", length=10, unique=true, nullable=true)
	private String nip;
	
	@Column(name="PETP_REGON", length=14, unique=true, nullable=true)
	private String regon;
	
	@Column(name="PETP_Email", length=50, unique=true, nullable=true)
	private String email;
	
	@Column(name="PETP_Address", length=150, unique=false, nullable=true)
	private String address;
	
	@Column(name="PETP_PhoneNumber", length=9, unique=false, nullable=true)
	private String phoneNumber;
	
	@Type(type="enumPetrolProviderStatus")
	@Column(name="PETP_Status", nullable=true, columnDefinition="SB_PETROL_PROVIDERS_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CIT_1_Id")
	private City city;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.PetrolDelivery.class, 
			mappedBy="petrolProvider")
    private Set<PetrolDelivery> petrolDeliveries;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getRegon() {
		return regon;
	}

	public void setRegon(String regon) {
		this.regon = regon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<PetrolDelivery> getPetrolDeliveries() {
		return petrolDeliveries;
	}

	public void setPetrolDeliveries(Set<PetrolDelivery> petrolDeliveries) {
		this.petrolDeliveries = petrolDeliveries;
	}
}

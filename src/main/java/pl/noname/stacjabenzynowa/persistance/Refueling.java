package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="refueling")
public class Refueling implements Serializable {

	private static final long serialVersionUID = -6832907186370321222L;
	
	@Id
	@Column(name="REFk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="REF_Cost", precision=8, scale=2, unique=false, nullable=false)
	private BigDecimal cost;
	
	@Column(name="REF_Fuel", precision=8, scale=2, unique=false, nullable=false)
	private BigDecimal fuel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REF_RefuelingDate",unique=false, nullable=false)
	private Date refuelingDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PETC_1_Id", nullable = false)
	private PetrolContainer petrolContainer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLI_2_Id", nullable = true)
	private Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REFT_3_Id", nullable = false)
	private RefuelingType refuelingType;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Refueling.class, 
			mappedBy="refueling")
    private Set<Refueling> refueling;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getFuel() {
		return fuel;
	}

	public void setFuel(BigDecimal fuel) {
		this.fuel = fuel;
	}

	public Date getRefuelingDate() {
		return refuelingDate;
	}

	public void setRefuelingDate(Date refuelingDate) {
		this.refuelingDate = refuelingDate;
	}

	public PetrolContainer getPetrolContainer() {
		return petrolContainer;
	}

	public void setPetrolContainer(PetrolContainer petrolContainer) {
		this.petrolContainer = petrolContainer;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public RefuelingType getRefuelingType() {
		return refuelingType;
	}

	public void setRefuelingType(RefuelingType refuelingType) {
		this.refuelingType = refuelingType;
	}

	public Set<Refueling> getRefueling() {
		return refueling;
	}

	public void setRefueling(Set<Refueling> refueling) {
		this.refueling = refueling;
	}
	
}

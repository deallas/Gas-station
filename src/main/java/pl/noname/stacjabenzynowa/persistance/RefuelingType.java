package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="refueling_types")
public class RefuelingType implements Serializable {

	private static final long serialVersionUID = -8927404522577960299L;

	@Id
	@Column(name="REFTk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="REFT_Name", length=50, unique=false, nullable=false)
	private String name;
	
	@Column(name="REFT_Cost", precision=5, scale=2, unique=false, nullable=false)
	private BigDecimal cost;
	
	@Column(name="REFT_LoyalityPoints", nullable=false)
	private Integer loyalityPoints;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Refueling.class, 
			mappedBy="refuelingType")
    private Set<Refueling> refueling;

	/* ----------------------------------------------------------- */
	
	public RefuelingType(String name, BigDecimal cost, Integer loyalityPoints) {
		this.name = name;
		this.cost = cost;
		this.loyalityPoints = loyalityPoints;
	}
	
	public RefuelingType() {}
	
	/* ----------------------------------------------------------- */
	
	public Integer getId() {
		return id;
	}

	public Integer getLoyalityPoints() {
		return loyalityPoints;
	}

	public void setLoyalityPoints(Integer loyalityPoints) {
		this.loyalityPoints = loyalityPoints;
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Set<Refueling> getRefueling() {
		return refueling;
	}

	public void setRefueling(Set<Refueling> refueling) {
		this.refueling = refueling;
	}
	
}

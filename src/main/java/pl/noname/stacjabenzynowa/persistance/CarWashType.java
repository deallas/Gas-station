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
@Table(name="car_wash_types")
public class CarWashType implements Serializable {

	private static final long serialVersionUID = -6426342114076639786L;
	
	@Id
	@Column(name="CAWTk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CAWT_Name", length=50, unique=false, nullable=false)
	private String name;
	
	@Column(name="CAWT_Cost", precision=5, scale=2, unique=false, nullable=false)
	private BigDecimal cost;
	
	@Column(name="CAWT_LoyalityPoints", nullable=false)
	private Integer loyalityPoints;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.CarWash.class, 
			mappedBy="carWashType")
    private Set<CarWash> carWashes;

	/* ----------------------------------------------------------- */

	public CarWashType(String name, BigDecimal cost, Integer loyalityPoints) {
		this.name = name;
		this.cost = cost;
		this.loyalityPoints = loyalityPoints;
	}
	
	public CarWashType() {}
	
	/* ----------------------------------------------------------- */
	
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Set<CarWash> getCarWashes() {
		return carWashes;
	}

	public void setCarWashes(Set<CarWash> carWashes) {
		this.carWashes = carWashes;
	}

	public Integer getLoyalityPoints() {
		return loyalityPoints;
	}

	public void setLoyalityPoints(Integer loyalityPoints) {
		this.loyalityPoints = loyalityPoints;
	}
	
}

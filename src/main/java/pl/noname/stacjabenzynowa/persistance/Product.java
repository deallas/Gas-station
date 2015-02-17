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
@Table(name="products")
public class Product implements Serializable {

	private static final long serialVersionUID = 7565255394932839233L;
	
	@Id
	@Column(name="PROk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="PRO_Name", length=100, unique=false, nullable=false)
	private String name;
	
	@Column(name="PRO_Cost", precision=8, scale=2, unique=false, nullable=false)
	private BigDecimal cost;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.BillElement.class, 
			mappedBy="product")
    private Set<BillElement> billElements;

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
}

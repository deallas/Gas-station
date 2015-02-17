package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bill_elements")
public class BillElement implements Serializable {

	private static final long serialVersionUID = 2395045650850862790L;
	
	@Id
	@Column(name="BILEk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRO_1_Id")
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REF_2_Id")
	private Refueling refueling;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARW_3_Id")
	private CarWash carWash;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BIL_4_Id")
	private Bill bill;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Refueling getRefueling() {
		return refueling;
	}

	public void setRefueling(Refueling refueling) {
		this.refueling = refueling;
	}

	public CarWash getCarWash() {
		return carWash;
	}

	public void setCarWash(CarWash carWash) {
		this.carWash = carWash;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
}

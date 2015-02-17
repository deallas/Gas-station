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
@Table(name="petrol_containers_deliveries")
public class PetrolContainersDelivery implements Serializable {

	private static final long serialVersionUID = 1859365763934947892L;

	@Id
	@Column(name="PECDk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PETD_1_Id", nullable = false)
	private PetrolDelivery petrolDelivery;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PETC_2_Id", nullable = false)
	private PetrolContainer petrolContainer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PetrolDelivery getPetrolDelivery() {
		return petrolDelivery;
	}

	public void setPetrolDelivery(PetrolDelivery petrolDelivery) {
		this.petrolDelivery = petrolDelivery;
	}

	public PetrolContainer getPetrolContainer() {
		return petrolContainer;
	}

	public void setPetrolContainer(PetrolContainer petrolContainer) {
		this.petrolContainer = petrolContainer;
	}
	
}

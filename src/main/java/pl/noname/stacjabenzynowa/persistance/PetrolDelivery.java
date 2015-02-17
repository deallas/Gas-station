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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@TypeDef(name="enumPetrolDeliveryStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumPetrolDeliveryStatus.class)
@Entity
@Table(name="petrol_deliveries")
public class PetrolDelivery implements Serializable {

	private static final long serialVersionUID = -2438783519188714900L;
	
	public enum Status{
		WAITING,
		COMPLETED;
	}
	
	@Id
	@Column(name="PETDk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PETD_DeliveryDate", unique=false, nullable=false)
    private Date deliveryDate;
	
	@Column(name="PETD_Quantity", unique=false, nullable=false)
	private int quantity;
	
	@Column(name="PETD_Cost", precision=8, scale=2, unique=false, nullable=false)
	private BigDecimal cost;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PETD_OrderDate", unique=false, nullable=false)
    private Date orderDate;
	
	@Type(type="enumPetrolDeliveryStatus")
	@Column(name="PETD_Status", nullable=false, columnDefinition="SB_PETROL_DELIVERIES_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PETP_1_Id", nullable = true)
	private PetrolProvider petrolProvider;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.PetrolContainersDelivery.class, 
			mappedBy="petrolDelivery")
    private Set<PetrolContainersDelivery> petrolContainersDeliveries;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PetrolProvider getPetrolProvider() {
		return petrolProvider;
	}

	public void setPetrolProvider(PetrolProvider petrolProvider) {
		this.petrolProvider = petrolProvider;
	}

	public Set<PetrolContainersDelivery> getPetrolContainersDeliveries() {
		return petrolContainersDeliveries;
	}

	public void setPetrolContainersDeliveries(
			Set<PetrolContainersDelivery> petrolContainersDeliveries) {
		this.petrolContainersDeliveries = petrolContainersDeliveries;
	}
}

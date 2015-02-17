package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.TypeDef;

@TypeDef(name="enumPetrolContainerMeasurementType", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumPetrolContainerMeasurementType.class)
@Entity
@Table(name="petrol_container_measurements")
public class PetrolContainerMeasurement implements Serializable {

	private static final long serialVersionUID = 6815484684202381412L;

	public enum Type {
		PRESSURE,
		PETROL_LEVEL;
	}
	
	@Id
	@Column(name="PECMk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PECM_MeasurementDate",unique=false, nullable=false)
	private Date measurementDate;
	
	@org.hibernate.annotations.Type(type="enumPetrolContainerMeasurementType")
	@Column(name="PECM_Type", nullable=false, columnDefinition="SB_PETROL_CONTAINERS_MEASUREMENTS_TYPE")
	private Type type;
	
	@Column(name="PECM_Value", precision=10, scale=2, unique=false, nullable=false)
	private BigDecimal value;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PETC_1_Id", nullable = false)
	private PetrolContainer petrolContainer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public PetrolContainer getPetrolContainer() {
		return petrolContainer;
	}

	public void setPetrolContainer(PetrolContainer petrolContainer) {
		this.petrolContainer = petrolContainer;
	}
}

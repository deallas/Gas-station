package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

@TypeDef(name="enumPetrolContainerType", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumPetrolContainerType.class)
@Entity
@Table(name="petrol_containers")
public class PetrolContainer implements Serializable {

	private static final long serialVersionUID = 2760119131611188487L;

	public enum Type {
		E95,
		E98,
		ON,
		LPG;
	}
	
	@Id
	@Column(name="PETCk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@org.hibernate.annotations.Type(type="enumPetrolContainerType")
	@Column(name="PETC_Type", nullable=false, columnDefinition="SB_PETROL_CONTAINERS_TYPE")
	private Type type;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Refueling.class, 
			mappedBy="petrolContainer")
    private Set<Refueling> refueling;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.PetrolContainerLog.class, 
			mappedBy="petrolContainer")
    private Set<PetrolContainerLog> containerLog;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement.class, 
			mappedBy="petrolContainer")
    private Set<PetrolContainerMeasurement> containerMeasurement;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.PetrolContainersDelivery.class, 
			mappedBy="petrolContainer")
    private Set<PetrolContainersDelivery> containersDelivery;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<Refueling> getRefueling() {
		return refueling;
	}

	public void setRefueling(Set<Refueling> refueling) {
		this.refueling = refueling;
	}

	public Set<PetrolContainerLog> getContainerLog() {
		return containerLog;
	}

	public void setContainerLog(Set<PetrolContainerLog> containerLog) {
		this.containerLog = containerLog;
	}

	public Set<PetrolContainerMeasurement> getContainerMeasurement() {
		return containerMeasurement;
	}

	public void setContainerMeasurement(
			Set<PetrolContainerMeasurement> containerMeasurement) {
		this.containerMeasurement = containerMeasurement;
	}

	public Set<PetrolContainersDelivery> getContainersDelivery() {
		return containersDelivery;
	}

	public void setContainersDelivery(
			Set<PetrolContainersDelivery> containersDelivery) {
		this.containersDelivery = containersDelivery;
	}

}

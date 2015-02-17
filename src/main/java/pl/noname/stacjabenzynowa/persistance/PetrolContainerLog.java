package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
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

@Entity
@Table(name="petrol_container_logs")
public class PetrolContainerLog implements Serializable {

	private static final long serialVersionUID = -4102141197533610961L;

	@Id
	@Column(name="PECLk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PECL_DateAdded",unique=false, nullable=false)
	private Date dateAdded;
	
	@Column(name="PECL_Message", unique = false, nullable = false)
	private String message;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PETC_1_Id", nullable = false)
	private PetrolContainer petrolContainer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PetrolContainer getPetrolContainer() {
		return petrolContainer;
	}

	public void setPetrolContainer(PetrolContainer petrolContainer) {
		this.petrolContainer = petrolContainer;
	}
}

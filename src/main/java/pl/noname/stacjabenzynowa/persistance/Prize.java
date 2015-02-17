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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Table(name="prizes")
@TypeDef(name="enumPrizeStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumPrizeStatus.class)
public class Prize implements Serializable {

	private static final long serialVersionUID = -6477835760891085931L;
	
	public enum Status {
		WAITING,
		COMPLETED;
	}
	
	@Id
	@Column(name="PRIZk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PRIZ_DateOfSelection",unique=false, nullable=false)
	private Date dateOfSelection;
	
	@Type(type="enumPrizeStatus")
	@Column(name="PRIZ_Status", nullable=false, columnDefinition="SB_PRIZE_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLI_1_Id", nullable=false)
	private Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRCT_2_Id", nullable=false)
	private PrizeCategory category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateOfSelection() {
		return dateOfSelection;
	}

	public void setDateOfSelection(Date dateOfSelection) {
		this.dateOfSelection = dateOfSelection;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public PrizeCategory getCategory() {
		return category;
	}

	public void setCategory(PrizeCategory category) {
		this.category = category;
	}
}

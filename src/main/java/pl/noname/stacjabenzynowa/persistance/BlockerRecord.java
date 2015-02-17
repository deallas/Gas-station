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

@TypeDef(name="enumBlockerRecordStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumBlockerRecordStatus.class)
@Entity
@Table(name="blocker_records")
public class BlockerRecord implements Serializable {

	private static final long serialVersionUID = -2274156832541961569L;
	
	public enum Status {
		INACTIVE,
		ACTIVE;
	}

	@Id
	@Column(name="BLORk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="BLOR_Ip", length = 30, unique=false, nullable=false)
	private String ip;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BLOR_DateCreated",unique=false, nullable=false)
	private Date dateCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BLOR_DateExpired",unique=false, nullable=true)
	private Date dateExpired;
	
	@Column(name="BLOR_Message", unique=false, nullable=true)
	private String message;
	
	@Column(name="BLOR_Priority",length = 45, unique=false, nullable=false)
	private String priority;
	
	@Type(type="enumBlockerRecordStatus")
	@Column(name="BLOR_Status", nullable=false, columnDefinition="SB_BLOCKER_RECORDS_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RESG_1_Id")
	private AclResgroup resgroup;
	
	/* ----------------------------------------------------------- */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AclResgroup getResgroup() {
		return resgroup;
	}

	public void setResgroup(AclResgroup resgroup) {
		this.resgroup = resgroup;
	}
	
}

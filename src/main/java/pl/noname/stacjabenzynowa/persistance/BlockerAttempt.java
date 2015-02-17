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


@TypeDef(name="enumBlockerAttemptStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumBlockerAttemptStatus.class)
@Entity
@Table(name="blocker_attempts")
public class BlockerAttempt implements Serializable {
	
	private static final long serialVersionUID = -5765846336868055405L;

	public enum Status {
		EXPIRED,
		ACTIVE;
	}
	
	@Id
	@Column(name="BLOAk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="BLOA_Ip", length = 30, nullable=false)
	private String ip;
	
	@Column(name="BLOA_Attempts", nullable=false)
	private int attempts;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BLOA_DateExpired", nullable=false)
	private Date dateExpired = null;
	
	@Type(type="enumBlockerAttemptStatus")
	@Column(name="BLOA_Status", nullable=false, columnDefinition="SB_BLOCKER_ATTEMPTS_STATUS")
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

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
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

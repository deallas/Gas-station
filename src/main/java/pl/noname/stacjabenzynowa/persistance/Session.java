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

@TypeDef(name="enumSessionStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumSessionStatus.class)
@Entity
@Table(name="sessions")
public class Session implements Serializable {

	private static final long serialVersionUID = 4542594502474286223L;
	
	public enum Status{
		ACTIVE,
		EXPIRED,
		STOLEN;
	}
	
	@Id
	@Column(name="SESk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="SES_Key", length=50, unique=true, nullable=false)
	private String key;
	
	@Column(name="SES_Value", unique=false, nullable=true)
	private String value;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SES_DateCreated", unique=false, nullable=false)
    private Date dateCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SES_DateExpired", unique=false, nullable=false)
    private Date dateExpired;
	
	@Column(name="SES_Ip", length=30, unique=false, nullable=false)
	private String ip;
	
	@Column(name="SES_Useragent", length=150, unique=false, nullable=true)
	private String useragent;
	
	@Type(type="enumSessionStatus")
	@Column(name="SES_Status", nullable=false, columnDefinition="SB_SESSION_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLI_1_Id")
	private Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EMP_2_Id")
	private Employee employee;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}

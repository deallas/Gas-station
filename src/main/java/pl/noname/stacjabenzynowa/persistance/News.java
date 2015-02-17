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

@TypeDef(name="enumNewsStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumNewsStatus.class)
@Entity
@Table(name="news")
public class News implements Serializable {

	private static final long serialVersionUID = -3488327285788241361L;
	
	public enum Status {
		INACTIVE,
		ACTIVE;
	}
	
	@Id
	@Column(name="NEWSk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="NEWS_Name", length=50, unique=false, nullable=false)
	private String name;

	@Column(name="NEWS_ShortDescription", length=200, unique=false, nullable=true)
	private String description = null;
	
	@Column(name="NEWS_Text", unique=false, nullable=true)
	private String text = null;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="NEWS_DateAdded",unique=false, nullable=false)
	private Date dateAdded;
	
	@Type(type="enumNewsStatus")
	@Column(name="NEWS_Status", nullable=false, columnDefinition="SB_NEWS_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EMP_1_Id")
	private Employee employee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
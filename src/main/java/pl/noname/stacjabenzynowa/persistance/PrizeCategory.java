package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@TypeDef(name="enumPrizeCategoryStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumPrizeCategoryStatus.class)
@Entity
@Table(name="prize_categories")
public class PrizeCategory implements Serializable {

	private static final long serialVersionUID = 4808296527189651072L;
	
	public enum Status {
		ACTIVE, 
		INACTIVE;
	}
	
	@Id
	@Column(name="PRCTk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="PRCT_Name", length=50, unique=false, nullable=false)
	private String name;
	
	@Column(name="PRCT_MinPoints", unique=false, nullable=false)
	private int minPoints;
	
	@Type(type="enumPrizeCategoryStatus")
	@Column(name="PRCT_Status", nullable=false, columnDefinition="SB_PRIZE_CATEGORIES_STATUS")
	private Status status;

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

	public int getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(int minPoints) {
		this.minPoints = minPoints;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
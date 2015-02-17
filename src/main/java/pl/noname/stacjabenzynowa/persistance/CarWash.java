package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
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

@Entity
@Table(name="car_wash")
public class CarWash implements Serializable {

	private static final long serialVersionUID = 4195673132458287825L;

	@Id
	@Column(name="CARWk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CARW_DateBeginWash", unique=false, nullable=false)
    private Date dateBeginWash;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CARW_DateEndWash", unique=false, nullable=false)
    private Date dateEndWash;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CAWT_1_Id")
	private CarWashType carWashType;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.BillElement.class, 
			mappedBy="carWash")
    private Set<BillElement> billElements;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateBeginWash() {
		return dateBeginWash;
	}

	public void setDateBeginWash(Date dateBeginWash) {
		this.dateBeginWash = dateBeginWash;
	}

	public Date getDateEndWash() {
		return dateEndWash;
	}

	public void setDateEndWash(Date dateEndWash) {
		this.dateEndWash = dateEndWash;
	}

	public CarWashType getCarWashType() {
		return carWashType;
	}

	public void setCarWashType(CarWashType carWashType) {
		this.carWashType = carWashType;
	}

	public Set<BillElement> getBillElements() {
		return billElements;
	}

	public void setBillElements(Set<BillElement> billElements) {
		this.billElements = billElements;
	}

}

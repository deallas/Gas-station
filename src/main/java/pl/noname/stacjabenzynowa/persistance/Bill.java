package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="bills")
public class Bill implements Serializable {

	private static final long serialVersionUID = -7280660054801364732L;

	@Id
	@Column(name="BILk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BIL_InvoiceDate",unique=false, nullable=false)
	private Date invoiceDate;
	
	@Column(name="BIL_Amount", precision=8, scale=2, unique=false, nullable=false)
	private BigDecimal amount;
	
	@Column(name="BIL_VAT", precision=7, scale=2, unique=false, nullable=false)
	private BigDecimal vat;
	
	@Column(name="BIL_Total", precision=8, scale=2, unique=false, nullable=false)
	private BigDecimal total;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLI_1_Id", nullable = true)
	private Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EMP_2_Id", nullable = false)
	private Employee employee;

	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.BillElement.class, 
			mappedBy="bill")
    private Set<BillElement> billElements;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public Set<BillElement> getBillElements() {
		return billElements;
	}

	public void setBillElements(Set<BillElement> billElements) {
		this.billElements = billElements;
	}
}

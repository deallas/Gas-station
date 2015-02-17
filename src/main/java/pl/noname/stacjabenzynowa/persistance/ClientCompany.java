package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="client_companies")
public class ClientCompany implements Serializable {

	private static final long serialVersionUID = -136436164963057081L;
	
	@Id
	@Column(name="CLICk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CLIC_CompanyName", length=50, unique=false, nullable=false)
	private String companyName;
	
	@Column(name="CLIC_REGON", length=14, unique=true, nullable=false)
	private String regon;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLI_1_Id", nullable=false)
	private Client client;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegon() {
		return regon;
	}

	public void setRegon(String regon) {
		this.regon = regon;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
}

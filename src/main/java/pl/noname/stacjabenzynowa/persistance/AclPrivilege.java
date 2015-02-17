package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="acl_privileges")
public class AclPrivilege implements Serializable {

	private static final long serialVersionUID = 6875462530782754051L;

	@Id
	@Column(name="PRIVk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="PRIV_Name", length=30, unique=false, nullable=false)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RES_1_Id", nullable=false)
	private AclResource resource;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclRulesPrivilege.class, 
			mappedBy = "privilege")
	private Set<AclRulesPrivilege> rulesPrivileges;

	/* ----------------------------------------------------------- */
	

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
	
	public AclResource getResource() {
		return resource;
	}

	public void setResource(AclResource resource) {
		this.resource = resource;
	}
	
	public Set<AclRulesPrivilege> getRulesPrivileges() {
		return rulesPrivileges;
	}

	public void setRulesPrivileges(Set<AclRulesPrivilege> rulesPrivileges) {
		this.rulesPrivileges = rulesPrivileges;
	}
}

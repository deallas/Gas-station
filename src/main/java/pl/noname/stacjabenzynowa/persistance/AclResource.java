package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="acl_resources")
public class AclResource implements Serializable
{	
	private static final long serialVersionUID = 2141818616636567452L;

	@Id
	@Column(name="RESk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="RES_Name", length=50, unique=true, nullable=false)
	private String name;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclResourcesResgroup.class, 
			mappedBy = "resource")
	private Set<AclResourcesResgroup> resourcesResgroups;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclPrivilege.class, 
			mappedBy = "resource")
	private Set<AclPrivilege> privileges;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclRule.class, 
			mappedBy = "resource")
	private Set<AclRule> rules;

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

	public Set<AclResourcesResgroup> getResourcesResgroups() {
		return resourcesResgroups;
	}

	public void setResourcesResgroups(Set<AclResourcesResgroup> resourcesResgroups) {
		this.resourcesResgroups = resourcesResgroups;
	}

	public Set<AclPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<AclPrivilege> privileges) {
		this.privileges = privileges;
	}

	public Set<AclRule> getRules() {
		return rules;
	}

	public void setRules(Set<AclRule> rules) {
		this.rules = rules;
	}
}

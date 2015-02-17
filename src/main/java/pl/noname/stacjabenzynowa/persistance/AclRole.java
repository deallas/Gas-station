package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="acl_roles")
public class AclRole implements Serializable {

	private static final long serialVersionUID = -6017412351730808662L;
	
	@Id
	@Column(name="ROLk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="ROL_Name", length = 30, unique=true, nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "role")
	private Set<AclRule> rules;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclRoleParent.class,
			mappedBy = "role")
	private Set<AclRoleParent> roles;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclRoleParent.class,
			mappedBy = "roleParent")
	private Set<AclRoleParent> rolesParents;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Client.class, 
			mappedBy = "role")
	private Set<Client> clients;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Employee.class, 
			mappedBy = "role")
	private Set<Employee> employees;
	
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

	public Set<AclRule> getRules() {
		return rules;
	}

	public void setRules(Set<AclRule> rules) {
		this.rules = rules;
	}

	public Set<AclRoleParent> getRoles() {
		return roles;
	}

	public void setRoles(Set<AclRoleParent> roles) {
		this.roles = roles;
	}

	public Set<AclRoleParent> getRolesParents() {
		return rolesParents;
	}

	public void setRolesParents(Set<AclRoleParent> rolesParents) {
		this.rolesParents = rolesParents;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
}

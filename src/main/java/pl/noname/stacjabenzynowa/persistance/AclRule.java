package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
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
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Type;

@TypeDef(name="enumAclRuleAction", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumAclRuleAction.class)
@Entity
@Table(name="acl_rules")
public class AclRule implements Serializable {

	private static final long serialVersionUID = -4185301533804441766L;
	
	public enum Action {
		ALLOWED,
		DENIED;
	}
	
	@Id
	@Column(name="RULk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="RUL_Action", nullable=false, columnDefinition="SB_ACL_RULE_TYPE")
	@Type(type="enumAclRuleAction")
	Action action;
	
	@Column(name="RUL_Priority", unique=false, nullable=false)
	private int priority;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROL_1_Id", nullable = true)
	private AclRole role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RES_2_Id", nullable = true)
	private AclResource resource;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.AclRulesPrivilege.class, 
			mappedBy = "rule")
	private Set<AclRulesPrivilege> rulesPrivileges;

	/* ----------------------------------------------------------- */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public AclRole getRole() {
		return role;
	}

	public void setRole(AclRole role) {
		this.role = role;
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
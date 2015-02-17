package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="acl_rules_privileges")
public class AclRulesPrivilege implements Serializable {

	private static final long serialVersionUID = -5835759642830492215L;
	
	@Id
	@Column(name="RULPk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RUL_1_Id", nullable = false)
	private AclRule rule;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRIV_2_Id", nullable = false)
	private AclPrivilege privilege;

	/* ----------------------------------------------------------- */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AclRule getRule() {
		return rule;
	}

	public void setRule(AclRule rule) {
		this.rule = rule;
	}

	public AclPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(AclPrivilege privilege) {
		this.privilege = privilege;
	}
	
}
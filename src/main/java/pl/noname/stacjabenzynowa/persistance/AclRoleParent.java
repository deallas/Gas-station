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
@Table(name="acl_role_parents")
public class AclRoleParent implements Serializable {

	private static final long serialVersionUID = 1868415518870948208L;
	
	@Id
	@Column(name="ROLPk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROL_1_Id")
	private AclRole role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROL_2_Id_parent")
	private AclRole roleParent;

	/* ----------------------------------------------------------- */
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AclRole getRole() {
		return role;
	}

	public void setRole(AclRole role) {
		this.role = role;
	}

	public AclRole getRoleParent() {
		return roleParent;
	}

	public void setRoleParent(AclRole roleParent) {
		this.roleParent = roleParent;
	}
}

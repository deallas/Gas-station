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
@Table(name="acl_resources_resgroups")
public class AclResourcesResgroup implements Serializable {

	private static final long serialVersionUID = -6222098839239356861L;

	@Id
	@Column(name="REGPk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RES_1_Id", nullable = false)
	private AclResource resource;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RESG_2_Id", nullable = false)
	private AclResgroup resgroup;

	/* ----------------------------------------------------------- */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AclResource getResource() {
		return resource;
	}

	public void setResource(AclResource resource) {
		this.resource = resource;
	}

	public AclResgroup getResgroup() {
		return resgroup;
	}

	public void setResgroup(AclResgroup resgroup) {
		this.resgroup = resgroup;
	}
}

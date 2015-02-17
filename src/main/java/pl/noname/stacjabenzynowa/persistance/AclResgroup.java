package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="acl_resgroups")
public class AclResgroup implements Serializable {

	private static final long serialVersionUID = -2212393640573939780L;

	@Id
	@Column(name="RESGk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="RESG_Name", length=30, unique=true, nullable=false)
	private String name;

	@OneToMany(mappedBy = "resgroup", cascade=CascadeType.ALL)
	private Set<BlockerAttempt> attempts;
	
	@OneToMany(mappedBy = "resgroup", cascade=CascadeType.ALL)
	private Set<BlockerRecord> records;
	
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
	
	public Set<BlockerAttempt> getAttempts() {
		return attempts;
	}

	public void setAttempts(Set<BlockerAttempt> attempts) {
		this.attempts = attempts;
	}

	public Set<BlockerRecord> getRecords() {
		return records;
	}

	public void setRecords(Set<BlockerRecord> records) {
		this.records = records;
	}
}

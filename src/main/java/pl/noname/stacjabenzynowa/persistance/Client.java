package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pl.noname.stacjabenzynowa.security.CustomAuthority;
import pl.noname.stacjabenzynowa.util.SBUtils;

@TypeDef(name="enumClientStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumClientStatus.class)
@Entity
@Table(name="clients")
public class Client implements Serializable, UserDetails {

	private static final long serialVersionUID = -2086962309223783935L;
	
	public enum Status {
		INACTIVE,
		ACTIVE,
		BANNED;
	}
	
	@Id
	@Column(name="CLIk_1_Id", columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CLI_NIP", length=10, unique=false, nullable=true)
	private String nip;
	
	@Column(name="CLI_Password", length=60, unique=false, nullable=false)
	private String password;
	
	@Column(name="CLI_Email", length=100, unique=true, nullable=false)
	private String email;
	
	@Column(name="CLI_Address", length=150, unique=false, nullable=true)
	private String address;
	
	@Column(name="CLI_PhoneNumber", length=9, unique=false, nullable=false)
	private String phoneNumber;
	
	@Column(name="CLI_Points", unique=false, nullable=true)
	private Integer points = 0;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLI_DateAdded", unique=false, nullable=false)
    private Date dateAdded;
	
	@Type(type="enumClientStatus")
	@Column(name="CLI_Status", nullable=true, columnDefinition="SB_USER_STATUS")
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CIT_1_Id", nullable = true)
	private City city;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROL_2_Id", nullable = false)
	private AclRole role;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.ClientPeople.class, 
			mappedBy="client")
    private Set<ClientPeople> clientPeople;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.ClientCompany.class, 
			mappedBy="client")
    private Set<ClientCompany> companies;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Session.class, 
			mappedBy="client")
    private Set<Session> sessions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public AclRole getRole() {
		return role;
	}

	public void setRole(AclRole role) {
		this.role = role;
	}

	public Set<ClientPeople> getClientPeople() {
		return clientPeople;
	}

	public void setClientPeople(Set<ClientPeople> clientPeople) {
		this.clientPeople = clientPeople;
	}

	public Set<ClientCompany> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<ClientCompany> companies) {
		this.companies = companies;
	}

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<CustomAuthority> authorities = new HashSet<CustomAuthority>();
		SBUtils.getRoles(role, authorities);
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.status == Client.Status.INACTIVE){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.status == Client.Status.BANNED){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if(this.status == Client.Status.INACTIVE){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isEnabled() {
		if(this.status == Client.Status.ACTIVE){
			return true;
		}else{
			return false;
		}
	}

	
}

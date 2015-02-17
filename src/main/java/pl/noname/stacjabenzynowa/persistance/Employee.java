package pl.noname.stacjabenzynowa.persistance;

import java.io.Serializable;
import java.util.Collection;
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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pl.noname.stacjabenzynowa.security.CustomAuthority;
import pl.noname.stacjabenzynowa.util.SBUtils;

@TypeDefs({
	@TypeDef(name="enumEmployeeGender", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumEmployeeGender.class),
	@TypeDef(name="enumEmployeeStatus", typeClass=pl.noname.stacjabenzynowa.persistance.enumconverter.EnumEmployeeStatus.class)
})
@Entity
@Table(name="employees")
public class Employee implements Serializable, UserDetails {

	private static final long serialVersionUID = 8464457793788610041L;
	
	public enum Status
	{
		INACTIVE,
		ACTIVE,
		BANNED;
	}
	
	public enum Gender {
		UNKNOWN,
		MALE,
		FEMALE;
	}
	
	@Id
	@Column(name="EMPk_1_Id", columnDefinition = "serial")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="EMP_Name", length=50, unique=false, nullable=false)
	private String name;
	
	@Column(name="EMP_Surname", length=50, unique=false, nullable=false)
	private String surname;
	
	@Column(name="EMP_PESEL",length=11, unique=true, nullable=false)
	private String pesel;
	
	@Column(name="EMP_NIP", length=10, unique=true, nullable=true)
	private String nip;
	
	@Type(type="enumEmployeeGender")
	@Column(name="EMP_Gender", nullable=true, columnDefinition="SB_EMPLOYEES_GENDER")
	private Gender gender;
	
	@Column(name="EMP_Email", length=100, unique=true, nullable=false)
	private String email;
	
	@Column(name="EMP_PhoneNumber", length=9, unique=false, nullable=true)
	private String phoneNumber;
	
	@Column(name="EMP_Password", length=60, unique=false, nullable=false)
	private String password;
	
	@Type(type="enumEmployeeStatus")
	@Column(name="EMP_Status", nullable=true, columnDefinition="SB_EMPLOYEES_STATUS")
	private Status status;
	
	@Column(name="EMP_Address", length=150, unique=false, nullable=true)
	private String address;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CIT_1_Id", nullable=true)
	private City city;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROL_2_Id", nullable=false)
	private AclRole role;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.Session.class, 
			mappedBy="employee")
    private Set<Session> sessions;
	
	@OneToMany(targetEntity=pl.noname.stacjabenzynowa.persistance.News.class, 
			mappedBy="employee")
    private Set<News> news;

	public Employee() {
		this.gender = Gender.UNKNOWN;
		this.status = Status.INACTIVE;
	}
	
	public Employee(String name, String surname, String pesel, String nip,
			Gender gender, String email, String phoneNumber, String password, 
			Status status, String address, City city, AclRole role) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.nip = nip;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.status = status;
		this.address = address;
		this.city = city;
		this.role = role;
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
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
		if(this.status == Employee.Status.INACTIVE){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.status == Employee.Status.BANNED){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if(this.status == Employee.Status.INACTIVE){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isEnabled() {
		if(this.status == Employee.Status.ACTIVE){
			return true;
		}else{
			return false;
		}
	}
}

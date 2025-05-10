package com.iportal.model.jobs;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.iportal.model.Catalogue;
import com.iportal.model.City;
import com.iportal.model.Country;

/**
 * @version 2.0
 * @created 01-Jun-2006 5:38:02 PM
 */
@Entity
@Table(name="tb_requester")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="requester",
								allocationSize=20
								)

public class Requester  {

	private Long code;
	private String firstName;
	private String lastName;
	private City city;
	private String address;
	private String phoneNumber;
	private String vitae;
	private Calendar dateCreated;
	private Boolean gender;
	private Catalogue maritalStatus;
	private Catalogue instructionLevel;
	private Catalogue studyBranch;
	private Catalogue salaryAspiration;
	private Boolean isWorking;
    private Set<RequesterWorkCity> requesterWorkCity;
    private String identification;
    private String secondName;
    private String secondLastName;
    private String officePhone;
    private String cell;
    private String email;
    private Calendar birthDate;
	private Boolean vehicle;
	private Boolean travelPossibity;
	private String enterpriceName;
	private String enterpriceCity;
	private Boolean briefApplication;
	private String addInfo;
	private Country country;
	private String requestedCity;

	@Id 
    @Column(name="requester_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	
	/**
	 * @return Returns the address.
	 */
	@Column(name="requester_address")
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the city.
	 */
	@ManyToOne
	@JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	/**
	 * @return Returns the curriculumVitae.
	 */
	@Column(name="requester_vitae")
	public String getVitae() {
		return vitae;
	}
	/**
	 * @param curriculumVitae The curriculumVitae to set.
	 */
	public void setVitae(String vitae) {
		this.vitae = vitae;
	}
	
	/**
	 * @return Returns the date when the application is submitted by the requestor.
	 */
	@Column(name="requester_aplication_date")
	@Temporal(TemporalType.TIMESTAMP)
	@org.hibernate.annotations.Type(type="calendar")

	public Calendar getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated The dateCreated to set.
	 */
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	/**
	 * @return Returns the firstName.
	 */
	@Column(name="requester_first_name")
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the lastName.
	 */
	@Column(name="requester_last_name")
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	@Column(name="requester_phone")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return Returns the gender.
	 */
	@Column(name="requester_gender")
	public Boolean getGender() {
		return gender;
	}

	/**
	 * @param gender The gender to set.
	 */
	@Column(name="requester_gender")
	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	/**
	 * @return Returns the marital_status.
	 */
	@ManyToOne
	@JoinColumn(name="marital_status_code")
	public Catalogue getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param marital_status The maritalStatus to set.
	 */
	public void setMaritalStatus(Catalogue maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return Returns the instructionLevel.
	 */
	@ManyToOne
	@JoinColumn(name="instruction_level_code")
	public Catalogue getInstructionLevel() {
		return instructionLevel;
	}

	/**
	 * @param instructionLevel The instructionLevel to set.
	 */
	public void setInstructionLevel(Catalogue instructionLevel) {
		this.instructionLevel = instructionLevel;
	}

	/**
	 * @return Returns the salaryAspiration.
	 */
	@ManyToOne
	@JoinColumn(name="salary_aspiration_code")
	public Catalogue getSalaryAspiration() {
		return salaryAspiration;
	}

	/**
	 * @param salaryAspiration The salaryAspiration to set.
	 */
	public void setSalaryAspiration(Catalogue salaryAspiration) {
		this.salaryAspiration = salaryAspiration;
	}

	/**
	 * @return Returns the studyBranch.
	 */
	@ManyToOne
	@JoinColumn(name="study_branch_code")
	public Catalogue getStudyBranch() {
		return studyBranch;
	}

	/**
	 * @param studyBranch The studyBranch to set.
	 */
	public void setStudyBranch(Catalogue studyBranch) {
		this.studyBranch = studyBranch;
	}

	/**
	 * @return Returns the isWorking.
	 */
	@Column(name="requester_working")
	public Boolean getIsWorking() {
		return isWorking;
	}

	/**
	 * @param isWorking The isWorking to set.
	 */
	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

    /**
     * @return Returns the requesterWorkCity.
     */
    @OneToMany(mappedBy="requester")
    public Set<RequesterWorkCity> getRequesterWorkCity() {
        return requesterWorkCity;
    }

    /**
     * @param requesterWorkCity The requesterWorkCity to set.
     */
    public void setRequesterWorkCity(Set<RequesterWorkCity> requesterWorkCity) {
        this.requesterWorkCity = requesterWorkCity;
    }
    
    @Column(name="requester_identification")
	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	@Column(name="requester_second_first_name")
	public String getSecondName() {
		return secondName;
	}
	

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Column(name="requester_second_last_name")
	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	
	@Column(name="requester_office_phone")
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	
	@Column(name="requester_mobile")
	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}
	
	@Column(name="requester_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="requester_birth_date")
	@Temporal(TemporalType.TIMESTAMP)
	@org.hibernate.annotations.Type(type="calendar")

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	
	@Column(name="requester_vehicle")
	public Boolean getVehicle() {
		return vehicle;
	}

	public void setVehicle(Boolean vehicle) {
		this.vehicle = vehicle;
	}
	
	@Column(name="requester_travel_possible")
	public Boolean getTravelPossibity() {
		return travelPossibity;
	}

	public void setTravelPossibity(Boolean travelPossibity) {
		this.travelPossibity = travelPossibity;
	}
	
	@Column(name="requester_enterprise_name")
	public String getEnterpriceName() {
		return enterpriceName;
	}

	public void setEnterpriceName(String enterpriceName) {
		this.enterpriceName = enterpriceName;
	}
	
	@Column(name="requester_enterprise_city")
	public String getEnterpriceCity() {
		return enterpriceCity;
	}

	public void setEnterpriceCity(String enterpriceCity) {
		this.enterpriceCity = enterpriceCity;
	}
	
	@Column(name="requester_instruction_addinfo")
	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	
	@Column(name="requester_brief_application")
	public Boolean getBriefApplication() {
		return briefApplication;
	}

	public void setBriefApplication(Boolean briefApplication) {
		this.briefApplication = briefApplication;
	}
	
	@ManyToOne
	@JoinColumn(name="country_code")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Column(name="requester_city")
	public String getRequestedCity() {
		return requestedCity;
	}

	public void setRequestedCity(String requestedCity) {
		this.requestedCity = requestedCity;
	}
	

}
/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.job;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

public class RequestForm extends BaseForm {
	
	/**
     * 
     */
    private static final long serialVersionUID = 7153261710227666416L;
    private Long code;
	private String firstName;
	private String lastName;
	private Long city;
	private String address;
	private String phoneNumber;
	private String vitae;
	private String dateCreated;
	private Boolean gender;
	private Long maritalStatus;
	private Long instructionLevel;
	private Long studyBranch;
	private Long salaryAspiration;
	private Boolean isWorking;
    //private Set<RequesterWorkCity> requesterWorkCity;
    private String identification;
    private String secondName;
    private String secondLastName;
    private String officePhone;
    private String cell;
    private String email;
    private String birthDate;
	private Boolean vehicle;
	private Boolean travelPossibity;
	private String enterpriceName;
	private String enterpriceCity;
	private Long[] cityCodes;
	private Long position;
	private Boolean experience;
	private Boolean briefApplication;
	private String addInfo;
	private Long country;
	private String requestedCity;
	
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.firstName = null;
		this.lastName = null;
		this.city = null;
		this.address = null;
		this.phoneNumber = null;
		this.vitae = null;
		this.dateCreated = null;
		this.gender = Globals.TRUE;
		this.maritalStatus = null;
		this.instructionLevel = null;
		this.studyBranch = null;
		this.salaryAspiration = null;
		this.isWorking = Globals.TRUE;
		this.identification = null;
		this.secondName = null;
		this.secondLastName = null;
		this.officePhone = null;
		this.cell = null;
		this.email = null;
		this.birthDate = null;
		this.vehicle = Globals.TRUE;
		this.travelPossibity = Globals.TRUE;
		this.enterpriceName = null;
		this.enterpriceCity = null;
		this.cityCodes = null;
		this.position = null;
		this.experience = Globals.TRUE;
		this.briefApplication=null;
		this.addInfo=null;
		this.country=null;
		this.requestedCity=null;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Calendar getBirth() {
    	
    	Calendar calendar = new GregorianCalendar();
    	
		if ( this.getBirthDate() != null && this.getBirthDate().length() > 0  ) { 
			
			calendar.setTime( DateFormatUtils.parseToDate(this.getBirthDate(), Constants.DATE_FORMAT) );
			return calendar;
		}
		return null;
		
    	
	}
  
  public void setBirth(Calendar calendar) {
		if ( calendar != null )
			
			this.birthDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
	}
	
	
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	
	 public Calendar getDate() {
	    	Calendar calendar = new GregorianCalendar();
	    	
			if ( this.getDateCreated() != null && this.getDateCreated().length() > 0  ) { 
				calendar.setTime( DateFormatUtils.parseToDate(this.getDateCreated(), Constants.DATE_FORMAT) );
				return calendar;
			}
			return null;
			
		}
	  
	  public void setDate(Calendar calendar) {
			if ( calendar != null )
				this.dateCreated = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
		}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnterpriceCity() {
		return enterpriceCity;
	}
	public void setEnterpriceCity(String enterpriceCity) {
		this.enterpriceCity = enterpriceCity;
	}
	public String getEnterpriceName() {
		return enterpriceName;
	}
	public void setEnterpriceName(String enterpriceName) {
		this.enterpriceName = enterpriceName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public Long getInstructionLevel() {
		return instructionLevel;
	}
	public void setInstructionLevel(Long instructionLevel) {
		this.instructionLevel = instructionLevel;
	}
	public Boolean getIsWorking() {
		return isWorking;
	}
	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Long maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getSalaryAspiration() {
		return salaryAspiration;
	}
	public void setSalaryAspiration(Long salaryAspiration) {
		this.salaryAspiration = salaryAspiration;
	}
	public String getSecondLastName() {
		return secondLastName;
	}
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public Long getStudyBranch() {
		return studyBranch;
	}
	public void setStudyBranch(Long studyBranch) {
		this.studyBranch = studyBranch;
	}
	public Boolean getTravelPossibity() {
		return travelPossibity;
	}
	public void setTravelPossibity(Boolean travelPossibity) {
		this.travelPossibity = travelPossibity;
	}
	public Boolean getVehicle() {
		return vehicle;
	}
	public void setVehicle(Boolean vehicle) {
		this.vehicle = vehicle;
	}
	public String getVitae() {
		return vitae;
	}
	public void setVitae(String vitae) {
		this.vitae = vitae;
	}
	public Long[] getCityCodes() {
		return cityCodes;
	}
	public void setCityCodes(Long[] cityCodes) {
		this.cityCodes = cityCodes;
	}
	public Boolean getExperience() {
		return experience;
	}
	public void setExperience(Boolean experience) {
		this.experience = experience;
	}
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public Boolean getBriefApplication() {
		return briefApplication;
	}
	public void setBriefApplication(Boolean briefApplication) {
		this.briefApplication = briefApplication;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public String getRequestedCity() {
		return requestedCity;
	}
	public void setRequestedCity(String requestedCity) {
		this.requestedCity = requestedCity;
	}
	
}


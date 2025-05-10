/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.ctrl.system.jobs;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.ctrl.BasePortalForm;
import com.yage.commons.DateFormatUtils;

/**
 * Represents the form used for searching job application requests
 * @author martha
 *
 */
public class PositionRequestForm extends BasePortalForm {

	private static final long serialVersionUID = 2L;

	private Long code;
	
	private Long areaCode;
	
	private Long positionCode;
	
	private Long countryCode;
	
	private Long cityCode;
	
	private String fromDate;
	
	private String toDate;
	
	private Boolean isWorking;
	
	private Boolean experience;
	
	private Long maritalStatusCode;
	
	private Long instructionLevelCode;
	
	private Long studyBranchCode;

	private Long salaryAspirationCode;
    
    private Integer experienceOption;
    
    private Integer workingOption;
	
	/**
     * Creates a new instance of AreaForm
     */
	public PositionRequestForm() {
		super();
		
	}
	
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
    	code = null;
  
   }

	/**
	 * @return Returns the code.
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}	
	
	
    public Calendar getFrom() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) {
			Date fromDate = DateFormatUtils.parseToDate(this.getFromDate(),language.getDateFormat());
			calendar.setTime( fromDate );
			return calendar;
		}
		return null;
	}
    
	public void setFrom(Calendar calendar) {
		if ( calendar != null )
			this.fromDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}

	public Calendar getTo() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getToDate() != null && this.getToDate().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setTo(Calendar calendar) {
		if ( calendar != null )
			this.toDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}

	/**
	 * @return Returns the cityCode.
	 */
	public Long getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode The cityCode to set.
	 */
	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return Returns the countryCode.
	 */
	public Long getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode The countryCode to set.
	 */
	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return Returns the positionCode.
	 */
	public Long getPositionCode() {
		return positionCode;
	}

	/**
	 * @param positionCode The positionCode to set.
	 */
	public void setPositionCode(Long positionCode) {
		this.positionCode = positionCode;
	}

	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return Returns the areaCode.
	 */
	public Long getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode The areaCode to set.
	 */
	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return Returns the experience.
	 */
	public Boolean getExperience() {
		return experience;
	}

	/**
	 * @param experience The experience to set.
	 */
	public void setExperience(Boolean experience) {
		this.experience = experience;
	}

	/**
	 * @return Returns the isWorking.
	 */
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
	 * @return Returns the maritalStatusCode.
	 */
	public Long getMaritalStatusCode() {
		return maritalStatusCode;
	}

	/**
	 * @param maritalStatusCode The maritalStatusCode to set.
	 */
	public void setMaritalStatusCode(Long maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}

	/**
	 * @return Returns the instructionLevelCode.
	 */
	public Long getInstructionLevelCode() {
		return instructionLevelCode;
	}

	/**
	 * @param instructionLevelCode The instructionLevelCode to set.
	 */
	public void setInstructionLevelCode(Long instructionLevelCode) {
		this.instructionLevelCode = instructionLevelCode;
	}

	/**
	 * @return Returns the salaryAspirationCode.
	 */
	public Long getSalaryAspirationCode() {
		return salaryAspirationCode;
	}

	/**
	 * @param salaryAspirationCode The salaryAspirationCode to set.
	 */
	public void setSalaryAspirationCode(Long salaryAspirationCode) {
		this.salaryAspirationCode = salaryAspirationCode;
	}

	/**
	 * @return Returns the studyBranchCode.
	 */
	public Long getStudyBranchCode() {
		return studyBranchCode;
	}

	/**
	 * @param studyBranchCode The studyBranchCode to set.
	 */
	public void setStudyBranchCode(Long studyBranchCode) {
		this.studyBranchCode = studyBranchCode;
	}

    /**
     * @return Returns the experienceOption.
     */
    public Integer getExperienceOption() {
        return experienceOption;
    }

    /**
     * @param experienceOption The experienceOption to set.
     */
    public void setExperienceOption(Integer experienceOption) {
        this.experienceOption = experienceOption;
    }

    /**
     * @return Returns the workingOption.
     */
    public Integer getWorkingOption() {
        return workingOption;
    }

    /**
     * @param workingOption The workingOption to set.
     */
    public void setWorkingOption(Integer workingOption) {
        this.workingOption = workingOption;
    }	

}

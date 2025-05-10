/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.container;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.ctrl.BasePortalForm;
import com.yage.Globals;

/**
 * Represents a Department Form Object.
 * 
 * @author  YAGE pajaro
 * @version 1.1
 *
 */
public class DepartmentContainerForm extends BasePortalForm {
		
	private static final long serialVersionUID = -3211555521532769957L;

	private Long code; 
	
	private String name;
	
	private String services;
	
	private String schedules;
	
	private Long cityCode;
	
	private Long zoneCode;
	
	private String adress;
		
	private Boolean isEnabled;
	
	private String email;
	
	private String phone;

	
	/**
     * Creates a new instance of documentForm
     */
    public DepartmentContainerForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1); 
        code		= null;
    	name		= null;
    	services	= null;
    	schedules	= null;
    	cityCode 	= null;
    	zoneCode 	= null;
    	adress		= null;
    	isEnabled 	= Globals.FALSE;
    	email		= null;
    	phone		= null;
    } 

    
    
	/**
	 * @return Returns the public atention schedules.
	 */
	public String getSchedules() {
		return schedules;
	}


	/**
	 * @param path The path to set.
	 */
	public void setSchedules(String path) {
		this.schedules = path;
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


	/**
	 * @return Returns the isEnabled.
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}


	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	/**
	 * @return Returns the leadinText.
	 */
	public String getAdress() {
		return adress;
	}


	/**
	 * @param leadinText The leadinText to set.
	 */
	public void setAdress(String leadinText) {
		this.adress = leadinText;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return Returns the categoryCode.
	 */
	public Long getCityCode() {
		return cityCode;
	}


	/**
	 * @param categoryCode The categoryCode to set.
	 */
	public void setCityCode(Long categoryCode) {
		this.cityCode = categoryCode;
	}


	/**
	 * @return Returns the typeCode.
	 */
	public Long getZoneCode() {
		return zoneCode;
	}


	/**
	 * @param typeCode The typeCode to set.
	 */
	public void setZoneCode(Long typeCode) {
		this.zoneCode = typeCode;
	}


	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return Returns the services.
	 */
	public String getServices() {
		return services;
	}


	/**
	 * @param services The services to set.
	 */
	public void setServices(String services) {
		this.services = services;
	}

	
	
}//end class

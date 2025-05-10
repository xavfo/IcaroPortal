/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.ctrl.system.jobs;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * @author pajaro
 *
 */
public class AreaForm extends BaseForm {

	private static final long serialVersionUID = 2L;

	private Long code;
	
	private String name;
	
	private String email;
	
	private Boolean isEnabled;
	
	private Set listOfPosition;
	
	private Long areaCode;
	
    /**
     * Creates a new instance of AreaForm
     */
	public AreaForm() {
		super();
		
	}
	
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
    	code = null;
    	name = null;
    	email = null;
    	isEnabled = Globals.TRUE;
    	listOfPosition = null;
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
	 * @return Returns the listOfPosition.
	 */
	public Set getListOfPosition() {
		return listOfPosition;
	}

	/**
	 * @param listOfPosition The listOfPosition to set.
	 */
	public void setListOfPosition(Set listOfPosition) {
		this.listOfPosition = listOfPosition;
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

	
}

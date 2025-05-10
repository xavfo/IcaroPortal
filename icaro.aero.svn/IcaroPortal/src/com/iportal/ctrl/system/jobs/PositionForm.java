/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.ctrl.system.jobs;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.model.jobs.Area;
import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * @author pajaro
 *
 */
public class PositionForm extends BaseForm {

	private static final long serialVersionUID = 2L;

	private Long code;
	
	private Area area;
	
	private Long areaCode;
	
	private String name;
	
	private String shortDescription;
	
	private String longDescription;
	
	private Boolean isEnabled;
	
	private String profileDescription;
	
	private Long maritalStatusCode;
	
	
	
    /**
     * Creates a new instance of AreaForm
     */
	public PositionForm() {
		super();
		
	}
	
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
    	code = null;
    	area = null;
    	name = null;
    	shortDescription = null;
    	longDescription = null;
    	isEnabled = Globals.TRUE;
    	profileDescription = null;
   }

	/**
	 * @return Returns the area.
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area The area to set.
	 */
	public void setArea(Area area) {
		this.area = area;
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
	 * @return Returns the longDescription.
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription The longDescription to set.
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
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
	 * @return Returns the profileDescription.
	 */
	public String getProfileDescription() {
		return profileDescription;
	}

	/**
	 * @param profileDescription The profileDescription to set.
	 */
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	/**
	 * @return Returns the shortDescription.
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription The shortDescription to set.
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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
}

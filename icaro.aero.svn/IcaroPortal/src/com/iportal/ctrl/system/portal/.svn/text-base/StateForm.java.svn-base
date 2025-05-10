/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment SysStateForm
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class StateForm extends BaseForm {

	private static final long serialVersionUID = -3308583082405393969L;

	private String name;
    
    private String description;
    
    private String key;
    
    private Long countryCode;
    
    private Boolean isEnabled;
    
    

    /**
     * Creates a new instance of SysRoleForm
     */
    public StateForm() {
        super();
    }
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        description = null;
        key = null;
        countryCode = null;
        isEnabled = Globals.TRUE;
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
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
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
}

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
 * comment CityForm
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class CountryForm extends BaseForm {

	private static final long serialVersionUID = -3626320803819696061L;

	private String name;
    
    private String description;
    
    private String iso;
    
    private String key;
    
    private Boolean isEnabled;
    
    

    /**
     * Creates a new instance of SysRoleForm
     */
    public CountryForm() {
        super();
    }
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        description = null;
        key = null;
        iso = null;
        isEnabled = Globals.TRUE;
    }


	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
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
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the iso.
	 */
	public String getIso() {
		return iso;
	}
	/**
	 * @param iso The iso to set.
	 */
	public void setIso(String iso) {
		this.iso = iso;
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
}

/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment ContentForm
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 *
 */
public class MenuForm extends BaseForm {

    private static final long serialVersionUID = 5284818922967311929L;

    private Long menuLanguageCode;
    
    private String name;
    
    private Boolean enabled;
    
    
    /**
     * Creates a new instance of SysRoleForm
     */
    public MenuForm() {
        super();
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	super.reset(mapping, request);
    	name = null;
    	enabled = Globals.TRUE;
    }

	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
	 * @return Returns the menuLangaugeCode.
	 */
	public Long getMenuLanguageCode() {
		return menuLanguageCode;
	}
	/**
	 * @param menuLangaugeCode The menuLangaugeCode to set.
	 */
	public void setMenuLanguageCode(Long menuLanguageCode) {
		this.menuLanguageCode = menuLanguageCode;
	}
	
}

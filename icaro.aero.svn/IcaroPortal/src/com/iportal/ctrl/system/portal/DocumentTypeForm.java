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

import com.yage.struts.action.BaseForm;

/**
 * comment CityForm
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class DocumentTypeForm extends BaseForm {

	private static final long serialVersionUID = -8165620249700618822L;

	private String name;
    
    private String icon; 
    
    /**
     * Creates a new instance of DocumentTypeForm
     */
    public DocumentTypeForm() {
        super();
    }
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        icon = null;
    }
    
    

	/**
	 * @return Returns the icon.
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon The icon to set.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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

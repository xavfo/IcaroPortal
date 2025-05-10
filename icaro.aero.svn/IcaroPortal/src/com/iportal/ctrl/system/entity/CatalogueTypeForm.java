/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.entity;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author mcnovillo
 *
 */
public class CatalogueTypeForm extends BaseForm {
	
	private static final long serialVersionUID = -839622834356933986L;
	
	private String name;
	
    /**
     * Creates a new instance of SysAuditForm
     */
	public CatalogueTypeForm() {
		super();
	}
	
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
    	name = null;
   }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

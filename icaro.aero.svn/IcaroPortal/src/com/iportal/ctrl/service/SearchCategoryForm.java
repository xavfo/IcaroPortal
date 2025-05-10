/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.ctrl.BasePortalForm;
import com.yage.Globals;

/**
 * comment SearchLinkCategoryForm
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class SearchCategoryForm extends BasePortalForm {
	
	private static final long serialVersionUID = 5815275146619080424L;

	private Long categoryCode;	
	
	private Long sectorCode;	
	
	private Boolean form;
	
  
    /**
     * Creates a new instance of BasePortalForm
     */
    public SearchCategoryForm() {
        super();
    }
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		categoryCode = null;
		sectorCode = null;
		form = Globals.FALSE;
	}	
		
	/**
	 * @return Returns the categoryCode.
	 */
	public Long getCategoryCode() {
		return categoryCode;
	}
	/**
	 * @return Returns the form.
	 */
	public Boolean getForm() {
		return form;
	}
	/**
	 * @return Returns the sectorCode.
	 */
	public Long getSectorCode() {
		return sectorCode;
	}
	/**
	 * @param categoryCode The categoryCode to set.
	 */
	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**
	 * @param form The form to set.
	 */
	public void setForm(Boolean form) {
		this.form = form;
	}
	/**
	 * @param sectorCode The sectorCode to set.
	 */
	public void setSectorCode(Long sectorCode) {
		this.sectorCode = sectorCode;
	}
}

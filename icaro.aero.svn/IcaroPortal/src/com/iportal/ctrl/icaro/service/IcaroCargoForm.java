/*
 * Created on Aug 17, 2007
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.ctrl.icaro.service;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author ftamayo
 *
 * Forma para invocar a webServices
 */
public class IcaroCargoForm extends BaseForm {	
	

	private static final long serialVersionUID = -3245049881491553773L;

	private String docType;
	
	private String docNumber;
	
	
	

	public void clear () {
		this.docType = null;
		this.docNumber = null; 
	}
	
	
	/**
	 * 
	 */
	public IcaroCargoForm() {
		super();
		this.clear ();
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.clear ();
	}


	/**
	 * @return Returns the docNumber.
	 */
	public String getDocNumber() {
		return docNumber;
	}


	/**
	 * @param docNumber The docNumber to set.
	 */
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}


	/**
	 * @return Returns the docType.
	 */
	public String getDocType() {
		return docType;
	}


	/**
	 * @param docType The docType to set.
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}


	
}

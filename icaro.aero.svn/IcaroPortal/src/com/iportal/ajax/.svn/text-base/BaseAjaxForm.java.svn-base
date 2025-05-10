/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ajax;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Formulario para accion Ajax
 * @author andresg
 * @author  YAGE
 * @version 1.0
 * @see org.apache.struts.action.ActionForm
 */
public class BaseAjaxForm extends ActionForm {

	private static final long serialVersionUID = -2860083980683005383L;
	
	private Long code;
	
	/**
     *  Crea una nueva instancia de BaseForm
     */
    public BaseAjaxForm() {
        super();
    }    
    
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.code = null;
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
}

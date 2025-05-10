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
 * comment MenuForm
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class MenuForm extends BaseForm {
    
	private static final long serialVersionUID = 3104131153027840321L;

	private String name;
    
    private Boolean _default;
    
    private Long defaultCode;
    
    
    

    /**
     * Creates a new instance of MenuForm
     */
    public MenuForm() {
        super();
    }
    
    
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
       _default = Globals.FALSE;
       defaultCode = null;
    }
    
    
    /**
     * @return Returns the _default.
     */
    public Boolean getDefault() {
        return _default;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param _default The _default to set.
     */
    public void setDefault(Boolean _default) {
        this._default = _default;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the defaultCode.
     */
    public Long getDefaultCode() {
        return defaultCode;
    }
    /**
     * @param defaultCode The defaultCode to set.
     */
    public void setDefaultCode(Long defaultCode) {
        this.defaultCode = defaultCode;
    }
}

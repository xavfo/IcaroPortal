/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SysProfileForm extends BaseForm { 

	private static final long serialVersionUID = 2L;
	
	private Long roleCode;
    
    private Long moduleCode;
    
    
    
    /**
     * Creates a new instance of SysProfileForm
     */
    public SysProfileForm() {
        super();
    }
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        roleCode = null;
        moduleCode = null;
    }
    
    
    /**
     * @return Returns the moduleCode.
     */
    public Long getModuleCode() {
        return moduleCode;
    }
    /**
     * @return Returns the roleCode.
     */
    public Long getRoleCode() {
        return roleCode;
    }
    /**
     * @param moduleCode The moduleCode to set.
     */
    public void setModuleCode(Long moduleCode) {
        this.moduleCode = moduleCode;
    }
    /**
     * @param roleCode The roleCode to set.
     */
    public void setRoleCode(Long roleCode) {
        this.roleCode = roleCode;
    }
}

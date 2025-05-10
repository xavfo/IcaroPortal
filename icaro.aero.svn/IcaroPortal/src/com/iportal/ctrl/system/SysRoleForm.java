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
public class SysRoleForm extends BaseForm {

	private static final long serialVersionUID = 2L;
	
    private String name;
    
    private String description;
    
    private Long parentCode;
    
    

    /**
     * Creates a new instance of SysRoleForm
     */
    public SysRoleForm() {
        super();
    }
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        description = null;
        parentCode = null;
    }
    
    
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the parentCode.
     */
    public Long getParentCode() {
        return parentCode;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param parentCode The parentCode to set.
     */
    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }
}

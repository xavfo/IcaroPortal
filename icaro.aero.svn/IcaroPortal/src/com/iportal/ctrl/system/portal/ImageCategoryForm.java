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
 * comment ImageCategoryForm
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ImageCategoryForm extends BaseForm {

	private static final long serialVersionUID = -3308583082405393969L;

	private String name;
    
    private Long parentCode;
    
    

    /**
     * Creates a new instance of SysRoleForm
     */
    public ImageCategoryForm() {
        super();
    }
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        parentCode = null;
    }


	public String getName() {
		return name;
	}


	public Long getParentCode() {
		return parentCode;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}
    
    
}

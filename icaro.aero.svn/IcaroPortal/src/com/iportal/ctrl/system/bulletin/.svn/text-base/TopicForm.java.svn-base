/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment TopicForm
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class TopicForm extends BaseForm {
	
	private static final long serialVersionUID = -4621343022608888611L;

	private String description;
	
	private String name;
	
	private Boolean enabled;
	
    /**
     * Creates a new instance of FaqCategoryForm
     */
    public TopicForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        description = null;
        enabled = Globals.TRUE;
    }  
    
    

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}

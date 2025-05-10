/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment MenuItemForm
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 *
 */
public class RelatedContentForm extends BaseForm {

    private static final long serialVersionUID = 5284818922967311929L;

    private String name;

    private Long contentCode;
    
    private Long relatedCode;
    
    private String relatedDescription;
    
    private Boolean enabled;
    
    /**
     * Creates a new instance of SysRoleForm
     */
    public RelatedContentForm() {
        super();
    }
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    	super.reset(arg0, arg1);
    	name = null;
        contentCode = null;
        relatedCode = null;
        enabled = Globals.TRUE;
    }
    
	/**
	 * @return Returns the contentCode.
	 */
	public Long getContentCode() {
		return contentCode;
	}
	/**
	 * @param contentCode The contentCode to set.
	 */
	public void setContentCode(Long contentCode) {
		this.contentCode = contentCode;
	}
	
	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	/**
	 * @return Returns the relatedCode.
	 */
	public Long getRelatedCode() {
		return relatedCode;
	}
	/**
	 * @param relatedCode The relatedCode to set.
	 */
	public void setRelatedCode(Long relatedCode) {
		this.relatedCode = relatedCode;
	}

	/**
	 * @return Returns the relatedDescription.
	 */
	public String getRelatedDescription() {
		return relatedDescription;
	}
	/**
	 * @param relatedDescription The relatedDescription to set.
	 */
	public void setRelatedDescription(String relatedDescription) {
		this.relatedDescription = relatedDescription;
	}
}
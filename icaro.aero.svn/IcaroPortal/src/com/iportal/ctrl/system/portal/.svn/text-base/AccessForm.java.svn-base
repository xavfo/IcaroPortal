/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.iportal.biz.BaseHelper;
import com.yage.struts.action.BaseForm;

/**
 * comment AccessForm
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class AccessForm extends BaseForm {

    private static final long serialVersionUID = 7107686841932186383L;

    private String name;
    
    private String description;
    
    private String url;
    
    private String path;
    
    private Integer height;
    
    private Integer width;
    
    private Long relatedCode;
    
    private String relatedName;
    
    private Long accessUrlCode;
    
    private String className;
    
    

    /**
     * Creates a new instance of SysRoleForm
     */
    public AccessForm() {
        super();
    }
    
    /* 
     * (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = super.validate(mapping, request);		
		    	
		if( null == this.getClassName() || "".equals(this.getClassName()) ) { 
            if(null == this.getUrl() || 0 == this.getUrl().length()) {
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.url")));
            }
        } else if ( this.getRelatedCode() == null || this.getRelatedCode().longValue() == 0 ) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt,relatedContent")));
		}				
		return errors;
	}
    
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.name = null;
        this.url = null;
        this.path = null;
        this.height = null;
        this.width = null;
        this.relatedCode = null;
        this.relatedName = null;
        this.description = null;
        this.accessUrlCode = null;
        this.className = "";
    }


    
	/**
	 * @return Returns the accessUrlCode.
	 */
	public Long getAccessUrlCode() {
		return accessUrlCode;
	}


	public Integer getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public Long getRelatedCode() {
		return relatedCode;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public String getUrl() {
		return url;
	}

	public Integer getWidth() {
		return width;
	}
    
    public String getClassName() {
        return className;
    }


	/**
	 * @param accessUrlCode The accessUrlCode to set.
	 */
	public void setAccessUrlCode(Long accessUrlCode) {
		this.accessUrlCode = accessUrlCode;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRelatedCode(Long relatedCode) {
		this.relatedCode = relatedCode;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
}

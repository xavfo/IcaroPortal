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

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment AccessMenuForm
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class AccessMenuForm extends BaseForm {

    private static final long serialVersionUID = 5284818922967311929L;
       
    private Boolean enabled;
        
    private String accessDescription;
    
    private Integer order;
    
    private Long accessCode;
    
    private Long menuLanguageCode;
    
    private Long displayModeCode;
    
    private Integer menuX;
    
    private Integer menuY;
    
    private Integer menuWidth;
    
    
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
    public AccessMenuForm() {
        super();
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    	super.reset(arg0, arg1);
    	this.enabled = Globals.TRUE;
        this.accessCode = null;
        this.accessDescription = null;
        this.menuLanguageCode = null;
        this.order = null;
        this.menuWidth = null;
        this.displayModeCode = null;
        this.menuX = null;
        this.menuY = null;
        //Propiedades del Access relacionado a aste item de menu accessMenu
        this.name = null;
        this.description = null;
        this.url = null;
        this.path = null;
        this.height = null;
        this.width = null;
        this.relatedCode = null;
        this.relatedName = null;
        this.accessUrlCode = null;
        this.className = "";
    }

	/**
	 * @return Returns the accessCode.
	 */
	public Long getAccessCode() {
		return accessCode;
	}

	/**
	 * @return Returns the accessDescription.
	 */
	public String getAccessDescription() {
		return accessDescription;
	}

	/**
	 * @return Returns the displayModeCode.
	 */
	public Long getDisplayModeCode() {
		return displayModeCode;
	}

	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @return Returns the menuLanguageCode.
	 */
	public Long getMenuLanguageCode() {
		return menuLanguageCode;
	}

	/**
	 * @return Returns the menuWidth.
	 */
	public Integer getMenuWidth() {
		return menuWidth;
	}

	/**
	 * @return Returns the menuX.
	 */
	public Integer getMenuX() {
		return menuX;
	}

	/**
	 * @return Returns the menuY.
	 */
	public Integer getMenuY() {
		return menuY;
	}

	/**
	 * @return Returns the order.
	 */
	public Integer getOrder() {
		return order;
	}
	
	
	
	

	
	/**
	 * @return Returns the accessUrlCode.
	 */
	public Long getAccessUrlCode() {
		return accessUrlCode;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the height.
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return Returns the relatedCode.
	 */
	public Long getRelatedCode() {
		return relatedCode;
	}

	/**
	 * @return Returns the relatedName.
	 */
	public String getRelatedName() {
		return relatedName;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return Returns the width.
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param accessUrlCode The accessUrlCode to set.
	 */
	public void setAccessUrlCode(Long accessUrlCode) {
		this.accessUrlCode = accessUrlCode;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param height The height to set.
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param relatedCode The relatedCode to set.
	 */
	public void setRelatedCode(Long relatedCode) {
		this.relatedCode = relatedCode;
	}

	/**
	 * @param relatedName The relatedName to set.
	 */
	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param width The width to set.
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @param accessCode The accessCode to set.
	 */
	public void setAccessCode(Long accessCode) {
		this.accessCode = accessCode;
	}

	/**
	 * @param accessDescription The accessDescription to set.
	 */
	public void setAccessDescription(String accessDescription) {
		this.accessDescription = accessDescription;
	}

	/**
	 * @param displayModeCode The displayModeCode to set.
	 */
	public void setDisplayModeCode(Long displayModeCode) {
		this.displayModeCode = displayModeCode;
	}

	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @param menuLanguageCode The menuLanguageCode to set.
	 */
	public void setMenuLanguageCode(Long menuLanguageCode) {
		this.menuLanguageCode = menuLanguageCode;
	}

	/**
	 * @param menuWidth The menuWidth to set.
	 */
	public void setMenuWidth(Integer menuWidth) {
		this.menuWidth = menuWidth;
	}

	/**
	 * @param menuX The menuX to set.
	 */
	public void setMenuX(Integer menuX) {
		this.menuX = menuX;
	}

	/**
	 * @param menuY The menuY to set.
	 */
	public void setMenuY(Integer menuY) {
		this.menuY = menuY;
	}

	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    

}

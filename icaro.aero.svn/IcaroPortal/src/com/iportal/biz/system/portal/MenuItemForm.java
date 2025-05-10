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
public class MenuItemForm extends BaseForm {

    private static final long serialVersionUID = 5284818922967311929L;

    private String name;

    private Integer level;
    
    private Boolean group;
    
    private Boolean enabled;
    
    private Long parentCode;
    
    private String parentDescription;
    
    private Long displayModeCode;
    
    private Long contentCode;
    
    private String contentDescription;
    
    private Long menuLanguageCode;
    
    private Integer order;
    
    private Integer menuX;
    
    private Integer menuY;
    
    private Integer menuWidth;
    
    
    /**
     * Creates a new instance of SysRoleForm
     */
    public MenuItemForm() {
        super();
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    	super.reset(arg0, arg1);
    	name = null;
        level = null;
        group = Globals.FALSE;
        enabled = Globals.TRUE;
        parentCode = null;
        displayModeCode = null;
        contentCode = null;
        contentDescription = null;
        menuLanguageCode = null;
        order = null;
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
	 * @return Returns the displayModeCode.
	 */
	public Long getDisplayModeCode() {
		return displayModeCode;
	}

	/**
	 * @param displayModeCode The displayModeCode to set.
	 */
	public void setDisplayModeCode(Long displayModeCode) {
		this.displayModeCode = displayModeCode;
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
	 * @return Returns the group.
	 */
	public Boolean getGroup() {
		return group;
	}

	/**
	 * @param group The group to set.
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}

	/**
	 * @return Returns the level.
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return Returns the menuLanguageCode.
	 */
	public Long getMenuLanguageCode() {
		return menuLanguageCode;
	}

	/**
	 * @param menuLanguageCode The menuLanguageCode to set.
	 */
	public void setMenuLanguageCode(Long menuLanguageCode) {
		this.menuLanguageCode = menuLanguageCode;
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
	 * @return Returns the parentCode.
	 */
	public Long getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode The parentCode to set.
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return Returns the contentDescription.
	 */
	public String getContentDescription() {
		return contentDescription;
	}
	/**
	 * @param contentDescription The contentDescription to set.
	 */
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	/**
	 * @return Returns the parentDescription.
	 */
	public String getParentDescription() {
		return parentDescription;
	}
	/**
	 * @param parentDescription The parentDescription to set.
	 */
	public void setParentDescription(String parentDescription) {
		this.parentDescription = parentDescription;
	}

	/**
	 * @return Returns the order.
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
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

}

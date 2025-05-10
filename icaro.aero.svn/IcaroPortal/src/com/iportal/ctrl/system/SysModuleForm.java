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

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SysModuleForm extends BaseForm {

	private static final long serialVersionUID = 2L;

	private Integer level;
    
    private Boolean group;
    
    private String name;
    
    private String key;
    
    private String url;
    
    private Integer orderIndex;
    
    private Integer x;
    
    private Integer y;
    
    private Long parentCode;
    
    private Long accessModeCode;
    
    private Boolean showPanel;
    

    /**
     * Creates a new instance of SysModuleForm
     */
    public SysModuleForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        level = Constants.DEFAULT_ROOT_LEVEL;
        group = Globals.TRUE;
        name = null;
        key = null;
        url = null;
        orderIndex = Globals.DEFAULT_PROPERTY_INDEX;
        x = null;
        y = null;
        parentCode = null;
        showPanel = false;
    }
    
    
    /**
     * @return Returns the group.
     */
    public Boolean getGroup() {
        return group;
    }
    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }
    /**
     * @return Returns the level.
     */
    public Integer getLevel() {
        return level;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the orderIndex.
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }
    /**
     * @return Returns the parentCode.
     */
    public Long getParentCode() {
        return parentCode;
    }
    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }
    /**
     * @return Returns the x.
     */
    public Integer getX() {
        return x;
    }
    /**
     * @return Returns the y.
     */
    public Integer getY() {
        return y;
    }
    /**
     * @param group The group to set.
     */
    public void setGroup(Boolean group) {
        this.group = group;
    }
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @param level The level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param orderIndex The orderIndex to set.
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
    /**
     * @param parentCode The parentCode to set.
     */
    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }
    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @param x The x to set.
     */
    public void setX(Integer x) {
        this.x = x;
    }
    /**
     * @param y The y to set.
     */
    public void setY(Integer y) {
        this.y = y;
    }


	/**
	 * @return Returns the accessModeCode.
	 */
	public Long getAccessModeCode() {
		return accessModeCode;
	}


	/**
	 * @param accessModeCode The accessModeCode to set.
	 */
	public void setAccessModeCode(Long accessModeCode) {
		this.accessModeCode = accessModeCode;
	}


	/**
	 * @return Returns the showPanel.
	 */
	public Boolean getShowPanel() {
		return showPanel;
	}


	/**
	 * @param showPanel The showPanel to set.
	 */
	public void setShowPanel(Boolean showPanel) {
		this.showPanel = showPanel;
	}
}

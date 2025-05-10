/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * comment ContentForm
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 *
 */
public class SearchContentForm extends BaseForm {

    private static final long serialVersionUID = 5284818922967311929L;

    private String title;
    
    private Integer level;
    
    private Boolean enabled;
    
    private String treeName;
    
    private Boolean equal;// Indicador true: busca mismo nivel que el par�metro level, false: busca cualquier nivel distinto a level
    
    private Long actualCode;
    
    /**
     * Creates a new instance of SysRoleForm
     */
    public SearchContentForm() {
        super();
    }
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        title = null;
        enabled = null;
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
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * Control properties
	 */
	
	private Long formIndex;
	
	private String controlCode;
	
	private String controlLevel;
	
	private String controlDescription;

	/**
	 * @return Returns the formIndex.
	 */
	public Long getFormIndex() {
		return formIndex;
	}
	/**
	 * @param formIndex The formIndex to set.
	 */
	public void setFormIndex(Long formIndex) {
		this.formIndex = formIndex;
	}

	/**
	 * @return Returns the controlCode.
	 */
	public String getControlCode() {
		return controlCode;
	}
	/**
	 * @param controlCode The controlCode to set.
	 */
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	/**
	 * @return Returns the controlDescription.
	 */
	public String getControlDescription() {
		return controlDescription;
	}
	/**
	 * @param controlDescription The controlDescription to set.
	 */
	public void setControlDescription(String controlDescription) {
		this.controlDescription = controlDescription;
	}

	/**
	 * @return Returns the controlLevel.
	 */
	public String getControlLevel() {
		return controlLevel;
	}
	/**
	 * @param controlLevel The controlLevel to set.
	 */
	public void setControlLevel(String controlLevel) {
		this.controlLevel = controlLevel;
	}

	/**
	 * @return Returns the treeName.
	 */
	public String getTreeName() {
		return treeName;
	}
	/**
	 * @param treeName The treeName to set.
	 */
	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public Long getActualCode() {
		return actualCode;
	}

	public Boolean getEqual() {
		return equal;
	}

	public void setActualCode(Long actualCode) {
		this.actualCode = actualCode;
	}

	public void setEqual(Boolean equal) {
		this.equal = equal;
	}
}
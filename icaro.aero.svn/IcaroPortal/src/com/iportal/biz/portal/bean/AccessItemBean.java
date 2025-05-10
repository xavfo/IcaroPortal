/*
 * Created 12/04/2007
 *	AccessItemBean.java
 */
package com.iportal.biz.portal.bean;

import com.yage.Globals;



/**
 * Objeto especializado para consultar los 
 * datos necesarios para armar un acceso a cualquier
 * recurso del sistem (AccessMenu).
 * 
 * @author YAGE(hernan)
 * @version 1.0
 *
 */
public class AccessItemBean {

	private Long relatedCode;
	
	private String relatedName;
	
	private String name;
	
	private Long displayModeCode;
	
	private Integer order;
	
	private String url;
	
	//Indica si link es externo o no
	private Boolean external;
	
	private String resourcePath;
	
	private Long menuCode;
    
	private String menuName;

	
	private String resourcePathExtension;
    
	private String resourcePathNoExtension;
	
	private String description;

	
	/**
	 * 
	 */
	public AccessItemBean() {
		super();
		this.clear();
	}
	
	
	
	/**
	 * @param relatedCode
	 * @param relatedName
	 * @param name
	 * @param displayModeCode
	 * @param order
	 * @param url
	 * @param resourcePath
	 * @param menuCode
	 * @param menuName
	 */
	
	public AccessItemBean(Long displayModeCode, Integer order, String name, Long relatedCode, String relatedName, String url, String resourcePath, Long menuCode, String menuName) {
		super();
		this.clear();
		this.relatedCode = relatedCode;
		this.relatedName = relatedName;
		this.name = name;
		this.displayModeCode = displayModeCode;
		this.order = order;
		this.url = url;
		this.resourcePath = resourcePath;
		this.menuCode = menuCode;
		this.menuName = menuName;
	}

	public AccessItemBean(Long displayModeCode, Integer order, String name, Long relatedCode, String relatedName, String url, Boolean isExternal, String resourcePath, Long menuCode, String menuName) {
		super();
		this.clear();
//		this.external = isExternal;
		this.relatedCode = relatedCode;
		this.relatedName = relatedName;
		this.name = name;
		this.displayModeCode = displayModeCode;
		this.order = order;
		this.url = url;
		this.resourcePath = resourcePath;
		this.menuCode = menuCode;
		this.menuName = menuName;
	}
	
	public AccessItemBean(Long displayModeCode, Integer order, String name, Long relatedCode, String relatedName, String url, Boolean isExternal, String resourcePath, Long menuCode, String menuName, String description) {
		super();
		this.clear();
		this.external = isExternal;
		this.relatedCode = relatedCode;
		this.relatedName = relatedName;
		this.name = name;
		this.displayModeCode = displayModeCode;
		this.order = order;
		this.url = url;
		this.resourcePath = resourcePath;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.description=description;
	}

	public AccessItemBean(Long displayModeCode, Integer order, String name, Long relatedCode, String relatedName, String url, String resourcePath, Long menuCode, String menuName, String description) {
		super();
		this.clear();
		this.relatedCode = relatedCode;
		this.relatedName = relatedName;
		this.name = name;
		this.displayModeCode = displayModeCode;
		this.order = order;
		this.url = url;
		this.resourcePath = resourcePath;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.description=description;
	}

	protected void clear () {
		this.external = Globals.FALSE;
		this.relatedCode = null;
		this.relatedName = null;
		this.name = null;
		this.displayModeCode = null;
		this.order = null;
		this.url = null;
		this.resourcePath = null;
		this.resourcePathExtension = null;
		this.resourcePathNoExtension = null;
		this.menuCode = null;
		this.menuName = null;
		this.description=null;
		
	}



	/**
	 * @return Returns the displayModeCode.
	 */
	public Long getDisplayModeCode() {
		return displayModeCode;
	}
	

	/**
	 * Indica si link es externo o no
	 * @return TRUE si el link es externo (no colocar contextPath FALSE es interno, requiere el contexPath
	 */
	public Boolean getExternal() {
		return external;
	}



	/**
	 * @return Returns the menuCode.
	 */
	public Long getMenuCode() {
		return menuCode;
	}



	/**
	 * @return Returns the menuName.
	 */
	public String getMenuName() {
		return menuName;
	}



	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}



	/**
	 * @return Returns the order.
	 */
	public Integer getOrder() {
		return order;
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
	 * @return Returns the resourcePath.
	 */
	public String getResourcePath() {
		return resourcePath;
	}



	/**
	 * @return Returns the resourcePathExtension.
	 */
	public String getResourcePathExtension() {
		return resourcePathExtension;
	}



	/**
	 * @return Returns the resourcePathNoExtension.
	 */
	public String getResourcePathNoExtension() {
		return resourcePathNoExtension;
	}



	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param displayModeCode The displayModeCode to set.
	 */
	public void setDisplayModeCode(Long displayModeCode) {
		this.displayModeCode = displayModeCode;
	}
	


	public void setExternal(Boolean external) {
		this.external = external;
	}



	/**
	 * @param menuCode The menuCode to set.
	 */
	public void setMenuCode(Long menuCode) {
		this.menuCode = menuCode;
	}



	/**
	 * @param menuName The menuName to set.
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
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
	 * @param resourcePath The resourcePath to set.
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}



	/**
	 * @param resourcePathExtension The resourcePathExtension to set.
	 */
	public void setResourcePathExtension(String resourcePathExtension) {
		this.resourcePathExtension = resourcePathExtension;
	}



	/**
	 * @param resourcePathNoExtension The resourcePathNoExtension to set.
	 */
	public void setResourcePathNoExtension(String resourcePathNoExtension) {
		this.resourcePathNoExtension = resourcePathNoExtension;
	}



	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
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

	
	

}

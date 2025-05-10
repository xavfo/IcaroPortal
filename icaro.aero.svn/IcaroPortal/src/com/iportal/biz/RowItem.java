/*
 * Created on May 5, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.biz;

import java.util.List;

import com.yage.Globals;

/**
 * Objeto light que recupera solo algunas propiedades de otro objeto.
 * @author jorge
 * @version 1.1
 *
 */
public class RowItem {

	private Long code;
	
	private Long parentCode;
	
	private String name;
	
    private String parentName;
    
	private String nameValue;
	
	private String description;
	
	private Boolean group;
	
	private Integer level;
	
	private Long familyCode;
	
	private String url;
	
	private String accessImagePath;
	
	private String accessImageExtension;
    
	private String accessImageNameNoExtension;
    
    private List childList;
	
	//private String type;
	
	
	/**
	 * 
	 */
	public RowItem() {
		super();
		this.clear ();
	}
	
	private void clear () {
		this.code = null;
		this.parentCode = null;
		this.name = null;
        this.parentName = null;
		this.nameValue = null;
		this.description = null;
		this.group = null;
		this.level = null;
		this.familyCode = null;
		this.url = null;
		this.accessImagePath = null;
		this.accessImageExtension = null;
	    this.accessImageNameNoExtension = null;
        this.childList = null;
	}

	public RowItem(Long code, String name, String description) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
	
	

	public RowItem(Long code, String name, String url, String accessImage) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.url = url;
		this.accessImagePath = accessImage;
	}


	/**
	 * 
	 */
	public RowItem(Long code, String name) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
	}
	
	
	/**
	 * @param name
	 * @param nameValue
	 */
	public RowItem(String name, String nameValue) {
		super();
		this.clear ();
		this.name = name;
		this.nameValue = nameValue;
	}
	
	/*public RowItem(Long code, String name, String description, String url) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.url = url;
	}*/
	
	public RowItem(Long code, String name, String description, String url, String nameValue) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.url = url;
		this.nameValue = nameValue;
	}
	
	public RowItem(Long code, Long parentCode, String name, String description, String url, String nameValue) {
		super();
		this.code = code;
		this.parentCode=code;
		this.name = name;
		this.description = description;
		this.url = url;
		this.nameValue = nameValue;
	}
	
	public RowItem(Long code, String name, String nameValue, Long level) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.nameValue = nameValue;
		this.level = new Integer(level.intValue());
	}
	
	public RowItem(Long code, Long parentCode, String name) {
		super();
		this.clear ();
		this.code = code;
		this.parentCode = parentCode; 
		this.name = name;
	}
	
    public RowItem(Long parentCode, String parentName, Long code, String name) {
        super();
        this.clear ();
        this.code = code;
        this.name = name;
        this.parentCode = parentCode; 
        this.parentName = parentName;
    }

    public RowItem(Long parentCode, String parentName, Long code, String name, Integer level) {
        super();
        this.clear ();
        this.code  = code;
        this.name  = name;
        this.level = level;
        this.parentCode = parentCode; 
        this.parentName = parentName;
        
    }

	public RowItem(Long code, Long parentCode, Boolean group, String name) {
		super();
		this.clear ();
		this.code = code;
		this.parentCode = parentCode; 
		this.group = group;
		this.name = name;
	}
	
	public RowItem(Long code, Long parentCode, Integer level,String name) {
		super();
		this.clear ();
		this.code = code;
		this.parentCode = parentCode; 
		this.level = level;
		this.name = name;
	}

	public RowItem(Long code, Long parentCode, String name, Integer level, Boolean group, Long familyCode) {
		super();
		this.clear ();
		this.code = code;
		this.parentCode = parentCode;
		this.name = name;
		this.level = level;
		this.group = group;
		this.familyCode = familyCode;
	}
	
	public RowItem(Long code, Long parentCode, String name, Integer level, Boolean group) {
		super();
		this.clear ();
		this.code = code;
		this.parentCode = parentCode;
		this.name = name;
		this.level = level;
		this.group = group;
		this.familyCode = null;
	}
	
	public RowItem(Long code, String name, Integer level) {
		super();
		this.clear ();
		this.code = code;
		this.name = name;
		this.level = level;
	}


	/**
	 * @param name
	 */
	public RowItem(String name) {
		super();
		this.clear ();
		this.name = name;
	}
	
	public RowItem(Integer year) {
		super();
		this.clear ();
		this.name = year.toString();
		this.level= year; 
	}

	public static void main(String[] args) {
	}
	/**
	 * @return Returns the code.
	 */
	public Long getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
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
     * @return the parentName
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * @param parentName the parentName to set
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
	 * @return Returns the nameValue.
	 */
	public String getNameValue() {
		return nameValue;
	}
	/**
	 * @param nameValue The nameValue to set.
	 */
	public void setNameValue(String nameValue) {
		this.nameValue = nameValue;
	}


	public Boolean getGroup() {
		return group;
	}

	/**
	 * 
	 * @param group
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}


	public Integer getLevel() {
		return level;
	}

	/**
	 * 
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}


	/**
	 * @return Returns the familyCode.
	 */
	public Long getFamilyCode() {
		return familyCode;
	}
	/**	
	 * @param familyCode The familyCode to set.
	 */
	public void setFamilyCode(Long familyCode) {
		this.familyCode = familyCode;
	}
	
	
	public Boolean getIsFamily(){
		Boolean isFamily = Globals.FALSE;
		if (familyCode != null && familyCode.longValue()>0)
			isFamily = Globals.TRUE;
			
		return isFamily;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessImagePath() {
		return accessImagePath;
	}

	public void setAccessImagePath(String accessImagePath) {
		this.accessImagePath = accessImagePath;
	}
	
	/**
     * Devuelve la extensi�n del archivo adjunto.
     * @return
     */
	public String getAccessImageExtension() {
		if  (this.accessImageExtension == null) {
			if ( this.accessImagePath != null && this.accessImagePath.length() > 0) {
				setAccessImageExtension(this.accessImagePath.substring(this.accessImagePath.lastIndexOf(".")+1).trim());
			}else {
				setAccessImageExtension(null);
			}
		}
		return accessImageExtension;
	}

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setAccessImageExtension(String imageExtension) {
		this.accessImageExtension = imageExtension;
	}
	
	/**
     * Devuelve el path del archivo sin extensi�n para presentaci�n de archivo FLASH.
     * @return
     */
	public String getAccessImageNameNoExtension() {
		if  (this.accessImageNameNoExtension == null) {
			if ( this.accessImagePath != null && this.accessImagePath.length() > 0) {
				setAccessImageNameNoExtension(this.accessImagePath.substring(0,this.accessImagePath.lastIndexOf(".")).trim());
			}else {
				setAccessImageNameNoExtension(null);
			}
		}
		return accessImageNameNoExtension;
	}

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setAccessImageNameNoExtension(String imageNameNoExtension) {
		this.accessImageNameNoExtension = imageNameNoExtension;
	}

    /**
     * @return the childSet
     */
    public List getChildList() {
        return childList;
    }

    /**
     * @param childSet the childSet to set
     */
    public void setChildList(List childList) {
        this.childList = childList;
    }

}

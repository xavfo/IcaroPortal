/*
 * Created on May 5, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.biz;

import java.util.Calendar;

/**
 * Objeto light que recupera solo algunas propiedades de otro objeto.
 * @author jorge
 * @version 1.1
 *
 */
public class AuditRowItem {

	private Long code;
	
	private String user;
    
    private String role;
    
    private Calendar date;
	
    private String action;
    
    private String entityName;
    
    private Long entityCode;
    
    private String nameValue;
    
    private Integer level;
    
   
	
	/**
	 * 
	 */
	public AuditRowItem() {
		super();
		this.clear ();
	}
	
	private void clear () {
		this.code = null;
	}

	public AuditRowItem(Long code, String user, String role, Calendar date, String action, String entityName, String entityType, Long entityCode) {
		super();
		this.clear ();
		this.code = code;
        this.user = user;
        this.role = role;
        this.date = date;
        this.action = action;
        this.entityName = entityName;
        this.nameValue = entityType;
        this.entityCode = entityCode;
            
	}


    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * @return Returns the code.
     */
    public Long getCode() {
        return code;
    }

    /**
     * @return Returns the date.
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * @return Returns the entityName.
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return Returns the role.
     */
    public String getRole() {
        return role;
    }

    /**
     * @return Returns the user.
     */
    public String getUser() {
        return user;
    }

    /**
     * @param action The action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * @param date The date to set.
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * @param entityName The entityName to set.
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return Returns the entityCode.
     */
    public Long getEntityCode() {
        return entityCode;
    }

    /**
     * @param entityCode The entityCode to set.
     */
    public void setEntityCode(Long entityCode) {
        this.entityCode = entityCode;
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

   	
	
}

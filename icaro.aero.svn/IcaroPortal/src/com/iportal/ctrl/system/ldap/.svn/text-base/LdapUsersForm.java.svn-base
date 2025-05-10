/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.ldap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * Formulario de Ldap.
 * 
 * @author  YAGE (martha)
 * @version 1.0
 */
public class LdapUsersForm extends BaseForm {
	
	private static final long serialVersionUID = -1857525314176392204L;

	private Boolean enabled;
	
	private Integer registered;
    
    private String[] usernames;
    
    private String username;
    
    private Integer status;
    
    private String[] cityCodes;
    
    private String cityCode;
   
    private String departmentCode;
    
    private List ldapUsers;
   
    
    
        
    /**
     * Creates a new instance of LdapForm
     */
    public LdapUsersForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.enabled = Globals.TRUE;
        this.registered = null;
        this.username = null;
        this.status = null;
        this.cityCode = new String("0");
        this.departmentCode = new String("0");
    }



	public Boolean getEnabled() {
		return enabled;
	}


	public Integer getRegistered() {
		return registered;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public void setRegistered(Integer registered) {
		this.registered = registered;
	}


    /**
     * @return Returns the usernames.
     */
    public String[] getUsernames() {
        return usernames;
    }


    /**
     * @param usernames The usernames to set.
     */
    public void setUsernames(String[] usernames) {
        this.usernames = usernames;
    }


    /**
     * @return Returns the status.
     */
    public Integer getStatus() {
        return status;
    }


    /**
     * @param status The status to set.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }


    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * @return Returns the cityCodes.
     */
    public String[] getCityCodes() {
        return cityCodes;
    }


    /**
     * @param cityCodes The cityCodes to set.
     */
    public void setCityCodes(String[] cityCodes) {
        this.cityCodes = cityCodes;
    }


    /**
     * @return Returns the cityCode.
     */
    public String getCityCode() {
        return cityCode;
    }


    /**
     * @param cityCode The cityCode to set.
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    /**
     * @return Returns the departmentCode.
     */
    public String getDepartmentCode() {
        return departmentCode;
    }


    /**
     * @param departmentCode The departmentCode to set.
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }


    /**
     * @return Returns the ldapUsers.
     */
    public List getLdapUsers() {
        return ldapUsers;
    }


    /**
     * @param ldapUsers The ldapUsers to set.
     */
    public void setLdapUsers(List ldapUsers) {
        this.ldapUsers = ldapUsers;
    }


}

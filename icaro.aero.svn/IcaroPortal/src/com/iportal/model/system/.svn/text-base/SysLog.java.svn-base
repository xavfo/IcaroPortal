/*
 * Created on 02/02/2005
 * Desarrollado por YAGE @2005
 */
package com.iportal.model.system;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_log")
@javax.persistence.TableGenerator(
		name="TABLE_GEN",
		table="tb_sequence",
		pkColumnName = "name",
		valueColumnName = "next_val",
		pkColumnValue="log",
		allocationSize=20
		)
public class SysLog {
	
	private Long code;
	
	private Long userCode;
    
	private String userName;

    private Long rolCode;
    
	private String roleName;
	
	private Calendar loginDate;
    
    private Calendar logoutDate;
	
	/**
	 * 
	 */
	public SysLog() {
		super();
		this.code = null;
		this.loginDate = null;
		this.logoutDate = null;
		this.userCode = null;
		this.userName = null;
		this.rolCode = null;
		this.roleName = null;
	}
	
   @Id 
    @Column(name="log_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

    /**
     * @return Returns the loginDate.
     */
   @Column(name="log_in_date")
    public Calendar getLoginDate() {
        return loginDate;
    }
    
    /**
     * @return Returns the logoutDate.
     */
   @Column(name="log_out_date")
    public Calendar getLogoutDate() {
        return logoutDate;
    }
    
    /**
     * @return Returns the rolCode.
     */
   @Column(name="log_role_code")
    public Long getRolCode() {
        return rolCode;
    }
    
    /**
     * @return Returns the roleName.
     */
   @Column(name="log_role_name")
    public String getRoleName() {
        return roleName;
    }
    
    /**
     * @return Returns the userCode.
     */
   @Column(name="log_user_code")
    public Long getUserCode() {
        return userCode;
    }
    
    /**
     * @return Returns the userName.
     */
    @Column(name="log_user_name")
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    
    /**
     * @param loginDate The loginDate to set.
     */
    public void setLoginDate(Calendar loginDate) {
        this.loginDate = loginDate;
    }
    
    /**
     * @param logoutDate The logoutDate to set.
     */
    public void setLogoutDate(Calendar logoutDate) {
        this.logoutDate = logoutDate;
    }
    
    /**
     * @param rolCode The rolCode to set.
     */
    public void setRolCode(Long rolCode) {
        this.rolCode = rolCode;
    }
    
    /**
     * @param roleName The roleName to set.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * @param userCode The userCode to set.
     */
    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }
    
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}

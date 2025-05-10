/*
  * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.system;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.iportal.biz.audit.SysAuditHelper;
import com.iportal.cart.model.catalog.Seller;
import com.yage.servlet.http.security.Identity;
import com.yage.servlet.http.security.Inventory;


/*
 * This class persist data to the <code>tb_sys_user</code> 
 * database table.
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 * @hibernate.class table="tb_sys_user"
 */
@Entity
@Table(name="tb_sys_user")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="sys_user",
								allocationSize=20
								)
public class SysUser implements Identity, HttpSessionBindingListener {

    private Long code;
    
    private String name;
    
    private String password;
    
    private Boolean enabled;
    
    private Boolean application;
    
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private SysRole role;
    
    private Seller seller;

    private SysAccessMode accessMode;
    
	private Boolean reset;
    
	private transient Inventory inventory;
    
    private Calendar registrationDate;
	
    private transient SysLog log;
    
    
    
    /**
     * Creates a new instance of SysUser
     */
    public SysUser() {
        super();
    }
    
    /**
     * Creates a new instance of SysUser
     */
    
    public SysUser(Inventory inventory) {
        super();
        this.inventory = inventory;
    }
    
    @Transient
    public String toString() {
        return name;
    }
    
    @Id 
    @Column(name="sys_user_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
    public Long getCode() {
        return code;
    }
    
    @Column(name="sys_user_email", length=200)
    public String getEmail() {
        return email;
    }
    
	/*
	 * @hibernate.property 
	 *     column="sys_user_enabled"
	 *     access="property"
	 */
    @Column(name="sys_user_enabled")
    public Boolean getEnabled() {
        return enabled;
    }
    
	/*
	 * @hibernate.property 
	 *     column="sys_user_application"
	 *     access="property"
	 */
    @Column(name="sys_user_application")
    public Boolean getApplication() {
        return application;
    }
    
	/*
	 * @hibernate.property 
	 *     column="sys_user_first_name"
	 *     access="property"
	 */
    @Column(name="sys_user_first_name", length=100)
    public String getFirstName() {
        return firstName;
    }
    
	/*
	 * @hibernate.property 
	 *     column="sys_user_last_name"
	 *     access="property"
	 */
    @Column(name="sys_user_last_name", length=100)
    public String getLastName() {
        return lastName;
    }
    
	/*
	 * @hibernate.property 
	 *     column="sys_user_name"
	 *     access="property"
	 */
    @Column(name="sys_user_name", length=20)
    public String getName() {
        return name;
    }
    
	/*
	 * @hibernate.property 
	 *     column="sys_user_password"
	 *     access="property"
	 */
    @Column(name="sys_user_password", length=100)
    public String getPassword() {
        return password;
    }
    
    /*
	 * @hibernate.many-to-one 
	 * 	   class="com.iportal.model.system.SysRole"
	 *     column="sys_role_code"
	 *     lazy="false"
	 *     access="property"
	 */
    @ManyToOne
    @JoinColumn(name="sys_role_code")
    public SysRole getRole() {
        return role;
    }
    
    
    
    /**
	 * @return Returns the seller.
	 */
    @ManyToOne
    @JoinColumn(name="seller_code")
    public Seller getSeller() {
		return seller;
	}

	/*
	 * @hibernate.many-to-one
	 * 		column="sys_access_mode_code"
	 * 		class="com.iportal.model.system.SysAccessMode"
	 * 		access="property"
	 */
    @ManyToOne
    @JoinColumn(name="sys_access_mode_code")
	public SysAccessMode getAccessMode(){
		return accessMode;
	}

    @Column(name="sys_user_reset")
    public Boolean getReset(){
		return reset;
	}
    
	/**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param role The role to set.
     */
    public void setRole(SysRole role) {
        this.role = role;
    }
    
    
    
    /**
	 * @param seller The seller to set.
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
     * @param application The application to set.
     */
    public void setApplication(Boolean application) {
        this.application = application;
    }
    
	/**
	 * 
	 * @param accessMode
	 */
	public void setAccessMode(SysAccessMode accessMode){
		this.accessMode = accessMode;
	}

	/**
	 * 
	 * @param reset
	 */
	public void setReset(Boolean reset){
		this.reset = reset;
	}
	
	/**
	 * @return Returns the inventory.
	 */
	@Transient
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @param inventory The inventory to set.
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/*
	 * Identity Interface Methods
	 * 
	 */
	@Transient
	public String getFullName(){
		return firstName + lastName;
	}
	@Transient
    public String getRoleName(){
    	return role.getName();
    }
    @Transient
    public String getUserName(){
    	return name;
    }
    
    
    
	public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
		if (this.inventory != null) {
            this.inventory.add(httpSessionBindingEvent.getSession(), this);
            SysAuditHelper.openLog(this);
        }
        
	}
	
	public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
		if (this.inventory != null) {
            this.inventory.remove(httpSessionBindingEvent.getSession().getId());
            SysAuditHelper.closeLog(this.getLog());
        }
        
	}

    /**
     * @return Returns the registrationDate.
     */
    @Column(name="sys_user_registration_date")
    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate The registrationDate to set.
     */
    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return Returns the log.
     */
    @Transient
    public SysLog getLog() {
        return log;
    }

    /**
     * @param log The log to set.
     */
    public void setLog(SysLog log) {
        this.log = log;
    }
    
    
    
}

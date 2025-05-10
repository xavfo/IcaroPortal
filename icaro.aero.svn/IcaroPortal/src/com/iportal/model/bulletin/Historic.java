/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.bulletin;

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

/**
 * This class persist data to the <code>tb_historic</code> database table.
 * 
 * author  YAGE (Jorge Lomas)
 * version 1.0
 *
 */
@Entity
@Table(name="tb_historic")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="historic",
								allocationSize=20
								)
public class Historic {
	
	private Long code;
	
	private Calendar date;
	
	private Long userCode;
	
	private String userName;
	
	private Long roleCode;
	
	private String roleName;
	
	private Bulletin bulletin;
	
	@Id 
    @Column(name="historic_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

	@Column(name="historic_date")
	public Calendar getDate() {
		return date;
	}

	@Column(name="historic_role_code")
	public Long getRoleCode() {
		return roleCode;
	}

	@Column(name="historic_role_name")
	public String getRoleName() {
		return roleName;
	}

	@Column(name="historic_user_code")
	public Long getUserCode() {
		return userCode;
	}

	@Column(name="historic_user_name")
	public String getUserName() {
		return userName;
	}

	@ManyToOne
	@JoinColumn(name="bulletin_code")
	public Bulletin getBulletin() {
		return bulletin;
	}

	/**
	 * @param bulletin The bulletin to set.
	 */
	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
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
	 * @param roleCode The roleCode to set.
	 */
	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
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
    
    @Transient
    public String toString() {
        return this.userName + " - " + this.bulletin.getTitle();
    }
}

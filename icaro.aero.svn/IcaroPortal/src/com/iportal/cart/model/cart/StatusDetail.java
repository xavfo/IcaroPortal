/*
 * Created 05/02/2007
 *	StatusDetail.java
 */
package com.iportal.cart.model.cart;

import java.util.Calendar;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.iportal.model.system.SysUser;

/**
 * Propiedades comunes a objetos para auditoria y seguimiento de Cambios de Estado
 * @author hernan
 * @version 1.0
 *
 */
@MappedSuperclass
public abstract class StatusDetail {

	protected Long code;
	
	protected SysUser user;
	
	protected Calendar creation;
	
	protected String remoteAddress;
	
	protected String remoteHost;

	/**
	 * 
	 */
	public StatusDetail() {
		super();
		this.clear();
	}
	

	
	protected void clear () {
		this.code = null;
		this.user = null;
		this.creation   = null;
		this.remoteHost = null;
		this.remoteAddress = null;
	}



	/**
	 * @return Returns the code.
	 */
	@Transient 
	public Long getCode() {
		return code;
	}



	/**
	 * @return Returns the creation.
	 */
	public Calendar getCreation() {
		return creation;
	}



	/**
	 * @return Returns the remoteAddress.
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}



	/**
	 * @return Returns the remoteHost.
	 */
	public String getRemoteHost() {
		return remoteHost;
	}



	/**
	 * @return Returns the user.
	 */
    @ManyToOne
    @JoinColumn(name="sys_user_code")
	public SysUser getUser() {
		return user;
	}



	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}



	/**
	 * @param creation The creation to set.
	 */
	public void setCreation(Calendar creation) {
		this.creation = creation;
	}



	/**
	 * @param remoteAddress The remoteAddress to set.
	 */
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}



	/**
	 * @param remoteHost The remoteHost to set.
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}



	/**
	 * @param user The user to set.
	 */
	public void setUser(SysUser user) {
		this.user = user;
	}
	
	


}

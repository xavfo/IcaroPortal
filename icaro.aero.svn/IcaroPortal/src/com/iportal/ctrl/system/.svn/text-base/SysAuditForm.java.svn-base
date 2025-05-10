/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * @author andresg
 *
 */
public class SysAuditForm extends BaseForm {

	/**
     * 
     */
    private static final long serialVersionUID = 3711362064954793898L;

    private String dateForm;

    private String fromDate;
	
	private String toDate;

	private Long user;
	
	private String userName;
	
	private String userRoleName;
	
	private String className;
	
	private Long entity;
	
	private String actionString;
	
	private String module;
	
	private String method;
	
	private String uri;
	
	private String url;
	
	private String remoteAddress;
	
	private String remoteHost;

    private Boolean search;
	
    /**
     * Creates a new instance of SysAuditForm
     */
	public SysAuditForm() {
		super();
	}
	
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
    	dateForm = null;
    	fromDate = null;
    	toDate = null;
    	user = null;
    	userName = null;
    	userRoleName = null;
    	className = null;
    	entity = null;
    	actionString = null;
    	module = null;
    	method = null;
    	uri = null;
    	url = null;
    	remoteAddress = null;
    	remoteHost = null;
        search = Globals.FALSE;  
   }

    public Calendar getDate() {
   		Calendar calendar = new GregorianCalendar();
   			if ( this.getDateForm() != null && this.getDateForm().length() > 0  ) { 
   				calendar.setTime( DateFormatUtils.parseToDate(this.getDateForm(), Constants.DATE_FORMAT) );
   				return calendar;
   			}
   			return null;
   		}
   		
   	public void setDate(Calendar calendar) {
   		if ( calendar != null )
   			this.dateForm = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
   	}

    public Calendar getFrom() {
    	Calendar calendar = new GregorianCalendar();
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT) );
			return calendar;
		}
		return null;
	}
	
	public void setFrom(Calendar calendar) {
		if ( calendar != null )
			this.fromDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
	}
	
    public Calendar getTo() {
    	Calendar calendar = new GregorianCalendar();
		if ( this.getToDate() != null && this.getToDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT) );
			return calendar;
		}
		return null;
	}
	
	public void setTo(Calendar calendar) {
		if ( calendar != null )
			this.toDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
	}
 	/**
	 * @return Returns the actionString.
	 */
	public String getActionString() {
		return actionString;
	}
	/**
	 * @param action The actionString to set.
	 */
	public void setActionString(String actionString) {
		this.actionString = actionString;
	}
	/**
	 * @return Returns the className.
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return Returns the dateForm.
	 */
	public String getDateForm() {
		return dateForm;
	}
	/**
	 * @param dateForm The dateForm to set.
	 */
	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}
	/**
	 * @return Returns the entity.
	 */
	public Long getEntity() {
		return entity;
	}
	/**
	 * @param entity The entity to set.
	 */
	public void setEntity(Long entity) {
		this.entity = entity;
	}
	/**
	 * @return Returns the method.
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return Returns the module.
	 */
	public String getModule() {
		return module;
	}
	/**
	 * @param module The module to set.
	 */
	public void setModule(String module) {
		this.module = module;
	}
	/**
	 * @return Returns the remoteAddress.
	 */
	public String getRemoteAddress() {
		return remoteAddress;
	}
	/**
	 * @param remoteAddress The remoteAddress to set.
	 */
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	/**
	 * @return Returns the remoteHost.
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	/**
	 * @param remoteHost The remoteHost to set.
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	/**
	 * @return Returns the search.
	 */
	public Boolean getSearch() {
		return search;
	}
	/**
	 * @param search The search to set.
	 */
	public void setSearch(Boolean search) {
		this.search = search;
	}
	/**
	 * @return Returns the uri.
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri The uri to set.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return Returns the user.
	 */
	public Long getUser() {
		return user;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(Long user) {
		this.user = user;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the userRoleName.
	 */
	public String getUserRoleName() {
		return userRoleName;
	}
	/**
	 * @param userRoleName The userRoleName to set.
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	/**
	 * @return Returns the dateToForm.
	 */
	
	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}

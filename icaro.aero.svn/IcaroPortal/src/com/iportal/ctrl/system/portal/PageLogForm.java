/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * Forma para administraci�n del log de p�gina y reporte de recursos
 *  
 * @author andresg
 * @version 1.0
 * @see com.yage.struts.action.BaseForm
 */
public class PageLogForm extends BaseForm {

	/**
     * 
     */
    private static final long serialVersionUID = 460497472858130479L;

    private String sessionId;

	private String dateForm;

    private String fromDate;
	
	private String toDate;
	
	private String remoteAddress;
	
	private String remoteHost;
	
	private String reference;

	private String url;
	
	private Long resourceCode;
	
	private String resourceDescription;

	private String resourceType;

	private String user;

	private String userName;

	private Boolean search;
	
	private Boolean isReport;

	/**
	 * Crea un nuevo objeto.
	 */
	public PageLogForm() {
		super();
	}

	/* Limpia los atributos del objeto.
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
    	dateForm = null;
    	fromDate = null;
    	toDate = null;
    	sessionId = null;
    	remoteAddress = null;
    	remoteHost = null;
    	reference = null;
    	url = null;
    	resourceCode = null;
    	resourceDescription = null;
    	resourceType = null;
    	user = null;
    	userName = null;
        search = Globals.FALSE;
        isReport = Globals.FALSE;
   }
	
    /**
     * Transforma el atributo dateForm en un objeto de tipo {@link java.util.GregorianCalendar} y devuelve ese objeto 
     * @return
     */
    public Calendar getDate() {
   		Calendar calendar = new GregorianCalendar();
   			if ( this.getDateForm() != null && this.getDateForm().length() > 0  ) { 
   				calendar.setTime( DateFormatUtils.parseToDate(this.getDateForm(), Constants.DATE_FORMAT) );
   				return calendar;
   			}
   			return null;
   		}
   	
    /**
   	 * @param calendar
   	 */
   	public void setDate(Calendar calendar) {
   		if ( calendar != null )
   			this.dateForm = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
   	}

    /**
     * Transforma el atributo fromDate en un objeto de tipo  {@link java.util.GregorianCalendar} y devuelve ese objeto 
     * @return
     */
    public Calendar getFrom() {
    	Calendar calendar = new GregorianCalendar();
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT) );
			return calendar;
		}
		return null;
	}
	
	/**
	 * @param calendar
	 */
	public void setFrom(Calendar calendar) {
		if ( calendar != null )
			this.fromDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
	}
	
    /**
     * Transforma el atributo toDate en un objeto de tipo {@link java.util.GregorianCalendar} y devuelve ese objeto 
     * @return
     */
    public Calendar getTo() {
    	Calendar calendar = new GregorianCalendar();
		if ( this.getToDate() != null && this.getToDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT) );
			return calendar;
		}
		return null;
	}
	
	/**
	 * @param calendar
	 */
	public void setTo(Calendar calendar) {
		if ( calendar != null )
			this.toDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
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
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
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
	 * @return Returns the resourceCode.
	 */
	public Long getResourceCode() {
		return resourceCode;
	}
	/**
	 * @param resourceCode The resourceCode to set.
	 */
	public void setResourceCode(Long resourceCode) {
		this.resourceCode = resourceCode;
	}
	/**
	 * @return Returns the resourceDescription.
	 */
	public String getResourceDescription() {
		return resourceDescription;
	}
	/**
	 * @param resourceDescription The resourceDescription to set.
	 */
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}
	/**
	 * @return Returns the resourceType.
	 */
	public String getResourceType() {
		return resourceType;
	}
	/**
	 * @param resourceType The resourceType to set.
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
	 * @return Returns the sessionId.
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId The sessionId to set.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
	public String getUser() {
		return user;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(String user) {
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
	 * @return Returns the isReport.
	 */
	public Boolean getIsReport() {
		return isReport;
	}
	/**
	 * @param isReport The isReport to set.
	 */
	public void setIsReport(Boolean isReport) {
		this.isReport = isReport;
	}
}

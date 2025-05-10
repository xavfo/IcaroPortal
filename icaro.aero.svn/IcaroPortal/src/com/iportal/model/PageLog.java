/*
 * Created Aug 8, 2006
 *	PageLog.java
 */
package com.iportal.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * Registro de Log de visitas
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_page_log")

public class PageLog {

	private Long code;
	
	private String sessionId;

	private Calendar date;
	
	private String url;
	
	private String remoteAddress;
	
	private String remoteHost;
	
	private Long resourceCode;
	
	private String resourceDescription;

	private String resourceType;
	
	private String userAgent;
	
	private String referrer;
	
	private String language;

	
	/**
	 * 
	 */
	public PageLog() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.code = null;
		this.sessionId = null;
		this.date = null;
		this.url = null;
		this.remoteAddress = null;
		this.remoteHost = null;
		this.resourceCode = null;
		this.resourceDescription = null;
		this.resourceType = null;
		this.userAgent = null;
		this.referrer = null;
		this.language = null;
	}

	/**
	 * @return Returns the code.
	 */
	@Id 
    @Column(name = "page_log_code")
    @GeneratedValue (generator="idGenerator")
    @GenericGenerator (
    		name="idGenerator",
    		strategy="com.yage.hibernate.SequenceTableGenerator",
    		parameters = {
    				@Parameter(name="table", value="tb_sequence"),
    				@Parameter(name="sequence", value="page_log"),
    				@Parameter(name="columnId", value="name"),
    				@Parameter(name="columnValue", value="next_val")
    		}
    	)
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the date.
	 */
    @Column(name="page_log_date")
	public Calendar getDate() {
		return date;
	}

	/**
	 * @return Returns the language.
	 */
    @Column(name="page_log_language")
	public String getLanguage() {
		return language;
	}

	/**
	 * Obtiene el link Referrer, es decir,
	 * el link desde donde se llego al 
	 * presente request
	 * Referer
	 * @return Returns the referrer.
	 */
    @Column(name="page_log_referrer")
	public String getReferrer() {
		return referrer;
	}

	/**
	 * @return Returns the remoteAddress.
	 */
    
    @Column(name="page_log_remote_address")
	public String getRemoteAddress() {
		return remoteAddress;
	}

	/**
	 * @return Returns the remoteHost.
	 */
    @Column(name="page_log_remote_host")
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * @return Returns the resourceCode.
	 */
    @Column(name="page_log_resource_code")
	public Long getResourceCode() {
		return resourceCode;
	}

	/**
	 * @return Returns the resourceDescription.
	 */
    @Column(name="page_log_resource_description")
	public String getResourceDescription() {
		return resourceDescription;
	}

	/**
	 * @return Returns the resourceType.
	 */
    @Column(name="page_log_resource_type")
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @return Returns the sessionId.
	 */
    @Column(name="page_log_session_id")
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @return Returns the url.
	 */
    @Column(name="page_log_url")
	public String getUrl() {
		return url;
	}

	/**
	 * @return Returns the userAgent.
	 */
    @Column(name="page_log_user_agent")
	public String getUserAgent() {
		return userAgent;
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
	 * @param language The language to set.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @param referrer The referrer to set.
	 */
	public void setReferrer(String referrer) {
		this.referrer = referrer;
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
	 * @param resourceCode The resourceCode to set.
	 */
	public void setResourceCode(Long resourceCode) {
		this.resourceCode = resourceCode;
	}

	/**
	 * @param resourceDescription The resourceDescription to set.
	 */
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	/**
	 * @param resourceType The resourceType to set.
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @param sessionId The sessionId to set.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param userAgent The userAgent to set.
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
    
	@Transient
	public String toString(){
	    return this.getReferrer() + ", " + this.getUrl() + ", " + this.resourceType;    
    }

}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_audit")
@javax.persistence.TableGenerator(
		name="TABLE_GEN",
		table="tb_sequence",
		pkColumnName = "name",
		valueColumnName = "next_val",
		pkColumnValue="audit",
		allocationSize=20
		)
public class SysAudit {
	
	private Long code;
	
	private Calendar date;
	
	private String className;
	
	private Long entity;
    
    private String entityName;
	
	private String action;
	
	private String module;
	
	private String method;
	
	private String uri;
	
	private String url;
	
	private String remoteAddress;
	
	private String remoteHost;
    
    private SysLog log;

	
	/**
	 * 
	 */
	public SysAudit() {
		super();
		this.code = null;
		this.date = null;
		this.className = null;
		this.entity = null;
        this.entityName = null;
		this.action = null;
		this.module = null;
		this.method = null;
		this.uri = null;
		this.url = null;
		this.remoteAddress = null;
		this.remoteHost = null;
	}
	
    @Id 
    @Column(name="audit_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
    
    @Column(name="audit_action", length=100)
	public String getAction() {
		return action;
	}

    
    @Column(name="audit_class")
	public String getClassName() {
		return className;
	}
    
    @Column(name="audit_date")
	public Calendar getDate() {
		return date;
	}
    
    @Column(name="audit_entity")
	public Long getEntity() {
		return entity;
	}
    
    @Column(name="audit_entity_name")
    public String getEntityName() {
        return entityName;
    }


    @Column(name="audit_method")
	public String getMethod() {
		return method;
	}

    @Column(name="audit_module")
	public String getModule() {
		return module;
	}

    @Column(name="audit_remote_address")
	public String getRemoteAddress() {
		return remoteAddress;
	}

    @Column(name="audit_remote_host")
	public String getRemoteHost() {
		return remoteHost;
	}
    
    @Column(name="audit_uri")
	public String getUri() {
		return uri;
	}
    
    @Column(name="audit_url")
	public String getUrl() {
		return url;
	}
    
    @ManyToOne
    @JoinColumn(name="log_code")
    public SysLog getLog() {
        return log;
    }

    
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		this.className = className;
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
	 * @param entity The entity to set.
	 */
	public void setEntity(Long entity) {
		this.entity = entity;
	}
	/**
	 * @param method The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @param module The module to set.
	 */
	public void setModule(String module) {
		this.module = module;
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
	 * @param uri The uri to set.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

    /**
     * @param log The log to set.
     */
    public void setLog(SysLog log) {
        this.log = log;
    }

    /**
     * @param entityName The entityName to set.
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

}

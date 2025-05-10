/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/*
 * This class persist data to the <code>tb_banner_type</code> database table.
 * 
 * @author  YAGE (hleon)
 * @version 3.0
 */

@Entity
@Table(name="tb_access_url")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="access_url",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class AccessUrl {
	
    private Long code;

    private String name;
    
    private String path;
    
    private String className;
    
    
    
    /**
     * Creates a new instance of Account
     */
    public AccessUrl() {
        super();
        this.code = null;
        this.name = null;
        this.path = null;
        this.className = null;
        
    }
    @Transient
    public String toString() {
        return name;
    }
    

    /**
	 * @return Returns the className.
	 */
    @Column(name="access_url_class_name")
	public String getClassName() {
		return className;
	}
    
    @Transient
    public String getClassNameShort() {
        int lastIndex = className.lastIndexOf('.');
        if(-1 == lastIndex) {
            return className;
        }
        return className.substring(lastIndex,className.length());
    }

    @Id 
    @Column(name="access_url_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    
	@Column(name="access_url_name")
    public String getName() {
        return name;
    }
	
    @Column(name="access_url_path")
	public String getPath() {
		return path;
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
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
		this.path = path;
	}
	
}
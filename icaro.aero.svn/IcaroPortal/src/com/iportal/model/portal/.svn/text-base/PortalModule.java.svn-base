/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class persist data to the <code>tb_portal_module</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 * 
 */

@Entity
@Table(name="tb_portal_module")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="portal_module",
								allocationSize=20
								)
public class PortalModule {

    private Long code;
    
    private String name;
    
    private String key;
    
    private String forward;
    
    /**
     * Creates a new instance of PortalModule
     */
    public PortalModule() {
        super();
    }

    @Id 
    @Column(name="portal_module_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	@Column(name="portal_module_forward")
	public String getForward() {
		return forward;
	}
	/**
	 * @param forward The name to set.
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}

	@Column(name="portal_module_key")
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	@Column(name="portal_module_name")
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}

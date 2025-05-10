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
 * This class persist data to the <code>tb_display_mode</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 * 
 */

@Entity
@Table(name="tb_display_mode")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="display_mode",
								allocationSize=20
								)
public class DisplayMode {

    private Long code;
    
    private String name;
    
    private String key;
    
    private Boolean enabled;
    
    /**
     * Creates a new instance of Menu
     */
    public DisplayMode() {
        super();
    }

    @Id 
    @Column(name="display_mode_code")
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

	@Column(name="display_mode_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name="display_mode_key")
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	@Column(name="display_mode_name")
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

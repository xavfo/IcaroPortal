/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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

/**
 * This class persist data to the <code>tb_role</code> database table.
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 */
@Entity
@Table(name="tb_occupation")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="occupation",
								allocationSize=20
								)
public class Occupation {

    private Long code;
    
    private String name;
    
    private String key;
    
    
    /**
     * Creates a new instance of Role
     */
    public Occupation() {
        super();
    }
    @Transient
    public String toString() {
        return name;
    }
    
    @Id
    @Column(name="occupation_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    @Column(name="occupation_name")
    public String getName() {
        return name;
    }
    
    @Column(name="occupation_key")
    public String getKey() {
        return key;
    }
    
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}

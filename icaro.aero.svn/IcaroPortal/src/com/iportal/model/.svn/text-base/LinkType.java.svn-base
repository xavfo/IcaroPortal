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


/**
 *  
 * @author YAGE (monica)
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_link_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="link_type",
								allocationSize=20
								)
public class LinkType {

    private Long code;
    
    private String name;
    
    
    /**
     * Creates a new instance of Document
     */
    public LinkType() {
        super();
    }
    

    public String toString() {
        return name;
    }
    

    @Id 
    @Column(name="link_type_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
		return code;
	}
	/**
	 * @hibernate.property 
	 *     column="link_type_name"
	 */
	@Column(name="link_type_name")
	public String getName() {
		return name;
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
}

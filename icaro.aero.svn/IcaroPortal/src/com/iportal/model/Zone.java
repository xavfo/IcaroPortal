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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Representa un objeto Zone.  Una ciudad se divide en "zonas".
 * 
 * @author  pajaro
 * @version 1.0
 *
 * @hibernate.class table="tb_zone"
 * @hibernate.cache usage="read-write"
 */
@Entity
@Table(name="tb_zone")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="zone",
								allocationSize=20
								)
public class Zone {

	private Long code;
	
	private String name;
	
	private Boolean isEnabled;	           
    
    private City city;
    
    /**
     * Creates a new instance of Catalogue
     */
    public Zone() {
        super();
    }
    
    @Id 
    @Column(name="zone_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	
    @Column(name="zone_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	
	
    @Column(name="zone_name", length=200 )
	public String getName() {
		return name;
	}
		
	@ManyToOne
    @JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}
	
	/**
	 * @param city The city to set.
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	public void setCode(Long code) {
		this.code = code;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}	

	public void setName(String name) {
		this.name = name;
	}

}

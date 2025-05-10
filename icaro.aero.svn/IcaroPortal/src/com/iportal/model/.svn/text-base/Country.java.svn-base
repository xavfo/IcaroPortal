/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.yage.Globals;

/**
 * This class persist data to the <code>tb_country</code> database table.
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 * 
 */
@Entity
@Table(name="tb_country")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="country",
								allocationSize=1
								)
public class Country {

	private Long code;
	
	private String name;
	
	private String iso;
	
	private String key;

	private Set<State> states;
	
	private Boolean isEnabled;
	
	private Boolean homeDelivery;
	
	/**
     * Creates a new instance of Country
     */
    public Country() {
        super();
    	this.code = null;
    	this.name = null;
    	this.key  = null;
    	this.iso  = null;
    	this.states = null;
    	this.isEnabled = Globals.FALSE;
    	this.homeDelivery = Globals.FALSE;

    }
    

    public String toString() {
        return name;
    }
    
    @Id
    @Column(name = "country_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }

    @Column(name="country_name")
    public String getName() {
        return name;
    }

    @Column(name="country_iso")
    public String getIso() {
        return this.iso;
    }

    @Column(name="country_key")
	public String getKey() {
		return key;
	}

	/**
     * @hibernate.set 
     *     table="tb_state"
     *     lazy="true"
     * 
     * @hibernate.key column="country_code"
     * @hibernate.one-to-many class="com.iportal.model.State"
     */
    @OneToMany(mappedBy="country")
    public Set<State> getStates() {
        return states;
    }
    
    @Column(name="country_home_delivery")
    public Boolean getHomeDelivery() {
		return homeDelivery;
	}


	@Column(name="country_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}


	public void setCode(Long code) {
		this.code = code;
	}


	public void setHomeDelivery(Boolean homeDelivery) {
		this.homeDelivery = homeDelivery;
	}


	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public void setIso(String iso) {
		this.iso = iso;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setStates(Set<State> states) {
		this.states = states;
	}
	
	

}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yage.Globals;

/*
 * This class persist data to the <code>tb_city</code> database table.
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 */
@Entity
@Table(name="tb_city")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "city",
								allocationSize = 1
								)
public class City {

	private Long code;
	
	private String name;
	
	private String key;

    private State state;
    
    private Boolean isEnabled;
    
    private Boolean homeDelivery;
    
    /**
     * Creates a new instance of City
     */
    public City() {
        super();
    	this.code = null;
    	this.name = null;
    	this.key = null;
    	this.state = null;
    	this.isEnabled = Globals.FALSE;
    	this.homeDelivery = Globals.FALSE;
    }
    
    public String toString() {
        return name;
    }
   
    @Id
    @Column(name="city_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
	/**
	 * @return Returns the homeDelivery.
	 */
    @Column(name="city_home_delivery")
	public Boolean getHomeDelivery() {
		return homeDelivery;
	}

    @Column(name="city_key", length=200)
    public String getKey() {
        return key;
    }


	@Column(name="city_name", length=200)
    public String getName() {
        return name;
    }
   
    
    

    /**
	 * @return Returns the route.
	 */


	@ManyToOne
    @JoinColumn(name="state_code")
    public State getState() {
        return state;
    }

    @Column(name="city_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}
    
    

	/**
	 * @param homeDelivery The homeDelivery to set.
	 */
	public void setHomeDelivery(Boolean homeDelivery) {
		this.homeDelivery = homeDelivery;
	}

	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	/**
     * @param string
     */
    public void setKey(String string) {
        this.key = string;
    }

    /**
     * @param long1
     */
    public void setCode(Long long1) {
        code = long1;
    }

	

    /**
     * @param country
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_state")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="state",
								allocationSize=1
								)
public class State {

	private Long code;
	
	private String name;
	
	private String key;

    private Country country;
    
    private Set<City> cities;
    
    private Boolean isEnabled;
    

    /**
     * Creates a new instance of State
     */
    public State() {
        super();
    }
    
    @Transient
    public String toString() {
        return name;
    }
    
    @Id 
    @Column(name="state_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }

    /*
     * @hibernate.property 
     *     column="state_name"
     */
    @Column(name="state_name", length=200)
    public String getName() {
        return name;
    }

    /*
     * @hibernate.property 
     *     column="state_key"
     */
    @Column(name="state_key", length=200)
    public String getKey() {
        return key;
    }

    /*
     * @hibernate.many-to-one 
     *     column="country_code" 
     *     class="com.iportal.model.Country"
     */
    @ManyToOne
    @JoinColumn(name="country_code")
    public Country getCountry() {
        return country;
    }

	/*
     * @hibernate.set 
     *     table="tb_city"
     *     lazy="true"
     * 
     * @hibernate.key column="state_code"
     * @hibernate.one-to-many class="com.iportal.model.City"
     */
    @OneToMany(mappedBy="state")
	public Set<City> getCities() {
		return cities;
	}

	
	/*
     * @hibernate.property 
     *     column="state_enabled"
     */
    @Column(name="state_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
     * @param cities The cities to set.
     */
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    /**
     * @param country The country to set.
     */
    public void setCountry(Country country) {
        this.country = country;
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

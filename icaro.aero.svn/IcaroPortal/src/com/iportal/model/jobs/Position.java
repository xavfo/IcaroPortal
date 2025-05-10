/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.jobs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @version 2.0
 * @created 01-Jun-2006 5:38:02 PM
 */
@Entity
@Table(name="tb_position")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="position",
								allocationSize=20
								)

public class Position{

	private Long code;
	private Area area;	
	private String name;
	private String shortDescription;
	private String longDescription;
	private Boolean isEnabled;
	private String profileDescription;
	

	public Position(){
		super();
	}

	@Id 
    @Column(name="position_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode(){
		return code;
	}


	/**
	 * 
	 * @param newVal
	 */
	public void setCode(Long newVal){
		code = newVal;
	}

	@Lob
	@Column(name="position_short_description")
	public String getShortDescription(){
		return shortDescription;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setShortDescription(String newVal){
		shortDescription = newVal;
	}

	@Column(name="position_name", length=200)
	public String getName(){
		return name;
	}

	
	public void setName(String newVal){
		name = newVal;
	}

	@Lob
	@Column(name="position_long_description")
	public String getLongDescription(){
		return longDescription;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLongDescription(String newVal){
		longDescription = newVal;
	}

	@Lob
	@Column(name="position_profile_description")
	public String getProfileDescription(){
		return profileDescription;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setProfileDescription(String newVal){
		profileDescription = newVal;
	}

	@ManyToOne
    @JoinColumn(name="area_code")	
	public Area getArea(){
		return area;
	}
	/**
	 * 
	 * @param newVal
	 */
	public void setArea(Area newVal){
		area = newVal;
	}
	

	/**
	 * 
	 * @param newVal
	 */
	public void setIsEnabled(Boolean newVal){
		isEnabled = newVal;
	}


	@Column(name="position_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}

}
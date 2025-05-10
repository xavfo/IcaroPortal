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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @version 2.0
 * @created 12-Jul-2006 10:26:02 PM
 */
@Entity
@Table(name="tb_position_requested")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="position_requested",
								allocationSize=20
								)
public class PositionRequest{

	private Long code;
	
	private Position position;
	
	private Requester requester;
	
	private Boolean experience;
	
	public PositionRequest(){
		super();
	}

	@Id 
    @Column(name="position_requested_code")
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

	@ManyToOne
	@JoinColumn(name="position_code")
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position The position to set.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return Returns the requester.
	 */
	@ManyToOne
	@JoinColumn(name="requester_code")
	public Requester getRequester() {
		return requester;
	}

	/**
	 * @param requester The requester to set.
	 */
	public void setRequester(Requester requester) {
		this.requester = requester;
	}

	/**
	 * @return Returns the experience.
	 */
	@Column(name="position_requested_experience")
	public Boolean getExperience() {
		return experience;
	}

	/**
	 * @param experience The experience to set.
	 */
	public void setExperience(Boolean experience) {
		this.experience = experience;
	}

}
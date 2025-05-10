/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.model.container;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a DocumentContainer Object.
 * 
 * @author  Pajaro
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_image_container")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="image_container",
								allocationSize=20
								)

public class ImageContainer {

	private Long code;
	
	private String title;
		
	private String description;
	
	private String photo1;
	
	private String photo2;
	
	private String photo3;
	
	private Boolean isEnabled;
	
	private Calendar from;
	
	private Calendar to;
	
	
	/**
     * Creates a new instance of CatalogueType
     */
    public ImageContainer() {
        super();
    }
        
    @Id 
    @Column(name="image_container_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
		
	/**
	 * @return Returns the title
	 */
	@Column(name="image_container_title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the description.
	 */
	@Column(name="image_container_description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param content1 The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the photo1.
	 */
	@Column(name="image_container_photo1")
	public String getPhoto1() {
		return photo1;
	}

	/**
	 * @param photo1 The photo1 to set.
	 */
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	/**
	 * @return Returns the from.
	 */
	@Column(name="image_container_from")
	public Calendar getFrom() {
		return from;
	}

	/**
	 * @param from The from to set.
	 */
	public void setFrom(Calendar from) {
		this.from = from;
	}

	/**
	 * @return Returns the photo2.
	 */
	@Column(name="image_container_important_photo2")
	public String getPhoto2() {
		return photo2;
	}

	/**
	 * @param photo2 The photo2 to set.
	 */
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	/**
	 * @return Returns the isEnabled.
	 */
	@Column(name="image_container_enabled")
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
	 * @return Returns the photo3.
	 */
	@Column(name="image_photo3")
	public String getPhoto3() {
		return photo3;
	}

	/**
	 * @param photo3 The photo3 to set.
	 */
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}

	/**
	 * @return Returns the to.
	 */
	@Column(name="image_container_to")
	public Calendar getTo() {
		return to;
	}

	/**
	 * @param to The to to set.
	 */
	public void setTo(Calendar to) {
		this.to = to;
	}
	
}//end class

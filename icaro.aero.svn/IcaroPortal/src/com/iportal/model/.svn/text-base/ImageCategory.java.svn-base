/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * This class persist data to the <code>tb_image_category</code> database table.
 * 
 * @author YAGE (Jorge Lomas)
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_image_category")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="image_category",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class ImageCategory {

    private Long code;
    
    private String name;
    
    private ImageCategory parent;
    
    private Set<ImageCategory> imageCategories;
    
    @Id 
    @Column(name="image_category_code")
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
	
	@Column(name="image_category_name")
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name="image_category_parent")
	public ImageCategory getParent() {
		return parent;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(ImageCategory parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	public Set<ImageCategory> getImageCategories() {
		return imageCategories;
	}
	/**
	 * @param imageCategories The imageCategories to set.
	 */
	public void setImageCategories(Set<ImageCategory> imageCategories) {
		this.imageCategories = imageCategories;
	}
}

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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iportal.model.Catalogue;
import com.iportal.model.LinkType;
import com.iportal.model.portal.Content;

/**
 * Represents a DocumentContainer Object.
 * 
 * @author  mcnovillo
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_link_container")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="link_container",
								allocationSize=20
								)
public class LinkContainer {

	private Long code;
	
	private String title;
	
	private String url;
	
	private String description;
	
	private Calendar from;
	
	private Calendar to;
	
	private Boolean isEnabled;
	
	private LinkType type;
	
	private Catalogue category;
	
	private Set<Content> contents;
	
	 private Long index;
	
	
	/**
     * Creates a new instance of CatalogueType
     */
    public LinkContainer() {
        super();
    }
        
    @Id 
    @Column(name="link_container_code")
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
	@Column(name="link_container_title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}

	/**
	 * @return Returns the description.
	 */
	@Column(name="link_container_description")
	public String getDescription() {
		return description;
	}
	
	 /**
	 * @return Returns the path.
	 */
	@Column(name="link_container_index")
	public Long getIndex() {
		return index;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String content1) {
		this.description = content1;
	}

	/**
	 * @return Returns the from.
	 */
	@Column(name="link_container_from")
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
	 * @return Returns the isEnabled.
	 */
	@Column(name="link_container_enabled")
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
	 * @return Returns the path.
	 */
	@Column(name="link_container_url")
	public String getUrl() {
		return url;
	}

	/**
	 * @param path The path to set.
	 */
	public void setUrl(String path) {
		this.url = path;
	}

	/**
	 * @return Returns the to.
	 */
	@Column(name="link_container_to")
	public Calendar getTo() {
		return to;
	}

	/**
	 * @param to The to to set.
	 */
	public void setTo(Calendar to) {
		this.to = to;
	}

	/**
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="CATALOGUE_CODE")
	public Catalogue getCategory() {
		return category;
	}

	/**
	 * @param category The category to set.
	 */
	public void setCategory(Catalogue category) {
		this.category = category;
	}
	/**
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="LINK_TYPE_CODE")
	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}

	/**
	 * @return Returns the contents.
	 */
	@ManyToMany ( mappedBy="listOfRelatedLink", targetEntity=Content.class)
	public Set<Content> getContents() {
		return contents;
	}

	/**
	 * @param contents The contents to set.
	 */
	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}
	/**
	 * @param path The path to set.
	 */
	public void setIndex(Long index) {
		this.index = index;
	}
	
	
}//end class

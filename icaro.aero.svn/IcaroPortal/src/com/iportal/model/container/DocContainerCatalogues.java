/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.model.container;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.iportal.model.Catalogue;

/**
 * Represents a DocumentContainerCatalogue Object.
 * This object holds the catalogues that belong to the DocumentContainer Object
 * 
 * @author  Pajaro
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_container_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="container_type",
								allocationSize=20
								)
public class DocContainerCatalogues {

	private Long code;
	
	private DocumentContainer container;
	
	private Catalogue type;
	
	private Catalogue category;
	
	/**
	 * Creates a new instance of CatalogueType
	 */
	public DocContainerCatalogues() {
	    super();
	}

	@Id 
	@Column(name="container_type_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @return Returns the container.
	 */
	@OneToOne
	@JoinColumn(name="DOC_CONTAINER_CODE")
	public DocumentContainer getContainer() {
		return container;
	}

	/**
	 * @param container The container to set.
	 */
	public void setContainer(DocumentContainer container) {
		this.container = container;
	}

	/**
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="CATALOGUE_DOCCATEGORY")
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
	 * @return Returns the type.
	 */
	@ManyToOne
	@JoinColumn(name="CATALOGUE_TYPEDOCCATEGORY")
	public Catalogue getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(Catalogue type) {
		this.type = type;
	}
}

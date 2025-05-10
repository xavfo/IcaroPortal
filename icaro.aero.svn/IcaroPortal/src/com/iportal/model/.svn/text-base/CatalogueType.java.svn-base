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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Tipo de catálogo.
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 * @hibernate.class table="tb_catalogue_type"
 * @hibernate.cache usage="read-write"
 */
@Entity
@Table(name="tb_catalogue_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="catalogue_type",
								allocationSize=20
								)
public class CatalogueType {

	private Long code;
	
	private String name;
	
	private Set<Catalogue> catalogues;
    
    /**
     * Creates a new instance of CatalogueType
     */
    public CatalogueType() {
        super();
    }
    
    @Id 
    @Column(name="catalogue_type_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	
	
	@OneToMany(mappedBy="type")
	public Set<Catalogue> getCatalogues() {
		return catalogues;
	}
	
	public void setCatalogues(Set<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	/*
	 * @hibernate.property 
	 *     column="catalogue_type_name"
	 */
	@Column(name="catalogue_type_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

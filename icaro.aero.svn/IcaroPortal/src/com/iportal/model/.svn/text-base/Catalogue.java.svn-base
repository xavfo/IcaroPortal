/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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

/*
 * Catálogo de catálogos: tipos de cuentas bancarias, prioridades, unidades, frecuencia, títulos, entre otros.
 * 
 * @author  YAGE (pajaro)
 * @version 1.0
 *
 * @hibernate.class table="tb_catalogue"
 * @hibernate.cache usage="read-write"
 */
@Entity
@Table(name="tb_catalogue")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="catalogue",
								allocationSize=20
								)
public class Catalogue {

	private Long code;
	
	private String name;
	
	private Boolean enabled;
	
	private String icon;
	
    private CatalogueType type;
    
    private String description;
    
    private String email;
    
    private String administrator;
     
    
    /**
     * Creates a new instance of Catalogue
     */
    public Catalogue() {
        super();
    }

    @Id 
    @Column(name="catalogue_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

    @Column(name="catalogue_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
    
    @Column(name="catalogue_icon")
	public String getIcon() {
		return icon;
	}
    
	@Column(name="catalogue_name", length=200)
    public String getName() {
		return name;
	}
	
	@ManyToOne
	@JoinColumn(name="catalogue_type_code")
	public CatalogueType getType() {
		return type;
	}

	@Column(name="catalogue_description", length=200)
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCode(Long code) {
		this.code = code;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(CatalogueType type) {
		this.type = type;
	}
	@Column(name="catalogue_administrator")
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}
	@Column(name="catalogue_email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}

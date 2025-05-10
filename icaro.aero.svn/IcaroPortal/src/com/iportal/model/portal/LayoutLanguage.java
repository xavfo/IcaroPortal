/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iportal.model.Language;

/*
 * This class persist data to the <code>tb_layout_language</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 * 
 */

@Entity
@Table(name="tb_layout_language")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="layout_language",
								allocationSize=20
								)
public class LayoutLanguage {

    private Long code;
    
    private String name;
    
    private Layout layout;
    
    private Language language;
    
    /**
     * Creates a new instance of LayoutLanguage
     */
    public LayoutLanguage() {
        super();
    }

    @Id 
    @Column(name="layout_lang_code")
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

	
	@ManyToOne
	@JoinColumn(name="language_code")
	public Language getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	@Column(name="layout_lang_name")
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
	@JoinColumn(name="layout_code")
	public Layout getLayout() {
		return layout;
	}
	/**
	 * @param layout The layout to set.
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
}
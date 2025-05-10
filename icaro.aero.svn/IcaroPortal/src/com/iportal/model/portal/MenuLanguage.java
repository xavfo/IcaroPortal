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
 * This class persist data to the <code>tb_menu_language</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 * 
 */

@Entity
@Table(name="tb_menu_language")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="menu_language",
								allocationSize=20
								)
public class MenuLanguage {
	
    private Long code;
    
    private String name;
    
    private Menu menu;
    
    private Language language;
    
    /**
     * Creates a new instance of Menu
     */
    public MenuLanguage() {
        super();
    }

    @Id 
    @Column(name="menu_lang_code")
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

	@ManyToOne
	@JoinColumn(name="menu_code")
	public Menu getMenu() {
		return menu;
	}
	/**
	 * @param menu The menu to set.
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name="menu_lang_name")
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}

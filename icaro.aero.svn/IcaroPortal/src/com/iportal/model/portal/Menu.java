/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.portal;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.iportal.model.Language;

/*
 * This class persist data to the <code>tb_menu</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 *
 */

@Entity
@Table(name="tb_menu")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="menu",
								allocationSize=20
								)
public class Menu {

    private Long code;
    
    private Boolean enabled;
    
    private Integer level;
    
    private Set<MenuLanguage> menuLanguages;
    
    /**
     * Creates a new instance of Menu
     */
    public Menu() {
        super();
    }

    @Id 
    @Column(name="menu_code")
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

	@Column(name="menu_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(mappedBy="menu")
	public Set<MenuLanguage> getMenuLanguages() {
		return menuLanguages;
	}
	/**
	 * @param menuLanguages The menuLanguages to set.
	 */
	public void setMenuLanguages(Set<MenuLanguage> menuLanguages) {
		this.menuLanguages = menuLanguages;
	}
	
	@Column(name="menu_level")
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/*
	 * Not persistent method
	 * 
	 */
	@Transient
	public MenuLanguage getMenuLanguage(Language language) {
		MenuLanguage menuLanguage = null;
		
		Iterator it = this.getMenuLanguages().iterator();
		while (it.hasNext()) {
			MenuLanguage currentItem = (MenuLanguage) it.next();
			if (currentItem.getLanguage().getCode().equals(language.getCode()))
				return currentItem;
		}
		
		return menuLanguage;
	}
}

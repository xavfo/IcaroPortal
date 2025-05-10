/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.portal;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * This class persist data to the <code>tb_menu_item</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 * @see     com.iportal.model.portal.AccessMenu
 * @deprecated As of CMS version 1.2,
 * replaced by <code>{@link com.iportal.model.portal.AccessMenu}</code>.
 * @deprecated
 * 
 */

@Entity
@Table(name="tb_menu_item")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="menu_item",
								allocationSize=20
								)
@Deprecated 
public class MenuItem {

    private Long code;
    
    private String name;
    
    private Integer order;
    
    private Boolean group;
    
    private Integer level;
    
    private Boolean enabled;
    
    private MenuItem parent;
    
    private Set<MenuItem> menuItems;
    
    private DisplayMode displayMode;
    
    private Content content;
    
    private MenuLanguage menuLanguage;
    
    private Integer menuX;
    
    private Integer menuY;
    
    private Integer menuWidth;

    
    /**
     * Creates a new instance of Menu
     */
    public MenuItem() {
        super();
    }

    @Id 
    @Column(name="menu_item_code")
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

	@Column(name="menu_item_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToOne
	@JoinColumn(name="content_code")
	public Content getContent() {
		return content;
	}
	/**
	 * @param content The content to set.
	 */
	public void setContent(Content content) {
		this.content = content;
	}

	@ManyToOne
	@JoinColumn(name="display_mode_code")
	public DisplayMode getDisplayMode() {
		return displayMode;
	}
	/**
	 * @param displayMode The displayMode to set.
	 */
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}

	@Column(name="menu_item_group")
	public Boolean getGroup() {
		return group;
	}
	/**
	 * @param group The group to set.
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}

	@Column(name="menu_item_level")
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@ManyToOne
	@JoinColumn(name="menu_item_parent")
	public MenuItem getParent() {
		return parent;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(MenuItem parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	@OrderBy("code")
	public Set<MenuItem> getMenuItems() {
		return menuItems;
	}
	/**
	 * @param menuItems The menuItems to set.
	 */
	public void setMenuItems(Set<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@ManyToOne
	@JoinColumn(name="menu_lang_code")
	public MenuLanguage getMenuLanguage() {
		return menuLanguage;
	}
	/**
	 * @param menuLanguage The menuLanguage to set.
	 */
	public void setMenuLanguage(MenuLanguage menuLanguage) {
		this.menuLanguage = menuLanguage;
	}

	@Column(name="menu_item_name")
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="menu_item_order")
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return Returns the menuWidth.
	 */
	@Column(name="menu_item_width")
	public Integer getMenuWidth() {
		return menuWidth;
	}

	/**
	 * @return Returns the menuX.
	 */
	@Column(name="menu_item_positionx")
	public Integer getMenuX() {
		return menuX;
	}

	/**
	 * @return Returns the menuY.
	 */
	@Column(name="menu_item_positiony")
	public Integer getMenuY() {
		return menuY;
	}

	/**
	 * @param menuWidth The menuWidth to set.
	 */
	public void setMenuWidth(Integer menuWidth) {
		this.menuWidth = menuWidth;
	}

	/**
	 * @param menuX The menuX to set.
	 */
	public void setMenuX(Integer menuX) {
		this.menuX = menuX;
	}

	/**
	 * @param menuY The menuY to set.
	 */
	public void setMenuY(Integer menuY) {
		this.menuY = menuY;
	}
	
	
}

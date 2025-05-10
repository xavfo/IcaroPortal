/*
 * Created 05/04/2007
 *	AccessMenu.java
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

import com.iportal.model.Access;

/**
 * @author YAGE(hernan)
 * @version 1.2
 *
 */
@Entity
@Table(name="tb_access_menu")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="access_menu",
								allocationSize=20
								)
public class AccessMenu {

	private Access access;
	
	private Long code;
    
    private Integer order;
    
    private Boolean enabled;
    
    private MenuLanguage menuLanguage;
    
    private DisplayMode displayMode;
    
    private Integer menuX;
    
    private Integer menuY;
    
    private Integer menuWidth;


	/**
	 * 
	 */
	public AccessMenu() {
		super();
		this.access = null;
		this.code = null;
		this.order = null;
		this.enabled = null;
		this.menuLanguage = null;
		this.displayMode = null;
		this.menuX = null;
		this.menuY = null;
		this.menuWidth = null;
	}


	/**
	 * @return Returns the access.
	 */
	@ManyToOne
	@JoinColumn(name="access_code")
	public Access getAccess() {
		return access;
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="access_menu_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}


	/**
	 * @return Returns the displayMode.
	 */
	@ManyToOne
	@JoinColumn(name="display_mode_code")
    public DisplayMode getDisplayMode() {
		return displayMode;
	}


	/**
	 * @return Returns the enabled.
	 */
	@Column(name="access_menu_enabled")	
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @return Returns the menuLanguage.
	 */
	@ManyToOne
	@JoinColumn(name="menu_lang_code")
	public MenuLanguage getMenuLanguage() {
		return menuLanguage;
	}


	/**
	 * @return Returns the menuWidth.
	 */
	@Column(name="access_menu_width")
	public Integer getMenuWidth() {
		return menuWidth;
	}


	/**
	 * @return Returns the menuX.
	 */
	@Column(name="access_menu_positionx")
	public Integer getMenuX() {
		return menuX;
	}


	/**
	 * @return Returns the menuY.
	 */
	@Column(name="access_menu_positiony")
	public Integer getMenuY() {
		return menuY;
	}


	/**
	 * @return Returns the order.
	 */
	@Column(name="access_menu_order")
	public Integer getOrder() {
		return order;
	}


	/**
	 * @param access The access to set.
	 */
	public void setAccess(Access access) {
		this.access = access;
	}


	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}


	/**
	 * @param displayMode The displayMode to set.
	 */
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}


	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @param menuLanguage The menuLanguage to set.
	 */
	public void setMenuLanguage(MenuLanguage menuLanguage) {
		this.menuLanguage = menuLanguage;
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


	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	

}

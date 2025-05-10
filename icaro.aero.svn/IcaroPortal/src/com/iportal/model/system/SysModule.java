/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.system;

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
import javax.persistence.Transient;

import com.iportal.biz.Group;
import com.iportal.biz.GroupBase;

/**
 * This class persist data to the <code>tb_sys_module</code> 
 * database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.1
*/
@Entity
@Table(name="tb_sys_module")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="sys_module",
								allocationSize=1
								)
public class SysModule extends GroupBase {

    private Long code;
    
    private Integer level;
    
    private Boolean group;
    
    private String name;
    
    private String key;
    
    private String url;
    
    private Integer orderIndex;
    
    private Integer x;
    
    private Integer y;
    
    private SysModule parent;
    
    private Set<SysModule> modules;
    
    private Set<SysProfile> profiles;
    
	private SysAccessMode accessMode;
	
	private Boolean showPanel;
	
	/**
     * Creates a new instance of SysModule
     */
    public SysModule() {
        super();
    }
    
    @Transient
    public String toString() {
        return name;
    }
    
    
    /*
     * Property Code this is PK
     */
    @Id 
    @Column(name="sys_module_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    
    
    /*
     * Property Group
     */
    @Column(name="sys_module_group")
    public Boolean getGroup() {
        return group;
    }
    public void setGroup(Boolean group) {
        this.group = group;
    }
    
    
    
	@Column(name="sys_module_key", length=150)
    public String getKey() {
        return key;
    }
	/**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    
    
	@Column(name="sys_module_level")
    public Integer getLevel() {
        return level;
    }
	/**
     * @param level The level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
	
    
	@Column(name="sys_module_name", length=100)
    public String getName() {
        return name;
    }
	/**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
	
    
	@Column(name="sys_module_order_index")
    public Integer getOrderIndex() {
        return orderIndex;
    }
	/**
     * @param orderIndex The orderIndex to set.
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
	
    
	@Column(name="sys_module_url", length=255)
    public String getUrl() {
        return url;
    }
	/**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    
	@Column(name="sys_module_x")
    public Integer getX() {
        return x;
    }
	/**
     * @param x The x to set.
     */
    public void setX(Integer x) {
        this.x = x;
    }
    
	@Column(name="sys_module_y")
    public Integer getY() {
        return y;
    }
	/**
     * @param y The y to set.
     */
    public void setY(Integer y) {
        this.y = y;
    }
	
    
	@ManyToOne
	@JoinColumn(name="sys_module_parent")
    public SysModule getParent() {
        return parent;
    }
	/**
     * @param parent The parent to set.
     */
    public void setParent(SysModule parent) {
        this.parent = parent;
    }
	
    
	@OneToMany(mappedBy="parent")
	public Set<SysModule> getModules() {
        return modules;
    }
	/**
     * @param modules The modules to set.
     */
    public void setModules(Set<SysModule> modules) {
        this.modules = modules;
    }
	
	
	/*
	 * @hibernate.set 
	 *     table="tb_sys_profile"
	 *     inverse="true"
	 *     lazy="true"
	 * 
	 * @hibernate.key column="sys_module_code"
	 * 
	 * @hibernate.one-to-many class="com.iportal.model.system.SysProfile"
	 */
	@OneToMany(mappedBy="module")
    public Set<SysProfile> getProfiles() {
        return profiles;
    }
	/**
     * @param profiles The profiles to set.
     */
    public void setProfiles(Set<SysProfile> profiles) {
        this.profiles = profiles;
    }
	
	
	@ManyToOne
	@JoinColumn(name="sys_access_mode_code")
	public SysAccessMode getAccessMode(){
		return accessMode;
	}
	/**
	 * @param accessMode
	 */
	public void setAccessMode(SysAccessMode accessMode){
		this.accessMode = accessMode;
	}
		
    
	@Column(name="sys_module_show_panel")
	public Boolean getShowPanel(){
		return showPanel;
	}
	/**
	 * @param showPanel
	 */
	public void setShowPanel(Boolean showPanel){
		this.showPanel = showPanel;
	}
    
    
    
    /* (non-Javadoc)
     * @see com.iportal.biz.Group#getAncestor()
     */
    @Transient
    public Group getAncestor() {
        return this.parent;
    }

}

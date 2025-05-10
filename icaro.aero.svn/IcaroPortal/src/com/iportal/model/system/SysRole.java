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
 * This class persist data to the <code>tb_sys_role</code> 
 * database table.
 * 
 * @author  YAGE (pablor)
 * @version 1.1
 */
@Entity
@Table(name="tb_sys_role")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="sys_role",
								allocationSize=1
								)
public class SysRole extends GroupBase {

    private Long code;
    
    private Integer level;
    
    private String name;
    
    private String description;
    
    private SysRole parent;
    
    private Set<SysRole> roles;
    
    private Set<SysProfile> profiles;
    
    /**
     * Creates a new instance of SysRole
     */
    public SysRole() {
        super();
    }
    @Transient
    public String toString() {
        return name;
    }
    
    @Id 
    @Column(name="sys_role_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    @Column(name="sys_role_description")
    public String getDescription() {
        return description;
    }
    
	
    @Column(name="sys_role_level")
    public Integer getLevel() {
        return level;
    }
    
    @Column(name="sys_role_name")
    public String getName() {
        return name;
    }
    
    @ManyToOne
    @JoinColumn(name="sys_role_parent")
    public SysRole getParent() {
        return parent;
    }
    
    @OneToMany(mappedBy="parent")
    public Set<SysRole> getRoles() {
        return roles;
    }
    
    @OneToMany(mappedBy="role")
    public Set<SysProfile> getProfiles() {
        return profiles;
    }
    
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param level The level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param parent The parent to set.
     */
    public void setParent(SysRole parent) {
        this.parent = parent;
    }
    /**
     * @param roles The roles to set.
     */
    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
    
    /**
     * @param profiles The profiles to set.
     */
    public void setProfiles(Set<SysProfile> profiles) {
        this.profiles = profiles;
    }
        
    /* (non-Javadoc)
     * @see com.iportal.biz.Group#getAncestor()
     */
    @Transient
    public Group getAncestor() {
        return this.parent;
    }
}

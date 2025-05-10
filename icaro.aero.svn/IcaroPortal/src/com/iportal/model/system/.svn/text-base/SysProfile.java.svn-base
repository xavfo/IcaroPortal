/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class persist data to the <code>tb_sys_profile</code> 
 * database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.1
 */
@Entity
@Table(name="tb_sys_profile")
//@SequenceGenerator(name = "SRI_PORTAL_SEQUENCE", initialValue = 1, allocationSize=50, sequenceName = "SEQ_SYS_PROFILE")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="sys_profile",
								allocationSize=20
								)
public class SysProfile {

    private Long code;
    
    private SysRole role;
    
    private SysModule module;
    
    
    /**
     * Creates a new instance of SysProfile
     */
    public SysProfile() {
        super();
    }
    
    public SysProfile(SysRole r, SysModule m) {
        role = r;
        module = m;
    }
    
    @Id 
    @Column(name = "sys_profile_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    @ManyToOne
    @JoinColumn(name="sys_module_code")
    public SysModule getModule() {
        return module;
    }
    
    @ManyToOne
    @JoinColumn(name="sys_role_code")
    public SysRole getRole() {
        return role;
    }
    
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    
    /**
     * @param module The module to set.
     */
    public void setModule(SysModule module) {
        this.module = module;
    }
    
    /**
     * @param role The role to set.
     */
    public void setRole(SysRole role) {
        this.role = role;
    }
}

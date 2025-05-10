/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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
import javax.persistence.Table;

/*
 * This class persist data to the <code>tb_layout</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 * 
 */

@Entity
@Table(name="tb_layout")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="layout",
								allocationSize=20
								)
public class Layout {

    private Long code;
    
    private String forward;
    
    private Set<LayoutLanguage> layoutLanguages;
    
    private PortalModule portalModule;
    
    /**
     * Creates a new instance of Menu
     */
    public Layout() {
        super();
    }

    @Id 
    @Column(name="layout_code")
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

	@Column(name="layout_forward")
	public String getForward() {
		return forward;
	}

	/**
	 * @param forward The name to set.
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}

	@OneToMany(mappedBy="layout")
	public Set<LayoutLanguage> getLayoutLanguages() {
		return layoutLanguages;
	}

	/**
	 * @param layoutLanguages The layoutLanguages to set.
	 */
	public void setLayoutLanguages(Set<LayoutLanguage> layoutLanguages) {
		this.layoutLanguages = layoutLanguages;
	}

	@ManyToOne
	@JoinColumn(name="portal_module_code")
	public PortalModule getPortalModule() {
		return portalModule;
	}
	/**
	 * @param portalModule The portalModule to set.
	 */
	public void setPortalModule(PortalModule portalModule) {
		this.portalModule = portalModule;
	}

}

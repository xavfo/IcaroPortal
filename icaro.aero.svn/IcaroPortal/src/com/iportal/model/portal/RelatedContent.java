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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/*
 * This class persist data to the <code>tb_related_content</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 2.0
 */

@Entity
@Table(name="tb_related_content")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="related_content",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class RelatedContent {
	
    private Long code;
    
    private String name;
    
    private Content content;
    
    private Content related;
    
    private Boolean enabled;
    
    
    @Id 
    @Column(name="related_content_code")
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
	@JoinColumn(name="content_related")
	public Content getRelated() {
		return related;
	}
	/**
	 * @param related The related to set.
	 */
	public void setRelated(Content related) {
		this.related = related;
	}
	
	@Column(name="related_content_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Column(name="related_content_name")
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
/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iportal.model.Catalogue;

/**
 * This class persist data to the <code>tb_news</code> 
 * database table.
 * 
 * @author  mcnovillo
 * @version 1.1
 *
 */
@Entity
@Table(name="tb_news_document")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="news_document",
								allocationSize=20
								)

public class NewsDocument {
	
	private Long code;
	
	private Catalogue category;
	
	/**
     * Creates a new instance of News
     */
    public NewsDocument() {
        super();
    }
    
    	  
    @Id 
    @Column(name="news_document_code")
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
    @JoinColumn(name="catalogue_code")	
	public Catalogue getCategory() {
		return category;
	}

	/**
	 * @param type The type to set.
	 */
	public void setCategory(Catalogue type) {
		this.category = type;
	}
}

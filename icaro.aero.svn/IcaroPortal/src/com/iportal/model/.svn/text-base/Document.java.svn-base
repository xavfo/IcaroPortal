/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * This class persist data to the <code>tb_document</code> database table.
 * 
 * @author YAGE (jtite)
 * @version 1.0
 */
@Entity
@Table(name="tb_document")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="document",
								allocationSize=20
								)
public class Document {
	
    private Long code;
    
    private String name;
    
    private String file;
    
    private DocumentType type;
    
    private Boolean isEnabled;
    
    
    
    /**
     * Creates a new instance of Document
     */
    public Document() {
        super();
    }
    @Transient
    public String toString() {
        return name;
    }
    
    @Id
    @Column(name="document_code")
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
	
	@Column(name="document_name")
    public String getName() {
        return name;
    }
    
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
	    this.name = name;
	}

	@Column(name="document_file")
    public String getFile() {
        return file;
    }
    
	/**
	 * @param file The file to set.
	 */
	public void setFile(String file) {
	    this.file = file;
	}

	@ManyToOne
	@JoinColumn(name="document_type_code")
	public DocumentType getType() {
		return type;
	}
	
	
    /**
	 * @param documentType The documentType to set.
	 */
	public void setType(DocumentType documentType) {
		this.type = documentType;
	}
	
	@Column(name="document_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
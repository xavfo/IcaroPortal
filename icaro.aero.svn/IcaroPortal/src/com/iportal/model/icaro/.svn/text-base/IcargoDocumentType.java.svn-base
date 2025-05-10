/**
 * 
 */
package com.iportal.model.icaro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Informacion de persona de contacto
 * 
 * @author ftamayo
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_icargo_document_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "icargo_document_type",
								allocationSize = 1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IcargoDocumentType {
	
	private Long code;
	
	private String name;
	
	private String documentCode;

	public IcargoDocumentType() {
		super();
		this.code = null;
		this.name = null;
		this.documentCode = null;
	}

	@Id 
	@Column(name="icargo_document_type_code")
	public Long getCode() {
		return code;
	}

	@Column(name="icargo_document_type_name")
	public String getName() {
		return name;
	}

	@Column(name="icargo_document_type_abbreviation")
	public String getDocumentCode() {
		return documentCode;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}

	
}


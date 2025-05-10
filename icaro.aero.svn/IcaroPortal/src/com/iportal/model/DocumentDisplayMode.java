/*
 * Created Jul 12, 2006
 *	DocumentDisplayMode.java
 */
package com.iportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Describe la manera de despliegue del documento, ya sea como
 * una animación Flash, Video o link
 *   
 * @author YAGE (hernan)
 * @version 1.0
 */
@Entity
@Table(name="tb_document_display_mode")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="document_display_mode",
								allocationSize=20
								)
public class DocumentDisplayMode {

	private Long code;
	
	private String name;
	
	
	/**
	 * 
	 */
	public DocumentDisplayMode() {
		super();
		this.clear();
	}
	
	private void clear () {
		this.code = null;
		this.name = null;
		
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="doc_display_mode_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the name.
	 */
    @Column(name="doc_display_mode_name")
	public String getName() {
		return name;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}

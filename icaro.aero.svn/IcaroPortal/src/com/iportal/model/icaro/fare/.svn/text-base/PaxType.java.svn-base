/**
 * 
 */
package com.iportal.model.icaro.fare;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Información de acerca  de tipos de pasajeros existentes para
 * las distintas tarifas aereas.
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_pax_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "pax_type",
								allocationSize = 20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class PaxType {
	
	private Long code;
	
	private String name;
	
	private String enName;
	
	private String externalCode;

	public PaxType() {
		super();
		this.code = null;
		this.name = null;
		this.enName = null;
		this.externalCode = null;
	}

	@Id 
	@Column(name="pax_type_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
	
	@Column(name="pax_type_en_name")
	public String getEnName() {
		return enName;
	}

	@Column(name="pax_type_external")
	public String getExternalCode() {
		return externalCode;
	}

	@Column(name="pax_type_name")
	public String getName() {
		return name;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	


}

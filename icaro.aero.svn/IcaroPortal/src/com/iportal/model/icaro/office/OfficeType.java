/**
 * 
 */
package com.iportal.model.icaro.office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Tipos de oficinas para ICARO
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_office_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "office_type",
								allocationSize = 1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class OfficeType {
	
	private Long code;
	
	private String name;

	public OfficeType() {
		super();
		this.code = null;
		this.name = null;
	}

	@Id 
	@Column(name="office_type_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	@Column(name="office_type_name")
	public String getName() {
		return name;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}

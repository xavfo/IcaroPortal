package com.iportal.cart.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="tb_id_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="id_type",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)								
public class IDType {

	private Long code;
	
	private String name;
	
	public IDType() {
		super();
		this.code = null;
		this.name = null;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="id_type_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the name.
	 */
    @Column(name="id_type_name")
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

/*
 * Created Jan 9, 2007
 *	OrderStatus.java
 */
package com.iportal.cart.model.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author hernan
 *
 */
@Entity
@Table(name="tb_order_status")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="order_status",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class OrderStatus {

	private Long code;
	
	private String name;
	
	private String notes;

	/**
	 * 
	 */
	public OrderStatus() {
		super();
		this.code  = null;
		this.name  = null;
		this.notes = null;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="order_status_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the name.
	 */
    @Column(name="order_status_name")
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the notes.
	 */
    @Column(name="order_status_notes")
	public String getNotes() {
		return notes;
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

	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}

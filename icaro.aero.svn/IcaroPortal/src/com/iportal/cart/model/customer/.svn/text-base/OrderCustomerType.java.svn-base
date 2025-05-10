/*
 * Created Jan 9, 2007
 *	PaymentType.java
 */
package com.iportal.cart.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Tipos de Usuarios que pueden realizar una compra o reserva de tickets
 * 
 * @author ftamayo
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_order_customer_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="order_customer_type",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class OrderCustomerType {

	private Long code;
	
	private String name;
	
	
		
	/**
	 * 
	 */
	public OrderCustomerType() {
		super();
		this.code     = null;
		this.name     = null;
		
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="order_customer_type_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	

	public Long getCode() {
		return code;
	}


	
	/**
	 * @return Returns the name.
	 */
    @Column(name="order_customer_type_name")
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

/*
 * Created Jan 9, 2007
 *	CustomerAddress.java
 */
package com.iportal.cart.model.customer;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.cart.model.Address;


/**
 * Direcciones para entrega de mercader�a de clientes del portal 
 * de comercio electr�nico.
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_customer_address")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="customer_address",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class CustomerAddress {

	private Long code;
	
	private Customer customer;
	
	private Address address;
	

	/**
	 * 
	 */
	public CustomerAddress() {
		super();
		this.code = null;
		this.customer  = null;
		this.address   = null;
	}
	
	
	
	/**
	 * @return Returns the address.
	 */
	@Embedded
	@AttributeOverrides( {
	@AttributeOverride(name = "name", column = @Column(name="CUSTOMER_ADDRESS_NAME") ),
	@AttributeOverride(name = "street1", column = @Column(name="CUSTOMER_ADDRESS_STREET1") ),
	@AttributeOverride(name = "street2", column = @Column(name="CUSTOMER_ADDRESS_STREET2") ),
	@AttributeOverride(name = "phone", column = @Column(name="CUSTOMER_ADDRESS_PHONE") ),
	@AttributeOverride(name = "reference", column = @Column(name="CUSTOMER_ADDRESS_REFERENCE") )
	})
	public Address getAddress() {
		return address;
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="customer_address_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
	/**
	 * @return Returns the customer.
	 */
	@ManyToOne
	@JoinColumn(name="customer_code")
	public Customer getCustomer() {
		return customer;
	}
	

	/**
	 * @param address The address to set.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	/**
	 * @param customer The customer to set.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}

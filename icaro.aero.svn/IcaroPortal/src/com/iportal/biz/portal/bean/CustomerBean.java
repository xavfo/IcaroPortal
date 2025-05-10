/**
 * 
 */
package com.iportal.biz.portal.bean;

import com.iportal.cart.model.customer.OrderCustomerType;

/**
 * Bean de resumen para cargar informacion relacionada a customers
 * 
 * @author hernan
 * @version 1.0
 * 
 */
public class CustomerBean {

	private OrderCustomerType customerType;

	private String name;



	public CustomerBean() {
		super();
		this.clear();
	}



	private void clear() {
		this.customerType = null;
		this.name = null;
		
	}

	public CustomerBean(OrderCustomerType customerType, String name ) {
		super();
		this.customerType = customerType;
		this.name = name;		
	}



	/**
	 * @return Returns the customerType.
	 */
	public OrderCustomerType getCustomerType() {
		return customerType;
	}



	/**
	 * @param customerType The customerType to set.
	 */
	public void setCustomerType(OrderCustomerType customerType) {
		this.customerType = customerType;
	}



	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}

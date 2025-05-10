/*
 * Created Jan 11, 2007
 *	Address.java
 */
package com.iportal.cart.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.iportal.model.City;
import com.iportal.model.State;

/**
 * @author hernan
 *
 */
@Embeddable
public class Address {
	
	private State state;
	
	private City city;
	
	private String name;
	
	private String street1;
	
	private String street2;
	
	private String phone;
	
	private String reference;
	
	
	/**
	 * 
	 */
	public Address() {
		super();
		this.state     = null;
		this.name      = null;
		this.street1   = null;
		this.street2   = null;
		this.reference = null;
		this.city  = null;
		this.phone = null;

	}


	/**
	 * @return Returns the city.
	 */
	
	@ManyToOne
	@JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}


	/**
	 * @return Returns the name.
	 */
	@Column(name="order_shipping_name")
	public String getName() {
		return name;
	}


	/**
	 * @return Returns the phone.
	 */
	@Column(name="order_shipping_phone")
	public String getPhone() {
		return phone;
	}


	/**
	 * @return Returns the reference.
	 */
	@Column(name="order_shipping_reference")
	public String getReference() {
		return reference;
	}


	/**
	 * @return Returns the state.
	 */
	@ManyToOne
	@JoinColumn(name="state_code")
	public State getState() {
		return state;
	}


	/**
	 * @return Returns the street1.
	 */
	@Column(name="order_shipping_street1")
	public String getStreet1() {
		return street1;
	}


	/**
	 * @return Returns the street2.
	 */
	@Column(name="order_shipping_street2")
	public String getStreet2() {
		return street2;
	}


	/**
	 * @param city The city to set.
	 */
	public void setCity(City city) {
		this.city = city;
	}


	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}


	/**
	 * @param state The state to set.
	 */
	public void setState(State state) {
		this.state = state;
	}


	/**
	 * @param street1 The street1 to set.
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}


	/**
	 * @param street2 The street2 to set.
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	

}

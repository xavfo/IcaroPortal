/*
 * Created 20/03/2007
 *	HomeAddress.java
 */
package com.iportal.cart.model.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.iportal.cart.model.Address;
import com.iportal.model.Country;

/**
 * @author hernan
 *
 */
@Embeddable
public class HomeAddress extends Address  {
	
	private Country country;

    private String stateName;

    private String cityName;
	
	/**
	 * 
	 */
	public HomeAddress() {
		super();
		this.country = null;		
        this.stateName = null;        
        this.cityName = null;        
	}


	/**
	 * @return Returns the cityName.
	 */
	@Column(name="customer_home_city")
	public String getCityName() {
		return cityName;
	}


	/**
	 * @return Returns the country.
	 */
	@ManyToOne
	@JoinColumn(name="country_code")
	public Country getCountry() {
		return country;
	}


	/**
	 * @return Returns the phone.
	 */
	@Column(name="customer_home_phone")
	public String getPhone() {
		return super.getPhone();
	}


	/**
	 * @return Returns the reference.
	 */
	@Column(name="customer_home_reference")
	public String getReference() {
		return super.getReference();
	}


	/**
	 * @return Returns the stateName.
	 */
	@Column(name="customer_home_state")
	public String getStateName() {
		return stateName;
	}


	/**
	 * @return Returns the street1.
	 */
	@Column(name="customer_home_street1")
	public String getStreet1() {
		return super.getStreet1();
	}


	/**
	 * @return Returns the street2.
	 */
	@Column(name="customer_home_street2")
	public String getStreet2() {
		return super.getStreet2();
	}


	/**
	 * @param cityName The cityName to set.
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	/**
	 * @param country The country to set.
	 */
	public void setCountry(Country country) {
		this.country = country;
	}


    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}

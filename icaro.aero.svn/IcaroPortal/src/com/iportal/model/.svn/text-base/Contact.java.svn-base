/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * This class persist data to the <code>tb_contact_form</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 2.0
 */

@Entity
@Table(name="tb_contact_form")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="contact_form",
								allocationSize=20
								)
public class Contact {
	
    private Long code;

    private String name;
    
    private String lastName;
    
    private String email;
    
    private String address;
    
    private String phone;
    
    private String cityName;
    
    private String stateName;
    
    private Country country;
    
    private String comment;
    
    private String position;
    
    private String company;
    
    private Catalogue catalogue;
    
    private Calendar date; //contact_date
    
    
   /**
     * Creates a new instance of Account
     */
    public Contact() {
        super();
    }
    @Transient
    public String toString() {
        return name;
    }
    
    @Id
    @Column(name="contact_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    
    @Column(name="contact_date")
	public Calendar getDate() {
		return date;
	}
	
	@Column(name="contact_name")
    public String getName() {
        return name;
    }
    
	@Column(name="contact_email")
    public String getEmail() {
        return email;
    }
    
    @Column(name="contact_comment")
	public String getComment() {
		return comment;
	}
    
    @Column(name="contact_company")
    public String getCompany() {
		return company;
	}
    
    /**
	 * @return Returns the position.
	 */
    @Column(name="contact_position")
	public String getPosition() {
		return position;
	}
    
    
	/**
	 * @return Returns the stateName.
	 */
    @Column(name="contact_state")
	public String getStateName() {
		return stateName;
	}

    @ManyToOne
	@JoinColumn(name="catalogue_code")
    public Catalogue getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	
	
	/**
	 * @param position The position to set.
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
     * @param company The company to set.
     */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
     * @param comment The comment to set.
     */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    
    
   
    public void setDate(Calendar date) {
		this.date = date;
	}
	/**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
   
   
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="contact_last_name")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="contact_address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="contact_phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="contact_city")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String city) {
		this.cityName = city;
	}
	
	@ManyToOne
	@JoinColumn(name="country_code")
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	/**
	 * @param stateName The stateName to set.
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

    
   
  
    
}

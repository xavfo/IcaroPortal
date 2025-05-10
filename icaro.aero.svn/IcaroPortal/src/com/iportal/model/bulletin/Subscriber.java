/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.bulletin;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.iportal.model.Country;

/*
 * This class persist data to the <code>tb_subscriber</code> database table.
 * 
 * @author  YAGE (lourdes)
 * @version 2.0
 */

@Entity
@Table(name="tb_subscriber")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="subscriber",
								allocationSize=20
								)
public class Subscriber {
	
    private Long code;

    private String firstName;
    
    private String lastName;
    
    private String occupation;
    
    private String address;
    
    private GregorianCalendar systemDate;
    
    private String city;
    
    private String state;
    
    private String phoneCountryCode;
    
    private String phoneCityCode;
    
    private String phone;
    
    private String email;
    
    private Boolean isSubscribed;
        
    private Country country;
    
    private Set<SubscriberTopic> subscriberTopics;
    
    private Boolean enabled;

    /**
     * Creates a new instance of Account
     */
    public Subscriber() {
        super();
    }
    @Transient
    public String toString() {
        return firstName;
    }
    
    @Id 
    @Column(name="subscriber_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    @Column(name="subscriber_firstName")
    public String getFirstName() {
        return firstName;
    }
    
    @Column(name="subscriber_lastName")
    public String getLastName() {
        return lastName;
    }
    
    
    @Column(name="subscriber_system_date")
    public GregorianCalendar getSystemDate() {
        return systemDate;
    }   
   
    @Column(name="subscriber_occupation")
	public String getOccupation() {
		return occupation;
	}
    
    @Column(name="subscriber_address")
	public String getAddress() {
		return address;
	}
    
    
    @Column(name="subscriber_city")
    public String getCity() {
		return city;
	}
    
  
    @Column(name="subscriber_state")
    public String getState() {
		return state;
	}
    
   
    @Column(name="subscriber_phone_country_code")
    public String getPhoneCountryCode() {
		return phoneCountryCode;
	}
    
    
    @Column(name="subscriber_phone_city_code")
    public String getPhoneCityCode() {
		return phoneCityCode;
	}
    
    
    @Column(name="subscriber_phone")
    public String getPhone() {
		return phone;
	}
    
    
    @Column(name="subscriber_email")
    public String getEmail() {
		return email;
	}
    
    
    @Column(name="subscriber_isSubscribed")
    public Boolean getIsSubscribed() {
		return isSubscribed;
	}
    
    
	@ManyToOne
	@JoinColumn(name="country_code")
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@OneToMany(mappedBy="subscriber")
	public Set<SubscriberTopic> getSubscriberTopics() {
		return subscriberTopics;
	}
	
	@Column(name="subscriber_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setSubscriberTopics(Set<SubscriberTopic> subscriberTopics) {
		this.subscriberTopics = subscriberTopics;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setCode(Long code) {
		this.code = code;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setSystemDate(GregorianCalendar systemDate) {
		this.systemDate = systemDate;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setPhoneCityCode(String phoneCityCode) {
		this.phoneCityCode = phoneCityCode;
	}
	
	public void setPhoneCountryCode(String phoneCountryCode) {
		this.phoneCountryCode = phoneCountryCode;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public String getFullName(){
		return firstName + lastName;
	}
}

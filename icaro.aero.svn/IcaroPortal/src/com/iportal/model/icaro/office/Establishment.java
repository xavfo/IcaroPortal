/**
 * 
 */
package com.iportal.model.icaro.office;

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

import com.iportal.model.Catalogue;
import com.iportal.model.City;
import com.iportal.model.icaro.ContactInfo;

/**
 * Informacion de establecimientos
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_establishment")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "establishment",
								allocationSize = 20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Establishment {

	
	private Long code;
	
	private City city;
	
	private Catalogue category;
	
	private String name;
	
	private Boolean enabled;
	
	private String address;
	
	private String image;
	
	private String phone1;
	
	private String phone2;
	
	private String fax;
	
	private String email;
	
	private String schedule;
	
	private String description;
	
	private ContactInfo contact;
	
	
	

	public Establishment() {
		super();
		this.address     = null;
		this.city = null;
		this.code = null;
		this.contact = null;
		this.description = null;
		this.email = null;
		this.enabled     = null;
		this.category = null;
		this.name = null;
		this.fax  = null;
		this.image = null;
		this.phone1 = null;
		this.phone2 = null;
		this.schedule    = null;

	}


	@Column(name="establishment_address")
	public String getAddress() {
		return address;
	}

	@ManyToOne
    @JoinColumn(name="catalogue_code")
	public Catalogue getCategory() {
		return category;
	}

	@ManyToOne
    @JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}

	@Id 
	@Column(name="establishment_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}


	@Embedded
	public ContactInfo getContact() {
		return contact;
	}


	@Column(name="establishment_description")
	public String getDescription() {
		return description;
	}

	@Column(name="establishment_email")
	public String getEmail() {
		return email;
	}

	@Column(name="establishment_enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@Column(name="establishment_fax")
	public String getFax() {
		return fax;
	}

	@Column(name="establishment_image")
	public String getImage() {
		return image;
	}

	@Column(name="establishment_name")
	public String getName() {
		return name;
	}


	@Column(name="establishment_phone1")
	public String getPhone1() {
		return phone1;
	}


	@Column(name="establishment_phone2")
	public String getPhone2() {
		return phone2;
	}


	@Column(name="establishment_schedule")
	public String getSchedule() {
		return schedule;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public void setCategory(Catalogue category) {
		this.category = category;
	}




	public void setCity(City city) {
		this.city = city;
	}




	public void setCode(Long code) {
		this.code = code;
	}




	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}




	public void setFax(String fax) {
		this.fax = fax;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}




	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}




	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	

	
	

}

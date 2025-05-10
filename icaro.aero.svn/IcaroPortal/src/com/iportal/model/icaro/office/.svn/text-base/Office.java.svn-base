/**
 * 
 */
package com.iportal.model.icaro.office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.model.City;

/**
 * Informacion de oficinas y puntos de venta
 * @author hernan
 * @version 1.0
 *
 */

@Entity
@Table(name="tb_office")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "office",
								allocationSize = 20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Office {


	private Long code;
	
	private City city;
	
	private OfficeType type;
	
	private String name;
	
	private Boolean enabled;
	
	private String address;
	
	private String image;
	
	private String phone;
	
	private String fax;
	
	private String email;
	
	private String schedule;
	
	private String description;
	
	
	public Office() {
		super();
		this.address     = null;
		this.city = null;
		this.code = null;
		this.description = null;
		this.email = null;
		this.enabled     = null;
		this.type = null;
		this.name = null;
		this.fax  = null;
		this.image = null;
		this.phone = null;
		this.schedule    = null;
		
	}


	@Column(name="office_address")
	public String getAddress() {
		return address;
	}


	@ManyToOne
    @JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}


	@Id 
	@Column(name="office_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}


	@Column(name="office_description")
	public String getDescription() {
		return description;
	}


	@Column(name="office_email")
	public String getEmail() {
		return email;
	}


	@Column(name="office_enabled")
	public Boolean getEnabled() {
		return enabled;
	}


	@Column(name="office_fax")
	public String getFax() {
		return fax;
	}


	@Column(name="office_image")
	public String getImage() {
		return image;
	}


	@Column(name="office_name")
	public String getName() {
		return name;
	}


	@Column(name="office_phone")
	public String getPhone() {
		return phone;
	}


	@Column(name="office_schedule")
	public String getSchedule() {
		return schedule;
	}


	@ManyToOne
    @JoinColumn(name="office_type_code")
	public OfficeType getType() {
		return type;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public void setCode(Long code) {
		this.code = code;
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


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}


	public void setType(OfficeType type) {
		this.type = type;
	}
	
	

	
	
}

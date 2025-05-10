/**
 * 
 */
package com.iportal.model.icaro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.model.City;

/**
 * Informacion de aeropuertos registrados
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_airport")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "airport",
								allocationSize = 1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Airport {

	private Long code;
	
	private String name;
	
	private String iataCode;
	
	private City city;
	
	private Boolean enabled;
	
	private String fullName;

	public Airport() {
		super();
		this.code = null;
		this.name = null;
		this.iataCode = null;
		this.city = null;
		this.enabled=null;
	}
	 
	@Transient
	public String getFullName () {
		if (fullName == null) {
			StringBuffer msg = new StringBuffer();
			if (this.getCity() != null) {
				msg.append(this.getCity().getName());
				msg.append("-");
			}
			msg.append(this.getName());
			msg.append("(");
			msg.append(this.iataCode);
			msg.append(")");
			fullName = msg.toString();
		}
		return fullName;
	}

	@ManyToOne
    @JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}

	@Id 
	@Column(name="airport_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	@Column(name="airport_iata_code")
	public String getIataCode() {
		return iataCode;
	}

	@Column(name="airport_name")
	public String getName() {
		return name;
	}
	
	@Column(name="airport_enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
}

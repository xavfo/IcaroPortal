/**
 * 
 */
package com.iportal.model.icaro.fare;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.Constants;
import com.yage.commons.DateFormatUtils;

/**
 * 
 * Listado de vuelos con horarios disponibles por frecuencias.
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_flight")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "flight",
								allocationSize = 20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Flight {
	
	private Calendar arrival;
	
	private Long code;
	
	private Calendar departure;
	
	private Boolean enabled;
	
	private Frequency frequency;
	
	private String notes;
	
	private String number;

	public Flight() {
		super();
		this.arrival = null;
		this.code = null;
		this.departure = null;
		this.enabled = null;
		this.frequency = null;
		this.notes = null;
		this.number = null;
	}

	@Transient
	public Integer getArrivalHour () {
		return this.arrival.get(Calendar.HOUR_OF_DAY);
	}

	@Transient
	public Integer getArrivalMinute () {
		return this.arrival.get(Calendar.MINUTE);
	}

	@Transient
	public  Integer getArrivalSecond () {
		return this.arrival.get(Calendar.SECOND);		
	}

	@Transient
	public  String getArrivalTime () {
		return getTimeString (this.arrival, Constants.TIME_FORMAT);
		
	}

	@Transient
	public Integer getDepartureHour () {
		return this.departure.get(Calendar.HOUR_OF_DAY);
	}

	@Transient
	public Integer getDepartureMinute () {
		return this.departure.get(Calendar.MINUTE);
	}

	@Transient
	public  Integer getDepartureSecond () {
		return this.departure.get(Calendar.SECOND);		
	}

	@Transient
	public  String getDepartureTime () {
		return getTimeString (this.departure, Constants.TIME_FORMAT);
		
	}

	@Transient
	public  String getTimeString (Calendar date, String pattern) {
		return DateFormatUtils.format(date.getTime(), pattern);
	}

	@Column(name="flight_arrival")
	public Calendar getArrival() {
		return arrival;
	}

	@Id 
	@Column(name="flight_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	@Column(name="flight_departure")
	public Calendar getDeparture() {
		return departure;
	}

	@Column(name="flight_enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@ManyToOne
    @JoinColumn(name="frequency_code")
	public Frequency getFrequency() {
		return frequency;
	}

	@Column(name="flight_notes")
	public String getNotes() {
		return notes;
	}

	@Column(name="flight_number")
	public String getNumber() {
		return number;
	}

	public void setArrival(Calendar arrival) {
		this.arrival = arrival;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setDeparture(Calendar departure) {
		this.departure = departure;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

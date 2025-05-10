/*
 * Created 29/03/2007 Route.java
 */
package com.iportal.model.icaro.fare;

import java.util.HashSet;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.model.icaro.Airport;

/**
 * Tipos de Ruta para infromacion de vuelos
 * 
 * @author YAGE(hernan)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "tb_route")
@javax.persistence.TableGenerator(name = "TABLE_GEN", table = "tb_sequence", pkColumnName = "name", valueColumnName = "next_val", pkColumnValue = "route", allocationSize = 20)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Route {

    private Long code;

    private String name;

    private Boolean enabled;

    private String notes;

    private String rateNotes;

    private String rateDoc;

    private Airport departure;

    private Airport arrival;

    private Set<Frequency> frequencies;

    /**
     * 
     */
    public Route() {
	super();
	this.code = null;
	this.enabled = null;
	this.frequencies = new HashSet<Frequency>();
	this.name = null;
	this.notes = null;
	this.rateDoc = null;
	this.rateNotes = null;
	this.departure = null;
	this.arrival = null;
    }

    /**
     * @return Returns the code.
     */

    @Id
    @Column(name = "route_code")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    public Long getCode() {
	return code;
    }

    /**
     * @return Returns the enabled.
     */
    @Column(name = "route_enabled")
    public Boolean getEnabled() {
	return this.enabled;
    }

    @OneToMany(mappedBy = "route")
    @org.hibernate.annotations.OrderBy(clause = "frequency_name asc")
    public Set<Frequency> getFrequencies() {
	return frequencies;
    }

    /**
     * @return Returns the name.
     */
    @Column(name = "route_name")
    public String getName() {
	return name;
    }

    /**
     * @return Returns the notes.
     */
    @Column(name = "route_notes")
    public String getNotes() {
	return notes;
    }

    /**
     * @return the departure
     */
    @ManyToOne
    @JoinColumn(name = "airport_departure_code")
    public Airport getDeparture() {
	return departure;
    }

    /**
     * @return the arrival
     */
    @ManyToOne
    @JoinColumn(name = "airport_arrival_code")
    public Airport getArrival() {
	return arrival;
    }

    /**
     * @param departure
     *                the departure to set
     */
    public void setDeparture(Airport departure) {
	this.departure = departure;
    }

    /**
     * @param arrival
     *                the arrival to set
     */
    public void setArrival(Airport arrival) {
	this.arrival = arrival;
    }

    public void setCode(Long code) {
	this.code = code;
    }

    public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
    }

    public void setFrequencies(Set<Frequency> frequencies) {
	this.frequencies = frequencies;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * relación bidireccionalmente al frequency con esta ruta además elimina
     * referencias anteriores.
     * 
     * @param frequency
     *                frequency que se va a relacionar con esta ruta
     */
    @Transient
    public void addFrequency(Frequency frequency) {

	if (frequency == null) {
	    throw new IllegalArgumentException("...frequency cannot be null!!");
	}
	if (frequency.getRoute() != null
		&& frequency.getRoute().getFrequencies() != null) {
	    frequency.getRoute().getFrequencies().remove(frequency);
	}
	frequency.setRoute(this);

	this.frequencies.add(frequency);
    }

    /**
     * @return Returns the rateDoc.
     */
    @Column(name = "route_rate_doc")
    public String getRateDoc() {
	return rateDoc;
    }

    /**
     * @param rateDoc
     *                The rateDoc to set.
     */
    public void setRateDoc(String rateDoc) {
	this.rateDoc = rateDoc;
    }

    /**
     * @return Returns the rateNotes.
     */
    @Column(name = "route_rate_notes")
    public String getRateNotes() {
	return rateNotes;
    }

    /**
     * @param rateNotes
     *                The rateNotes to set.
     */
    public void setRateNotes(String rateNotes) {
	this.rateNotes = rateNotes;
    }

}

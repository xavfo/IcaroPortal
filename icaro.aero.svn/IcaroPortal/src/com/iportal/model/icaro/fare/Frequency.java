/**
 * 
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

/**
 * Frecuencias disponibles en cada ruta de vuelos
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_frequency")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue = "frequency",
								allocationSize = 20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Frequency {
	
	private Long code;
	
	private String name;
	
    private Boolean enabled;
    
    private String  notes;
    
    private Route route;
    
    private Set<Flight> flights;

	public Frequency() {
		super();
		this.code = null;
		this.name = null;
	    this.enabled = null;
	    this.flights = new HashSet<Flight> ();
	    this.notes   = null;
	    this.route   = null;
	}

	@Id 
	@Column(name="frequency_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	@Column(name="frequency_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	
	
	@OneToMany( mappedBy="frequency" )
	@org.hibernate.annotations.OrderBy(clause="flight_number asc")
	public Set<Flight> getFlights() {
		return flights;
	}

	@Column(name="frequency_name")
	public String getName() {
		return name;
	}

	@Column(name="frequency_notes")
	public String getNotes() {
		return notes;
	}

	@ManyToOne
    @JoinColumn(name="route_code")
	public Route getRoute() {
		return route;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	/**
	 * relación bidireccionalmente al flight con esta frecuencia
	 * además elimina referencias anteriores.
	 * @param flight flight que se va a relacionar con esta frecuencia
	 */
	@Transient
	public void addFlight (Flight flight) {
	
		if (flight == null) {
			throw new IllegalArgumentException ("...flight cannot be null!!");
		}
		if (flight.getFrequency() != null  && flight.getFrequency().getFlights()!= null) {
			flight.getFrequency().getFlights().remove(flight);
		}
		flight.setFrequency(this);
		
		
		this.flights.add(flight);
	}
 
	
	
    
    


}

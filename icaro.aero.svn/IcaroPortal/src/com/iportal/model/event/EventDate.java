/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.event;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.iportal.Constants;
import com.iportal.model.City;
import com.yage.commons.Formater;


/**
 * Representa un objeto EventDate.  Categoria de Eventos.
 * 
 * @author  YAGE (andresg)
 * @version 1.0
 */
@Entity
@Table(name="tb_event_date")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="event_date",
								allocationSize=20
								)
public class EventDate {
	
	private Long code;
	
	private Calendar from;
	
	private Calendar to;
	
	private String location;
	
	private String notes;
	
	private String schedule;
	
	private String responsibleEmail;
	
	private String optionalEmail;
	
	private Event event;
	
	private City city;
	
    /**
     * Creates a new instance of EventDate
     */
    public EventDate() {
        super();
    }
    
    /**
	 * @return Returns the code.
     */
    @Id 
    @Column(name="event_date_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

    @Column(name="event_date_from")
    public Calendar getFrom() {
        return from;
    }

    @Column(name="event_date_to")
    public Calendar getTo() {
        return to;
    }

    @Column(name="event_date_location")
    @OrderBy ("location")
    public String getLocation() {
        return location;
    }

    @Column(name="event_date_notes")
    public String getNotes() {
        return notes;
    }
    
    @Column(name="event_date_optional_email")
    public String getOptionalEmail() {
		return optionalEmail;
	}

    @Column(name="event_date_responsible_email")
	public String getResponsibleEmail() {
		return responsibleEmail;
	}

	@ManyToOne
	@JoinColumn(name="event_code")
	public Event getEvent() {
		return event;
	}

	@ManyToOne
	@JoinColumn(name="city_code")
	public City getCity() {
		return city;
	}


	
    /**
     * @param city The city to set.
     */
    public void setCity(City city) {
        this.city = city;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    /**
     * @param event The event to set.
     */
    public void setEvent(Event event) {
        this.event = event;
    }
    /**
     * @param from The from to set.
     */
    public void setFrom(Calendar from) {
        this.from = from;
    }
    /**
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * @param notes The notes to set.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**
     * @param to The to to set.
     */
    public void setTo(Calendar to) {
        this.to = to;
    }

    @Column(name="event_date_schedule")
	public String getSchedule() {
		return schedule;
	}
    
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public void setOptionalEmail(String optionalEmail) {
		this.optionalEmail = optionalEmail;
	}

	public void setResponsibleEmail(String responsibleEmail) {
		this.responsibleEmail = responsibleEmail;
	}
	
	@Transient
    public String toString(){
        String eventDate = this.getEvent().getTitle() + ", " + this.getCity().getName();
        String from = Formater.formatDate(this.getFrom(), Constants.DATE_TIME_FORMAT);
        String to = Formater.formatDate(this.getTo(), Constants.DATE_TIME_FORMAT);
        if (from != null && to != null){
            eventDate = this.getEvent().getTitle() + ", " + this.getCity().getName() 
            + ", " + from + " - " + to;
        }
	    return eventDate;
    }
}

/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.iportal.model.event.Event;
import com.iportal.model.event.EventDate;

/*
 * This class persist data to the <code>tb_event_subscriber</code> database table.
 * 
 * @author  YAGE (Lourdes Ojeda)
 * @version 2.0
 */

@Entity
@Table(name="tb_event_subscriber")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="event_subscriber",
								allocationSize=20
								)
public class EventSubscriber {
	
	private Long code;
	
	private String name;
	
	private String lastName;
	
	private String address;
	
	private GregorianCalendar systemDate;
	
	private String phone;
	
    private String email;
    
    private String enterprise;
    
    private String occupation;
    
    private Event event;
    
    private EventDate eventDate;

    /**
     * Creates a new instance of Account
     */
    public EventSubscriber() {
        super();
    }
    @Transient
    public String toString() {
        return name;
    }
    
    @Id 
    @Column(name="event_subscriber_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
	
    
    @Column(name="event_subscriber_name")
    public String getName() {
        return name;
    }
    
   
    
    @Column(name="event_subscriber_lastName")
    public String getLastName() {
        return lastName;
    }
    
   
    
    @Column(name="event_subscriber_address")
    public String getAddress() {
        return address;
    }
    
    @Column(name="event_subscriber_system_date")
    public GregorianCalendar getSystemDate() {
        return systemDate;
    }   
    
   
    
    @Column(name="event_subscriber_phone")
    public String getPhone() {
        return phone;
    }
    
    
    @Column(name="event_subscriber_email")
    public String getEmail() {
        return email;
    }
    
    
    @Column(name="event_subscriber_enterprise")
    public String getEnterprise() {
        return enterprise;
    }
   
    
    @Column(name="event_subscriber_occupation")
    public String getOccupation() {
        return occupation;
    }
    
    
    @ManyToOne
	@JoinColumn(name="event_code")
    public Event getEvent() {
		return event;
	}
    
    @ManyToOne
	@JoinColumn(name="event_date_code")
    public EventDate getEventDate() {
		return eventDate;
	}
    
		
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setSystemDate(GregorianCalendar systemDate) {
		this.systemDate = systemDate;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public void setEventDate(EventDate eventDate) {
		this.eventDate = eventDate;
	}
}

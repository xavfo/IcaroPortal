/**
 * 
 */
package com.iportal.cart.model.cart;

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

import com.iportal.biz.worldspan.FlightInfo;
import com.iportal.biz.worldspan.Segment;
import com.iportal.model.icaro.Airport;

/**
 * Informacion de itinerarios en las ordenes de compra
 * 
 * @author YAGE(hernan)
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_itinerary")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="itinerary",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Itinerary {
	
	private Long code;
	
	private Airport departureAirport;
	
	private Airport arrivalAirport;
	
	private Order order;
	
	private String flightNumber;
	
	private Calendar arrivalDateTime;
	
	private Calendar departureDateTime;
	
	private String departureIataCode;
	
	private String arrivalIataCode;
	
	private String airlineCode;
	
	private String airplane;
	
	private Boolean goingLeg;
	
	private Integer legNumber; //itinerary_leg_number
	
	private String classOfService; //itinerary_leg_number
	
	private transient Segment segment;
	
	private transient FlightInfo flightInfo;
	
	

	@Transient
	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	@Transient
	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	@Column(name="itinerary_flight_airline")
	public String getAirlineCode() {
		return airlineCode;
	}

	@Column(name="itinerary_flight_equipment")
	public String getAirplane() {
		return airplane;
	}

    @ManyToOne
    @JoinColumn(name="airport_arrival_code")
	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

    @Column(name="itinerary_arrival_date")
    public Calendar getArrivalDateTime() {
		return arrivalDateTime;
	}

    @Column(name="itinerary_arrival_iata")
    public String getArrivalIataCode() {
		return arrivalIataCode;
	}
    
    @Column(name="itinerary_service_class")
    public String getClassOfService() {
		return classOfService;
	}

	@Id 
    @Column(name="itinerary_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
    public Long getCode() {
		return code;
	}

    @ManyToOne
    @JoinColumn(name="airport_departure_code")
    public Airport getDepartureAirport() {
		return departureAirport;
	}

    @Column(name="itinerary_departure_date")
    public Calendar getDepartureDateTime() {
		return departureDateTime;
	}

    @Column(name="itinerary_departure_iata")
    public String getDepartureIataCode() {
		return departureIataCode;
	}
    
    @Column(name="itinerary_flight_number")    
	public String getFlightNumber() {
		return flightNumber;
	}
    
    
    @Column(name="itinerary_going_leg")
    public Boolean getGoingLeg() {
		return goingLeg;
	}
    
    @Column(name="itinerary_leg_number")
    public Integer getLegNumber() {
		return legNumber;
	}

	@ManyToOne
    @JoinColumn(name="order_code")
    public Order getOrder() {
		return order;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public void setArrivalDateTime(Calendar arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public void setArrivalIataCode(String arrivalIataCode) {
		this.arrivalIataCode = arrivalIataCode;
	}
	
	

	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public void setDepartureDateTime(Calendar departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public void setDepartureIataCode(String departureIataCode) {
		this.departureIataCode = departureIataCode;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public void setGoingLeg(Boolean goingLeg) {
		this.goingLeg = goingLeg;
	}
	
	

	public void setLegNumber(Integer legNumber) {
		this.legNumber = legNumber;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	
	
	
}

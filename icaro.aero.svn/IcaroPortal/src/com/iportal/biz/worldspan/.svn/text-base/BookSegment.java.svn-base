/**
 * 
 */
package com.iportal.biz.worldspan;

/**
 * @author hernan
 *
 */
public class BookSegment {
	
	
	
	public BookSegment() {
		super();
		this.clear();
	}
	
	public void clear () {
		
		this.indicator = null;
		this.marriedSegmentNumber = null;
		this.worldGroupLocator = null;
		this.airlineCode = null;
		this.flightNumber = null;
		this.departureBookingClass = null;
		
		this.departureDay = null;
		this.departureMonth = null;
		
		this.departureHour = null;
		this.departureMinute = null;
		this.departureAirport = null;
		 	
		this.arrivalDay = null;
		this.arrivalMonth = null;
		this.arrivalHour = null;
		this.arrivalMinute  = null;
		this.arrivalAirport = null;
		
		this.eticketElegible= null;
		this.seatPreferenceStatus= null;
		this.sellIndicator= null;
		this.actualStatus= null;
	}

	/*
	 * Type of segment.
	 * Valid Values:
	 * “B”=Book, Power Book, Book and Price, or Book and Price with inventory.
	 * “A”=ARNK
	 * “O”=Open
	 * “M”=Memo
	 * “L”=Waitlist
	 * “X”= NN Status
	 * “Y”=Memo, YK passive action code (cannot be used in the NW partition; RESTRICTED USE)
	 * 
	 * ARNK-must be the only segment in the LEG
	 * Memo - cannot be used with NW; reflects how the segment is booked; for example, a waitlist request may find available seats and book; therefore the SEGMENT-INDICATOR of the response is B.
	 */
	private String indicator;//
	private String marriedSegmentNumber;//MARRIED_SEG_NUM
	
	private String worldGroupLocator;//WGRP_LOCATOR  WorldGroup PNR Locator
	private String airlineCode; //AIRLINE_CODE If only 2-chars, left justified
	
	private String flightNumber; //FLIGHT_NUM Right justified, zero filled; 'OPEN' for open segment.

	
	private String departureBookingClass;//DEP_CLASS Departure Booking Class code.
	
	private String departureDay;//DEP_DAY  Departure day of month
	private String departureMonth;//DEP_MONTH  Departure month. Format MMM
	
	private String departureHour;//DEP_HOUR Departure hour. Valid values: 00-23
	private String departureMinute;//DEP_MIN   Departure minute. Valid values: 00-59
	private String departureAirport;//DEP_AIRPORT Departure airport code
	 	
	private String arrivalDay;//ARRIV_DAY  Arrival day of month. Format DD
	private String arrivalMonth;//ARRIV_MONTH  Arrival month. Format MMM
	private String arrivalHour;//ARRIV_HOUR Arrival hour. Valid values: 00-23
	private String arrivalMinute;//ARRIV_MIN    Arrival minute. Valid value: 00-59
	private String arrivalAirport;//ARRIV_AIRPORT Arrival airport code
	
	private String eticketElegible; // E_TICKETING Field indicates whether the segment is eligible for electronic ticketing. Value echoed from the PNR itinerary display.
	
	private String seatPreferenceStatus; //SEAT_PREF_STATUS Status of Seat-Pref request, if specified in BPC. Some airlines do not retain seat selection. "Y" = YES AVAILABLE; seat request was successful, "A" = NO AIRLINE PARTICIPATION; Non-participant airline in 4RA$, "D" = NO DATE OUT-OF-BOUNDARY; seat request out of date boundary, "F" = NO FAILED; seat request within date boundary, but failed
	private String sellIndicator; //SELL_INDICATOR  "*" (Direct Access), "$" (Direct Sell), "H" (Host flight)
	private String actualStatus; //ACTUAL_STATUS Status returned as a result of an *IA entry.
	public String getActualStatus() {
		return actualStatus;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public String getArrivalDay() {
		return arrivalDay;
	}

	public String getArrivalHour() {
		return arrivalHour;
	}

	public String getArrivalMinute() {
		return arrivalMinute;
	}

	public String getArrivalMonth() {
		return arrivalMonth;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public String getDepartureBookingClass() {
		return departureBookingClass;
	}

	public String getDepartureDay() {
		return departureDay;
	}

	public String getDepartureHour() {
		return departureHour;
	}

	public String getDepartureMinute() {
		return departureMinute;
	}

	public String getDepartureMonth() {
		return departureMonth;
	}

	public String getEticketElegible() {
		return eticketElegible;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getMarriedSegmentNumber() {
		return marriedSegmentNumber;
	}

	public String getSeatPreferenceStatus() {
		return seatPreferenceStatus;
	}

	public String getSellIndicator() {
		return sellIndicator;
	}

	public String getWorldGroupLocator() {
		return worldGroupLocator;
	}

	public void setActualStatus(String actualStatus) {
		this.actualStatus = actualStatus;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public void setArrivalDay(String arrivalDay) {
		this.arrivalDay = arrivalDay;
	}

	public void setArrivalHour(String arrivalHour) {
		this.arrivalHour = arrivalHour;
	}

	public void setArrivalMinute(String arrivalMinute) {
		this.arrivalMinute = arrivalMinute;
	}

	public void setArrivalMonth(String arrivalMonth) {
		this.arrivalMonth = arrivalMonth;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public void setDepartureBookingClass(String departureBookingClass) {
		this.departureBookingClass = departureBookingClass;
	}

	public void setDepartureDay(String departureDay) {
		this.departureDay = departureDay;
	}

	public void setDepartureHour(String departureHour) {
		this.departureHour = departureHour;
	}

	public void setDepartureMinute(String departureMinute) {
		this.departureMinute = departureMinute;
	}

	public void setDepartureMonth(String departureMonth) {
		this.departureMonth = departureMonth;
	}

	public void setEticketElegible(String ticketElegible) {
		eticketElegible = ticketElegible;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public void setMarriedSegmentNumber(String marriedSegmentNumber) {
		this.marriedSegmentNumber = marriedSegmentNumber;
	}

	public void setSeatPreferenceStatus(String seatPreferenceStatus) {
		this.seatPreferenceStatus = seatPreferenceStatus;
	}

	public void setSellIndicator(String sellIndicator) {
		this.sellIndicator = sellIndicator;
	}

	public void setWorldGroupLocator(String worldGroupLocator) {
		this.worldGroupLocator = worldGroupLocator;
	}

	
	
}

/**
 * 
 */
package com.iportal.biz.worldspan;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.iportal.model.icaro.Airport;
import com.yage.commons.DateFormatUtils;

/**
 * javaBean con informacion de segmentos
 * @author hernan
 * @version 1.0
 *
 */
public class Segment {
	
	protected Integer legNumber; //LEG_NUM
	
	protected String availabilityLocation;//AVA_LOC
	
	protected String allotmentIndicator;//ALL_IND
	
	protected String worldGroupLocator;//WGR_LOC WorldGroup PNR Locator
	
	protected String availabilityDepartureTime; //AVA_DEP_TIM
	
	protected String availabilityArrivalTime; //AVA_ARR_TIM
	
	protected String segmentType;//SEG_TYP Segment Type: Valid values are "S" - Starting Segment. If no SEG_TYP field sent, then it's not the starting segment
	
	protected String airlineCode; //ARL_COD Airline Code
	
	protected String flightNumber; //FLI_NUM Flight Number
	
	protected String departureAirportCode; //DEP_ARP Departure Airport
	
	protected String departureDate; //DEP_DAT Departure Date DDMMM
	
	protected String departureTime; //DEP_TIM Departure Time hhmm
	
	protected String relativeDayIndicatorDeparture; //REL_DAY_IND_DEP Relative Day Indicator to Departure. Valid Values: "#" day later, "-" day before, "blank" same day.
	
	protected Integer relativeDayDeparture; // REL_DAY_DEP Relative Day to Departure. Values = 1, 2, etc.
	
	protected String arrivalAirportCode; //ARR_ARP Arrival Airport
	
	protected String arrivalDate; //ARR_DAT DDMMM Arrival Date
	
	protected String arrivalTime; //ARR_TIM Arrival Time hhmm
	
	protected String relativeDayIndicatorArrival; //REL_DAY_IND_ARR Relative Day Indicator to Arrival. Valid Values: "#" day later, "-" day before, "blank" same day.
	
	protected Integer relativeDayArrival; // REL_DAY_ARR Relative Day to Arrival. Values = 1, 2, etc.
	
	protected String equipmentCode; // EQP_COD Equipment code.
	
	protected String mealCode; //MEA_COD  Meal Code
	
	protected Integer numberStops; //NUM_STO Number of stops
	
	protected String departureTerminal; //DEP_TER Departure Terminal

	protected String arrivalTerminal; //ARR_TER Arrival Terminal

	protected String codeShare;//COD_SHA Code Share. Valid Value is "Y".
	
	protected String airlineSource;//ARL_SOU Airline Source. Valie value is "Y"
	
	protected String classService;//COS Class of Service. List of classes of service and the number of seats available in that class. If no class is requested all classes are returned.
	
	//DATOS PARA PRESENTAR EN PANTALLA
	protected Calendar arrivalDateTime;
	
	protected Calendar departureDateTime;
	
	protected Airport departureAirport;
	
	protected Airport arrivalAirport;

	public Segment() {
		super();
		this.clear();
	}

	public void clear () {
		this.legNumber = null;
		this.availabilityLocation = null;
		this.allotmentIndicator = null;
		this.worldGroupLocator = null;
		this.availabilityDepartureTime = null;
		this.availabilityArrivalTime   = null;
		this.segmentType = null;
		this.airlineCode= null;
		this.flightNumber = null;
		this.departureAirportCode = null;
		this.departureDate = null;
		this.departureTime = null;
		this.relativeDayIndicatorDeparture = null;
		this.relativeDayDeparture = null;
		this.arrivalAirportCode = null; 
		this.arrivalDate = null;
		this.arrivalTime = null;
		this.relativeDayIndicatorArrival = null;
		this.relativeDayArrival = null;
		this.equipmentCode = null;
		this.mealCode = null;
		this.numberStops = null;
		this.departureTerminal = null;
		this.arrivalTerminal = null;
		this.codeShare = null;
		this.airlineSource = null;
		this.classService = null;
		
		this.arrivalDateTime   = null;
		this.departureDateTime = null;
		this.departureAirport  = null;
		this.arrivalAirport  = null;
		
		
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public String getAirlineSource() {
		return airlineSource;
	}

	public String getAllotmentIndicator() {
		return allotmentIndicator;
	}

	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public String getArrivalTerminal() {
		return arrivalTerminal;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getAvailabilityArrivalTime() {
		return availabilityArrivalTime;
	}

	public String getAvailabilityDepartureTime() {
		return availabilityDepartureTime;
	}

	public String getAvailabilityLocation() {
		return availabilityLocation;
	}

	public String getClassService() {
		return classService;
	}

	public String getCodeShare() {
		return codeShare;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public Integer getLegNumber() {
		return legNumber;
	}

	public String getMealCode() {
		return mealCode;
	}

	public Integer getNumberStops() {
		return numberStops;
	}

	public Integer getRelativeDayArrival() {
		return relativeDayArrival;
	}

	public Integer getRelativeDayDeparture() {
		return relativeDayDeparture;
	}

	public String getRelativeDayIndicatorArrival() {
		return relativeDayIndicatorArrival;
	}

	public String getRelativeDayIndicatorDeparture() {
		return relativeDayIndicatorDeparture;
	}

	public String getSegmentType() {
		return segmentType;
	}

	public String getWorldGroupLocator() {
		return worldGroupLocator;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public void setAirlineSource(String airlineSource) {
		this.airlineSource = airlineSource;
	}

	public void setAllotmentIndicator(String allotmentIndicator) {
		this.allotmentIndicator = allotmentIndicator;
	}

	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setArrivalTerminal(String arrivalTerminal) {
		this.arrivalTerminal = arrivalTerminal;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setAvailabilityArrivalTime(String availabilityArrivalTime) {
		this.availabilityArrivalTime = availabilityArrivalTime;
	}

	public void setAvailabilityDepartureTime(String availabilityDepartureTime) {
		this.availabilityDepartureTime = availabilityDepartureTime;
	}

	public void setAvailabilityLocation(String availabilityLocation) {
		this.availabilityLocation = availabilityLocation;
	}

	public void setClassService(String classService) {
		this.classService = classService;
	}

	public void setCodeShare(String codeShare) {
		this.codeShare = codeShare;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public void setDepartureTerminal(String departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setLegNumber(Integer legNumber) {
		this.legNumber = legNumber;
	}

	public void setMealCode(String mealCode) {
		this.mealCode = mealCode;
	}

	public void setNumberStops(Integer numberStops) {
		this.numberStops = numberStops;
	}

	public void setRelativeDayArrival(Integer relativeDayArrival) {
		this.relativeDayArrival = relativeDayArrival;
	}

	public void setRelativeDayDeparture(Integer relativeDayDeparture) {
		this.relativeDayDeparture = relativeDayDeparture;
	}

	public void setRelativeDayIndicatorArrival(String relativeDayIndicatorArrival) {
		this.relativeDayIndicatorArrival = relativeDayIndicatorArrival;
	}

	public void setRelativeDayIndicatorDeparture(
			String relativeDayIndicatorDeparture) {
		this.relativeDayIndicatorDeparture = relativeDayIndicatorDeparture;
	}

	public void setSegmentType(String segmentType) {
		this.segmentType = segmentType;
	}

	public void setWorldGroupLocator(String worldGroupLocator) {
		this.worldGroupLocator = worldGroupLocator;
	}

	public Calendar getArrivalDateTime() {
		if (this.arrivalDateTime == null) {
			this.setArrivalDateTime( new GregorianCalendar ());
			int year = this.arrivalDateTime.get(Calendar.YEAR);
			if (this.arrivalDate != null) {
				this.arrivalDateTime.setTime(DateFormatUtils.parseToDate(this.arrivalDate, "ddMMM", Locale.ENGLISH));
			}
			if (this.getArrivalTime() != null) {
				this.arrivalDateTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(this.getArrivalTime().substring(0,2)));
				this.arrivalDateTime.set(Calendar.MINUTE, Integer.valueOf(this.getArrivalTime().substring(2,4)));
			}
			this.arrivalDateTime.set(Calendar.YEAR, year);
			this.arrivalDateTime.getTime();
		}
		return this.arrivalDateTime;
	}

	public Calendar getDepartureDateTime() {
		if (this.departureDateTime == null) {
			this.setDepartureDateTime(new GregorianCalendar ());
			int year = this.departureDateTime.get(Calendar.YEAR);
			if (this.departureDate != null) {
				this.departureDateTime.setTime(DateFormatUtils.parseToDate(this.departureDate, "ddMMM", Locale.ENGLISH));
			}
			if (this.departureTime != null) {
				this.departureDateTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(this.getDepartureTime().substring(0,2)));
				this.departureDateTime.set(Calendar.MINUTE, Integer.valueOf(this.getDepartureTime().substring(2,4)));
			}
			this.departureDateTime.set(Calendar.YEAR, year);
			this.departureDateTime.getTime();
		}

		return departureDateTime;
	}

	private void setArrivalDateTime(Calendar arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	private void setDepartureDateTime(Calendar departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}
	
	
	
	


}

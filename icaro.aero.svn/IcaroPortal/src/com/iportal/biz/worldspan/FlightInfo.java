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
 * @author hernan
 *
 */
public class FlightInfo {
	
	private String linkIndicator;
	
	private String airlineCode;
	
	private String flightNumber;
	
	private String fareClass;
	
	private String flightDate;
	
	private String flightDay;
	
	private String departureAirportCode;
	
	private String arrivalAirportCode;
	
	private String departureTime;
	
	private String arrivalTime;
	

	private String dayChangeIndicator;
	
	private Integer depArrvDateDifference;
	
	private String aircraftType;
	
	private String mealType;
	
	private Integer numberOfStops;
	
	private String eTicketEligible;
	
	private String linkResponseIndicator;
	
	private String codeShareInfo;
	
	private String pricingSegmentNumber;
	
	//DATOS PARA PRESENTAR EN PANTALLA
	protected Calendar arrivalDateTime;
	
	protected Calendar departureDateTime;
	
	protected Airport departureAirport;
	
	protected Airport arrivalAirport;

	
	/**
	 * Calcula la fecha de llegada segun el indicador de diferencias de dias
	 * si DAY_CHG_IND es igual a "#" la diferencia de dias es de un día mas 
	 * si DAY_CHG_IND es igual a "-"la diferencia de dias es de un día menos
	 * En funcion de DAY_CHG_IND se suma o resta el valor de DEP_ARR_DAT_DIF
	 * @return
	 */
	public Calendar getArrivalDateTime() {
		if (this.arrivalDateTime == null) {
			this.setArrivalDateTime( new GregorianCalendar ());
			int year = this.arrivalDateTime.get(Calendar.YEAR);
			if (this.flightDate != null) {
				this.arrivalDateTime.setTime(DateFormatUtils.parseToDate(this.flightDate, "ddMMM", Locale.ENGLISH));
				if (this.getDayChangeIndicator() != null && this.getDepArrvDateDifference() != null){
					this.arrivalDateTime.add(Calendar.DATE,("#".equals(this.getDayChangeIndicator())?1:-1)* this.getDepArrvDateDifference());	
				}
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
			if (this.flightDate != null) {
				this.departureDateTime.setTime(DateFormatUtils.parseToDate(this.flightDate, "ddMMM", Locale.ENGLISH));
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

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setArrivalAirport(Airport arrivalTo) {
		this.arrivalAirport = arrivalTo;
	}

	public void setDepartureAirport(Airport departureFrom) {
		this.departureAirport = departureFrom;
	}

	public FlightInfo() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.linkIndicator = null;
		this.airlineCode = null;
		this.flightNumber = null;
		this.fareClass = null;
		this.flightDate = null;
		this.flightDay = null;
		this.departureAirportCode = null;
		this.arrivalAirportCode = null;
		this.departureTime = null;
		this.arrivalTime = null;
		this.dayChangeIndicator = null;
		this.depArrvDateDifference = null;
		this.aircraftType = null;
		this.mealType = null;
		this.numberOfStops = null;
		this.eTicketEligible = null;
		this.linkResponseIndicator = null;
		this.codeShareInfo = null;
		this.pricingSegmentNumber = null;	
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getCodeShareInfo() {
		return codeShareInfo;
	}

	public String getDayChangeIndicator() {
		return dayChangeIndicator;
	}

	public Integer getDepArrvDateDifference() {
		return depArrvDateDifference;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getETicketEligible() {
		return eTicketEligible;
	}

	public String getFareClass() {
		return fareClass;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public String getFlightDay() {
		return flightDay;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getLinkIndicator() {
		return linkIndicator;
	}

	public String getLinkResponseIndicator() {
		return linkResponseIndicator;
	}

	public String getMealType() {
		return mealType;
	}

	public Integer getNumberOfStops() {
		return numberOfStops;
	}

	public String getPricingSegmentNumber() {
		return pricingSegmentNumber;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public void setArrivalAirportCode(String arrivalAirport) {
		this.arrivalAirportCode = arrivalAirport;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setCodeShareInfo(String codeShareInfo) {
		this.codeShareInfo = codeShareInfo;
	}

	public void setDayChangeIndicator(String dayChangeIndiator) {
		this.dayChangeIndicator = dayChangeIndiator;
	}

	public void setDepArrvDateDifference(Integer depArrvDateDifference) {
		this.depArrvDateDifference = depArrvDateDifference;
	}

	public void setDepartureAirportCode(String departureAirport) {
		this.departureAirportCode = departureAirport;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setETicketEligible(String ticketEligible) {
		eTicketEligible = ticketEligible;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public void setFlightDay(String flightDay) {
		this.flightDay = flightDay;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setLinkIndicator(String linkIndicator) {
		this.linkIndicator = linkIndicator;
	}

	public void setLinkResponseIndicator(String linkResponseIndicator) {
		this.linkResponseIndicator = linkResponseIndicator;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public void setNumberOfStops(Integer numberOfStops) {
		this.numberOfStops = numberOfStops;
	}

	public void setPricingSegmentNumber(String pricingSegmentNumber) {
		this.pricingSegmentNumber = pricingSegmentNumber;
	}
	
	

	
	
}





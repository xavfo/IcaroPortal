/**
 * 
 */
package com.iportal.biz.worldspan;

import java.util.ArrayList;
import java.util.List;

/**
 * Alternate Information 
 * @author hernan
 *
 */
public class PSAlternateInfo extends WorldspanResponse {
	
	private String comment;
	
	private Double baseFare;
	
	private Double taxFare;
	
	private Double totalFare;
	
	private Double powerTotalFare;//TODO esto es temporal hasta q PowerShopper envie correcta informacion
	
	private String contractNumber;
	
	private String currencyCode;
	
	private String fareType;
	
	private String accountCode;//ACC_COD 
	
	private String lastTicketDateInfo;//ACC_COD
	
	private String ticketAdvisory;//
	
	//Passenger Type Fare Information
	private List<PaxTypeInfo> paxTypes;
	
	//Flight Information
	private List<FlightInfo> flights;
	
	private List<TripInfo> trips;
	
	/**
	 * 
	 */
	public PSAlternateInfo() {
		super();
		this.clear();
	}
	
	public void clear () {
		if (this.paxTypes != null) {
			this.paxTypes.clear();
		}
		this.paxTypes = new ArrayList<PaxTypeInfo> ();
		if (this.flights != null) {
			this.flights.clear();
		}
		this.flights = new ArrayList<FlightInfo> ();
		if (this.trips != null) {
			this.trips.clear();
		}
		this.trips = new ArrayList<TripInfo> ();
		this.comment = null;
		this.baseFare = null;
		this.totalFare = null;
		this.powerTotalFare = null;
		this.taxFare = null;
		this.contractNumber = null;
		this.currencyCode = null;
		this.fareType = null;
		this.accountCode = null;
		this.lastTicketDateInfo = null;

	}
	
	public void addFlight (FlightInfo flight) {
		if (this.flights != null) {
			this.flights.add(flight);	
		}
		
	}

	public void addPaxTypeInfo (PaxTypeInfo pax) {
		if (this.paxTypes != null) {
			this.paxTypes.add(pax);	
		}
		
	}
	
	public void addTripInfo (TripInfo trip) {
		if (this.trips != null) {
			this.trips.add(trip);	
		}
		
	}

	public String getAccountCode() {
		return accountCode;
	}

	public Double getBaseFare() {
		return baseFare;
	}

	public String getComment() {
		return comment;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getFareType() {
		return fareType;
	}

	public List<FlightInfo> getFlights() {
		return flights;
	}

	public String getLastTicketDateInfo() {
		return lastTicketDateInfo;
	}

	public List<PaxTypeInfo> getPaxTypes() {
		return paxTypes;
	}

	public Double getTaxFare() {
		return taxFare;
	}

	public String getTicketAdvisory() {
		return ticketAdvisory;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public List<TripInfo> getTrips() {
		return trips;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public void setFlights(List<FlightInfo> flights) {
		this.flights = flights;
	}

	public void setLastTicketDateInfo(String lastTicketDateInfo) {
		this.lastTicketDateInfo = lastTicketDateInfo;
	}

	public void setPaxTypes(List<PaxTypeInfo> paxTypes) {
		this.paxTypes = paxTypes;
	}

	public void setTaxFare(Double taxFare) {
		this.taxFare = taxFare;
	}

	public void setTicketAdvisory(String ticketAdvisory) {
		this.ticketAdvisory = ticketAdvisory;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public void setTrips(List<TripInfo> trips) {
		this.trips = trips;
	}
	//TODO esto es temporal hasta q PowerShopper envie correcta informacion

	/**
	 * @return the powerTotalFare
	 */
	public Double getPowerTotalFare() {
	    return powerTotalFare;
	}

	/**
	 * @param powerTotalFare the powerTotalFare to set
	 */
	public void setPowerTotalFare(Double powerTotalFare) {
	    this.powerTotalFare = powerTotalFare;
	}
	
	



}


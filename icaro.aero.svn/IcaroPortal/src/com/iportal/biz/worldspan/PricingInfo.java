/**
 * 
 */
package com.iportal.biz.worldspan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hernan
 *
 */
public class PricingInfo {
	
	private String token;
	private Integer messageLength;
	private String currency;
	
	private Double baseFareAllPax;
	private Double totalFareAllPax;
	
	private String secondFareType;
	private String thirdFareType;
	
	private Integer decimalPositions;
	private Integer numberTrips;
	private Integer numberPaxTypes;
	
	private String securateContractNumber;
	
	private String lastTicketDateStr;
	private String ticketAdvisory;
	
	private List<PaxTypeInfo> paxTypes;
	
	public PricingInfo() {
		super();
		this.clear();
	}

	public void clear () {
		if (this.paxTypes != null) {
			this.paxTypes.clear();
		}
		this.paxTypes = null; // new ArrayList<PaxTypeInfo> ();
		this.token = null;
		this.messageLength = null;
		this.currency = null;
		this.baseFareAllPax = null;
		this.totalFareAllPax = null;
		this.secondFareType = null;
		this.thirdFareType = null;
		this.decimalPositions = null;
		this.numberTrips = null;
		this.numberPaxTypes = null;
		this.securateContractNumber = null;
		this.lastTicketDateStr = null;
		this.ticketAdvisory = null;

	}
	
	public void addPaxTypeInfo (PaxTypeInfo pax) {
		if (this.paxTypes != null) {
			this.paxTypes.add(pax);	
		}
		
	}

	public void setNumberPaxTypes(Integer numberPaxTypes) {
		this.numberPaxTypes = numberPaxTypes;
		if (this.paxTypes != null) {
			this.paxTypes.clear();
			this.paxTypes =null;
		}
		this.paxTypes = new ArrayList<PaxTypeInfo> (numberPaxTypes);

	}

	public Double getBaseFareAllPax() {
		return baseFareAllPax;
	}

	public String getCurrency() {
		return currency;
	}

	public Integer getDecimalPositions() {
		return decimalPositions;
	}

	public String getLastTicketDateStr() {
		return lastTicketDateStr;
	}

	public Integer getMessageLength() {
		return messageLength;
	}

	public Integer getNumberPaxTypes() {
		return numberPaxTypes;
	}

	public Integer getNumberTrips() {
		return numberTrips;
	}

	public List<PaxTypeInfo> getPaxTypes() {
		return paxTypes;
	}

	public String getSecondFareType() {
		return secondFareType;
	}

	public String getSecurateContractNumber() {
		return securateContractNumber;
	}

	public String getThirdFareType() {
		return thirdFareType;
	}

	public String getTicketAdvisory() {
		return ticketAdvisory;
	}

	public String getToken() {
		return token;
	}

	public Double getTotalFareAllPax() {
		return totalFareAllPax;
	}

	public void setBaseFareAllPax(Double baseFareAllPax) {
		this.baseFareAllPax = baseFareAllPax;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setDecimalPositions(Integer decimalPositions) {
		this.decimalPositions = decimalPositions;
	}

	public void setLastTicketDateStr(String lastTicketDateStr) {
		this.lastTicketDateStr = lastTicketDateStr;
	}

	public void setMessageLength(Integer messageLength) {
		this.messageLength = messageLength;
	}

	public void setNumberTrips(Integer numberTrips) {
		this.numberTrips = numberTrips;
	}

	public void setPaxTypes(List<PaxTypeInfo> paxTypes) {
		this.paxTypes = paxTypes;
	}

	public void setSecondFareType(String secondFareType) {
		this.secondFareType = secondFareType;
	}

	public void setSecurateContractNumber(String securateContractNumber) {
		this.securateContractNumber = securateContractNumber;
	}

	public void setThirdFareType(String thirdFareType) {
		this.thirdFareType = thirdFareType;
	}

	public void setTicketAdvisory(String ticketAdvisory) {
		this.ticketAdvisory = ticketAdvisory;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTotalFareAllPax(Double totalFareAllPax) {
		this.totalFareAllPax = totalFareAllPax;
	}
	
	
	
	

	
	
}


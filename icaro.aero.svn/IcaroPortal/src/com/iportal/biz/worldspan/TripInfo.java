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
public class TripInfo {
	
	private List<String> airportCodes;
	
	private String origin;
	
	private String destination;
	//Passenger Type Detail Information
	
	private List<PaxTypeDetailInfo> paxTypes;

	public TripInfo() {
		super();
		this.clear();
	}
	
	public void clear () {
		if (this.airportCodes != null) {
			this.airportCodes.clear();
		}
		this.airportCodes =  new ArrayList<String> ();
		if (this.paxTypes != null) {
			this.paxTypes.clear();
		}
		this.paxTypes =  new ArrayList<PaxTypeDetailInfo> ();
		this.origin = null;
		this.destination = null;
	}
	
	public void addPaxTypeDetailInfo (PaxTypeDetailInfo paxTypeDetail) {
		if (this.paxTypes != null) {
			this.paxTypes.add(paxTypeDetail);	
		}
		
	}

	public void addAirportCode (String airportCode) {
		if (this.airportCodes != null) {
			this.airportCodes.add(airportCode);	
		}
	}

	public List<String> getAirportCodes() {
		return airportCodes;
	}

	public String getDestination() {
		return destination;
	}

	public String getOrigin() {
		return origin;
	}

	public List<PaxTypeDetailInfo> getPaxTypes() {
		return paxTypes;
	}

	public void setAirportCodes(List<String> airportCodes) {
		this.airportCodes = airportCodes;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setPaxTypes(List<PaxTypeDetailInfo> paxTypes) {
		this.paxTypes = paxTypes;
	}
	
	
	
	


}

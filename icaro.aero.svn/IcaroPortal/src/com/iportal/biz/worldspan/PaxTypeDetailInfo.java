package com.iportal.biz.worldspan;


/**
 * @author hernan
 *
 */
public class PaxTypeDetailInfo {
	
	private String paxTypeCode;
	
	private String airlineCarrierCode;
	private String fareRestriction;
	private String fareBasisCode;
	
	public PaxTypeDetailInfo() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.paxTypeCode = null;
		this.airlineCarrierCode = null;
		
		this.fareRestriction = null;
		this.fareBasisCode = null;
	}

	public String getAirlineCarrierCode() {
		return airlineCarrierCode;
	}

	public String getFareBasisCode() {
		return fareBasisCode;
	}

	public String getFareRestriction() {
		return fareRestriction;
	}

	public String getPaxTypeCode() {
		return paxTypeCode;
	}

	public void setAirlineCarrierCode(String airlineCode) {
		this.airlineCarrierCode = airlineCode;
	}

	public void setFareBasisCode(String fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}

	public void setFareRestriction(String fareRestriction) {
		this.fareRestriction = fareRestriction;
	}

	public void setPaxTypeCode(String paxTypeCode) {
		this.paxTypeCode = paxTypeCode;
	}
	
	
	


}

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
public class PowerShopperResponse extends WorldspanResponse {
	
	private String customerInfo;
	
	private List<WorldspanError> errors;
	
	private List<PSAlternateInfo> alternatives;
	
	private String trackingid; 
	
	private String advisoryMessage;
	
	private Integer alternateCount;
	
	private Integer selectedAlternativeOptionIndex;
	
	
	public void clear () {
		this.customerInfo = null;
		if (this.errors != null) {
			this.errors.clear();
		}
		this.errors =  new ArrayList<WorldspanError> ();
		if (this.alternatives != null) {
			this.alternatives.clear();
		}
		this.alternatives =  null;
		
		this.trackingid= null;
		this.advisoryMessage = null;
		this.alternateCount = null;
		this.selectedAlternativeOptionIndex = null;
	}
	

	public void addError (WorldspanError error) {
		this.errors.add(error);
	}
	
	public void addAlternateInfo (PSAlternateInfo alternateInfo) {
		if (this.alternatives != null) {
			this.alternatives.add(alternateInfo);	
		}
	}
	
	public void setAlternateCount(Integer count) {
		this.alternateCount = count;
		if (this.alternatives != null) {
			this.alternatives.clear();
			this.alternatives =null;
		}
		this.alternatives = new ArrayList<PSAlternateInfo> (count);

	}

	public String getAdvisoryMessage() {
		return advisoryMessage;
	}

	public Integer getAlternateCount() {
		return alternateCount;
	}

	public List<PSAlternateInfo> getAlternatives() {
		return alternatives;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public List<WorldspanError> getErrors() {
		return errors;
	}

	public String getTrackingid() {
		return trackingid;
	}

	public void setAdvisoryMessage(String advisoryMessage) {
		this.advisoryMessage = advisoryMessage;
	}

	public void setAlternatives(List<PSAlternateInfo> alternatives) {
		this.alternatives = alternatives;
	}

	public void setCustomerInfo(String info) {
		this.customerInfo = info;
	}

	public void setErrors(List<WorldspanError> errors) {
		this.errors = errors;
	}

	public void setTrackingid(String trackingid) {
		this.trackingid = trackingid;
	}

	//METODOS DE PARA OPCIONES SELECCIONADAS POR USUARIO
	public Integer getSelectedAlternativeOptionIndex() {
		return selectedAlternativeOptionIndex;
	}

	public PSAlternateInfo getSelectedAlternativeOption() {

		if (this.getSelectedAlternativeOptionIndex() != null) {
			return this.getAlternatives().get(this.getSelectedAlternativeOptionIndex());
		}
		return null;
	}

	public void setSelectedAlternativeOptionIndex(Integer selectedFlightOptionIndex) {
		this.selectedAlternativeOptionIndex = selectedFlightOptionIndex;
	}

	
	
	




}

/**
 * 
 */
package com.iportal.biz.worldspan;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean para procesar respuesta de consulta de disponiblidad
 * (ADC1) es decir bean con estructura de respuesta para ADW1
 * @author hernan
 * @version 1.0
 *
 */
public class AvailabilityResponse extends WorldspanResponse {

	private Integer variableCount; //VAR_COU Variable Count Count for SEG_INF (Segment Information); Valid Value: 1-41
	
	private Integer legCount;// LEG_COU  Count of legs requested in the ADC request
	
	private String frame; 
	
	private List<Leg> legs;
	
	private List<Segment> segments;
	
	private List<String> availabilityVerbiages;
	
	private List<FlightOptionBean> departureOptions;
	
	private List<FlightOptionBean> arrivalOptions;
	
	private Integer selectedDepartureOptionIndex;
	
	private Integer selectedArrivalOptionIndex;

	
	public AvailabilityResponse() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.variableCount = null;
		this.legCount = null;
		this.frame = null;
		if (this.availabilityVerbiages != null) {
			this.availabilityVerbiages.clear();
		}
		this.availabilityVerbiages =  new ArrayList<String> ();
		if (this.legs != null) {
			this.legs.clear();
		}
		this.legs = null; new ArrayList<Leg> ();
		if (this.segments != null) {
			this.segments.clear();
		}
		this.segments = null; // new ArrayList<Segment> ();
		
		if (this.departureOptions != null) {
			this.departureOptions.clear();
		}
		this.departureOptions = null;
		
		if (this.arrivalOptions != null) {
			this.arrivalOptions.clear();
		}
		this.arrivalOptions = null;
		this.selectedDepartureOptionIndex = null;
		this.selectedArrivalOptionIndex = null;
	}
	
	public void addLeg (Leg leg) {
		this.legs.add(leg);
	}
	
	public void addAvailabilityVerbiage (String availabilityVerbiage) {
		this.availabilityVerbiages.add(availabilityVerbiage);
	}

	public void addSegment (Segment segment) {
		this.segments.add(segment);
	}

	
	/**
	 * @return the availabilityVerbiages
	 */
	public List<String> getAvailabilityVerbiages() {
		return availabilityVerbiages;
	}

	public Integer getLegCount() {
		return legCount;
	}

	public Integer getVariableCount() {
		return variableCount;
	}
	
	

	/**
	 * @param availabilityVerbiages the availabilityVerbiages to set
	 */
	public void setAvailabilityVerbiages(List<String> availabilityVerbiages) {
		this.availabilityVerbiages = availabilityVerbiages;
	}

	public void setLegCount(Integer legCount) {
		this.legCount = legCount;
		if (this.legs != null) {
			this.legs.clear();
			this.legs =null;
		}
		this.legs = new ArrayList<Leg> (legCount);
	}

	public void setVariableCount(Integer variableCount) {
		this.variableCount = variableCount;
		if (this.segments != null) {
			this.segments.clear();
			this.segments =null;
		}
		this.segments = new ArrayList<Segment> (variableCount);

	}

	public List<Leg> getLegs() {
		return legs;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public List<FlightOptionBean> getArrivalOptions() {
		return arrivalOptions;
	}

	public List<FlightOptionBean> getDepartureOptions() {
		return departureOptions;
	}


	public Integer getSelectedArrivalOptionIndex() {
		return selectedArrivalOptionIndex;
	}

	public Integer getSelectedDepartureOptionIndex() {
		return selectedDepartureOptionIndex;
	}

	//METODOS DE PARA OPCIONES SELECCIONADAS POR USUARIO
	public FlightOptionBean getSelectedArrivalOption() {

		if (this.getSelectedArrivalOptionIndex() != null) {
			return this.getArrivalOptions().get(this.getSelectedArrivalOptionIndex());
		}
		return null;
	}

	public FlightOptionBean getSelectedDepartureOption() {
		if (this.getSelectedDepartureOptionIndex() != null) {
			return this.getDepartureOptions().get(this.getSelectedDepartureOptionIndex());
		}
		return null;
	}

	public void setArrivalOptions(List<FlightOptionBean> arrivalOptions) {
		this.arrivalOptions = arrivalOptions;
	}

	public void setDepartureOptions(List<FlightOptionBean> departureOptions) {
		this.departureOptions = departureOptions;
	}

	public void setSelectedArrivalOptionIndex(Integer selectedArrivalOptionIndex) {
		this.selectedArrivalOptionIndex = selectedArrivalOptionIndex;
	}

	public void setSelectedDepartureOptionIndex(Integer selectedDepartureOptionIndex) {
		this.selectedDepartureOptionIndex = selectedDepartureOptionIndex;
	}
	

    

	
	
	
	
	
	
	
	

}

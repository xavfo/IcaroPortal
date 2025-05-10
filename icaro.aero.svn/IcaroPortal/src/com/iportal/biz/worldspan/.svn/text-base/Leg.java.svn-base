/**
 * 
 */
package com.iportal.biz.worldspan;

/**
 * Bean con informacion de leg (pierndas de vuelos)
 * @author hernan
 * @version 1.0
 *
 */
public class Leg {
	
	private String status; //LEG_STA
	
	private String verbiageStatus;//VER_STA
	
	private String partialReasonCode;//PAR_REA_COD 

	
	public Leg() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.status = null;
		this.verbiageStatus = null;
		this.partialReasonCode = null;
	}

	/**
	 * Codigo de razon parcial de error donde posibles valores son los
	 * siguientes:
	 * “T”(ime)=ADC processing timer expired; 
	 * “D”(irect Access Error)=ADC processing interrupted by DAER. 
	 * “BLANK”=No partial data error occurred when processing leg. 
	 * “S”(erver)=IO ERROR -TRY AGAIN LATER 
	 * “U”(nexpected)= D.I.R Error (plus D.I.R text) 
	 * “H”(ost ERR) (plus the text of the D.I.R error) 
	 * “N”(Session Problem) 
	 * “P”(ARS Unavailable
	 * 
	 * @return the partialReasonCode
	 */
	public String getPartialReasonCode() {
		return partialReasonCode;
	}

	/**
	 * Posibles estados para cada LEG solicitada en la peticion:
	 * WGER=Problem in accessing Worldgroup PNR
	 * DAER=Direct Access Error for this leg
	 * NWGA=No valid WORLDGROUP segments
	 * NVAL=No valid segments found
	 * 0WGA=Zero Worldgroup availability
	 * 0AVL=All segments sold out in booking class or transformed booking class
	 * CAVL=Some seats are available but not all segments in connection are available
	 * SAVL=Some seats are available but not enough seats in requested class to satisfy number of SEATS requested
	 * NSEG=Segments exceed 41, or would have if leg sent in itinerary
	 * IAER=Internal allotment flight not found
	 * NFDC=Requested class is not found in availability display
	 * GOOD=Availability search successful and valid segments returned	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Verbiage Status
	 * Estado de Verbiage Posibles valores:
	 * “Y”= Additional flight information would have been returned, but there was verbiage text present that could not be returned; 
     * “N”=No additional verbiage existsNote: Segments will not be returned without all verbiage text.
	 * @return the verbiageStatus
	 */
	public String getVerbiageStatus() {
		return verbiageStatus;
	}

	/**
	 * @param partialReasonCode the partialReasonCode to set
	 */
	public void setPartialReasonCode(String partialReasonCode) {
		this.partialReasonCode = partialReasonCode;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param verbiageStatus the verbiageStatus to set
	 */
	public void setVerbiageStatus(String verbiageStatus) {
		this.verbiageStatus = verbiageStatus;
	}
	
	

	

}

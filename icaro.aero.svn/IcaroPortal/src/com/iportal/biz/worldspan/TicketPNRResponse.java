/**
 * 
 */
package com.iportal.biz.worldspan;

import java.util.ArrayList;
import java.util.List;

import com.yage.commons.StringBean;

/**
 * Respuesta de Ticket PNR procesada despues de cobrar de manera exitosa una
 * reservacion
 * @author hernan
 * @version 1.0
 *
 */
public class TicketPNRResponse extends WorldspanResponse {

	private Integer variableCount; //VAR_COU Count for TIC-INF returned Valid values :0001-0004
	
	private String recordLocator; //REC_LOC PNR Record Locator
	
	private String ticketDate; //TIC_DAT Ticket Date
	
	private String ticketType; //TIC_TYP Ticket Type Indicator (ex. 'E' for electronic ticket
	
	private List<StringBean> eTickets; // TIC_NUM Ticket Number. minOccurs=1  maxLength=13 
	
	public void clear () {
		this.variableCount = null;
		this.recordLocator = null;
		this.ticketDate = null;
		this.ticketType = null;
		if (this.eTickets != null) {
			this.eTickets.clear();
		}
		this.eTickets =  new ArrayList<StringBean> ();
	
	}
	
	public void addETickets (StringBean number) {
		this.eTickets.add(number);
	}

	public List<StringBean> getETickets() {
		return eTickets;
	}

	public String getRecordLocator() {
		return recordLocator;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public String getTicketType() {
		return ticketType;
	}

	public Integer getVariableCount() {
		return variableCount;
	}

	public void setETickets(List<StringBean> tickets) {
		eTickets = tickets;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public void setVariableCount(Integer variableCount) {
		this.variableCount = variableCount;
	}
	
	

}

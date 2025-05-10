/*
 * Created on May 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.ctrl.icaro.service;


import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author ftamayo
 *
 * Forma para invocar a webServices
 */
public class ServiceAccountForm extends BaseForm {
	
	private static final long serialVersionUID = 2338076141082093779L;

	private String cardNumber;
	
	private String password;
	
	private BigInteger level;
	
	private String eTicketNumber;
	
	private String recordLocator;
	
	private BigInteger segments;
	
	private Double total;
	
	private Integer serviceType;
	
	

	public void clear () {
		this.cardNumber = null;
		this.eTicketNumber = null; 
		this.recordLocator = null;
		this.segments = null;
		this.total = null;
		this.password=null;
		this.level=null;
		this.serviceType=null;
	}
	
	
	/**
	 * 
	 */
	public ServiceAccountForm() {
		super();
		this.clear ();
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.clear ();
	}


	/**
	 * @return Returns the cardNumber.
	 */
	public String getCardNumber() {
		return cardNumber;
	}


	/**
	 * @param cardNumber The cardNumber to set.
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	/**
	 * @return Returns the eTicketNumber.
	 */
	public String getETicketNumber() {
		return eTicketNumber;
	}


	/**
	 * @param ticketNumber The eTicketNumber to set.
	 */
	public void setETicketNumber(String ticketNumber) {
		eTicketNumber = ticketNumber;
	}


	/**
	 * @return Returns the recordLocator.
	 */
	public String getRecordLocator() {
		return recordLocator;
	}


	/**
	 * @param recordLocator The recordLocator to set.
	 */
	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}


	/**
	 * @return Returns the segments.
	 */
	public BigInteger getSegments() {
		return segments;
	}


	/**
	 * @param segments The segments to set.
	 */
	public void setSegments(BigInteger segments) {
		this.segments = segments;
	}


	/**
	 * @return Returns the total.
	 */
	public Double getTotal() {
		return total;
	}


	/**
	 * @param total The total to set.
	 */
	public void setTotal(Double total) {
		this.total = total;
	}


	/**
	 * @return Returns the level.
	 */
	public BigInteger getLevel() {
		return level;
	}


	/**
	 * @param level The level to set.
	 */
	public void setLevel(BigInteger level) {
		this.level = level;
	}


	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return Returns the serviceType.
	 */
	public Integer getServiceType() {
		return serviceType;
	}


	/**
	 * @param serviceType The serviceType to set.
	 */
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	
}

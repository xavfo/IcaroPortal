/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.ctrl.BasePortalForm;
import com.yage.commons.DateFormatUtils;

/**
 * ContactForm
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class ContactForm extends BasePortalForm {
	
	private static final long serialVersionUID = -1613316450870572375L;
	
	private Long sectorCode;	
    
    private String fromDate;
	
	private String toDate;

    /**
     * Creates a new instance of BasePortalForm
     */
    public ContactForm() {
        super();
        this.sectorCode   = null;
		this.fromDate     = null;
		this.toDate       = null;

    }
    
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		sectorCode = null;
	}
	
	
	/**
	 * @return Returns the sectorCode.
	 */
	public Long getSectorCode() {
		return sectorCode;
	}
	/**
	 * @param sectorCode The sectorCode to set.
	 */
	public void setSectorCode(Long sectorCode) {
		this.sectorCode = sectorCode;
	}
	
	
	
    public String getFromDate() {
		return fromDate;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public String getToDate() {
		return toDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public Calendar getFrom() {
    	
    	Calendar calendar = new GregorianCalendar();
    	
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT) );
			return calendar;
		}
		return null;
		
    	
	}
	
	public void setFrom(Calendar calendar) {
		if ( calendar != null )
			
			this.fromDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
	}
	
    public Calendar getTo() {
    	Calendar calendar = new GregorianCalendar();
    	
		if ( this.getToDate() != null && this.getToDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT) );
            calendar.set(GregorianCalendar.HOUR_OF_DAY, 23);
            calendar.set(GregorianCalendar.MINUTE, 59);
            calendar.set(GregorianCalendar.SECOND, 59);
            calendar.set(GregorianCalendar.MILLISECOND, 999);
			return calendar;
		}
		return null;
		
	}
	
	public void setTo(Calendar calendar) {
		if ( calendar != null )
			this.toDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
	}	
}

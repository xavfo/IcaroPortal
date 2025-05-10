/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * comment SubscriberListForm
 * 
 * @author  YAGE (martha)
 * @version 1.0
 *
 */
public class SubscriberListForm extends BaseForm {
	
	private static final long serialVersionUID = -4621343022608888611L;

	private String city;
    
    private Long countryCode;
    
    private String fromDate;
    
    private String toDate;
	
    private String name;
    
    private String email;
    
    private Boolean search;
   
    public SubscriberListForm() {
        super();
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        city = null;
        countryCode = null;
        fromDate = null;
        toDate = null;
        name = null;
        email = null;
        search = null;
    }

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }


    /**
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * @return Returns the countryCode.
     */
    public Long getCountryCode() {
        return countryCode;
    }


    /**
     * @param countryCode The countryCode to set.
     */
    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * @return Returns the fromDate.
     */
    public String getFromDate() {
        return fromDate;
    }


    /**
     * @param fromDate The fromDate to set.
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }


    /**
     * @return Returns the toDate.
     */
    public String getToDate() {
        return toDate;
    }


    /**
     * @param toDate The toDate to set.
     */
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
            return calendar;
        }
        return null;
    }
      
    public void setTo(Calendar calendar) {
        if ( calendar != null )
            this.toDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}
}

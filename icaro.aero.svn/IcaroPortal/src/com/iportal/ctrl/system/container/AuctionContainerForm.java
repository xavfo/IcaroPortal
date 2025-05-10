/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.container;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.ctrl.BasePortalForm;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;

/**
 * @author  YAGE martha
 * @version 1.1
 *
 */
public class AuctionContainerForm extends BasePortalForm {
		
	private static final long serialVersionUID = -3211555521532769957L;

	private Long code; 
	
	private String name;
	
	private String coactiveFromDate;
	
	private String coactiveToDate;
	
	private String auctionFromDate;
	
	private String auctionToDate;
	
	private String description;
	
	private Boolean isEnabled;
	
	private Integer enabledOption;
	
	private String auctionDateField;
	private String coactiveDateField;
	
	private Boolean initialize;
	
	
	/**
     * Creates a new instance of documentForm
     */
    public AuctionContainerForm() {
        super();
    }
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);                    
    	name		    	= null;
		coactiveFromDate 	= null;
		coactiveToDate 		= null;
		auctionFromDate 	= null;
		auctionToDate 		= null;
		description 		= null;
        isEnabled	 		= Globals.TRUE;
        initialize 			= Globals.FALSE;
    } 
    
    public Calendar getAuctionFrom() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getAuctionFromDate() != null && this.getAuctionFromDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getAuctionFromDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
    
	public void setAuctionFrom(Calendar calendar) {
		if ( calendar != null )
			this.auctionFromDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}

	public Calendar getAuctionTo() {
		Calendar calendar = new GregorianCalendar();
		
		if ( this.getAuctionToDate() != null && this.getAuctionToDate().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getAuctionToDate(), language.getDateFormat()) );
			return calendar;
		}
		
		return null;
	}
	
	public void setAuctionTo(Calendar calendar) {
		if ( calendar != null )
			this.auctionToDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}
	
	public Calendar getCoactiveFrom() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getCoactiveFromDate() != null && this.getCoactiveFromDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getCoactiveFromDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
    
	public void setCoactiveFrom(Calendar calendar) {
		if ( calendar != null )
			this.coactiveFromDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}

	public Calendar getCoactiveTo() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getCoactiveToDate() != null && this.getCoactiveToDate().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getCoactiveToDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setCoactiveTo(Calendar calendar) {
		if ( calendar != null )
			this.coactiveToDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}
	
	
	public Calendar getCoactiveDate() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getCoactiveDateField() != null && this.getCoactiveDateField().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getCoactiveDateField(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setCoactiveDate(Calendar calendar) {
		if ( calendar != null )
			this.coactiveDateField = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}
	
	public Calendar getAuctionDate() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getAuctionDateField() != null && this.getAuctionDateField().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getAuctionDateField(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setAuctionDate(Calendar calendar) {
		if ( calendar != null )
			this.auctionDateField = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}	

	/**
	 * @return Returns the code.
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}


	/**
	 * @return Returns the isEnabled.
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}


	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	/**
	 * @return Returns the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param leadinText The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the auctionFromDate.
	 */
	public String getAuctionFromDate() {
		return auctionFromDate;
	}

	/**
	 * @param auctionFromDate The auctionFromDate to set.
	 */
	public void setAuctionFromDate(String auctionFromDate) {
		this.auctionFromDate = auctionFromDate;
	}

	/**
	 * @return Returns the auctionToDate.
	 */
	public String getAuctionToDate() {
		return auctionToDate;
	}

	/**
	 * @param auctionToDate The auctionToDate to set.
	 */
	public void setAuctionToDate(String auctionToDate) {
		this.auctionToDate = auctionToDate;
	}

	/**
	 * @return Returns the seizureFromDate.
	 */
	public String getCoactiveFromDate() {
		return coactiveFromDate;
	}

	/**
	 * @param seizureFromDate The coactiveFromDate to set.
	 */
	public void setCoactiveFromDate(String coactiveFromDate) {
		this.coactiveFromDate = coactiveFromDate;
	}

	/**
	 * @return Returns the seizureToDate.
	 */
	public String getCoactiveToDate() {
		return coactiveToDate;
	}

	/**
	 * @param coactiveToDate The coactiveToDate to set.
	 */
	public void setCoactiveToDate(String coactiveToDate) {
		this.coactiveToDate = coactiveToDate;
	}

	/**
	 * @return Returns the auctionDate.
	 */
	public String getAuctionDateField() {
		return auctionDateField;
	}

	/**
	 * @param auctionDate The auctionDate to set.
	 */
	public void setAuctionDateField(String auctionDateField) {
		this.auctionDateField = auctionDateField;
	}

	/**
	 * @return Returns the coactiveDate.
	 */
	public String getCoactiveDateField() {
		return coactiveDateField;
	}

	/**
	 * @param coactiveDate The coactiveDate to set.
	 */
	public void setCoactiveDateField(String coactiveDateField) {
		this.coactiveDateField = coactiveDateField;
	}

	/**
	 * @return Returns the enabledOption.
	 */
	public Integer getEnabledOption() {
		return enabledOption;
	}

	/**
	 * @param enabledOption The enabledOption to set.
	 */
	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}

	public Boolean getInitialize() {
		return initialize;
	}

	public void setInitialize(Boolean initialize) {
		this.initialize = initialize;
	}
	
}//end class

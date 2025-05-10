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
 * 
 * 
 * @author  YAGE mcnovillo
 * @version 1.1
 *
 */
public class FaqContainerForm extends BasePortalForm {
		
	private static final long serialVersionUID = -3211555521532769957L;
	
	private String question;	
	
	private String answer;
	
	private Long categoryCode;
	
	private String fromDate;
	
	private String toDate;	

	private Boolean isEnabled;
	
	private Integer enabledOption;
	
	private Long ownerCode;
	
	private Long index;
	
	/**
	 * @return Returns the path.
	 */
	public String getAnswer() {
		return answer;
	}


	/**
	 * @param path The path to set.
	 */
	public void setAnswer(String path) {
		this.answer = path;
	}


	/**
     * Creates a new instance of documentForm
     */
    public FaqContainerForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);                    
    	question		 = null;
    	answer			 = null;
    	categoryCode = null;    	    
    	fromDate	 = null;
    	toDate		 = null;                         
        isEnabled	 = Globals.TRUE;
        enabledOption = null;
    } 
    
    public Calendar getFrom() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
    
	public void setFrom(Calendar calendar) {
		if ( calendar != null )
			this.fromDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}

	public Calendar getTo() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getToDate() != null && this.getToDate().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setTo(Calendar calendar) {
		if ( calendar != null )
			this.toDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
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


	/**
	 * @return Returns the name.
	 */
	public String getQuestion() {
		return question;
	}


	/**
	 * @param name The name to set.
	 */
	public void setQuestion(String name) {
		this.question = name;
	}


	/**
	 * @return Returns the categoryCode.
	 */
	public Long getCategoryCode() {
		return categoryCode;
	}


	/**
	 * @param categoryCode The categoryCode to set.
	 */
	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}


	/**
	 * @return Returns the ownerCode.
	 */
	public Long getOwnerCode() {
		return ownerCode;
	}


	/**
	 * @param ownerCode The ownerCode to set.
	 */
	public void setOwnerCode(Long ownerCode) {
		this.ownerCode = ownerCode;
	}


	public Integer getEnabledOption() {
		return enabledOption;
	}


	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}


	public Long getIndex() {
		return index;
	}


	public void setIndex(Long index) {
		this.index = index;
	}

	
	
}//end class

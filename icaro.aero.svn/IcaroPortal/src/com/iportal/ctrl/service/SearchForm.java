/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.commons.DateFormatUtils;

/**
 * comment SearchForm
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class SearchForm extends SearchCategoryForm {
	
	private static final long serialVersionUID = -6184511381148443778L;

	private Long sectionCode;
	
	private Long newsSectionCode;
	
	private Long projectSectorCode;
	
	private String text;
	
	private String fromDate;
	
	private String toDate;
	
	private String fromDate2;
	
	private String toDate2;
	
    /**
     * Creates a new instance of BasePortalForm
     */
    public SearchForm() {
        super();
    }
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		sectionCode = null;
		newsSectionCode = null;
		projectSectorCode = null;
		text = null;
		fromDate = null;
		toDate = null;
		fromDate2 = null;
		toDate2 = null;
	}

	public Date getFrom() {
		if ( this.getFromDate() != null && this.getFromDate().length() > 0 )
			return DateFormatUtils.parseToDate(this.getFromDate(), language.getDateFormat());
		return null;
	}
	
	public Calendar getFromCalendar() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}	
	
	public Date getTo() {
		if ( this.getToDate() != null && this.getToDate().length() > 0 )
			return DateFormatUtils.parseToDate(this.getToDate(), language.getDateFormat());
		return null;
	}
	
	public Calendar getToCalendar() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getToDate() != null && this.getToDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}	
	
	public Date getFrom2() {
		if ( this.getFromDate2() != null && this.getFromDate2().length() > 0 )
			return DateFormatUtils.parseToDate(this.getFromDate2(), language.getDateFormat());
		return null;
	}
	
	public Calendar getFromCalendar2() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getFromDate2() != null && this.getFromDate2().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate2(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public Date getTo2() {
		if ( this.getToDate2() != null && this.getToDate2().length() > 0 )
			return DateFormatUtils.parseToDate(this.getToDate2(), language.getDateFormat());
		return null;
	}
	
	public Calendar getToCalendar2() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getToDate2() != null && this.getToDate2().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToDate2(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}	
	
	
	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @return Returns the fromDate2.
	 */
	public String getFromDate2() {
		return fromDate2;
	}
	/**
	 * @return Returns the newsSectionCode.
	 */
	public Long getNewsSectionCode() {
		return newsSectionCode;
	}
	/**
	 * @return Returns the projectSectorCode.
	 */
	public Long getProjectSectorCode() {
		return projectSectorCode;
	}
	/**
	 * @return Returns the sectionCode.
	 */
	public Long getSectionCode() {
		return sectionCode;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @return Returns the toDate2.
	 */
	public String getToDate2() {
		return toDate2;
	}
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @param fromDate2 The fromDate2 to set.
	 */
	public void setFromDate2(String fromDate2) {
		this.fromDate2 = fromDate2;
	}
	/**
	 * @param newsSectionCode The newsSectionCode to set.
	 */
	public void setNewsSectionCode(Long newsSectionCode) {
		this.newsSectionCode = newsSectionCode;
	}
	/**
	 * @param projectSectorCode The projectSectorCode to set.
	 */
	public void setProjectSectorCode(Long projectSectorCode) {
		this.projectSectorCode = projectSectorCode;
	}
	/**
	 * @param sectionCode The sectionCode to set.
	 */
	public void setSectionCode(Long sectionCode) {
		this.sectionCode = sectionCode;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @param toDate2 The toDate2 to set.
	 */
	public void setToDate2(String toDate2) {
		this.toDate2 = toDate2;
	}
}

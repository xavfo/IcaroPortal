/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.event;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.iportal.Constants;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * @author YAGE (aigiler). Formulario para Calendario de Eventos
 * @version 1.0
 * @see com.iportal.ctrl.BaseForm
 */
public class EventDateForm extends BaseForm {
	
	private static final long serialVersionUID = -94752887150994000L;

	private String strDate;
	
	private Integer year;
	
	private Integer month;
	
	private String[] date;
	
    private Integer startHour;
    
    private Integer startMinute;
    
    private Integer endHour;
	
    private Integer endMinute;
    
	private String location;
	
	private String schedule;
	
	private String notes;
	
	private String responsibleEmail;
	
	private String optionalEmail;
	
	private Long eventCode;
	
	private Long cityCode;
	
	private String cityName;
	
	private Long countryCode;
	
	private String countryName;
	
	private String fromStr;
	
	private String toStr;	
	
	
	/**
     * Creates a new instance of EventDateForm
     */
    public EventDateForm() {
        super();
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        date = new String[0];
		Calendar calendar = new GregorianCalendar();
		int minute = calendar.get(Calendar.MINUTE);
		minute = minute/5;
		minute = minute*5 + 5;
		calendar.set(Calendar.MINUTE, minute);
		
		this.year  = new Integer(calendar.get(Calendar.YEAR));
		this.month = new Integer(calendar.get(Calendar.MONTH) + 1);
	
		this.schedule = null;
		this.notes=null;
		this.location=null;
		this.strDate = null;
		this.date = null;
		this.startHour = null;
		this.startMinute = null;
		this.endHour = null;
		this.endMinute = null;
		this.cityCode = null;
		this.cityName = null;
		this.countryCode = null;
		this.countryName = null;
		this.fromStr = null;
		this.toStr = null;
    }
    
    /* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if (!(this.getDate() != null && this.getDate().length >0) ){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.eventDate.noDateSelected"));	
		}
				
		return errors;
	}
    
	public Calendar getFrom() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(DateFormatUtils.parseToDate(this.getStrDate(), Constants.DATE_FORMAT));
		calendar.set(Calendar.HOUR, this.startHour.intValue());
		calendar.set(Calendar.MINUTE, this.startMinute.intValue());
		return calendar;
	}
	
	public Calendar getTo() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(DateFormatUtils.parseToDate(this.getStrDate(), Constants.DATE_FORMAT));
		calendar.set(Calendar.HOUR, this.endHour.intValue());
		calendar.set(Calendar.MINUTE, this.endMinute.intValue());
		return calendar;
	}
	
	public Calendar getFromDate() {
		Calendar date = null;
		if ( this.getFromStr() != null && this.getFromStr().length() > 0  ) { 
			date = Calendar.getInstance();
			date.setTime(DateFormatUtils.parseToDate(this.getFromStr(), Constants.DATE_FORMAT));
			date.getTime();
		}
		return date;
	}
    
    public void setFromDate(Calendar date) {
		if ( date != null )
			this.setFromStr(DateFormatUtils.format(date.getTime(), Constants.DATE_FORMAT));
			 
	}
    
    public Calendar getToDate() {
		Calendar date = null;
		if ( this.getToStr() != null && this.getToStr().length() > 0  ) { 
			date = Calendar.getInstance();
			date.setTime(DateFormatUtils.parseToDate(this.getToStr(), Constants.DATE_FORMAT));
			date.getTime();
		}
		return date;
	}
    
    public void setToDate(Calendar date) {
		if ( date != null )
			this.setToStr(DateFormatUtils.format(date.getTime(), Constants.DATE_FORMAT));
			 
	}

	public Long getCityCode() {
		return cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public Long getCountryCode() {
		return countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public String[] getDate() {
		return date;
	}

	public Integer getEndHour() {
		return endHour;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public Long getEventCode() {
		return eventCode;
	}

	public String getFromStr() {
		return fromStr;
	}

	public String getLocation() {
		return location;
	}

	public Integer getMonth() {
		return month;
	}

	public String getNotes() {
		return notes;
	}

	public String getSchedule() {
		return schedule;
	}

	public Integer getStartHour() {
		return startHour;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public String getStrDate() {
		return strDate;
	}

	public String getToStr() {
		return toStr;
	}

	public Integer getYear() {
		return year;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setDate(String[] date) {
		this.date = date;
	}

	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public void setEventCode(Long eventCode) {
		this.eventCode = eventCode;
	}

	public void setFromStr(String fromStr) {
		this.fromStr = fromStr;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public void setToStr(String toStr) {
		this.toStr = toStr;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getOptionalEmail() {
		return optionalEmail;
	}

	public String getResponsibleEmail() {
		return responsibleEmail;
	}

	public void setOptionalEmail(String optionalEmail) {
		this.optionalEmail = optionalEmail;
	}

	public void setResponsibleEmail(String responsibleEmail) {
		this.responsibleEmail = responsibleEmail;
	}

}
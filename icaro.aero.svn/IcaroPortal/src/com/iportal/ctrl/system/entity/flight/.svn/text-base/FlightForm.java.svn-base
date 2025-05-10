/**
 * 
 */
package com.iportal.ctrl.system.entity.flight;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * Formulario para operaciones CRUD de vuelos
 * @author hernan
 * @version 1.0
 *
 */
public class FlightForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1645101044642891748L;

	private String arrivalDate;
	
	private String departureDate;
	
	private Integer arrivalHour;
	
	private Integer arrivalMinute;
	
	private Integer departureHour;
	
	private Integer departureMinute;

	private Boolean enabled;
	
	private Long frequencyCode;
	
	private String notes;
	
	private String number;
	
	private Integer enabledOption;
	
	/**
	 * 
	 */
	public FlightForm() {
		super();
	}
	
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    	this.arrivalDate = null;
    	this.arrivalHour = new Integer (0);
    	this.arrivalMinute   = new Integer (0);
    	this.departureDate = null;
    	this.departureHour   = new Integer (0);
    	this.departureMinute = new Integer (0);
    	this.enabled = Globals.FALSE;
    	this.frequencyCode = null;
    	this.notes  = null;
    	this.number = null;
    	this.enabledOption = null;
    	Calendar now = Calendar.getInstance();
    	this.setArrival(now);
    	this.setDeparture(now);
    	
    }
    
    public Calendar getArrival() {
		if ( this.getArrivalDate() != null && this.getArrivalDate().length() > 0  ) { 
			Calendar calendar = new GregorianCalendar();
			calendar.setTime( DateFormatUtils.parseToDate(this.getArrivalDate(), Constants.DATE_FORMAT) );
			calendar.set(Calendar.HOUR_OF_DAY, this.arrivalHour.intValue());
			calendar.set(Calendar.MINUTE, this.arrivalMinute.intValue());
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar;
		}
		return null;
	}
	
    public Calendar getDeparture() {
		if ( this.getDepartureDate() != null && this.getDepartureDate().length() > 0  ) { 
			Calendar calendar = new GregorianCalendar();
			calendar.setTime( DateFormatUtils.parseToDate(this.getDepartureDate(), Constants.DATE_FORMAT) );
			calendar.set(Calendar.HOUR_OF_DAY, this.departureHour.intValue());
			calendar.set(Calendar.MINUTE, this.departureMinute.intValue());
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar;
		}
		return null;
	}

    public void setArrival(Calendar calendar) {
		if ( calendar != null ) {
			this.setArrivalDate (calendar);
		}
	}
    
    public void setDeparture(Calendar calendar) {
		if ( calendar != null ) {
			setDepartureDate (calendar);
		}
		
	}

    private void setDepartureDate (Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, this.departureHour.intValue());
		calendar.set(Calendar.MINUTE, this.departureMinute.intValue());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		this.departureDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
    	
    }
    private void setArrivalDate (Calendar calendar) {

    	calendar.set(Calendar.HOUR_OF_DAY, this.arrivalHour.intValue());
		calendar.set(Calendar.MINUTE, this.arrivalMinute.intValue());
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		this.arrivalDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_TIME_FORMAT);
    	
    }


    public String getArrivalDate() {
		return arrivalDate;
	}

	public Integer getArrivalHour() {
		return arrivalHour;
	}

	public Integer getArrivalMinute() {
		return arrivalMinute;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public Integer getDepartureHour() {
		return departureHour;
	}

	public Integer getDepartureMinute() {
		return departureMinute;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Integer getEnabledOption() {
		return enabledOption;
	}

	public Long getFrequencyCode() {
		return frequencyCode;
	}

	public String getNotes() {
		return notes;
	}

	public String getNumber() {
		return number;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setArrivalHour(Integer arrivalHour) {
		this.arrivalHour = arrivalHour;
		this.setArrivalDate(this.getArrival());
	}

	public void setArrivalMinute(Integer arrivalMinute) {
		this.arrivalMinute = arrivalMinute;
		this.setArrivalDate(this.getArrival());
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
		
	}

	public void setDepartureHour(Integer departureHour) {
		this.departureHour = departureHour;
		this.setDepartureDate(this.getDeparture());
	}

	public void setDepartureMinute(Integer departureMinute) {
		this.departureMinute = departureMinute;
		this.setDepartureDate(this.getDeparture());
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}

	public void setFrequencyCode(Long frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setNumber(String number) {
		this.number = number;
	}
    

}

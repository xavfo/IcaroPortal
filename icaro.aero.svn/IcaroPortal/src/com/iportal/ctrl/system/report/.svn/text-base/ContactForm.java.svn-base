/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.report;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

public class ContactForm extends BaseForm {

	
	

		/**
	 * 
	 */
	private static final long serialVersionUID = 4904383842001266507L;

		private String name;
	    
		private String lastName;
		
		private String address;
		
		private String email;
	    
	    private String comment;
	    
	    private String position;
	    
	    private String company;
	    
	    private String phone;
	    
	    private Long countryCode;
	    
	    private String cityName;
	    
	    private String stateName;
	    
	    private String countryName;
	    
	    private Long cityCode;
	    
	    private String fromDate;
		
		private String toDate;
	    
		public ContactForm() {
	        super();
			this.name         = null;
			this.lastName     = null;
			this.address      = null;
			this.email        = null;
			this.comment      = null;
			this.position     = null;
			this.company      = null;
			this.phone        = null;
			this.countryCode  = null;
			this.cityName     = null;
			this.stateName    = null;
			this.countryName  = null;
			this.cityCode     = null;
			this.fromDate     = null;
			this.toDate       = null;

	    }
		
		public void reset(ActionMapping mapping, HttpServletRequest request) {
			super.reset(mapping, request);
			this.name         = null;
			this.lastName     = null;
			this.address      = null;
			this.email        = null;
			this.comment      = null;
			this.position     = null;
			this.company      = null;
			this.phone        = null;
			this.countryCode  = null;
			this.cityName     = null;
			this.stateName    = null;
			this.countryName  = null;
			this.cityCode     = null;

			this.fromDate     = null;
			this.toDate       = null;
		}


		/**
		 * @return Returns the address.
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @return Returns the cityCode.
		 */
		public Long getCityCode() {
			return cityCode;
		}

		/**
		 * @return Returns the cityName.
		 */
		public String getCityName() {
			return cityName;
		}

		/**
		 * @return Returns the comment.
		 */
		public String getComment() {
			return comment;
		}

		/**
		 * @return Returns the company.
		 */
		public String getCompany() {
			return company;
		}

		/**
		 * @return Returns the countryCode.
		 */
		public Long getCountryCode() {
			return countryCode;
		}

		/**
		 * @return Returns the countryName.
		 */
		public String getCountryName() {
			return countryName;
		}

		/**
		 * @return Returns the email.
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @return Returns the lastName.
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @return Returns the name.
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return Returns the phone.
		 */
		public String getPhone() {
			return phone;
		}

		/**
		 * @return Returns the position.
		 */
		public String getPosition() {
			return position;
		}

		/**
		 * @return Returns the stateName.
		 */
		public String getStateName() {
			return stateName;
		}

		/**
		 * @param address The address to set.
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * @param cityCode The cityCode to set.
		 */
		public void setCityCode(Long cityCode) {
			this.cityCode = cityCode;
		}

		/**
		 * @param cityName The cityName to set.
		 */
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		/**
		 * @param comment The comment to set.
		 */
		public void setComment(String comment) {
			this.comment = comment;
		}

		/**
		 * @param company The company to set.
		 */
		public void setCompany(String company) {
			this.company = company;
		}

		/**
		 * @param countryCode The countryCode to set.
		 */
		public void setCountryCode(Long countryCode) {
			this.countryCode = countryCode;
		}

		/**
		 * @param countryName The countryName to set.
		 */
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		/**
		 * @param email The email to set.
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @param lastName The lastName to set.
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @param name The name to set.
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @param phone The phone to set.
		 */
		public void setPhone(String phone) {
			this.phone = phone;
		}

		/**
		 * @param position The position to set.
		 */
		public void setPosition(String position) {
			this.position = position;
		}

		/**
		 * @param stateName The stateName to set.
		 */
		public void setStateName(String stateName) {
			this.stateName = stateName;
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
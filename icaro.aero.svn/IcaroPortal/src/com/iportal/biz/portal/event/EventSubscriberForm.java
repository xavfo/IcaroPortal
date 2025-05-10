/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal.event;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

public class EventSubscriberForm extends BaseForm {

	
	private static final long serialVersionUID = 2338076141082093779L;

		private String name;
		
		private String lastName;
		
		private String address;
		
		private String phone;
		
		private Long eventCode;
		
		private Long eventDateCode;
		
		private String eventDateDescription;
	    
	    private String email;
	    
	    private String enterprise;
	    
	    private String occupation;
	    
	    
	    @Override
		public void reset(ActionMapping arg0, ServletRequest arg1) {
			super.reset(arg0, arg1);
			name = null;
			lastName = null;
			address = null;
			phone = null;
			eventDateCode = null;
			eventDateDescription = null;
		    email = null;
		    enterprise = null;
		    occupation = null;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEnterprise() {
			return enterprise;
		}

		public void setEnterprise(String enterprise) {
			this.enterprise = enterprise;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Long getEventCode() {
			return eventCode;
		}

		public void setEventCode(Long eventCode) {
			this.eventCode = eventCode;
		}

		public Long getEventDateCode() {
			return eventDateCode;
		}
		
		public void setEventDateCode(Long eventDateCode) {
			this.eventDateCode = eventDateCode;
		}
		
		public String getEventDateDescription() {
			return eventDateDescription;
		}
		
		public void setEventDateDescription(String eventDateDescription) {
			this.eventDateDescription = eventDateDescription;
		}
}
/**
 * 
 */
package com.iportal.model.icaro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Informacion de persona de contacto
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Embeddable
public class ContactInfo {
	
	private String contactName;
	
	private String contactPosition;
	
	private Date lastContact;

	public ContactInfo() {
		super();
		this.contactName = null;
		this.contactPosition = null;
		this.lastContact = null;
	}

	@Column(name="establishment_contact_name")
	public String getContactName() {
		return contactName;
	}

	@Column(name="establishment_contact_position")
	public String getContactPosition() {
		return contactPosition;
	}

	@Column(name="establishment_last_contact")
	public Date getLastContact() {
		return lastContact;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public void setLastContact(Date lastContact) {
		this.lastContact = lastContact;
	}
	
	
	
	


}


/*
 * Created Jan 15, 2007
 *    BrandForm.java
 */
package com.iportal.ctrl.system.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * Forma de toma de datos para Establecimientos
 *
 * @author ferTamayo
 * @version 1.0
 *
 */
public class EstablishmentForm extends BaseForm {

    private static final long serialVersionUID = -3722679576195525985L;

    private Long cityCode;

    private Long categoryCode;

    private String name;

    private Boolean enabled;

    private String address;

    private String image;

    private String phone1;

    private String phone2;

    private String fax;

    private String email;

    private String schedule;

    private String description;

    private String contactName;

    private String contactPosition;

    private String lastContact;




    /**
     *
     */
    public EstablishmentForm() {
        super();
        this.clear();
    }

    public void clear () {
        this.cityCode=null;
        this.categoryCode=null;
        this.name=null;
        this.enabled=Globals.TRUE;;
        this.address=null;
        this.image=null;
        this.phone1=null;
        this.phone2=null;
        this.fax=null;
        this.email=null;
        this.schedule=null;
        this.description=null;
        this.contactName=null;
        this.contactPosition=null;
        this.lastContact=null;
    }

    /* (non-Javadoc)
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }

    /**
     * @return Returns the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return Returns the cityCode.
     */
    public Long getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode The cityCode to set.
     */
    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the enabled.
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return Returns the fax.
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return Returns the image.
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image to set.
     */
    public void setImage(String image) {
        this.image = image;
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
     * @return Returns the phone1.
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * @param phone1 The phone1 to set.
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * @return Returns the phone2.
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 The phone2 to set.
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * @return Returns the schedule.
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * @param schedule The schedule to set.
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * @return Returns the contactName.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName The contactName to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return Returns the contactPosition.
     */
    public String getContactPosition() {
        return contactPosition;
    }

    /**
     * @param contactPosition The contactPosition to set.
     */
    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public Calendar getTo() {
        Calendar calendar = new GregorianCalendar();

        if ( this.getLastContact() != null && this.getLastContact().length() > 0  ) {
            calendar.setTime( DateFormatUtils.parseToDate(this.getLastContact(), Constants.DATE_FORMAT) );
            return calendar;
        }
        return null;

    }

  public void setTo(Calendar calendar) {
        if ( calendar != null )
            this.lastContact = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }
    /**
     * @return Returns the lastContact.
     */
    public String getLastContact() {
        return lastContact;
    }

    /**
     * @param lastContact The lastContact to set.
     */
    public void setLastContact(String lastContact) {
        this.lastContact = lastContact;
    }









}

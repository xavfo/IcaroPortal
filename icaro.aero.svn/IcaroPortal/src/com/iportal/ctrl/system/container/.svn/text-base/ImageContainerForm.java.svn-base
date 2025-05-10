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
 * ImageContainerForm
 * 
 * @author  YAGE pajaro
 * @version 1.1
 *
 */
public class ImageContainerForm extends BasePortalForm {
			
	private static final long serialVersionUID = 1228139513004384060L;

	private String title;
	
	private String description;
	
	private Long thumbnailImageCode;
	
	private String thumbnailImageName;
	
	private Long mediumImageCode;
	
	private String mediumImageName;
	
	private Long largeImageCode;
	
	private String largeImageName;
	
	private Boolean enabled;
	
	private Integer enabledOption;
	
	private String fromStr;
	
	private String toStr;	
	
	private Long ownerCode;
	
	/**
     * Creates a new instance of documentForm
     */
    public ImageContainerForm() {
        super();
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);                    
        title = null;
    	description = null;
    	thumbnailImageCode = null;
    	thumbnailImageName = null;
    	mediumImageCode = null;
    	mediumImageName = null;
    	largeImageCode = null;
    	largeImageName = null;
    	enabled = Globals.TRUE;
    	enabledOption = -1;
    	fromStr = null;
    	toStr = null;	
    	ownerCode = null;
    }
    
	/**
	 * @return Returns the Objects ownerCode.
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
    
    /**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	
	/**
	 * @return Returns the enabledOption.
	 */
	public Integer getEnabledOption() {
		return enabledOption;
	}
	
	/**
	 * @return Returns the fromStr.
	 */
	public String getFromStr() {
		return fromStr;
	}
	
	/**
	 * @return Returns the largeImageCode.
	 */
	public Long getLargeImageCode() {
		return largeImageCode;
	}
	
	/**
	 * @return Returns the largeImageName.
	 */
	public String getLargeImageName() {
		return largeImageName;
	}
	
	/**
	 * @return Returns the mediumImageCode.
	 */
	public Long getMediumImageCode() {
		return mediumImageCode;
	}
	
	/**
	 * @return Returns the mediumImageName.
	 */
	public String getMediumImageName() {
		return mediumImageName;
	}
	
	/**
	 * @return Returns the thumbnailImageCode.
	 */
	public Long getThumbnailImageCode() {
		return thumbnailImageCode;
	}
	
	/**
	 * @return Returns the thumbnailImageName.
	 */
	public String getThumbnailImageName() {
		return thumbnailImageName;
	}
	
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return Returns the toStr.
	 */
	public String getToStr() {
		return toStr;
	}
	
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * @param enabledOption The enabledOption to set.
	 */
	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}
	
	/**
	 * @param fromStr The fromStr to set.
	 */
	public void setFromStr(String fromStr) {
		this.fromStr = fromStr;
	}
	
	/**
	 * @param largeImageCode The largeImageCode to set.
	 */
	public void setLargeImageCode(Long largeImageCode) {
		this.largeImageCode = largeImageCode;
	}
	
	/**
	 * @param largeImageName The largeImageName to set.
	 */
	public void setLargeImageName(String largeImageName) {
		this.largeImageName = largeImageName;
	}
	
	/**
	 * @param mediumImageCode The mediumImageCode to set.
	 */
	public void setMediumImageCode(Long mediumImageCode) {
		this.mediumImageCode = mediumImageCode;
	}
	
	/**
	 * @param mediumImageName The mediumImageName to set.
	 */
	public void setMediumImageName(String mediumImageName) {
		this.mediumImageName = mediumImageName;
	}
	
	/**
	 * @param thumbnailImageCode The thumbnailImageCode to set.
	 */
	public void setThumbnailImageCode(Long thumbnailImageCode) {
		this.thumbnailImageCode = thumbnailImageCode;
	}
	
	/**
	 * @param thumbnailImageName The thumbnailImageName to set.
	 */
	public void setThumbnailImageName(String thumbnailImageName) {
		this.thumbnailImageName = thumbnailImageName;
	}
	
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @param toStr The toStr to set.
	 */
	public void setToStr(String toStr) {
		this.toStr = toStr;
	}
	
	public Calendar getFrom() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getFromStr() != null && this.getFromStr().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getFromStr(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
    
	public void setFrom(Calendar calendar) {
		if ( calendar != null )
			this.fromStr = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}
	
	public Calendar getTo() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getToStr() != null && this.getToStr().length() > 0 ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getToStr(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setTo(Calendar calendar) {
		if ( calendar != null )
			this.toStr = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}	
}//end class

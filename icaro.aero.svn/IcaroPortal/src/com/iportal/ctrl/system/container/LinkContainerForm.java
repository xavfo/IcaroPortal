/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.container;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
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
public class LinkContainerForm extends BasePortalForm {
		
	private static final long serialVersionUID = -3211555521532769957L;
	
	private String title;
	
	private String url;
	
	private String description;
	
	private Boolean isEnabled;
	
	private Integer enabledOption;
	
	private Long typeCode;
	
	private Long categoryCode;
		
	private String fromDate;
	
	private String toDate;

	private Long index;
	/**
	 * @return Returns the path.
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param path The path to set.
	 */
	public void setUrl(String path) {
		this.url = path;
	}


	/**
     * Creates a new instance of documentForm
     */
    public LinkContainerForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);                    
        title = null;
    	url = null;
    	description = null;
    	isEnabled = Globals.TRUE;
    	enabledOption = null;
    	typeCode = null;
    	categoryCode = null;
    	fromDate = null;
    	toDate = null;
    } 
    
    public Date getFrom() {
    	
    	Date date = null;
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			date = DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT);
		}
		return date;
	}
    
	public void setFrom(Date date) {
		if ( date != null ) {
			this.fromDate = DateFormatUtils.format(date, Constants.DATE_FORMAT);
		}
	}

	public Date getTo() {
		Date date = null;
		if ( this.getToDate() != null && this.getToDate().length() > 0 ) { 
			date =   DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT);
		}
		return date;
	}
	
	public void setTo(Date date) {
		if ( date != null ) {
			this.toDate = DateFormatUtils.format(date, Constants.DATE_FORMAT);
		}
	}


	public Long getCategoryCode() {
		return categoryCode;
	}


	public String getDescription() {
		return description;
	}


	public Integer getEnabledOption() {
		return enabledOption;
	}


	public String getFromDate() {
		return fromDate;
	}


	public Boolean getIsEnabled() {
		return isEnabled;
	}


	public String getTitle() {
		return title;
	}


	public String getToDate() {
		return toDate;
	}


	public Long getTypeCode() {
		return typeCode;
	}


	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public void setTypeCode(Long typeCode) {
		this.typeCode = typeCode;
	}


	public Long getIndex() {
		return index;
	}


	public void setIndex(Long index) {
		this.index = index;
	}

	
}//end class

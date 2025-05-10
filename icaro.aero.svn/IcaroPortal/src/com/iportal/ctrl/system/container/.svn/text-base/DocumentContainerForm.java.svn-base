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
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 *  NewsForm para recoger información de noticias
 * 
 * @author  YAGE pajaro(se paso cambiando los nombres de autores sin hace nada)
 * @version 1.1
 *
 */
public class DocumentContainerForm extends BaseForm {
		
	private static final long serialVersionUID = -3211555521532769957L;

	private String title;
	
	private Boolean isEnabled;
	
	private String description;
	
	private String fromDate;
	
	private String toDate;	

	private String keywords;
	
	private String path;
	
	private Long categoryCode;
	
	private Long docTypeCode;
	
	private Long displayModeCode;
	
	private Integer enabledOption;
	
	private Long ownerCode;
	


	/**
     * Creates a new instance of documentForm
     */
    public DocumentContainerForm() {
        super();
    }


    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);                    
    	this.docTypeCode  = null;
    	clear();
    } 
    
    public void clear() {
        this.title        = null;
    	this.isEnabled    = Globals.TRUE;
    	this.description  = null;
    	this.fromDate     = null;
    	this.toDate       = null;	
    	this.keywords     = null;
    	this.path         = null;
    	this.categoryCode = null;
    	this.enabledOption   = null;
    	this.displayModeCode = null;
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


	/**
	 * @return Returns the categoryCode.
	 */
	public Long getCategoryCode() {
		return categoryCode;
	}


	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @return Returns the displayModeCode.
	 */
	public Long getDisplayModeCode() {
		return displayModeCode;
	}


	/**
	 * @return Returns the docTypeCode.
	 */
	public Long getDocTypeCode() {
		return docTypeCode;
	}


	/**
	 * @return Returns the enabledOption.
	 */
	public Integer getEnabledOption() {
		return enabledOption;
	}


	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}


	/**
	 * @return Returns the isEnabled.
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}


	/**
	 * @return Returns the keyWords.
	 */
	public String getKeywords() {
		return keywords;
	}


	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}


	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}


	/**
	 * @param categoryCode The categoryCode to set.
	 */
	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}


	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @param displayModeCode The displayModeCode to set.
	 */
	public void setDisplayModeCode(Long displayModeCode) {
		this.displayModeCode = displayModeCode;
	}


	/**
	 * @param docTypeCode The docTypeCode to set.
	 */
	public void setDocTypeCode(Long docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	/**
	 * @param enabledOption The enabledOption to set.
	 */
	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}

	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	/**
	 * @param keyWords The keyWords to set.
	 */
	public void setKeywords(String keyWords) {
		this.keywords = keyWords;
	}


	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}


	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param toDate The toDate to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
    
	
}

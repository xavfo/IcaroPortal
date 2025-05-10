/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.news;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.ctrl.BasePortalForm;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;

/**
 * 
 * 
 * @author  YAGE pajaro
 * @version 1.1
 *
 */
public class NewsForm extends BasePortalForm {
	
	
	private static final long serialVersionUID = 8971258923813006604L;

	private Long code;
	
	private String title;

	private String leadingText;
	
	private String text;
	
	private Long introImageCode;
	
	private Long mainImageCode;
	
	private String introImageName;
	
	private String mainImageName;
	
	private Boolean isEnabled;
	
	private Boolean isLocalInfo;
	
	private Boolean publishHome;
	
	
	private Long categoryCode;	
	
	private Long brandCode;
	
	private String fromDate;
	
	private String toDate;
    
    private String creationDate;
	
	private Integer enabledOption;
	
	private Integer localInfoOption;
	
	private Long index;
	
	/**
     * Creates a new instance of newsForm
     */
    public NewsForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    	
        super.reset(arg0, arg1);
        categoryCode	= null;             
    	title 			= null;
    	leadingText		= null;
    	text 			= null;
    	fromDate		= null;
    	toDate			= null;
        creationDate    = null;
    	introImageCode 		= null;
        mainImageCode 		= null; 
        introImageName 		= null;
        mainImageName 		= null;     
        isEnabled		= Globals.TRUE;
        enabledOption   = null;
        isLocalInfo		= Globals.TRUE;
        localInfoOption = null;
        publishHome     = Globals.FALSE;
        brandCode		= null;
    } 
    
    public Calendar getFrom() {
		Calendar date = null;
		if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) { 
			date = Calendar.getInstance();
			date.setTime(DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT));
			date.getTime();
		}
		return date;
	}
    
    public void setFrom(Calendar date) {
		if ( date != null )
			this.setFromDate(DateFormatUtils.format(date.getTime(), Constants.DATE_FORMAT));
			 
	}
    
    public Calendar getTo() {
		Calendar date = null;
		if ( this.getToDate() != null && this.getToDate().length() > 0  ) { 
			date = Calendar.getInstance();
			date.setTime(DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT));
			date.getTime();
		}
		return date;
	}
    
    public void setTo(Calendar date) {
		if ( date != null )
			this.setToDate(DateFormatUtils.format(date.getTime(), Constants.DATE_FORMAT));
			 
	}
    

    public Calendar getCreation() {
        Calendar date = null;
        if ( this.getCreationDate() != null && this.getCreationDate().length() > 0  ) { 
            date = Calendar.getInstance();
            date.setTime(DateFormatUtils.parseToDate(this.getCreationDate(), Constants.DATE_FORMAT));
            date.getTime();
        }
        return date;
    }
    
    public void setCreation(Calendar date) {
        if ( date != null )
            this.setCreationDate(DateFormatUtils.format(date.getTime(), Constants.DATE_FORMAT));
             
    }


	public Long getCategoryCode() {
		return categoryCode;
	}


	public Long getCode() {
		return code;
	}


	public Integer getEnabledOption() {
		return enabledOption;
	}


	public String getFromDate() {
		return fromDate;
	}


	public Long getIntroImageCode() {
		return introImageCode;
	}


	public String getIntroImageName() {
		return introImageName;
	}


	public Boolean getIsEnabled() {
		return isEnabled;
	}


	public Boolean getIsLocalInfo() {
		return isLocalInfo;
	}


	public String getLeadingText() {
		return leadingText;
	}


	public Integer getLocalInfoOption() {
		return localInfoOption;
	}


	public Long getMainImageCode() {
		return mainImageCode;
	}


	public String getMainImageName() {
		return mainImageName;
	}


	public Boolean getPublishHome() {
		return publishHome;
	}


	public String getText() {
		return text;
	}


	public String getTitle() {
		return title;
	}


	public String getToDate() {
		return toDate;
	}

    public String getCreationDate() {
        return creationDate;
    }
    
   

	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}


	public void setCode(Long code) {
		this.code = code;
	}


	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

	public void setIntroImageCode(Long introImageCode) {
		this.introImageCode = introImageCode;
	}


	public void setIntroImageName(String introImageName) {
		this.introImageName = introImageName;
	}


	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public void setIsLocalInfo(Boolean isLocalInfo) {
		this.isLocalInfo = isLocalInfo;
	}


	public void setLeadingText(String leadingText) {
		this.leadingText = leadingText;
	}


	public void setLocalInfoOption(Integer localInfoOption) {
		this.localInfoOption = localInfoOption;
	}


	public void setMainImageCode(Long mainImageCode) {
		this.mainImageCode = mainImageCode;
	}


	public void setMainImageName(String mainImageName) {
		this.mainImageName = mainImageName;
	}


	public void setPublishHome(Boolean publishHome) {
		this.publishHome = publishHome;
	}


	public void setText(String text) {
		this.text = text;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public Long getIndex() {
		return index;
	}


	public void setIndex(Long index) {
		this.index = index;
	}


	public Long getBrandCode() {
		return brandCode;
	}


	public void setBrandCode(Long brandCode) {
		this.brandCode = brandCode;
	}
    
	
}//end class

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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * Formulario de Evento.
 * 
 * @author  YAGE (andresg)
 * @version 1.0
 */
public class EventForm extends BaseForm {
	
	private static final long serialVersionUID = -1857525314176392204L;

	private Boolean enabled;
	
	private Integer enabledOption;
	
	private String title;

	private String summary;
	
	private String description;
	
	private String keyWords;

	private String subject;
	
	private String host;
	
	private Boolean main;
	
	private Boolean seminary;
	
	private Integer seminaryOption;
	
	private String audience;

	private Long introImageCode;
	
	private String introImageName;
    
    private Long mainImageCode;
    
    private String mainImageName;

    private Long categoryCode;

    private Long languageCode;
    
    private String fromDate;  
	
	private String toDate;	
	
	private Long cityCode;
	
	private String cityName;
	
	private Long countryCode;
	
	private String countryName;
	
	private Long index;
    
        
    /**
     * Creates a new instance of SysModuleForm
     */
    public EventForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.enabled = Globals.TRUE;
        this.enabledOption = null;
        this.title = null;
        this.summary = null;
        this.description = null;
        this.subject = null;
        this.host = null;
        this.main = Globals.TRUE;
        this.audience = null;
        this.introImageCode = null;
        this.introImageName = null;
        this.mainImageCode = null;
        this.mainImageName = null;    
        this.categoryCode = null;
        this.languageCode = null;	
        this.fromDate = null;
        this.toDate = null;
        this.seminary = Globals.FALSE;
        this.seminaryOption = null;
        this.cityCode = null;
		this.cityName = null;
		this.countryCode = null;
		this.countryName = null;
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


	public String getAudience() {
		return audience;
	}


	public Long getCategoryCode() {
		return categoryCode;
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


	public String getDescription() {
		return description;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public Integer getEnabledOption() {
		return enabledOption;
	}


	public String getFromDate() {
		return fromDate;
	}


	public String getHost() {
		return host;
	}


	public Long getIntroImageCode() {
		return introImageCode;
	}


	public String getIntroImageName() {
		return introImageName;
	}


	public String getKeyWords() {
		return keyWords;
	}


	public Long getLanguageCode() {
		return languageCode;
	}


	public Boolean getMain() {
		return main;
	}


	public Long getMainImageCode() {
		return mainImageCode;
	}


	public String getMainImageName() {
		return mainImageName;
	}


	public Boolean getSeminary() {
		return seminary;
	}


	public Integer getSeminaryOption() {
		return seminaryOption;
	}


	public String getSubject() {
		return subject;
	}


	public String getSummary() {
		return summary;
	}


	public String getTitle() {
		return title;
	}


	public String getToDate() {
		return toDate;
	}


	public void setAudience(String audience) {
		this.audience = audience;
	}


	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
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


	public void setDescription(String description) {
		this.description = description;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public void setIntroImageCode(Long introImageCode) {
		this.introImageCode = introImageCode;
	}


	public void setIntroImageName(String introImageName) {
		this.introImageName = introImageName;
	}


	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}


	public void setLanguageCode(Long languageCode) {
		this.languageCode = languageCode;
	}


	public void setMain(Boolean main) {
		this.main = main;
	}


	public void setMainImageCode(Long mainImageCode) {
		this.mainImageCode = mainImageCode;
	}


	public void setMainImageName(String mainImageName) {
		this.mainImageName = mainImageName;
	}


	public void setSeminary(Boolean seminary) {
		this.seminary = seminary;
	}


	public void setSeminaryOption(Integer seminaryOption) {
		this.seminaryOption = seminaryOption;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public void setSummary(String summary) {
		this.summary = summary;
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

}

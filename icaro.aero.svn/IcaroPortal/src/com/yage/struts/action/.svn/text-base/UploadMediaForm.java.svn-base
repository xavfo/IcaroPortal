package com.yage.struts.action;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.model.Language;

public class UploadMediaForm extends UploadForm {

	private static final long serialVersionUID = 1653852463552004428L;
	
	private Integer width;
	
	private Integer height;
	
	private String mediaName;
	
	private String uploadPath;
	
	private Integer mediaType;
	
	private Long categoryCode;
	
	private String propertyCode;
	
	private String propertyName;
	
	private String propertyPath;
	
	private String url;
	
	public UploadMediaForm() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		ActionErrors errors = super.validate(arg0, arg1);
		
		if ( !(this.getCode()!= null && this.getCode()>0) 
				&& !(this.getFile()!= null && this.getFile().getFileName().length()>0) ){
				Language lang = (Language)arg1.getSession().getAttribute(Constants.LANGUAGE_KEY);
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.file", lang.getLocale())));
		}
		return errors;
	}
	
	
	/**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.width = null;
        this.height = null; 
        this.url = null;
        this.categoryCode = null;
    	this.propertyCode = null;
    	this.propertyName = null;    	
    	this.propertyPath = null;
    	
    	
    }
    
    

	/**
	 * @return Returns the height.
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height The height to set.
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return Returns the imageCategoryCode.
	 */
	public Long getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param imageCategorycode The imageCategorycode to set.
	 */
	public void setCategoryCode(Long imageCategoryCode) {
		this.categoryCode = imageCategoryCode;
	}

	/**
	 * @return Returns the propertyCode.
	 */
	public String getPropertyCode() {
		return propertyCode;
	}

	/**
	 * @param propertyCode The propertyCode to set.
	 */
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	/**
	 * @return Returns the propertyName.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName The propertyName to set.
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return Returns the propertyPath.
	 */
	public String getPropertyPath() {
		return propertyPath;
	}

	/**
	 * @param propertyPath The propertyPath to set.
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	/**
	 * @return Returns the width.
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width The width to set.
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the mediaType.
	 */
	public Integer getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType The mediaType to set.
	 */
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return Returns the uploadPath.
	 */
	public String getUploadPath() {
		return uploadPath;
	}
	/**
	 * @param uploadPath The uploadPath to set.
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	/**
	 * @return Returns the mediaName.
	 */
	public String getMediaName() {
		return mediaName;
	}
	/**
	 * @param mediaName The mediaName to set.
	 */
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	
	public Boolean getIsFlash() {
		boolean isFlash = false; 
		if ( uploadPath != null && uploadPath.length()>0 ){
			String ext = uploadPath.substring(uploadPath.lastIndexOf('.')+1);
			if (ext.equals("swf"))
				isFlash = true;
		}			
		return new Boolean(isFlash);
	}
	
	public String getSid() {
		if (this.getCode() == null)
			return null;
		Calendar now = new GregorianCalendar();
		StringBuffer str = new StringBuffer(this.getCode().toString());
		str.append(now.getTimeInMillis());
		return str.toString();
	}
}
/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.ctrl.BasePortalForm;
import com.iportal.model.Language;


/**
 * comment BulletinForm
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class BulletinForm extends BasePortalForm {
	
	private static final long serialVersionUID = 6590481008369891758L;

	private String subject;
	
	private String title;
	
	private String image;
	
	private String content;
	
	private Long topicCode;	
    /**
     * Creates a new instance of FaqCategoryForm
     */
    public BulletinForm() {
        super();
    }
    
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		ActionErrors errors = super.validate(arg0, arg1);
		
		if ( !(this.content!= null && this.content.length()>0) ){
			Language lang = (Language)arg1.getSession().getAttribute(Constants.LANGUAGE_KEY);
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.content", lang.getLocale())));
		}
		return errors;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        subject = null;
    	title = null;
    	image = null;
    	content = null;
    	topicCode = null;
    }

	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @return Returns the image.
	 */
	public String getImage() {
		return image;
	}


	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return Returns the topicCode.
	 */
	public Long getTopicCode() {
		return topicCode;
	}
	
	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @param image The image to set.
	 */
	public void setImage(String image) {
		this.image = image;
	}


	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}



	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param topicCode The topicCode to set.
	 */
	public void setTopicCode(Long topicCode) {
		this.topicCode = topicCode;
	}

 
}

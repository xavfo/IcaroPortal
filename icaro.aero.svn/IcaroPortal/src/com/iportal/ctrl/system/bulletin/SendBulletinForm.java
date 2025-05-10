/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.bulletin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.ctrl.BasePortalForm;


/**
 * comment SendBulletinForm
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class SendBulletinForm extends BasePortalForm {
	
	private static final long serialVersionUID = 6590481008369891758L;

	private Long bulletinCode;
	
	private Long topicCode;
	
    /**
     * Creates a new instance of FaqCategoryForm
     */
    public SendBulletinForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        bulletinCode = null;   
        topicCode = null;
    }


	public Long getBulletinCode() {
		return bulletinCode;
	}


	public Long getTopicCode() {
		return topicCode;
	}


	public void setBulletinCode(Long bulletinCode) {
		this.bulletinCode = bulletinCode;
	}


	public void setTopicCode(Long topicCode) {
		this.topicCode = topicCode;
	}
    
}

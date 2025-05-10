/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.model.Language;
import com.yage.struts.action.BaseForm;

/**
 * BasePortalForm
 * 
 * @author YAGE (pablo)
 * @version 1.0
 *
 */

public class BasePortalForm extends BaseForm {
	
	private static final long serialVersionUID = 2115483427835833153L;

	protected Language language;

    private Long languageCode;
    
    private Long menuCode;
    
    private Long sectionCode;
    
    
    /**
     * Creates a new instance of BasePortalForm
     */
    public BasePortalForm() {
        super();
    }
    
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		HttpSession session = request.getSession();
		language = (Language) session.getAttribute( Constants.LANGUAGE_KEY );
		if (language == null) {
		    LanguageUtils utils = new LanguageUtils();
		    //language = utils.getLanguage(request);
		    language = utils.getLanguage(Constants.LANGUAGE_SPANISH);
		}
	    languageCode = language.getCode();
	    
	    menuCode 	= null;
	    sectionCode = null;
	}
	
	
    /**
     * @return Returns the languageCode.
     */
    public Long getLanguageCode() {
        return languageCode;
    }
    
    /**
     * @param languageCode The languageCode to set.
     */
    public void setLanguageCode(Long languageCode) {
        this.languageCode = languageCode;
    }
    /**
     * @return Returns the menuCode.
     */
    public Long getMenuCode() {
        return menuCode;
    }
    /**
     * @param menuCode The menuCode to set.
     */
    public void setMenuCode(Long menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * @return Returns the sectionCode.
     */
    public Long getSectionCode() {
        return sectionCode;
    }
    /**
     * @param sectionCode The sectionCode to set.
     */
    public void setSectionCode(Long sectionCode) {
        this.sectionCode = sectionCode;
    }
    
}

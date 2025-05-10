/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.search;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.ctrl.BasePortalForm;
import com.iportal.model.Language;
import com.yage.Globals;

/**
 * 
 * @author  YAGE mcnovillo
 * @version 1.1
 *
 */
public class SearchGeneralForm extends BasePortalForm {
	
	
	/* (non-Javadoc)
     * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = super.validate(mapping, request); 
        if( null == searchSections && (null == searchAll || false == searchAll.booleanValue() ) ) {
           Language lang = (Language)request.getSession().getAttribute(Constants.LANGUAGE_KEY);
           errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.searchSection", lang.getLocale())));
        }
        return errors;
    }

    private static final long serialVersionUID = 8971258923813006604L;

	public String[] SEARCH_SECTIONS = {"product","institutional","provider","brand"};
    
	private String text;
	
	private Boolean searchAll;
	
	private String[] searchSections;
	
	/**
     * Creates a new instance of newsForm
     */
    public SearchGeneralForm() {
        super();
    }


    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    	
        super.reset(arg0, arg1);
        text = null;
        searchAll = Globals.FALSE;
        searchSections = null;
    }


    /**
     * @return the searchAll
     */
    public Boolean getSearchAll() {
        return searchAll;
    }


    /**
     * @param searchAll the searchAll to set
     */
    public void setSearchAll(Boolean searchAll) {
        this.searchAll = searchAll;
    }


    /**
     * @return the searchSections
     */
    public String[] getSearchSections() {
        return searchSections;
    }


    /**
     * @param searchSections the searchSections to set
     */
    public void setSearchSections(String[] searchSections) {
        this.searchSections = searchSections;
    }


    /**
     * @return the text
     */
    public String getText() {
        return text;
    }


    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIncludeProduct() {
        return searchAll || (null != searchSections && Arrays.asList(searchSections).indexOf("product")>=0);
    }
    
    public Boolean getIncludeInstitutional() {
        return searchAll || (null != searchSections && Arrays.asList(searchSections).indexOf("institutional")>=0);
    }
    
    public Boolean getIncludeProvider() {
        return searchAll || (null != searchSections && Arrays.asList(searchSections).indexOf("provider")>=0);
    }
    
    public Boolean getIncludeBrand() {
        return searchAll || (null != searchSections && Arrays.asList(searchSections).indexOf("brand")>=0);
    }
    	
}//end class

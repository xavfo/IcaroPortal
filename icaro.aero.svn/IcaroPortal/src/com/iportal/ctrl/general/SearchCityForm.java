/*
 * Created on 25/01/2005
 * Desarrollado por YAGE @2005
 */
package com.iportal.ctrl.general;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.SearchForm;

/**
 * @author mcnovillo
 * AlegroAdmin 2005
 */
public class SearchCityForm extends SearchForm {

    private static final long serialVersionUID = 7542261311097180730L;

    protected Long countryCode;

    /**
     * @return Returns the countryCode.
     */
    public Long getCountryCode() {
        return countryCode;
    }
    /**
     * @param countryCode The countryCode to set.
     */
    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }
    /**
     *
     */
    public SearchCityForm() {
        super();
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.countryCode  = null;
    }


}

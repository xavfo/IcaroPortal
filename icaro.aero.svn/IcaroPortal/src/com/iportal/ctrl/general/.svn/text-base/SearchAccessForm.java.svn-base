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
public class SearchAccessForm extends SearchForm {

    private static final long serialVersionUID = 3589974628542052291L;

    protected String relatedName;

    /**
     *
     */
    public SearchAccessForm() {
        super();
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.relatedName  = null;
    }

    /**
     * @return Returns the relatedName.
     */
    public String getRelatedName() {
        return relatedName;
    }

    /**
     * @param relatedName The relatedName to set.
     */
    public void setRelatedName(String relatedName) {
        this.relatedName = relatedName;
    }


}

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
public class SearchPortletForm extends SearchForm {

    private static final long serialVersionUID = 6148163780934937496L;

    protected Long alignOption;

    protected String width;

    protected String height;


    /**
     *
     */
    public SearchPortletForm() {
        super();
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        width = "200";
        height = "200";
    }

    /**
     * @return Returns the alignOption.
     */
    public Long getAlignOption() {
        return alignOption;
    }

    /**
     * @param alignOption The alignOption to set.
     */
    public void setAlignOption(Long alignOption) {
        this.alignOption = alignOption;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}

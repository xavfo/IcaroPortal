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
public class GeneralSearchForm extends SearchForm {

    private static final long serialVersionUID = 3776519914977262640L;

    protected Long option;

    private Long formIndex;

    private String controlCode;

    private String controlLevel;

    private String controlDescription;

    private String className;


    /**
     *
     */
    public GeneralSearchForm() {
        super();
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.option  = null;
        this.formIndex  = null;
        this.controlCode  = null;
        this.controlLevel = null;
        this.controlDescription = null;
        this.className = null;
    }



    /**
     * @return Returns the className.
     */
    public String getClassName() {
        return className;
    }

    public String getControlCode() {
        return controlCode;
    }

    public String getControlDescription() {
        return controlDescription;
    }

    public String getControlLevel() {
        return controlLevel;
    }

    public Long getFormIndex() {
        return formIndex;
    }

    public Long getOption() {
        return option;
    }



    /**
     * @param className The className to set.
     */
    public void setClassName(String className) {

        this.className = className;
    }

    public void setControlCode(String controlCode) {
        this.controlCode = controlCode;
    }

    public void setControlDescription(String controlDescription) {
        this.controlDescription = controlDescription;
    }

    public void setControlLevel(String controlLevel) {
        this.controlLevel = controlLevel;
    }

    public void setFormIndex(Long formIndex) {
        this.formIndex = formIndex;
    }

    public void setOption(Long option) {
        this.option = option;
    }


}

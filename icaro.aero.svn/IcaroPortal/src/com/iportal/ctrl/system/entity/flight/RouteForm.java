/*
 * Created 30/03/2007
 *    RouteForm.java
 */
package com.iportal.ctrl.system.entity.flight;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author YAGE(hernan)
 *
 */
public class RouteForm extends BaseForm {

    private static final long serialVersionUID = 5661836347570450075L;

    private String name;

    private Boolean enabled;

    private String  notes;

    private Integer enabledOption;

    private String rateNotes;

    private String rateDoc;



    /**
     *
     */
    public RouteForm() {
        super();
    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.name = null;
        this.enabled = null;
        this.notes = null;
        this.enabledOption=null;
        this.rateDoc=null;
        this.rateNotes=null;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Integer getEnabledOption() {
        return enabledOption;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setEnabledOption(Integer enabledOption) {
        this.enabledOption = enabledOption;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return Returns the rateDoc.
     */
    public String getRateDoc() {
        return rateDoc;
    }

    /**
     * @param rateDoc The rateDoc to set.
     */
    public void setRateDoc(String rateDoc) {
        this.rateDoc = rateDoc;
    }

    /**
     * @return Returns the rateNotes.
     */
    public String getRateNotes() {
        return rateNotes;
    }

    /**
     * @param rateNotes The rateNotes to set.
     */
    public void setRateNotes(String rateNotes) {
        this.rateNotes = rateNotes;
    }










}

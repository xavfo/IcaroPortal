/*
 * Created on 25/01/2005
 * Desarrollado por YAGE @2005
 */
package com.yage.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;

/**
 * @author consultoriayage
 * AlegroAdmin 2005
 */
public class SearchForm extends PopUpForm {

    private static final long serialVersionUID = -1217610281934299201L;

    protected String[] properties;

    protected String[] labels;

    protected String label;

    protected String name;

    protected Long relatedCode;




    /**
     *
     */
    public SearchForm() {
        super();
    }

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.properties  = null;
        this.labels      = null;
        this.label       = null;
        this.name        = null;
        this.relatedCode = null;
        this.setOrderField("name");
        this.setOrderAsc(Globals.TRUE);


    }


    /**
     * @return Returns the label.
     */
    public String getLabel() {
        return label;
    }
    /**
     * @return Returns the labels.
     */
    public String[] getLabels() {
        return labels;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the properties.
     */
    public String[] getProperties() {
        return properties;
    }
    /**
     * @return Returns the relatedCode.
     */
    public Long getRelatedCode() {
        return relatedCode;
    }
    /**
     * @param label The label to set.
     */
    public void setLabel(String label) {
        this.label = label;
    }
    /**
     * @param labels The labels to set.
     */
    public void setLabels(String[] labels) {
        this.labels = labels;
    }


    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param properties The properties to set.
     */
    public void setProperties(String[] properties) {
        this.properties = properties;
    }
    /**
     * @param relatedCode The relatedCode to set.
     */
    public void setRelatedCode(Long relatedCode) {
        this.relatedCode = relatedCode;
    }
}

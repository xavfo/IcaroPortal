/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;

/**
 * comment PopUpForm
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class PopUpForm extends BaseForm {

	private static final long serialVersionUID = -17522881782380182L;

	private String parent;
    
    private String form;
    
    private String property;
    
    private String value;
    
    private String[] values;
    
    private Integer index;
        
    /**
     * Crea una nueva instnacia de PopUpForm
     */
    public PopUpForm() {
        super();
    }
    
    
    
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        parent = null;
        form = null;
        property = null;
        value = null;
        values = new String[0];
        index = Globals.DEFAULT_PROPERTY_INDEX;
    }
    
    
    
    /**
     * @return Returns the form.
     */
    public String getForm() {
        return form;
    }
    
    /**
     * @return Returns the index.
     */
    public Integer getIndex() {
        return index;
    }
    
    /**
     * @return Returns the parent.
     */
    public String getParent() {
        return parent;
    }
    
    /**
     * @return Returns the property.
     */
    public String getProperty() {
        return property;
    }
    
    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @return Returns the values.
     */
    public String[] getValues() {
        return values;
    }
    
    /**
     * @param form The form to set.
     */
    public void setForm(String form) {
        this.form = form;
    }
    
    /**
     * @param index The index to set.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
    
    /**
     * @param property The property to set.
     */
    public void setProperty(String property) {
        this.property = property;
    }
    
    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * @param values The values to set.
     */
    public void setValues(String[] values) {
        this.values = values;
    }
}

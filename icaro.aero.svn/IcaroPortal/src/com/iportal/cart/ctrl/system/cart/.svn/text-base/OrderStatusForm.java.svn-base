/*
 * Created Jan 15, 2007
 *    OrderStatusForm.java
 */
package com.iportal.cart.ctrl.system.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * Forma para recoger datos de estados de ordenes de Pago.
 *
 * @author hernan
 *
 */
public class OrderStatusForm extends BaseForm {

    private static final long serialVersionUID = -5854691969004897393L;

    private String name;

    private String notes;

    /**
     *
     */
    public OrderStatusForm() {
        super();
        this.clear();
    }

    public void clear () {
        this.name  = null;
        this.notes = null;
    }

    /* (non-Javadoc)
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the notes.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param notes The notes to set.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }




}

/*
 * Created Jan 15, 2007
 *    PaymentStatusForm.java
 */
package com.iportal.cart.ctrl.system.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author hernan
 *
 */
public class PaymentStatusForm extends BaseForm {

    private static final long serialVersionUID = -5431473816147084535L;

    private String name;

    private String externalCode;

    private String message;

    /**
     *
     */
    public PaymentStatusForm() {
        super();
        this.clear();
    }

    public void clear () {
        this.name  = null;
        this.externalCode  = null;
        this.message  = null;

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
     * @return Returns the externalCode.
     */
    public String getExternalCode() {
        return externalCode;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param externalCode The externalCode to set.
     */
    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }





}

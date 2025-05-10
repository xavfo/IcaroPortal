/*
 * Created 22/01/2007
 *	OrderForm.java
 */
package com.iportal.cart.ctrl.system.cart;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;


/**
 * @author hernan
 * 
 */
public class TaxTypeForm extends BaseForm {

    private static final long serialVersionUID = 3075445008739064416L;

    private String Name;

    private String[] taxCodes;


    public void clear() {
        this.Name = null;

        this.taxCodes = null;

        this.listItems = Globals.TRUE;
    }


    /**
     * 
     */
    public TaxTypeForm() {
        super();
        this.clear();
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }


    public String[] getTaxCodes() {
        return taxCodes;
    }


    public void setTaxCodes(String[] taxCodes) {
        this.taxCodes = taxCodes;
    }


    public String getName() {
        return this.Name;
    }


    public void setName(String Name) {
        this.Name = Name;
    }

}

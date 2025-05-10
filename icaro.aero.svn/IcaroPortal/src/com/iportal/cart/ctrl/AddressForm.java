/*******************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 ******************************************************************************/

package com.iportal.cart.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

public abstract class AddressForm extends BaseForm {
	
    /**
     * 
     */
    private static final long serialVersionUID = -5123019157245105072L;
    private String cityName;
    private String addressName;
    private String phone;
    private String reference;
    private String stateName;
    private String street1;
    private String street2;
    
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		// Validate an attribute named "xxx"
		//if( getXXX() == null || getXXX().length() == 0 )
		//	errors.add("xxx",new ActionMessage("errors.required","xxx"));
		return errors;
	}
		
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
        this.cityName = null;
        this.addressName = null;
        this.phone = null;
        this.reference = null;
        this.stateName = null;
        this.street1 = null;
        this.street2 = null;
	}

    public String getCityName() {
        return cityName;
    }

    /**
     * @param city the city to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the name
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * @param name the name to set
     */
    public void setAddressName(String name) {
        this.addressName = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the state
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param state the state to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the street1
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * @param street1 the street1 to set
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    /**
     * @return the street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * @param street2 the street2 to set
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }
		
	
}
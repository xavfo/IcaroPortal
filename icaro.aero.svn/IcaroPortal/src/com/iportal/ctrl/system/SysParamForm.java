/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment CityForm
 * 
 * @author  YAGE 
 * @version 1.1 reescrito para Carro 
 * 
 *
 */
public class SysParamForm extends BaseForm {

    private static final long serialVersionUID = 6427543690328549074L;

    private String name;
    
    private String value;
    
    private String type;
    
    /**
     * Creates a new instance of SysRoleForm
     */
    public SysParamForm() {
        super();

        this.listItems = Globals.TRUE;
    }
    
    
    /**
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        this.name = null;
        this.value = null;
        this.type = "java.lang.String";

        this.listItems = Globals.TRUE;
    }


    /**
     * @return - The current name value.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value
     * 
     * @param - The new name value
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return - The current type value
     */
    public String getType() {
        return type;
    }


    /**
     * Sets the type value
     * 
     * @param - The new type value
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * @return - The current value
     */
    public String getValue() {
        return value;
    }


    /**
     *  Sets the value value
     * 
     * @param - The new value
     */
    public void setValue(String value) {
        this.value = value;
    }

}

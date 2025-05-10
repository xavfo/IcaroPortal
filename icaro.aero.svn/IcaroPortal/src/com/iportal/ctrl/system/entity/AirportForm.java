/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.entity;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * @author ferTamayo
 *
 */
public class AirportForm extends BaseForm {

    private static final long serialVersionUID = 824019725344402699L;

    private String name;

    private String iataCode;

    private Long cityCode;

    private Boolean enabled;

    private Integer enabledOption;
    /*
    private String name;



    private String icon;

    private Long typeCode;

    private String description;

    private String email;

    private String administrator;*/


    /**
     * Creates a new instance of AirportForm
     */
    public AirportForm() {
        super();

    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        enabled = Globals.TRUE;
        iataCode=null;
        cityCode=null;
        enabledOption=null;
        //icon = null;
        //typeCode = null;
   }

    /**
     * @return Returns the city.
     */
    public Long getCityCode() {
        return cityCode;
    }

    /**
     * @param city The city to set.
     */
    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return Returns the iataCode.
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * @param iataCode The iataCode to set.
     */
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the enabled.
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return Returns the enabledOption.
     */
    public Integer getEnabledOption() {
        return enabledOption;
    }

    /**
     * @param enabledOption The enabledOption to set.
     */
    public void setEnabledOption(Integer enabledOption) {
        this.enabledOption = enabledOption;
    }

}

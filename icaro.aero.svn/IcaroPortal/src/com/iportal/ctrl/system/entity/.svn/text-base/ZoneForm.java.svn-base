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
 * @author mcnovillo
 *
 */
public class ZoneForm extends BaseForm {

    private static final long serialVersionUID = -2199620378011273501L;

    private Long code;

    private String name;

    private Boolean isEnabled;

    private String icon;

    private Long cityCode;

    private String description;

    /**
     * Creates a new instance of ZoneForm
     */
    public ZoneForm() {
        super();

    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        isEnabled = Globals.TRUE;
        icon = null;
        cityCode = null;
   }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public Long getCityCode() {
        return cityCode;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return Returns the code.
     */
    public Long getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
}

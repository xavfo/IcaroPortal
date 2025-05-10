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
public class CatalogueForm extends BaseForm {

    private static final long serialVersionUID = 625658046017138271L;

    private String name;

    private Boolean enabled;

    private String icon;

    private Long typeCode;

    private String description;

    private String email;

    private String administrator;


    /**
     * Creates a new instance of SysAuditForm
     */
    public CatalogueForm() {
        super();

    }

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        enabled = Globals.TRUE;
        icon = null;
        typeCode = null;
   }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public Long getTypeCode() {
        return typeCode;
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

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

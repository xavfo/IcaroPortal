/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * LanguageForm
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class LanguageForm extends BaseForm {

	private static final long serialVersionUID = -3887831720529781914L;

	private String name;
    
    private Boolean _default;
    
    private Boolean enabled;
    
    private String localeLang;
    
    private String charset;
    
    private String integerFormat;
    
    private String floatFormat;
    
    private String currencyFormat;
    
    private String dateFormat;
    
    private String timeFormat;
    
    private String dateTimeFormat;
    
    private String shortDateFormat;
    
    private String longDateFormat;

    private Long defaultCode;
    
    
    /**
     * Creates a new isntance of LanguageForm
     */
    public LanguageForm() {
        super();
    }
    
    
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        name = null;
        _default = Globals.FALSE;
        enabled = Globals.TRUE;
        localeLang = null;
        charset = null;
        defaultCode = null;
        integerFormat = null;
        floatFormat = null;
        currencyFormat = null;
        dateFormat = null;
        timeFormat = null;
        dateTimeFormat = null;
        shortDateFormat = null;
        longDateFormat = null;
    }
    
    
    /**
     * @return Returns the _default.
     */
    public Boolean getDefault() {
        return _default;
    }
    /**
     * @return Returns the charset.
     */
    public String getCharset() {
        return charset;
    }
    /**
     * @return Returns the currencyFormat.
     */
    public String getCurrencyFormat() {
        return currencyFormat;
    }
    /**
     * @return Returns the dateFormat.
     */
    public String getDateFormat() {
        return dateFormat;
    }
    /**
     * @return Returns the dateTimeFormat.
     */
    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
    /**
     * @return Returns the defaultCode.
     */
    public Long getDefaultCode() {
        return defaultCode;
    }
    /**
     * @return Returns the floatFormat.
     */
    public String getFloatFormat() {
        return floatFormat;
    }
    /**
     * @return Returns the integerFormat.
     */
    public String getIntegerFormat() {
        return integerFormat;
    }
    /**
     * @return Returns the localeLang.
     */
    public String getLocaleLang() {
        return localeLang;
    }
    /**
     * @return Returns the longDateFormat.
     */
    public String getLongDateFormat() {
        return longDateFormat;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the shortDateFormat.
     */
    public String getShortDateFormat() {
        return shortDateFormat;
    }
    /**
     * @return Returns the timeFormat.
     */
    public String getTimeFormat() {
        return timeFormat;
    }
    /**
     * @param _default The _default to set.
     */
    public void setDefault(Boolean _default) {
        this._default = _default;
    }
    /**
     * @param charset The charset to set.
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }
    /**
     * @param currencyFormat The currencyFormat to set.
     */
    public void setCurrencyFormat(String currencyFormat) {
        this.currencyFormat = currencyFormat;
    }
    /**
     * @param dateFormat The dateFormat to set.
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    /**
     * @param dateTimeFormat The dateTimeFormat to set.
     */
    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
    /**
     * @param defaultCode The defaultCode to set.
     */
    public void setDefaultCode(Long defaultCode) {
        this.defaultCode = defaultCode;
    }
    /**
     * @param floatFormat The floatFormat to set.
     */
    public void setFloatFormat(String floatFormat) {
        this.floatFormat = floatFormat;
    }
    /**
     * @param integerFormat The integerFormat to set.
     */
    public void setIntegerFormat(String integerFormat) {
        this.integerFormat = integerFormat;
    }
    /**
     * @param localeLang The localeLang to set.
     */
    public void setLocaleLang(String localeLang) {
        this.localeLang = localeLang;
    }
    /**
     * @param longDateFormat The longDateFormat to set.
     */
    public void setLongDateFormat(String longDateFormat) {
        this.longDateFormat = longDateFormat;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param shortDateFormat The shortDateFormat to set.
     */
    public void setShortDateFormat(String shortDateFormat) {
        this.shortDateFormat = shortDateFormat;
    }
    /**
     * @param timeFormat The timeFormat to set.
     */
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
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
}

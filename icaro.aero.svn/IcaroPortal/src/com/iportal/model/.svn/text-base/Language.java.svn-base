/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yage.Globals;

/**
 * This class persist data to the <code>tb_language</code> database table.
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 */
@Entity
@Table(name="tb_language")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="language",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Language {

    private Long code;
    
    private Boolean _default;
    
    private Boolean enabled;
    
    private String name;
    
    private Locale locale;
    
    private String integerFormat;
    
    private String floatFormat;
    
    private String currencyFormat;
    
    private String dateFormat;
    
    private String timeFormat;
    
    private String dateTimeFormat;
    
    private String shortDateFormat;
    
    private String longDateFormat;
    
    private String charset;
    
    private transient Boolean userDefined;
    
    
    /**
     * Creates a new instance of Language
     */
    public Language() {
        super();
        userDefined = Globals.FALSE;
    }
    @Transient
    public String toString() {
        return name;
    }
    
	/**
	 * Returns if the current user has select to view information
	 * in this language in order to avoid the {@link biz.mindsoft.mindsite.ctrl.security.Language }
	 * to change it.
	 * 
	 * @return userDefined
	 */
    @Transient
	public Boolean getUserDefined() {
		return userDefined;
	}
    
	public void setUserDefined(Boolean userDefined) {
		this.userDefined = userDefined;
	}


    
    @Id
    @Column(name="language_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }
    
    @Column(name="language_default")
    public Boolean getDefault() {
        return _default;
    }
    
    @Column(name="language_enabled")
    public Boolean getEnabled() {
        return enabled;
    }
    
	@Column(name="language_charset")
    public String getCharset() {
        return charset;
    }
    
	@Column(name="language_locale")
    public Locale getLocale() {
        return locale;
    }
    
	@Column(name="language_name")
    public String getName() {
        return name;
    }
    
	@Column(name="language_integer_format")
    public String getIntegerFormat() {
        return integerFormat;
    }
    
	@Column(name="language_float_format")
    public String getFloatFormat() {
        return floatFormat;
    }
    
	@Column(name="language_currency_format")
    public String getCurrencyFormat() {
        return currencyFormat;
    }
    
	@Column(name="language_date_format")
    public String getDateFormat() {
        return dateFormat;
    }
    
	@Column(name="language_time_format")
    public String getTimeFormat() {
        return timeFormat;
    }
    
	@Column(name="language_date_time_format")
    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
    
	@Column(name="language_short_date_format")
    public String getShortDateFormat() {
        return shortDateFormat;
    }
    
	@Column(name="language_long_date_format")
    public String getLongDateFormat() {
        return longDateFormat;
    }
    

    /**
     * @param _default The _default to set.
     */
    public void setDefault(Boolean _default) {
        this._default = _default;
    }
    
    /**
     * @param charSet The charSet to set.
     */
    public void setCharset(String charSet) {
        this.charset = charSet;
    }
    
    /**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }
    
    /**
     * @param iso The iso to set.
     */
    public void setLocale(Locale l) {
        this.locale = l;
    }
    
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
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
     * @param longDateFormat The longDateFormat to set.
     */
    public void setLongDateFormat(String longDateFormat) {
        this.longDateFormat = longDateFormat;
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
     * @param enabled The enabled to set.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

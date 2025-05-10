/*
 * Created Mar 27, 2006
 *	NumberFormatUtils.java
 */
package com.yage.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase de básica para formatear números
 * 
 * @author hernan
 * @version 1.0
 *
 */
public class NumberFormatUtils {

    private Locale locale;

	/**
	 * 
	 */
	public NumberFormatUtils() {
		super();
        this.locale = null;
 
	}
	
    public Locale getLocale() {
        return this.locale;
        
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public String format(double number, String pattern) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(number);
    }
    
    public String format(long number, String pattern) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(number);
    }
    
    public String format(int number, String pattern) {
        return this.format((long)number, pattern);
    }
    
    public String format(float number, String pattern) {
        return this.format((double)number, pattern);
    }
    
    public String format(double number) {
        NumberFormat formatter = this.locale != null ? NumberFormat.getNumberInstance(this.locale): NumberFormat.getNumberInstance();
        return formatter.format(number);
    }

    public String format(long number) {
        NumberFormat formatter = this.locale != null ? NumberFormat.getNumberInstance(this.locale): NumberFormat.getNumberInstance();
        return formatter.format(number);
    }
    
    public String format(int number) {
        return this.format((long)number);
    }
    
    public String format(float number) {
        return this.format((double)number);
    }
    
    public String formatCurrency(double number) {
        NumberFormat formatter = this.locale != null ? NumberFormat.getCurrencyInstance(this.locale): NumberFormat.getCurrencyInstance();
        return formatter.format(number);
    }

    public String formatCurrency(long number) {
        NumberFormat formatter = this.locale != null ? NumberFormat.getCurrencyInstance(this.locale): NumberFormat.getCurrencyInstance();
        return formatter.format(number);
    }

    public String formatCurrency(int number) {
        return this.formatCurrency((long)number);
    }

    public String formatCurrency(float number) {
        return this.formatCurrency((double)number);
    }
    
    public String formatPercent(double number) {
        NumberFormat formatter = this.locale != null ? NumberFormat.getPercentInstance(this.locale): NumberFormat.getPercentInstance();
        return formatter.format(number);
    }

    public String formatPercent(long number) {
        NumberFormat formatter = this.locale != null ? NumberFormat.getPercentInstance(this.locale): NumberFormat.getPercentInstance();
        return formatter.format(number);
    }
    
    public String formatPercent(int number) {
        return this.formatPercent((long) number);
    }

    public String formatPercent(float number) {
        return this.formatPercent((double) number);
    }



}

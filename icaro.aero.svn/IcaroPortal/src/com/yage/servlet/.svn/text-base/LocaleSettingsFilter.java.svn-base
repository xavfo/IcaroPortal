/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * LocaleSettingsFilter
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public abstract class LocaleSettingsFilter implements Filter {

    
    /**
     * The default character encoding to set for requests that pass through
     * this filter.
     */
    protected String encoding;

    /**
     * The default locale to set for requests that pass through
     * this filter.
     */
    protected Locale locale;

    /**
     * The filter configuration object we are associated with.  If this value
     * is null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;

    
    
    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        this.encoding = null;
        this.locale = null;
        this.filterConfig = null;
    }
    
    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // Select and set (if needed) the character encoding to be used
        setLocaleSettings(request, response);

        // Pass control on to the next filter
        chain.doFilter(request, response);
    }
    
    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    	this.filterConfig = filterConfig;
        
    	this.encoding = filterConfig.getInitParameter("defaultEncoding");
        
        String str = filterConfig.getInitParameter("defaultLocale");
        if (str != null && str.length() > 0) {
            locale = new Locale(str);
        } else {
            locale = Locale.getDefault();
        }
    }
    
    
    
    protected abstract void setLocaleSettings(ServletRequest request, ServletResponse response) throws IOException, ServletException;
}

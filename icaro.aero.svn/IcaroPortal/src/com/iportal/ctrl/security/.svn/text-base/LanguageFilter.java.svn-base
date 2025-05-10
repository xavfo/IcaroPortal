/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;

import com.iportal.Constants;
import com.iportal.biz.LanguageUtils;
import com.iportal.model.Language;

/**
 * LocaleSettingsFilter
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class LanguageFilter extends com.yage.servlet.LocaleSettingsFilter {

    /**
     * Sets the language for application
     */
    protected void setLocaleSettings(ServletRequest req, ServletResponse res) 
    throws IOException, ServletException {
    	
    	if ( !(req instanceof HttpServletRequest) ) {
            return;
        }
    	
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        // Gets the Locale stored in session context
        HttpSession session = request.getSession();
        if (session != null) {
            String langHeader = request.getHeader("accept-language");
            
            if (langHeader == null) {
                langHeader = Locale.getDefault().getLanguage();
            } else {
                langHeader = langHeader.substring(0,2);
            }

        	Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
            if (language != null && ((language.getUserDefined().booleanValue()  ))) {
                request.setCharacterEncoding(language.getCharset());
                response.setLocale(language.getLocale());
                //setea la veriable locale de Struts
            	return;
            }
        }
        

        
        LanguageUtils utils = new LanguageUtils();
         //language = utils.getLanguage(request);
        //todo por defecto sera español        
         Language language = utils.getLanguage(Constants.LANGUAGE_SPANISH);
        if (language == null) {
            throw new ServletException("No language defined.");
        }
        
        request.setCharacterEncoding(language.getCharset());
        response.setLocale(language.getLocale());
	    this.locale = language.getLocale();
	    this.encoding = language.getCharset();
	   
        session.setAttribute(Constants.LANGUAGE_KEY, language);
        //setea la veriable locale de Struts
        session.setAttribute(Globals.LOCALE_KEY, locale);
    }
}


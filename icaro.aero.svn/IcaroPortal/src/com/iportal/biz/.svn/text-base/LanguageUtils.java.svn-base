/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class LanguageUtils {
    
    private static Log logger = LogFactory.getLog(LanguageUtils.class);
    

    /**
     * Creates a new instance of Language Utils
     */
    public LanguageUtils() {
        super();
    }
    
    
    public Language getLanguage(HttpServletRequest request) {
        Language language = null;
        
        // Gets the accept-language header
        String langHeader = request.getHeader("accept-language");
        
        if (langHeader == null) {
            langHeader = Locale.getDefault().getLanguage();
        } else {
            langHeader = langHeader.substring(0,2);
        }
        
        
        Session sess = null;
        Locale locale = null;
        List results = null;
        Query query = null;
        
        try {
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
            locale = new Locale(langHeader); 
            
            StringBuffer sql = new StringBuffer();
            sql.append("from Language as lang ");
            sql.append("where lang.locale = ? ");
            sql.append("and lang.enabled = ? ");
            
            query = sess.createQuery(sql.toString());
            
            query.setLocale(0, locale);
            query.setBoolean(1, Globals.TRUE.booleanValue());
            
            results = query.list();
            
            if (results != null && results.size() > 0) {
                language = (Language) results.get(0);
            } else {
                sql.delete(0, sql.length());
                sql.append("from Language as lang ");
                sql.append("where lang.default = ? ");
                
                query = sess.createQuery(sql.toString());
                query.setBoolean(0, Globals.TRUE.booleanValue());
                
                results = query.list();
                if (results != null && results.size() > 0) {
                    language = (Language) results.get(0);
                }
            }
            
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
        
        
        return language;
    }

    
    public Language getLanguage(Long code) {
        
        Language language = null;

        Session sess = null;
                
        try {
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
            language = (Language) sess.get(Language.class, code);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
				    sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		return language;
    }
    
    
}

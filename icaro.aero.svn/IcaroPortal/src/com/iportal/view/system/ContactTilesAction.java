/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.model.Language;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ContactTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(ContactTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        
        Language language;
        Long languageCode;
        List institutionList = null;
        List cityList = null; 
        List projectComponentList = null; 
        List projectSectorList = null; 
        
        try {
        	
        	HttpSession session = request.getSession();
    		language = (Language) session.getAttribute( Constants.LANGUAGE_KEY );
    	    languageCode = language.getCode();
    	    
            sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
           /* sql.append("from Institution as institution ");
            sql.append("where institution.language.code = ? ");
            sql.append("order by institution.name ");            
            institutionList = sess.find(sql.toString(), languageCode, Hibernate.LONG);
            
            query = sess.createQuery("from City as city order by city.name");
    		query.setCacheable(true);
    		cityList = query.list();
            
            sql = new StringBuffer();
            sql.append("from ProjectSector as sector ");
            sql.append("where sector.language.code = ? ");
            sql.append("order by sector.name ");            
            projectSectorList = sess.find(sql.toString(), languageCode, Hibernate.LONG);  */
                        
    		sql = new StringBuffer();
            sql.append("from Category as category ");
            sql.append("where category.language.code = ? ");
            sql.append("and category.level = 1 ");
            
			projectComponentList = sess.createQuery(sql.toString()).setLong(0, languageCode.longValue()).list();
            
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		//Save the Lists in request scope
		request.setAttribute("institutionList", institutionList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("projectComponentList", projectComponentList);
		request.setAttribute("projectSectorList", projectSectorList);
		

		return null;
    }

}

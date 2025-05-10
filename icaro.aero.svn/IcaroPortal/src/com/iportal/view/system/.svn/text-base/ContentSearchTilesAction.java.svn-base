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
public class ContentSearchTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(ContentSearchTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	Session sess = null;
       // List layoutList = null;
        List sectionList = null;
        
        try {
        	Language language = (Language)request.getSession()
        									.getAttribute(Constants.LANGUAGE_KEY);
        	
        	sess = getHibernateSession();
        	StringBuffer sql = new StringBuffer();

        	sql.append("SELECT new com.iportal.biz.ItemBean(content.code, content.title) ");
        	sql.append("FROM  Content as content ");
        	sql.append("WHERE content.level = 1 ");
        	sql.append("AND content.language.code = ? ");
        	
        	sectionList = sess.createQuery(sql.toString())
        										.setLong(0, language.getCode())
        										.list();
        	
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
		
		//request.setAttribute("layoutList", layoutList);
		request.setAttribute("sectionList", sectionList);
		
		return null;
    }

}


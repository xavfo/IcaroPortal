/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view;

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

import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class NewsSectionTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(NewsSectionTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
       
        
        List newsSectionList = null;
        
        try {
            sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
            sql.append("from NewsSection as ns ");
            sql.append("where ns.group = ? ");
            sql.append("order by ns.name ");
            
            newsSectionList = sess.createQuery(sql.toString()).setBoolean(0, Globals.FALSE.booleanValue()).list();
            
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
		request.setAttribute("newsSectionList", newsSectionList);
		

		return null;
    }

}

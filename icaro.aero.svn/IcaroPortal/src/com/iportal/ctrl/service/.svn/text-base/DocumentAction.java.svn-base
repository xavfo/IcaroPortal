/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * 
 * 
 * @author  YAGE (monica)
 * @version 1.0
 *
 */
public class DocumentAction extends BaseAction {

    private static Log logger = LogFactory.getLog(DocumentAction.class);
    

    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        // Gets the StringBuffer for the currently user's menu
        
        
        
		// gets the current form
        
        
        Session sess = null;
        List documentList = null;
        
        try {
        	
        	sess = getHibernateSession();
        	
            StringBuffer sql = new StringBuffer();
            
            sql.append("from Document as document ");
            sql.append("order by document.name, document.file ");
            
            Query query = sess.createQuery(sql.toString());
            documentList = query.list();
			
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
         
 		// Save the List of results in request scope
		request.setAttribute("documentList",documentList);
 		
 		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }
}

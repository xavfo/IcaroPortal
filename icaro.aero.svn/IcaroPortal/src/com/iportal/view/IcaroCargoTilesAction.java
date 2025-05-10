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
import org.hibernate.Query;
import org.hibernate.Session;

import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * @author  YAGE
 * @version 1.0
 *
 */
public class IcaroCargoTilesAction extends BaseTilesAction {

    
    private static Log logger = LogFactory.getLog(IcaroCargoTilesAction.class);
     
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        
        List cargoDocTypes = null;
        
        try {
        	 sess = getHibernateSession();
             
             query = sess.createQuery("from IcargoDocumentType as cargoDocType order by cargoDocType.name");
     		query.setCacheable(true);
     		cargoDocTypes = query.list();
            
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
		request.setAttribute("cargoDocTypes", cargoDocTypes);
		
		

		return null;
    }


}

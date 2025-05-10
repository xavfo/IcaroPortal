/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
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
import org.hibernate.Query;
import org.hibernate.Session;

import com.yage.struts.action.BaseTilesAction;
/**
 * Retrieve a list of cities.
 * 
 * @author ferTamayo
 * @version 1.0
 *
 */
public class OfficeTilesAction extends BaseTilesAction {

    

    private static Log logger = LogFactory.getLog(OfficeTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
    	Session sess = null;
        Query query = null;
        List officeTypeList = null; 
        List cityList = null;
         
        try {
            sess = getHibernateSession();
             
                query = sess.createQuery("select new com.iportal.biz.RowItem (city.code, city.name) from City as city order by city.name");
            	query.setCacheable(true);
	     		cityList = query.list();
     		
	     		query = sess.createQuery("select new com.iportal.biz.RowItem (type.code, type.name) from OfficeType as type order by type.name");
            	query.setCacheable(true);
            	officeTypeList = query.list();	     		 
	                            	
     		
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
 			request.setAttribute("cityList", cityList);
 			request.setAttribute("officeTypeList", officeTypeList);
		
 		
 		return null;
     }
 }
